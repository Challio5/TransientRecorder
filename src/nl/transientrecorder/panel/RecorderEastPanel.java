package nl.transientrecorder.panel;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

public class RecorderEastPanel extends JPanel {
	
	private RecorderEastCardsPanel channelA;
	private RecorderEastCardsPanel channelB;
	private RecorderEastCardsPanel channelC;
	private RecorderEastCardsPanel channelD;
	
	public RecorderEastPanel() {
		this.setLayout(new CardLayout());
		
		channelA = new RecorderEastCardsPanel();
		channelB = new RecorderEastCardsPanel();
		channelC = new RecorderEastCardsPanel();
		channelD = new RecorderEastCardsPanel();
		
		// Voegt de componenten in de juiste volgorde toe aan het paneel
		this.add(channelA, "Kanaal A");
		this.add(channelB, "Kanaal B");
		this.add(channelC, "Kanaal C");
		this.add(channelD, "Kanaal D");

	}
	
	// Delegeert de methoden van kanaalA om actionListeners toe te voegen
	public void addChannelAVelocityListener(ChangeListener listener) {
		channelA.addVelocityListener(listener);
	}

	public void addChannelAScaleListener(ActionListener listener) {
		channelA.addScaleListener(listener);
	}

	public void addChannelAColorChooserButtonListener(ActionListener listener) {
		channelA.addColorChooserButtonListener(listener);
	}

	// Delegeert de methoden van kanaalB om actionListeners toe te voegen
	public void addChannelBVelocityListener(ChangeListener listener) {
		channelB.addVelocityListener(listener);
	}

	public void addChannelBScaleListener(ActionListener listener) {
		channelB.addScaleListener(listener);
	}

	public void addChannelBColorChooserButtonListener(ActionListener listener) {
		channelB.addColorChooserButtonListener(listener);
	}
	
	// Delegeert de methoden van kanaalC om actionListeners toe te voegen
	public void addChannelCVelocityListener(ChangeListener listener) {
		channelC.addVelocityListener(listener);
	}

	public void addChannelCScaleListener(ActionListener listener) {
		channelC.addScaleListener(listener);
	}

	public void addChannelCColorChooserButtonListener(ActionListener listener) {
		channelC.addColorChooserButtonListener(listener);
	}
	
	// Delegeert de methoden van kanaalD om actionListeners toe te voegen
	public void addChannelDVelocityListener(ChangeListener listener) {
		channelD.addVelocityListener(listener);
	}

	public void addChannelDScaleListener(ActionListener listener) {
		channelD.addScaleListener(listener);
	}

	public void addChannelDColorChooserButtonListener(ActionListener listener) {
		channelD.addColorChooserButtonListener(listener);
	}
}
