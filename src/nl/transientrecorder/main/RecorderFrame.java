package nl.transientrecorder.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class RecorderFrame extends JFrame {
	
	public RecorderFrame(JMenuBar menu, JPanel panel) {
		// Voegt het menu toe aan het frame
		this.setJMenuBar(menu);

		// Vult het frame met het meegegeven panel
		this.setContentPane(panel);

		/*
		try {
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if(info.getName().equals("Nimbus")){
					UIManager.setLookAndFeel(info.getClassName());
				}
			}
		} catch(Exception e) {}
		*/
		
		// Zet de juiste argumenten voor het frame
		this.setTitle("Transient Recorder");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1200, 600));
		this.setVisible(true);
	}
}
