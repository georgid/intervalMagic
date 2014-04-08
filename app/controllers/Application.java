package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void searchIntervals(String myName, String myName2 ){
    	
    		double timeShift1 = -1.88;
    		ArrayList<Double[]> matchedTs1 = searchIntervalsOneSong(myName,
					myName2, SongParser.fileURI1, timeShift1);
    		
    		
    		double timeShift2 = 2.3;
    		ArrayList<Double[]> matchedTs2 = searchIntervalsOneSong(myName,
					myName2, SongParser.fileURI2, timeShift2);
    		
    		String myJsonVar = SongParser.resultSetToJSON(matchedTs1, matchedTs2);
    		
			String resultTs1 = null;
			String resultTs2 = null;

			
			
//			
//
//
//				// String myJsonVar = "{ \"matchedResults\": [{trackName=\"Black\", \"startTs\":\"10\", \"endTs\":20},{trackName=\"Black\", \"startTs\":\"30\", \"endTs\":50}]}";
//				String myJsonVar =   result2JSON();




			renderArgs.put("myJsonVar", myJsonVar);
    		render(myJsonVar, resultTs1, resultTs2 );	
    }

	public static ArrayList<Double[]> searchIntervalsOneSong(String myName,
			String myName2, String songName, double timeShift) {
		Song song = SongParser.parseSong(songName);
		song.intervalize();

		
		//input  
		ArrayList<Integer> query = new ArrayList<Integer>();
		int intervalOne = Integer.parseInt(myName);
		query.add(intervalOne);

		System.out.println(intervalOne);

		int intervalTwo = Integer.parseInt(myName2);
		query.add(intervalTwo);
		
		System.out.println(intervalTwo);

		// search 
		ArrayList<Double []> matchedTs = Searcher.search(query, song, timeShift);
		return matchedTs;
	}


}