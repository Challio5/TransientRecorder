package nl.transientrecorder.control;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.transientrecorder.model.Recorder;
import nl.transientrecorder.panel.RecorderEastPanel;
import nl.transientrecorder.view.RecorderNorthView;

public class RecorderNorthController {
	
	// Model met de weer te geven waarden
	private Recorder model;

	// Panels voor de layout en het besturen van de views
	private RecorderNorthView recorderPanel;
	private RecorderEastPanel channelPanel;
	
	public RecorderNorthController(Recorder model, RecorderNorthView recorderPanel , RecorderEastPanel channelPanel) {
		// Model
		this.model = model;	
		
		// Panel
		this.recorderPanel = recorderPanel;
		this.channelPanel = channelPanel;
		
		// Listener channelchooser
		recorderPanel.addPixelPerValueListener(new ChannelPixelPerValueHandler());
		recorderPanel.addMinValueListener(new MinValueHandler());
		recorderPanel.addMaxValueListener(new MaxValueHandler());
		recorderPanel.addChannelChooserListener(new ChannelChooserHandler());
	}
	
	// CheckBoxHandler van voor het opslaan van de ppvoptie in het model
	class ChannelPixelPerValueHandler implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() instanceof JCheckBox) {
				JCheckBox checkBox = (JCheckBox) e.getSource();
				boolean checked = checkBox.isSelected();
				model.setPixelPerValue(checked);
			}	
		}			
	}
	
	// MinValueHandler voor het tekenen van een minimum waarde in het grafiekscherm
	class MinValueHandler implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider slider = (JSlider) e.getSource();
			int value = slider.getValue();
			model.setMinValue(value);
		}
	}
	
	// MaxValueHandler voor het tekenen van een minimum waarde in het grafiekscherm
	class MaxValueHandler implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider slider = (JSlider) e.getSource();
			int value = slider.getValue();
			model.setMaxValue(value);
		}
	}
	
	// ChannelChooserHandler voor het kiezen van het te bewerken kanaal
	class ChannelChooserHandler implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			String kanaal = (String) e.getItem();
			CardLayout card = (CardLayout) channelPanel.getLayout();
		    card.show(channelPanel, kanaal);
		    model.setKanaal(kanaal);
		}
	}
}
