package controllers;

public class Note {

	public int MIDINOTE;
	public double startTs; 
	public double duration; 
	
	public Note(int midinote, double startTs, double duration ){
		this.MIDINOTE = midinote; 
		this.startTs = startTs;
		this.duration = duration;
	}
	
		
}
