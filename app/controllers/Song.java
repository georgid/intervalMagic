package controllers;
import java.util.ArrayList;


public class Song {

//	name and artist
	public String name; 
	public ArrayList<MelodicMotif> melodicMotives;
	
	public Song(String name){
		this.name = name;
		this.melodicMotives = new ArrayList<MelodicMotif>();
	
	}
	
	//
	public void intervalize()
	{
		
		// parse all motives 
		for (int j=0; j< this.melodicMotives.size(); j++) {
			
		
		MelodicMotif currMelodicMotif = this.melodicMotives.get(j);
		
		if (currMelodicMotif.notes.size() == 1)
			continue;
		// leave it null
			
		currMelodicMotif.intervals = new ArrayList<Integer>();
			
			// parse one melodic motif. calculate MIDI note differences. put in intervals
		for (int i = 0; i < currMelodicMotif.notes.size() - 1; i++){ 
				
				int currentMIDInote = currMelodicMotif.notes.get(i).MIDINOTE;
				int nextMIDInote = currMelodicMotif.notes.get(i + 1).MIDINOTE;
					
				currMelodicMotif.intervals.add(nextMIDInote -  currentMIDInote);
				
			}
		
		}// end of for loop for melodic motif
		
	}
	
	
	


}
