package nl.transientrecorder.panel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RecorderSouthPanel extends JPanel {
	private JLabel kanaalALabel;
	private JLabel kanaalBLabel;
	private JLabel kanaalCLabel;
	private JLabel kanaalDLabel;
	
	private JCheckBox kanaalACheckBox;
	private JCheckBox kanaalBCheckBox;
	private JCheckBox kanaalCCheckBox;
	private JCheckBox kanaalDCheckBox;
	
	public RecorderSouthPanel() {
		this.setLayout(new GridLayout(1,8));
		
		kanaalALabel = new JLabel("Kanaal A");
		kanaalBLabel = new JLabel("Kanaal B");
		kanaalCLabel = new JLabel("Kanaal C");
		kanaalDLabel = new JLabel("Kanaal D");
		
		kanaalACheckBox = new JCheckBox();
		kanaalBCheckBox = new JCheckBox();
		kanaalCCheckBox = new JCheckBox();
		kanaalDCheckBox = new JCheckBox();
		
		this.add(kanaalALabel);
		this.add(kanaalACheckBox);
		this.add(kanaalBLabel);
		this.add(kanaalBCheckBox);
		this.add(kanaalCLabel);
		this.add(kanaalCCheckBox);
		this.add(kanaalDLabel);
		this.add(kanaalDCheckBox);
	}
	
	// Methodes om een actionListener toe te voegen aan de checkbox of een kanaal zichtbaar is 
	public void addChannelAVisibilityListener(ActionListener listener) {
		kanaalACheckBox.addActionListener(listener);
	}
	
	public void addChannelBVisibilityListener(ActionListener listener) {
		kanaalBCheckBox.addActionListener(listener);
	}
	
	public void addChannelCVisibilityListener(ActionListener listener) {
		kanaalCCheckBox.addActionListener(listener);
	}
	
	public void addChannelDVisibilityListener(ActionListener listener) {
		kanaalDCheckBox.addActionListener(listener);
	}
}
