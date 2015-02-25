package nl.transientrecorder.view;

import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import nl.transientrecorder.model.Recorder;

public class RecorderNorthView extends JPanel implements Observer {
	
	// Label met checkbox om aan te geven of per pixel een waarde moet worden weergegeven
	private JLabel pixelPerValueLabel;
	private JCheckBox pixelPerValue;
	
	// Sliders die een minimum en maximum aangeven in de grafiek, wordt geupdate aan de grootte van het scherm
	private JSlider minValue;
	private JSlider maxValue;
	
	// Combobox waar het kanaal geselecteerd wordt en daarbij het bijbehorende panel
	private String[] channelNames;
	private JComboBox channelChooser;
	
	public RecorderNorthView() {
		// Gridlayout om alles in een rij achter elkaar weer te geven
		this.setLayout(new GridLayout(1,5));
		
		pixelPerValueLabel = new JLabel("PixelPerValue");
		pixelPerValue = new JCheckBox();
		
		minValue = new JSlider();
		minValue.setValue(0);
		
		maxValue = new JSlider();
		maxValue.setValue(0);
		
		channelNames = new String[] {"Kanaal A", "Kanaal B", "Kanaal C", "Kanaal D"};
		
		channelChooser = new JComboBox(channelNames);
		channelChooser.setEditable(false);
		
		// Voegt de componenten in de juiste volgorde toe aan het paneel
		this.add(pixelPerValueLabel);
		this.add(pixelPerValue);
		this.add(minValue);
		this.add(maxValue);
		this.add(channelChooser);
	}
	
	// Methode om een changeListener toe te voegen aan de checkbox of elke waarde een pixel in beslag neemt
	public void addPixelPerValueListener(ChangeListener listener) {
		pixelPerValue.addChangeListener(listener);
	}
	
	// Methode om een changelistener toe te voegen om een minimum in de grafiek te tekenen
	public void addMinValueListener(ChangeListener listener) {
		minValue.addChangeListener(listener);
	}
	
	// Methode om een changelistener toe te voegen om een maximum in de grafiek te tekenen
	public void addMaxValueListener(ChangeListener listener) {
		maxValue.addChangeListener(listener);
	}
	
	// Methode om een itemlistener toe te voegen om het geselecteerde kanaal te krijgen
	public void addChannelChooserListener(ItemListener listener) {
		channelChooser.addItemListener(listener);
	}

	@Override
	public void update(Observable o, Object arg1) {
		Recorder model = (Recorder) o;
		
		// Geeft de grootte van scherm mee waardoor de range van sliders kunnen worden aangepast
		// Deze dienen het hele scherm te kunnen bestrijken
		int sliderRange = model.getValueRange();
		minValue.setMaximum(sliderRange);
		maxValue.setMaximum(sliderRange);
	}
}
