package nl.transientrecorder.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import nl.transientrecorder.main.DebugFrame;
import nl.transientrecorder.model.Recorder;
import nl.transientrecorder.panel.DebugPanel;
import nl.transientrecorder.view.DebugCenterView;

public class DebugController {

	// Views voor het weergeven van de waarden
	private DebugCenterView debugView;
	
	// Panels voor de layout en het besturen van de views
	private DebugPanel debugPanel;
	
	public DebugController(Recorder model, DebugCenterView debugView) {	
		// View
		this.debugView = debugView;
		
		// Aanmelden views als observers
		model.addObserver(debugView);
		
		// Panels
		debugPanel = new DebugPanel(debugView);
		
		// Listener buttons debugPanel
		debugPanel.addActionListener(new DebugButtonHandler());
		
		// Frame
		new DebugFrame(debugPanel);
	}
	
	// DebugButtonHandler voor het pauzeren van het weergeven van data in het debugFrame
	class DebugButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JButton) {
				JButton button = (JButton) e.getSource();
				if(button.getText().equals("Start")) {}
				if(button.getText().equals("Stop")) {}
			}
		}
	}
}
