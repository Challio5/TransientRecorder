package nl.transientrecorder.model;

public class MeasuredValue {
	
	// De gegenereerde waarden en de bijbehorende tijd
	int value;
	long time;
	
	// Constructor
	public MeasuredValue(int value, long time) {
		this.value = value;
		this.time = time;
	}

	// Geeft de gegenereerde waarde terug
	public int getValue() {
		return value;
	}
	
	// Geeft de tijd van een gegenereerde waarde terug
	public long getTime() {
		return time;
	}
}
