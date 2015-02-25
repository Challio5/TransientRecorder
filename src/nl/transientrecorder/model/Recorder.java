package nl.transientrecorder.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;

public class Recorder extends Observable {
	
	// ArrayList met alle aangemaakte kanalen (1-4)
	private ArrayList<Channel> channelList;
	
	// Waarde voor actief kanaal
	private String kanaal;
	
	// Pixel per value optie
	private boolean pixelPerValue;
	
	// Min en Max Waarden
	private int valueRange;
	private int minValue;
	private int maxValue;
	
	// Constructor
	public Recorder() {
		channelList = new ArrayList<Channel>();
		
		channelList.add(new Channel());
		channelList.add(new Channel());
		channelList.add(new Channel());
		channelList.add(new Channel());
		
		kanaal = "Kanaal A";
	}

	// Methode om een MeasuredValue toe te voegen aan een van de kanalen
	public void addMeasuredValue(int channel, int value, long time) {
		channelList.get(channel).addToList(value, time);
		this.setChanged();
		this.notifyObservers();
	}
	
	// Geeft de LinkedList terug van een bepaald kanaal waar alle gegenereerde data in staat
	public LinkedList<MeasuredValue> getStoredValueList(int channel) {
		return channelList.get(channel).getStoredList();
	}
	
	// Geeft de LinkedList terug van een bepaald kanaal waar alle gegenereerde data in staat
	public LinkedList<MeasuredValue> getStackedDebugList(int channel) {
		return channelList.get(channel).getStackedDebugList();
	}
	
	// Geeft de LinkedList terug van een bepaald kanaal waar alle gegenereerde data in staat
	public LinkedList<MeasuredValue> getStackedGraphList(int channel) {
		return channelList.get(channel).getStackedGraphList();
	}
	
	// Geeft of set het actieve kanaal
	public String getKanaal() {
		return kanaal;
	}
	
	public void setKanaal(String kanaal) {
		this.kanaal = kanaal;
	}
	
	// Geeft of set een boolean terug die aangeeft of een bepaald kanaal zichtbaar is of niet
	public boolean isVisible(int channel) {
		return channelList.get(channel).isVisible();
	}

	public void setVisible(int channel, boolean visible) {
		channelList.get(channel).setVisible(visible);
	}
	
	// Geeft of set de snelheid van een bepaald kanaal
	public int getVelocity(int channel) {
		return channelList.get(channel).getVelocity();
	}

	public void setVelocity(int channel, int velocity) {
		channelList.get(channel).setVelocity(velocity);
	}

	// Geeft of set een boolean van een bepaald kanaal die aangeeft de waardes per pixel moeten worden weergegeven
	public boolean isPixelPerValue() {
		return pixelPerValue;
	}

	public void setPixelPerValue(boolean pixelPerValue) {
		this.pixelPerValue = pixelPerValue;
	}
	
	// Geeft of set de gevoeligheid van een bepaald kanaal
	public double getScale(int channel) {
		return channelList.get(channel).getScale();
	}

	public void setScale(int channel, double scale) {
		channelList.get(channel).setScale(scale);
	}
	
	// Geeft of set de kleur van de bepaald kanaal voor de weer te geven waarden
	public Color getColor(int channel) {
		return channelList.get(channel).getColor();
	}

	public void setColor(int channel, Color color) {
		channelList.get(channel).setColor(color);
	}

	// Getters en setters voor het ophalen en setten van de min en max waarden
	public int getValueRange() {
		return valueRange;
	}

	public void setValueRange(int valueRange) {
		this.valueRange = valueRange;
		this.setChanged();
		this.notifyObservers();
	}
	
	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
}
