package nl.transientrecorder.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import nl.transientrecorder.model.Recorder;
import nl.transientrecorder.panel.RecorderSouthPanel;

public class RecorderSouthController {
	
	// Model met de weer te geven waarden
	private Recorder model;
	
	// Panel
	private RecorderSouthPanel recorderPanel;
	
	public RecorderSouthController(Recorder model, RecorderSouthPanel recorderPanel) {
		// Model
		this.model = model;
		
		// Panel
		this.recorderPanel = recorderPanel;
		
		// Listeners visibility channel recorderPanel
		recorderPanel.addChannelAVisibilityListener(new ChannelAVisibilityHandler());
		recorderPanel.addChannelBVisibilityListener(new ChannelBVisibilityHandler());
		recorderPanel.addChannelCVisibilityListener(new ChannelCVisibilityHandler());
		recorderPanel.addChannelDVisibilityListener(new ChannelDVisibilityHandler());
	}
	
	// CheckBoxHandler van kanaal A voor het opslaan van de zichtbaarheidsoptie in het model
	class ChannelAVisibilityHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JCheckBox) {
				JCheckBox checkBox = (JCheckBox) e.getSource();
				boolean checked = checkBox.isSelected();
				model.setVisible(0, checked);
			}	
		}	
	}
	
	// CheckBoxHandler van kanaal B voor het opslaan van de zichtbaarheidsoptie in het model
	class ChannelBVisibilityHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JCheckBox) {
				JCheckBox checkBox = (JCheckBox) e.getSource();
				boolean checked = checkBox.isSelected();
				model.setVisible(1, checked);
			}	
		}	
	}
	
	// CheckBoxHandler van kanaal A voor het opslaan van de zichtbaarheidsoptie in het model
	class ChannelCVisibilityHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JCheckBox) {
				JCheckBox checkBox = (JCheckBox) e.getSource();
				boolean checked = checkBox.isSelected();
				model.setVisible(2, checked);
			}	
		}	
	}
	
	// CheckBoxHandler van kanaal A voor het opslaan van de zichtbaarheidsoptie in het model
	class ChannelDVisibilityHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JCheckBox) {
				JCheckBox checkBox = (JCheckBox) e.getSource();
				boolean checked = checkBox.isSelected();
				model.setVisible(3, checked);
			}	
		}	
	}
}
