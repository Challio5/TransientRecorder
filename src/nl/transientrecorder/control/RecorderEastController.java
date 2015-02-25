package nl.transientrecorder.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.transientrecorder.model.Recorder;
import nl.transientrecorder.panel.RecorderEastPanel;

public class RecorderEastController {
	
	// Model met de weer te geven waarden
	private Recorder model;

	// Panels voor de layout en het besturen van de views
	private RecorderEastPanel recorderPanel;
	
	public RecorderEastController(Recorder model, RecorderEastPanel recorderPanel) {
		// Model
		this.model = model;

		// Panel 
		this.recorderPanel = recorderPanel;
		
		// Listeners channelA recorderPanel
		recorderPanel.addChannelAVelocityListener(new ChannelAVelocityHandler());
		recorderPanel.addChannelAScaleListener(new ChannelAScaleHandler());
		recorderPanel.addChannelAColorChooserButtonListener(new ChannelAColorChooserButtonHandler());
		
		// Listeners channelB recorderPanel
		recorderPanel.addChannelBVelocityListener(new ChannelBVelocityHandler());
		recorderPanel.addChannelBScaleListener(new ChannelBScaleHandler());
		recorderPanel.addChannelBColorChooserButtonListener(new ChannelBColorChooserButtonHandler());
		
		// Listeners channelC recorderPanel
		recorderPanel.addChannelCVelocityListener(new ChannelCVelocityHandler());
		recorderPanel.addChannelCScaleListener(new ChannelCScaleHandler());
		recorderPanel.addChannelCColorChooserButtonListener(new ChannelCColorChooserButtonHandler());
		
		// Listeners channelD recorderPanel
		recorderPanel.addChannelDVelocityListener(new ChannelDVelocityHandler());
		recorderPanel.addChannelDScaleListener(new ChannelDScaleHandler());
		recorderPanel.addChannelDColorChooserButtonListener(new ChannelDColorChooserButtonHandler());
		
	}
	
	// SliderHandler van kanaal A voor het opslaan van de juiste snelheid in het model
	class ChannelAVelocityHandler implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() instanceof JSlider) {
				JSlider slider = (JSlider) e.getSource();
				int velocity = (int) (10 * Math.pow(2, slider.getValue() - 1));
				model.setVelocity(0, velocity);
			}	
		}
	}
	
	// ComboBoxHandler van kanaal A voor het opslaan van de juiste optie voor de schaal in het model
	class ChannelAScaleHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JComboBox) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Double item = (Double) comboBox.getSelectedItem();
				model.setScale(0, item);
			}
		}
	}
	
	// ButtonHandler van kanaal A voor het weergeven van een ColorChooserDialog en het opslaan van de geselecteerde kleur in het model
	class ChannelAColorChooserButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(new JFrame(), "Kies een kleur:", Color.BLACK);
			model.setColor(0, color);
		}
	}
	
	// SliderHandler van kanaal B voor het opslaan van de juiste snelheid in het model
	class ChannelBVelocityHandler implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() instanceof JSlider) {
				JSlider slider = (JSlider) e.getSource();
				int velocity =  (int) (10 * Math.pow(2, slider.getValue() - 1));
				model.setVelocity(1, velocity);
			}	
		}
	}
		
	// ComboBoxHandler van kanaal B voor het opslaan van de juiste optie voor de schaal in het model
	class ChannelBScaleHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JComboBox) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Double item = (Double) comboBox.getSelectedItem();
				model.setScale(1, item);
			}
		}
	}
		
	// ButtonHandler van kanaal B voor het weergeven van een ColorChooserDialog en het opslaan van de geselecteerde kleur in het model
	class ChannelBColorChooserButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(new JFrame(), "Kies een kleur:", Color.BLACK);
			model.setColor(1, color);
		}
	}
	
	// SliderHandler van kanaal C voor het opslaan van de juiste snelheid in het model
	class ChannelCVelocityHandler implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() instanceof JSlider) {
				JSlider slider = (JSlider) e.getSource();
				int velocity =  (int) (10 * Math.pow(2, slider.getValue() - 1));
				model.setVelocity(2, velocity);
			}	
		}
	}
		
	// ComboBoxHandler van kanaal C voor het opslaan van de juiste optie voor de schaal in het model
	class ChannelCScaleHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JComboBox) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Double item = (Double) comboBox.getSelectedItem();
				model.setScale(2, item);
			}
		}
	}
		
	// ButtonHandler van kanaal C voor het weergeven van een ColorChooserDialog en het opslaan van de geselecteerde kleur in het model
	class ChannelCColorChooserButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(new JFrame(), "Kies een kleur:", Color.BLACK);
			model.setColor(2, color);
		}
	}
	
	// SliderHandler van kanaal C voor het opslaan van de juiste snelheid in het model
	class ChannelDVelocityHandler implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() instanceof JSlider) {
				JSlider slider = (JSlider) e.getSource();
				int velocity =  (int) (10 * Math.pow(2, slider.getValue() - 1));
				model.setVelocity(3, velocity);
			}	
		}
	}
		
	// ComboBoxHandler van kanaal B voor het opslaan van de juiste optie voor de schaal in het model
	class ChannelDScaleHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JComboBox) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Double item = (Double) comboBox.getSelectedItem();
				model.setScale(3, item);
			}
		}
	}
		
	// ButtonHandler van kanaal B voor het weergeven van een ColorChooserDialog en het opslaan van de geselecteerde kleur in het model
	class ChannelDColorChooserButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(new JFrame(), "Kies een kleur:", Color.BLACK);
			model.setColor(3, color);
		}
	}
}

