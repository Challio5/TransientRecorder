package nl.transientrecorder.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class RecorderMenu extends JMenuBar {
	
	// Recordermenu met optie om het debugscherm te openen en het programma te sluiten
	private JMenu recorder;
	private JMenuItem debug;
	private JMenuItem screenshot;
	private JMenuItem close;
	
	// Filemenu met optie om de gegevens op te slaan
	private JMenu file;
	private JMenuItem save;
	
	// Extramenu met optie voor meer informatie
	private JMenu extra;
	private JMenuItem help;
	
	// Constructor
	public RecorderMenu() {
		recorder = new JMenu("Recorder");
		file = new JMenu("File");
		extra = new JMenu("Extra");
		
		// Recordermenu items
		debug = new JMenuItem("Show");
		screenshot = new JMenuItem("Screenshot");
		close = new JMenuItem("Close");
		recorder.add(debug);
		recorder.add(screenshot);
		recorder.add(close);
		
		// Filemenu items
		save = new JMenuItem("Save");
		//String operatingSystem = System.getProperty("os.name").toLowerCase();
		//if(operatingSystem.equals("windows")) {
			save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		//}
		//if(operatingSystem.equals("mac")) {
			//save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
		//}
		file.add(save);
		
		// Extramenu items
		help = new JMenuItem("Help");
		extra.add(help);
		
		this.add(recorder);
		this.add(file);
		this.add(extra);
	}
	
	// Methode om een actionListener toe te voegen aan het showDebug item voor het weergeven van het debugFrame
	public void addShowDebugActionListener(ActionListener listener) {
		debug.addActionListener(listener);
	}
	
	// Methode om een actionListener toe te voegen aan het screenshot item voor maken van een screenshot van het frame
	public void addScreenshotActionListener(ActionListener listener) {
		screenshot.addActionListener(listener);
	}
	
	// Methode om een actionListener toe te voegen aan het close item voor het sluiten van het programma
	public void addCloseActionListener(ActionListener listener) {
		close.addActionListener(listener);
	}
	
	// Methode om een actionListener toe te voegen aan het save item voor het opslaan van de data
	public void addSaveActionListener(ActionListener listener) {
		save.addActionListener(listener);
	}
	
	// Methode om een actionListener toe te voegen aan het help item voor het weergeven van extra informatie
	public void addHelpActionListener(ActionListener listener) {
		help.addActionListener(listener);
	}
}
