package nl.transientrecorder.main;

import nl.transientrecorder.control.RecorderController;
import nl.transientrecorder.model.Recorder;

// Maakt het model aan en geeft deze door aan de controller voor het opzetten van het programma
public class Main {
	public static void main (String[] args){
		Recorder model = new Recorder();
		new RecorderController(model);
	}
}
