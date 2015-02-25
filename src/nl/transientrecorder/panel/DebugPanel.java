package nl.transientrecorder.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class DebugPanel extends JPanel {
	
	private DebugSouthPanel panel;
	
	public DebugPanel(JPanel view) {
		// BorderLayout het plaatsen van het tekstgebied in het midden en de knopper eronder
		this.setLayout(new BorderLayout());
		
		panel = new DebugSouthPanel();
		
		// Voegt de componenten in het juiste gebied toe aan het paneel
		this.add(view, BorderLayout.CENTER);
		this.add(panel, BorderLayout.SOUTH);
	}
	
	// Delegeert methode om een actionListener toe te voegen aan de start en stop knop in DebugButtonPanel
	public void addActionListener(ActionListener listener) {
		panel.addActionListener(listener);
	}
}
