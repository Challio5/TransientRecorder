package nl.transientrecorder.panel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DebugTextPanel extends JPanel {
	private JLabel textAreaLabel;
	private JTextArea textArea;
	
	public DebugTextPanel(String kanaal) {
		// Borderlayout om het label klein boven de textvelden weer te geven
		this.setLayout(new BorderLayout());
		
		// Label voor het tekstveld
		textAreaLabel = new JLabel(kanaal);
		
		// Tekstveld voor is niet bewerkbaar, geeft alleen waarden weer
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		this.add(textAreaLabel, BorderLayout.NORTH);
		this.add(textArea, BorderLayout.CENTER);
	}

	// Delegeert de getText en setText methodes
	public String getText() {
		return textArea.getText();
	}

	public void setText(String input) {
		textArea.setText(input);
	}
}
