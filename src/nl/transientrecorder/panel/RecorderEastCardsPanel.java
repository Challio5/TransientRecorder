package nl.transientrecorder.panel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class RecorderEastCardsPanel extends JPanel {

	// Label met slider om de snelheid mee in te stellen
	private JLabel velocityLabel;
	private JSlider velocity;
	
	// Label met combobox inclusief stringarray met weer te geven opties voor het instellen van de schaal
	private Double[] comboBoxItems;
	private JLabel scaleLabel;
	private JComboBox scale;

	// Label met knop voor het weergeven van de dialog voor het kiezen van kleuren
	private JLabel colorChooserLabel;
	private JButton colorChooserButton;
	
	// Constructor
	public RecorderEastCardsPanel() {
		// Gridlayout om zowel de labels als componenten in twee verticale kolommen weer te geven
		this.setLayout(new GridLayout(6,1));
		
		velocityLabel = new JLabel("Snelheid (time/div)");
		velocity = new JSlider();

		// Argumenten voor de het toevoegen van een schaal en puntslider
		velocity.setValue(5);
		velocity.setMinimum(1);
		velocity.setMaximum(10);
		velocity.setMajorTickSpacing(1);
		velocity.setPaintTicks(true);
		
		comboBoxItems = new Double[] {0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0};
		scaleLabel = new JLabel("Schaal (unit/div)");
		scale = new JComboBox(comboBoxItems);
		scale.setSelectedItem(comboBoxItems[3]);
		
		colorChooserButton = new JButton("Kies Kleur");
		colorChooserLabel = new JLabel("Kleuren kiezen");
		
		this.add(velocityLabel);
		this.add(velocity);
		this.add(scaleLabel);
		this.add(scale);
		this.add(colorChooserLabel);
		this.add(colorChooserButton);
	}
	
	// Methode om een changeListener toe te voegen aan de slider om de snelheid mee in te stellen
	public void addVelocityListener(ChangeListener listener) {
		velocity.addChangeListener(listener);
	}
	
	// Methode om een actionListener toe te voegen aan de combobox om de juiste schaal mee in te stellen
	public void addScaleListener(ActionListener listener) {
		scale.addActionListener(listener);
	}
	
	// Methode om een actionListener toe te voegen aan de knop voor het weergeven van de dialog om kleuren mee te kiezen
	public void addColorChooserButtonListener(ActionListener listener) {
		colorChooserButton.addActionListener(listener);
	}
}
