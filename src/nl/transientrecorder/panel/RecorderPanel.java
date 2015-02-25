package nl.transientrecorder.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import nl.transientrecorder.view.RecorderCenterView;
import nl.transientrecorder.view.RecorderNorthView;

public class RecorderPanel extends JPanel {
	public RecorderPanel(RecorderCenterView graphView, RecorderNorthView chooserPanel, RecorderEastPanel controlPanel, RecorderSouthPanel visiblePanel) {
		this.setLayout(new BorderLayout());
		
		// Voegt de componenten in de juiste volgorde toe aan het paneel
		this.add(chooserPanel, BorderLayout.NORTH);
		this.add(graphView, BorderLayout.CENTER);
		this.add(controlPanel, BorderLayout.EAST);
		this.add(visiblePanel, BorderLayout.SOUTH);
	}
}