from flask import Flask, render_template, make_response, request, jsonify, redirect,json
from pymongo import MongoClient
from bson.objectid import ObjectId
import gridfs

app = Flask(__name__)

client = MongoClient()
db = client.k5
fs = gridfs.GridFS(db)
searchedWord = ""


@app.route('/')
def main_page():
    return render_template('index.html')

@app.route('/searchWord', methods=['POST'])
def searchWord():
    try:
        global searchedWord
        searchedWord = request.form['word']
        return searchedWord
    except Exception as e:
        return json.dumps({'message': str(e)})

@app.route('/getWords', methods=['GET'])
def getWords():
    try:
        response = []
        for word in db.k5pron.find():
            response.append(word['word'])
        return jsonify(response)
    except Exception as e:
        return json.dumps({'message': str(e)})



@app.route('/getPron', methods = ['POST', 'GET'])
def getPron():
    try:
        if request.method == 'POST':
            for w in db.k5pron.find():
                if w['word'] == searchedWord:
                    wordID = w['id']
                    break
            out = fs.get(ObjectId(wordID))
            response = make_response(out.read())
            response.headers['Content-Type'] = 'application/octet-stream'
            response.headers["Content-Disposition"] = "inline; filename={}".format(searchedWord + ".mp3")
            return response
    except Exception as e:
        return json.dumps({'message': str(e)})

@app.route('/addPron', methods=['GET', 'POST'])
def addPron():
    try:
        if request.method == 'POST':
            wordExists = False
            for w in db.k5pron.find():
                if w['word'] == searchedWord:
                    wordExists = True
                    break
            f = request.files['inputFile']
            if f:
                word = searchedWord
                fileID = fs.put(f)
                if not wordExists:
                    db.k5pron.insert_one({"word": word, "id": fileID})
                else:
                    db.k5pron.update({'word': word}, {"$set": {'id': fileID}})
            return redirect('/')
    except Exception as e:
        return json.dumps({'message': str(e)})


if __name__ == '__main__':
    app.run()