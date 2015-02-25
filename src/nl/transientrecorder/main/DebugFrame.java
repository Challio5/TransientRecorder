package nl.transientrecorder.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class DebugFrame extends JFrame {
	// Constructor
	public DebugFrame(JPanel panel) {
		// Vult het frame met het meegegeven panel
		this.setContentPane(panel);
		
		/*
		try {
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if(info.getName().equals("Nimbus")) {
					UIManager.setLookAndFeel(info.getClassName());
				}
			}
		} catch(Exception e) {}
		*/
		
		// Zet de juiste argumenten voor het frame
		this.setTitle("Debug Scherm");
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(new Dimension(1000, 800));
	}
}
