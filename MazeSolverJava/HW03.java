package ee.ttu.algoritmid.labyrinth;

import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.io.IOException;
import java.net.URISyntaxException;

public class HW03 {
    private MazeRunner mazeRunner;
    private List<String> way = new ArrayList<String>();
    //List of visited coordinates to prevent cycles
    private List<SimpleEntry<Integer,Integer>> wayCoordinates = new ArrayList<SimpleEntry<Integer,Integer>>();
    private boolean treasureFound = false;
    
    public HW03(String fileName) throws IOException, URISyntaxException {
        mazeRunner = new MazeRunner(fileName);
    }

    public MazeRunner getMazeRunner() {
        return mazeRunner;
    }
    /**
     * Returns the list of steps to take to get from beginning ("B") to
     * the treasure ("T").
     * @return  return the steps taken as a list of strings (e.g., ["E", "E", "E"])
     *          return null if there is no path (there is no way to get to the treasure).
     */
    public List<String> solve() {
        //TODO implement this method
        List<List<Integer>> initialScan = mazeRunner.scan();
        List<String> visited = new ArrayList<String>();
        SimpleEntry<Integer, Integer> startPoint = new SimpleEntry<Integer,Integer>(0,0);
        wayCoordinates.add(startPoint);
        way.add("Start");
        while(!treasureFound) {
        	if(initialScan.get(0).get(1) != -1) {
        		if(!visited.contains("N")) {
        			visited.add("N");
        			way.add("N");
        			SimpleEntry<Integer,Integer> nextCoordinates = new SimpleEntry<Integer,Integer>(startPoint.getKey(),startPoint.getValue()+1);
        			wayCoordinates.add(nextCoordinates);
        			mazeRunner.move("N");
        			if(decideForLocation(nextCoordinates, "N")){
        				way.remove(0);
        				return way;
        			}
        		} 
        	} 
        	if(initialScan.get(1).get(0) != -1) {
        		if(!visited.contains("W")) {
        			visited.add("W");
        			way.add("W");
        			SimpleEntry<Integer,Integer> nextCoordinates = new SimpleEntry<Integer,Integer>(startPoint.getKey()-1,startPoint.getValue());
        			wayCoordinates.add(nextCoordinates);
        			mazeRunner.move("W");
        			if(decideForLocation(nextCoordinates, "W")){
        				way.remove(0);
        				return way;
        			}
        		} 
        	}
        	if(initialScan.get(1).get(2) != -1) {
        		if(!visited.contains("E")) {
        			visited.add("E");
        			way.add("E");
        			SimpleEntry<Integer,Integer> nextCoordinates = new SimpleEntry<Integer,Integer>(startPoint.getKey()+1,startPoint.getValue());
        			wayCoordinates.add(nextCoordinates);
        			mazeRunner.move("E");
        			if(decideForLocation(nextCoordinates, "E")){
        				way.remove(0);
        				return way;
        			}
        		} 
        	}
        	if(initialScan.get(2).get(1) != -1) {
        		if(!visited.contains("S")) {
        			visited.add("S");
        			way.add("S");
        			SimpleEntry<Integer,Integer> nextCoordinates = new SimpleEntry<Integer,Integer>(startPoint.getKey(),startPoint.getValue()-1);
        			wayCoordinates.add(nextCoordinates);
        			mazeRunner.move("S");
        			if(decideForLocation(nextCoordinates, "S")){
        				way.remove(0);
        				return way;
        			}
        		} 
        	}
        	if(visited.size()>=4){
        		return null;
        	}
        	return null;
        }
        return null;
    }	
    
    private boolean decideForLocation(SimpleEntry<Integer, Integer> currentPoint, String lastMove){
    	List<List<Integer>> scanResults = mazeRunner.scan();
        List<String> visited = new ArrayList<String>();
        String moveBack;
        if(lastMove.equals("N")) {moveBack = "S";}
        else if(lastMove.equals("S")) {moveBack = "N";}
        else if(lastMove.equals("W")) {moveBack = "E";}
        else {moveBack = "W";}
        while(!treasureFound) {
        	if(scanResults.get(0).get(1) != -1) {
        		if(scanResults.get(0).get(1) == -2) {
        			treasureFound = true;
        			way.add("N");
        			return true;
        		} else if(!visited.contains("N") && !lastMove.equals("S")) {
        			visited.add("N");
        			SimpleEntry<Integer,Integer> nextCoordinates = new SimpleEntry<Integer,Integer>(currentPoint.getKey(),currentPoint.getValue()+1);
        			if(!wayCoordinates.contains(nextCoordinates)) {
	        			wayCoordinates.add(nextCoordinates);
	        			way.add("N");
	        			mazeRunner.move("N");
	        			if(decideForLocation(nextCoordinates, "N")){
	        				return true;
	        			}
        			}	
        		} 
        	} 
        	if(scanResults.get(1).get(0) != -1) {
        		if(scanResults.get(1).get(0) == -2) {
        			treasureFound = true;
        			way.add("W");
        			return true;
        		} else if(!visited.contains("W") && !lastMove.equals("E")) {
        			visited.add("W");
        			SimpleEntry<Integer,Integer> nextCoordinates = new SimpleEntry<Integer,Integer>(currentPoint.getKey()-1,currentPoint.getValue());
        			if(!wayCoordinates.contains(nextCoordinates)) {
	        			wayCoordinates.add(nextCoordinates);
	        			way.add("W");
	        			mazeRunner.move("W");
	        			if(decideForLocation(nextCoordinates, "W")){
	        				return true;
	        			}
        			}
        		} 
        	}
        	if(scanResults.get(1).get(2) != -1) {
        		if(scanResults.get(1).get(2) == -2) {
        			treasureFound = true;
        			way.add("E");
        			return true;
        		} else if(!visited.contains("E") && !lastMove.equals("W")) {
        			visited.add("E");
        			SimpleEntry<Integer,Integer> nextCoordinates = new SimpleEntry<Integer,Integer>(currentPoint.getKey()+1,currentPoint.getValue());
        			if(!wayCoordinates.contains(nextCoordinates)) {
	        			wayCoordinates.add(nextCoordinates);
	        			way.add("E");
	        			mazeRunner.move("E");
	        			if(decideForLocation(nextCoordinates, "E")){
	        				return true;
	        			}
        			}
        		} 
        	}
        	if(scanResults.get(2).get(1) != -1) {
        		if(scanResults.get(2).get(1) == -2) {
        			treasureFound = true;
        			way.add("S");
        			return true;
        		} else if(!visited.contains("S") && !lastMove.equals("N")) {
        			visited.add("S");
        			SimpleEntry<Integer,Integer> nextCoordinates = new SimpleEntry<Integer,Integer>(currentPoint.getKey(),currentPoint.getValue()-1);
        			if(!wayCoordinates.contains(nextCoordinates)) {
	        			wayCoordinates.add(nextCoordinates);
	        			way.add("S");
	        			mazeRunner.move("S");
	        			if(decideForLocation(nextCoordinates, "S")){
	        				return true;
	        			}
        			}
        		} 
        	}
        	mazeRunner.move(moveBack);
        	wayCoordinates.remove(currentPoint);
    		way.remove(way.size()-1);
    		return false;
        }
        mazeRunner.move(moveBack);
        wayCoordinates.remove(currentPoint);
		way.remove(way.size()-1);
        return false;
    }
}