package nl.transientrecorder.panel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DebugSouthPanel extends JPanel {
	
	// Start en stopknop voor het pauzeren van het uitlezen van de waarden
	private JButton start;
	private JButton stop;
	
	// Constructor
	public DebugSouthPanel() {
		// Gridlayout voor het horizontaal verdelen van het paneel
		this.setLayout(new GridLayout(1,2));
		
		start = new JButton("Start");
		stop = new JButton("Stop");
		
		// Voegt de componenten in de juiste volgorde toe aan het paneel
		this.add(start);
		this.add(stop);
	}
	
	// Methode om een actionListener toe te voegen aan de start en stop knop
	public void addActionListener(ActionListener listener) {
		start.addActionListener(listener);
		stop.addActionListener(listener);
	}
}
