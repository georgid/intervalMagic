package controllers;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;




public class SongParser {

	
	
	public static String fileURI1 = "/Users/joro/Documents/intervalQuery/soundgarden_-_black_hole_sun_separated_multitrack_-09_vocal_2.notes";
	public static String fileURI2 = "/Users/joro/Documents/intervalQuery/doors_spanish.notes.2";

	
	public static Integer START_TIMESTAMP = 0;
	public static Integer DURATION = 1;
	public static Integer MIDI_NOTE_NUMBER = 2;
		
//	public static String fileURI = "/Users/joro/Documents/intervalQuery/soundgarden_-_black_hole_sun_separated_multitrack_-09_vocal_2.notes";
	
	
		public static Song parseSong(String fileURI){
			
				Song song = new Song(fileURI);
			   ArrayList<String> studentTokens = new ArrayList<String>();
			   
			   boolean isInMotif = false; 
			   MelodicMotif currMotif = null;
			   
			    try {
			        // Open the file that is the first
			        // command line parameter
			        FileInputStream fstream = new FileInputStream(fileURI);
			        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			        String strLine;
			        
			        // Read File Line By Line
			        while ((strLine = br.readLine()) != null) {
			            strLine = strLine.trim();

			            
			            
//			                String[] noteTokens = strLine.split("\\s+");
			                String[] noteTokens = strLine.split(", ");

			                if ((noteTokens.length !=4) ) continue; 

			                
			                // check for real note
			                if (  Double.parseDouble(noteTokens[MIDI_NOTE_NUMBER])  != -1.0 ){
//			                	System.out.println(noteTokens[START_TIMESTAMP] + "is not a ntoe");
			                	
			                	// start new motif
			                	if (!isInMotif){
			                		isInMotif = true;
			                		currMotif = new MelodicMotif();
			                		song.melodicMotives.add(currMotif);
			                	}
			                	double midinote = Double.parseDouble(noteTokens[MIDI_NOTE_NUMBER]);
			                	Note currNote = new Note( 
			                			(int)Math.round(midinote), 
			                			Double.parseDouble(noteTokens[START_TIMESTAMP]), 
			                			Double.parseDouble(noteTokens[DURATION])
			                			);
			                	
			                	currMotif.notes.add(currNote);
			                }
			                else 		isInMotif = false;
 
			                	
			                // parse note
			                	
			                	
//			                studentTokens.add(students[TOKEN_COLUMN]);
			            


			        }

			        for (String s : studentTokens) {
			            System.out.println(s);
			        }

			        // Close the input stream
			        fstream.close();
			    } catch (Exception e) {// Catch exception if any
			        System.err.println("Error: " + e.getMessage());
			    }
			
			    return song;
			
		}
		
		

			
			public static String resultSetToJSON(ArrayList<Double []> matchedResults1, ArrayList<Double []> matchedResults2){
				
				JSONArray allResults = new JSONArray();
				
				JSONObject resultsOneSong = oneSongToJSONObject(matchedResults1, "blackHoleSun");
				
				allResults.add(resultsOneSong);
				JSONObject result2 = oneSongToJSONObject(matchedResults2, "spanish");
				allResults.add(result2);
			
				
				
				
				return allResults.toJSONString();
				
			}




			public static JSONObject oneSongToJSONObject(
					ArrayList<Double[]> matchedResults, String songNAme) {
				JSONObject resultsOneSong = new JSONObject();
				
				
				resultsOneSong.put("songName", songNAme);
			   
				JSONArray jsonArray = new JSONArray();
				
				
				
				
				for (Double [] matchedTsResult : matchedResults){
					
					JSONObject matchTs = new JSONObject();
					Double startTs = matchedTsResult[0] * 1000.0;
					Double endTs = matchedTsResult[1] * 1000.0;

					matchTs.put("startTs",startTs);
					matchTs.put("endTs", endTs );
					
					jsonArray.add(matchTs);
					
				}
				
				resultsOneSong.put("matches", jsonArray);
				return resultsOneSong;
			}
		
		
//		/**
//		 * @param args
//		 */
//		public static void main(String[] args) {
//			// TODO Auto-generated method stub
//			
//			Song song = SongParser.parseSong(fileURI1);
//			song.intervalize();
//			
//			// stub query to test search:
//			ArrayList<Integer> query = new ArrayList<Integer>();
//			query.add(11);
//			query.add(0);
//			
//			ArrayList<Double []> matchedTs = Searcher.search(query, song);
//			
//			String json2 = SongParser.resultSetToJSON(matchedTs);
//			
//			System.out.println(json2);
//		}
		
		
		

}
