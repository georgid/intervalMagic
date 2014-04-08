package controllers;
import java.util.ArrayList;

/**
 * 
 * TODO: make search not dependent on size of query. Now it is hard coded that sieze is 2
 * FIXME: hard coded difference in actual playtime of SC song.
 * */
public class Searcher {
	
	public static ArrayList<Double []> search(ArrayList<Integer> intervalQuery, Song targetSong, double timeShift){
		
		// result
		ArrayList<Double []> matchedTsList = new ArrayList<Double []>();
		
		// loop through motives
		for (MelodicMotif currMelMotif : targetSong.melodicMotives ){
		
		// sanity check : motif has at least two intervals
		if (currMelMotif.intervals == null) 
			continue; 
			if (currMelMotif.intervals.size() < 2 )
			continue;
			
			for (int pos=0; pos < currMelMotif.intervals.size() - 1 ; pos ++){
					
				// match criteria
				if (currMelMotif.intervals.get(pos) == intervalQuery.get(0) && 
						currMelMotif.intervals.get(pos + 1) == intervalQuery.get(1) ){
					
					double [] resultTs = new double [2];
					
					double startTs = currMelMotif.notes.get(pos).startTs;
					double endTs = currMelMotif.notes.get(pos+1).startTs + currMelMotif.notes.get(pos+1).duration;
					
					//FIXME: hard coded difference: 
					startTs = startTs + timeShift; 
					endTs = endTs + timeShift; 
					
					resultTs [0] = startTs;
					resultTs [1] = endTs;
					
					
					matchedTsList.add(new Double[] {startTs, endTs});
				} // end result set
					
			} 
				
			
		
		
		} // end of loop thfough motives
		
		return matchedTsList;
		
	}
	
	
	
	
	
}
