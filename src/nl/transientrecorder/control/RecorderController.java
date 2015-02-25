package nl.transientrecorder.control;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import nl.transientrecorder.main.DebugFrame;
import nl.transientrecorder.main.RecorderFrame;
import nl.transientrecorder.main.RecorderMenu;
import nl.transientrecorder.model.MeasuredValue;
import nl.transientrecorder.model.Recorder;
import nl.transientrecorder.panel.DebugPanel;
import nl.transientrecorder.panel.RecorderEastPanel;
import nl.transientrecorder.panel.RecorderPanel;
import nl.transientrecorder.panel.RecorderSouthPanel;
import nl.transientrecorder.view.DebugCenterView;
import nl.transientrecorder.view.RecorderCenterView;
import nl.transientrecorder.view.RecorderNorthView;

public class RecorderController implements DummySerialPortEventListener {
	
	// Model met de weer te geven waarden
	private Recorder model;
	
	// Menu
	private RecorderMenu recorderMenu;
	
	// Controllers
	private DebugController debugController;
	private RecorderCenterController centerController;
	private RecorderNorthController northController;
	private RecorderEastController eastController;
	private RecorderSouthController southController;
	
	// Panels
	private DebugPanel debugPanel;
	private RecorderPanel recorderPanel;
	private RecorderEastPanel recorderEastPanel;
	private RecorderSouthPanel recorderSouthPanel;

	// SerialPort voor het genereren van waarden
	private DummySerialPort port;
	private InputStream input;
	
	// Views voor het weergeven van de waarden
	private DebugCenterView debugView;
	private RecorderCenterView graphView;
	private RecorderNorthView northView;
	
	// Frames en een menu om de panel mee weer te geven
	private RecorderFrame recorderFrame;
	private DebugFrame debugFrame;

	// Constructor
	public RecorderController(Recorder model){
		// Model
		this.model = model;
		
		// Menu
		recorderMenu = new RecorderMenu();
		
		// Panels
		recorderEastPanel = new RecorderEastPanel();
		recorderSouthPanel = new RecorderSouthPanel();
		
		// View
		debugView = new DebugCenterView();
		graphView = new RecorderCenterView();
		northView = new RecorderNorthView();
		
		// Controllers
		debugController = new DebugController(model, debugView);
		centerController = new RecorderCenterController(model, graphView);
		eastController = new RecorderEastController(model, recorderEastPanel);
		northController = new RecorderNorthController(model, northView, recorderEastPanel);
		southController = new RecorderSouthController(model, recorderSouthPanel);
		
		// FramePanels
		recorderPanel = new RecorderPanel(graphView, northView, recorderEastPanel, recorderSouthPanel);
		debugPanel = new DebugPanel(debugView);
		
		// Setup SerialPort
		port = new DummySerialPort(100);
		try {
			port.addEventListener(this);
		} catch (TooManyListenersException e) {}
		port.notifyOnDataAvailable(true);
		input = port.getInputStream();
		
		// Aanmelden views als observers
		model.addObserver(graphView);
		model.addObserver(northView);
		
		// Listeners items Recordmenu
		recorderMenu.addShowDebugActionListener(new RecorderMenuShowDebugHandler());
		recorderMenu.addScreenshotActionListener(new RecorderMenuScreenshotHandler());
		recorderMenu.addCloseActionListener(new RecorderMenuCloseHandler());
		recorderMenu.addSaveActionListener(new RecorderMenuSaveHandler());
		recorderMenu.addHelpActionListener(null);
		
		// Frames
		recorderFrame = new RecorderFrame(recorderMenu, recorderPanel);
		recorderFrame.addWindowListener(new WindowCloseHandler());
		
		debugFrame = new DebugFrame(debugPanel);
	}
	
	// Afhandelen van binnenkomende data via een serialEvent
	@Override
	public void serialEvent(DummySerialPortEvent event) {
		if ( event.getEventType() == DummySerialPortEvent.DATA_AVAILABLE ) {
			try {
				boolean channel = true;
				while (input.available() > 0) {
					if(channel)
						if((char) input.read() == 'A') {
							int value = input.read();
							model.addMeasuredValue(0, value, System.currentTimeMillis());
							//System.out.println("Kanaal A:" + value);
						}
						if((char) input.read() == 'B') {
							int value = input.read();
							model.addMeasuredValue(1, value, System.currentTimeMillis());
							//System.out.println("Kanaal B:" + value);
						}
						if((char) input.read() == 'C') {
							int value = input.read();
							model.addMeasuredValue(2, value, System.currentTimeMillis());
							//System.out.println("Kanaal A:" + value);
						}
						if((char) input.read() == 'D') {
							int value = input.read();
							model.addMeasuredValue(3, value, System.currentTimeMillis());
							//System.out.println("Kanaal B:" + value);
						}
						else {
							System.out.println("Error");
						}				
					channel = !channel;
				} 
			} catch (IOException e) {}
		}
	}
	
	// WindowCloseHandler voor het vragen om op te sluiten bij het sluiten van het recorderFrame
	class WindowCloseHandler extends WindowAdapter {
	    // Geeft de mogelijk om op te slaan op het moment het programma wordt afgesloten
		@Override
		public void windowClosing(WindowEvent e) {
	    	// Opties in de warning dialog 
	    	Object[] options = {"Ja", "Nee"};
	    	// Opent de JOptionPane en slaat de gekozen optie op
	    	int choice = JOptionPane.showOptionDialog(recorderFrame,
	    		    	"Wilt u de gegevens bewaren?",
	    		    	"Opslaan",
	    		    	JOptionPane.YES_NO_CANCEL_OPTION,
	    		    	JOptionPane.WARNING_MESSAGE, 
	    		    	null, options, options[1]);
	    	// Als er op ja gedrukt is
	    	if(choice == JOptionPane.YES_OPTION) {
				// Maakt een JFileChooser aan met bijbehorende extensies
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Bestand", "csv");
			    chooser.setFileFilter(filter);
			     
			    // Opent de JFileChooser dialog en slaat de actie op
				int option = chooser.showSaveDialog(recorderFrame);
				if(option == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile(); 
					// Probeert de het bestand op te slaan als csv bestand
					try {
						// Maakt een bufferwriter aan voor data die moet worden opgeslagen
						BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
						
						// Voegt de data toen aan de buffer
						buffer.append("Value,\tTime\n");
							for(MeasuredValue value : model.getStoredValueList(0)) {
								buffer.append(value.getValue() + ",\t" + value.getTime() + "\n");
							}
						// Sluit de buffer en schrijft de data weg
						buffer.close();
					} 
					catch (IOException e1) {}
				}
	    	}
	    }
	}


	// MenuHandler voor het weergeven van het debugPanel
	class RecorderMenuShowDebugHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			debugFrame.setVisible(true);
		}
	}

	// MenuHandler voor het maken van een screenshot van het frame
	class RecorderMenuScreenshotHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Maakt een JFileChooser aan met bijbehorende extensies
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Bestand", "jpg");
			chooser.setFileFilter(filter);
		     
		    // Opent de JFileChooser dialog en slaat de actie op
			int option = chooser.showSaveDialog(new JFrame());
			if(option == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile(); 
				try {
					Robot robot = new Robot();
					BufferedImage screenshot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					ImageIO.write(screenshot, "JPG", file);
				} 
				catch (AWTException e1) {
					e1.printStackTrace();
				} 
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	// MenuHandler voor het afsluiten van het programma, triggert de WindowsCloseHandler
	class RecorderMenuCloseHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Frame frame : Frame.getFrames()) {
				if (frame.isActive()) {
					WindowEvent windowClosing = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
					frame.dispatchEvent(windowClosing);
				}
			}
		}
	}
	
	// MenuHandler voor het opslaan van de data
	class RecorderMenuSaveHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Maakt een JFileChooser aan met bijbehorende extensies
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Bestand", "csv");
		    chooser.setFileFilter(filter);
		     
		    // Opent de JFileChooser dialog en slaat de actie op
			int option = chooser.showSaveDialog(new JFrame());
			if(option == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile(); 
				// Probeert de het bestand op te slaan als csv bestand
				try {
					// Maakt een bufferwriter aan voor data die moet worden opgeslagen
					BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
					
					// Voegt de data toen aan de buffer
					buffer.append("Value,\tTime\n");
						for(MeasuredValue value : model.getStoredValueList(0)) {
							buffer.append(value.getValue() + ",\t" + value.getTime() + "\n");
						}
					
					// Sluit de buffer en schrijft de data weg
					buffer.close();
				} 
				catch (IOException e1) {}
			}
		}
	}
}
