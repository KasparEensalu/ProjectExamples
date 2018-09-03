var allWords;
var word;

function getWordsList() {
    $.ajax({
        type: "GET",
        url: '/getWords',
        success: function(response){
            allWords = response;
            $("#word").autocomplete({
                source: allWords
            });
        }
    });
}

function findWord() {
    word = $("#word").val();
    $.ajax({
        url: '/searchWord',
        data: {'word': word},
        type: 'POST',
        success: function(response) {
            handleUi(word);
        },
        error: function(error) {
            console.log(error);
        }
    });
}

function handleUi(word) {
    if(allWords.indexOf(word) === -1) {
        $("#pronDiv").show();
        $("#download").hide();
        $("#error").html("No pronounciation for <b>" + word + " </b> found. Feel free to add a file.");
    } else {
        $("#pronDiv").show();
        $("#download").show();
        $("#error").html("Pronouncation for <b>" + word + "</b> already in database. Adding will overwrite the file.");
    }
}

$("#pronDiv").hide();
$("#download").hide();
$("#errorDiv").hide();
getWordsList();
