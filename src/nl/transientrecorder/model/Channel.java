package nl.transientrecorder.model;

import java.awt.Color;
import java.util.LinkedList;

public class Channel {
	
	// Lijsten voor het opslaan van de gegenereerde waarden
	private LinkedList<MeasuredValue> storedList;
	private LinkedList<MeasuredValue> stackedDebugList;
	private LinkedList<MeasuredValue> stackedGraphList;
	
	// Eigenschappen van de weer te geven data
	private boolean visible;
	private int velocity;
	private double scale;
	private Color color;
	
	// Constructor
	public Channel() {
		storedList = new LinkedList<MeasuredValue>();
		stackedDebugList = new LinkedList<MeasuredValue>();
		stackedGraphList = new LinkedList<MeasuredValue>();
		
		velocity = (int) (10 * Math.pow(2, 5));
		scale = 1;
		color = new Color(0);
	}
	
	// Methode om een MeasuredValue toe te voegen aan alle lijsten
	public void addToList(int value, long time) {
		storedList.addFirst(new MeasuredValue(value, time));
		stackedDebugList.addFirst(new MeasuredValue(value, time));
		stackedGraphList.addFirst(new MeasuredValue(value, time));
	}
	
	// Geeft de LinkedList terug waar alle gegenereerde data in staat
	public LinkedList<MeasuredValue> getStoredList() {
		return storedList;
	}
	
	// Geeft de LinkedList terug waar de nog weer te geven MeasuredValue waarden in staan
	public LinkedList<MeasuredValue> getStackedDebugList() {
		return stackedDebugList;
	}
	
	// Geeft de LinkedList terug waar de nog te tekenen MeasuredValue waarden in staan
	public LinkedList<MeasuredValue> getStackedGraphList() {
		return stackedGraphList;
	}
	
	// Geeft of set een boolean terug die aangeeft of het kanaal zichtbaar is of niet
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	// Geeft of set de snelheid van het kanaal
	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	// Geeft of set de gevoeligheid van het kanaal
	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	// Geeft of set de kleur van het kanaal voor de weer te geven waarden
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
