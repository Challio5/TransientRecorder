package nl.transientrecorder.view;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import nl.transientrecorder.model.MeasuredValue;
import nl.transientrecorder.model.Recorder;
import nl.transientrecorder.panel.DebugTextPanel;

public class DebugCenterView extends JPanel implements Observer {
	
	// Tekstgebieden om de gegenereerde waarden weer te geven per kanaal
	private DebugTextPanel channelA;
	private DebugTextPanel channelB;
	private DebugTextPanel channelC;
	private DebugTextPanel channelD;
	
	// Constructor
	public DebugCenterView() {
		// Gridlayout voor splitsen van het scherm voor elk kanaal
		this.setLayout(new GridLayout(2,2));
		
		channelA = new DebugTextPanel("Kanaal A");
		channelB = new DebugTextPanel("Kanaal B");
		channelC = new DebugTextPanel("Kanaal C");
		channelD = new DebugTextPanel("Kanaal D");

		// Voegt de componenten in de juiste volgorde toe aan het paneel
		this.add(channelA);
		this.add(channelB);
		this.add(channelC);
		this.add(channelD);
	}
	
	// Krijgt de veranderende waarden binnen van het model
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Recorder) {
			// Cast naar de klasse van het model
			Recorder model = (Recorder) o;
			
			// Vraagt de laatste gegenereerde waarde op inclusief tijd
			if(model.getStackedDebugList(0).size() != 0) {
				MeasuredValue value = model.getStackedDebugList(0).pollLast();
				int valueA = value.getValue();
				long timeA = value.getTime();
						
				// Zet de waarde en tijd in het tekstveld gescheiden door een tab gevolgd door een enter
				channelA.setText(String.valueOf(valueA)  + "\t" + String.valueOf(timeA) +  "\n" + channelA.getText());
			}

			// Vraagt de laatste gegenereerde waarde op inclusief tijd
			if(model.getStackedDebugList(1).size() != 0) {
				MeasuredValue value = model.getStackedDebugList(1).pollLast();
				int valueB = value.getValue();
				long timeB = value.getTime();
					
				// Zet de waarde en tijd in het tekstveld gescheiden door een tab gevolgd door een enter
				channelB.setText(String.valueOf(valueB)  + "\t" + String.valueOf(timeB) +  "\n" + channelB.getText());
			}
			
			// Vraagt de laatste gegenereerde waarde op inclusief tijd
			if(model.getStackedDebugList(2).size() != 0) {
				MeasuredValue value = model.getStackedDebugList(2).pollLast();
				int valueC = value.getValue();
				long timeC = value.getTime();
					
				// Zet de waarde en tijd in het tekstveld gescheiden door een tab gevolgd door een enter
				channelC.setText(String.valueOf(valueC)  + "\t" + String.valueOf(timeC) +  "\n" + channelC.getText());
			}
			
			// Vraagt de laatste gegenereerde waarde op inclusief tijd
			if(model.getStackedDebugList(3).size() != 0) {
				MeasuredValue value = model.getStackedDebugList(3).pollLast();
				int valueD = value.getValue();
				long timeD = value.getTime();
					
				// Zet de waarde en tijd in het tekstveld gescheiden door een tab gevolgd door een enter
				channelD.setText(String.valueOf(valueD)  + "\t" + String.valueOf(timeD) +  "\n" + channelD.getText());
			}
		}
	}
}
