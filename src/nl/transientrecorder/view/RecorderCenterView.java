package nl.transientrecorder.view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import nl.transientrecorder.model.MeasuredValue;
import nl.transientrecorder.model.Recorder;

public class RecorderCenterView extends JPanel implements Observer{
	
	// Lijsten met de tekenen waardes
	private LinkedList<MeasuredValue> valueListA; 
	private LinkedList<MeasuredValue> valueListB;
	private LinkedList<MeasuredValue> valueListC; 
	private LinkedList<MeasuredValue> valueListD;
	
	// Actief kanaal
	private String kanaal;
	
	// Optie om per pixel te tekenen
	private boolean pixelPerValue;
	
	// Lijn voor min en max waarden
	private int minValue;
	private int maxValue;
	
	// Variabele voor het tekenen van de grafiek
	private boolean visibleA;
	private boolean visibleB;
	private boolean visibleC;
	private boolean visibleD;
	
	private int velocityA;
	private int velocityB;
	private int velocityC;
	private int velocityD;
	
	private double scaleA;
	private double scaleB;
	private double scaleC;
	private double scaleD;
	
	// Kleuren van de grafiek
	private Color colorA;
	private Color colorB;
	private Color colorC;
	private Color colorD;
	
	// Constructor
	public RecorderCenterView() {
		valueListA = new LinkedList<MeasuredValue>();
		valueListB = new LinkedList<MeasuredValue>();
		valueListC = new LinkedList<MeasuredValue>();
		valueListD = new LinkedList<MeasuredValue>();
		
		// Standaar actief kanaal is A
		kanaal = "Kanaal A";
		
		// Standaard kleur is zwart
		colorA = new Color(0);
		colorB = new Color(0);
		colorC = new Color(0);
		colorD = new Color(0);
	}
	
	// Tekent het paneel met een grafiek en raster
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawRaster(g);
		this.drawExtremeValues(g);
		this.drawGraph(g);
	}
	
	// Tekent het raster voor de grafiek
	public void drawRaster(Graphics g) {	
		// Maakt een copie van de grafische context en cast de graphics naar 2D
		Graphics2D g2 = (Graphics2D) g.create();
		
		// Tekent een string waar de snelheid in wordt weergegeven
		if(kanaal.equals("Kanaal A")) {
			g2.drawString(velocityA + " ms/div", 10, 20);
		}
		if(kanaal.equals("Kanaal B")) {
			g2.drawString(velocityB + " ms/div", 10, 20);
		}
		if(kanaal.equals("Kanaal C")) {
			g2.drawString(velocityC + " ms/div", 10, 20);
		}
		if(kanaal.equals("Kanaal D")) {
			g2.drawString(velocityD + " ms/div", 10, 20);
		}
		
		// Tekent de omlijning van het scherm
		g2.setColor(Color.GREEN);
		Stroke old = g2.getStroke();
		g2.setStroke(new BasicStroke(4));
		g2.drawRect(2, 2, this.getWidth() - 4, this.getHeight() - 4);
		g2.setStroke(old);
		
		// Maakt een nieuwe compositie en aan en maakt set deze
		AlphaComposite transparancy = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
		g2.setComposite(transparancy);
		
		// Tekent de verticale lijnen van het raster
		for(int x = 0; x <= this.getWidth() / 50; x++) {
			g2.drawLine(x * 50, 0, x * 50, this.getWidth());
		}
		
		// Tekent de horizontale lijn van het raster vanuit het midden van het scherm
		for(int y = 0; y <= this.getHeight() / 100; y++) {
			g2.drawLine(0, (this.getHeight() / 2) - (y * 50), this.getWidth(), (this.getHeight() / 2) - (y * 50));
			g2.drawLine(0, (this.getHeight() / 2) + (y * 50), this.getWidth(), (this.getHeight() / 2) + (y * 50));
		}
		
		// Gooit het kopie van de grafische context weg
		g2.dispose();
	}
	
	// Tekent de min en max waarde
	public void drawExtremeValues(Graphics g) {
		Graphics g1 = g.create();
		// Max waarde
		g.setColor(Color.RED);
		g.drawLine(0, (this.getHeight() / 2) - maxValue, 
				this.getWidth(), (this.getHeight() / 2) - maxValue);
		// Min waarde
		g.setColor(new Color(34, 139, 34));
		g.drawLine(0, (this.getHeight() / 2) + minValue, this.getWidth(), (this.getHeight() / 2) + minValue);
	}
	
	// Tekent de grafiek met de waarden die door het model worden afgegeven
	public void drawGraph(Graphics g) {
		// Als kanaal A zichtbaar is
		if(visibleA) {
			// Gaat achterstevoren door de arraylist met de te tekenen waarde (nieuwste waarden eerst)
			for(int i = 0; i < valueListA.size() - 1; i++) {
				// Maakt een kopie van de grafische context
				Graphics g1 = g.create(); 
				
				// Tekent de lijnen met de meegegeven kleur van rechts naar links
				g1.setColor(colorA);
				
				//if(valueListA.get(i).getValue() > minValue && valueListA.get(i).getValue() < maxValue ) {
					// Als de pixel per value optie geselecteerd is dan wordt er per pixel getekend
					if(pixelPerValue) {
						g1.drawLine(this.getWidth() - ((i * 50) / (velocityA / 10)), 
								((int) (valueListA.get(i).getValue() / scaleA)) + this.getHeight() / 2 - ((int) (128 / scaleA)), 
								this.getWidth() - ((i * 50) / (velocityA / 10)), 
								((int) (valueListA.get(i).getValue() / scaleA)) + this.getHeight() / 2 - ((int) (128 / scaleA)));
					}
					// Anders wordt er een lijn tussen de punten getrokken
					else {
						g1.drawLine(this.getWidth() - ((i * 50) / (velocityA / 10)), 
								((int) (valueListA.get(i).getValue() / scaleA)) + this.getHeight() / 2 - ((int) (128 / scaleA)), 
								this.getWidth() - (((i + 1) * 50) / (velocityA / 10)), 
								((int) (valueListA.get(i + 1).getValue() / scaleA)) + this.getHeight() / 2 - ((int) (128 / scaleA)));
					}
				//}

				// Gooit het kopie van de grafische context weg
				g1.dispose();
			}
		}
		
		// Als kanaal B zichtbaar is
		if(visibleB) {
			// Gaat achterstevoren door de arraylist met de te tekenen waarde (nieuwste waarden eerst)
			for(int i = 0; i < valueListB.size() - 1; i++) {
				// Maakt een kopie van de grafische context
				Graphics g1 = g.create(); 
				
				// Tekent de lijnen met de meegegeven kleur van rechts naar links
				g1.setColor(colorB);
				// Als de pixel per value optie geselecteerd is dan wordt er per pixel getekend
				if(pixelPerValue) {
					g1.drawLine(this.getWidth() - ((i * 50) / (velocityB / 10)), 
							((int) (valueListB.get(i).getValue() / scaleB)) + this.getHeight() / 2 - ((int) (128 / scaleB)), 
							this.getWidth() - ((i * 50) / (velocityB / 10)), 
							((int) (valueListB.get(i).getValue() / scaleB)) + this.getHeight() / 2 - ((int) (128 / scaleB)));
				}
				// Anders wordt er een lijn tussen de punten getrokken
				else {
					g1.drawLine(this.getWidth() - ((i * 50) / (velocityB / 10)), 
							((int) (valueListB.get(i).getValue() / scaleB)) + this.getHeight() / 2 - ((int) (128 / scaleB)), 
							this.getWidth() - (((i + 1) * 50) / (velocityB / 10)), 
							((int) (valueListB.get(i + 1).getValue() / scaleB)) + this.getHeight() / 2 - ((int) (128 / scaleB)));
				}
				
				// Gooit het kopie van de grafische context weg
				g1.dispose();
			}
		}
		
		// Als kanaal C zichtbaar is
		if(visibleC) {
			// Gaat achterstevoren door de arraylist met de te tekenen waarde (nieuwste waarden eerst)
			for(int i = 0; i < valueListC.size() - 1; i++) {
				// Maakt een kopie van de grafische context
				Graphics g1 = g.create(); 
				
				// Tekent de lijnen met de meegegeven kleur van rechts naar links
				g1.setColor(colorC);
				// Als de pixel per value optie geselecteerd is dan wordt er per pixel getekend
				if(pixelPerValue) {
					g1.drawLine(this.getWidth() - ((i * 50) / (velocityC / 10)), 
							((int) (valueListC.get(i).getValue() * scaleC)) + this.getHeight() / 2 - ((int) (128 * scaleC)), 
							this.getWidth() - ((i * 50) / (velocityC / 10)), 
							((int) (valueListC.get(i).getValue() * scaleC)) + this.getHeight() / 2 - ((int) (128 * scaleC)));
				}
				// Anders wordt er een lijn tussen de punten getrokken
				else {
					g1.drawLine(this.getWidth() - ((i * 50) / (velocityC / 10)), 
							((int) (valueListC.get(i).getValue() / scaleC)) + this.getHeight() / 2 - ((int) (128 / scaleC)), 
							this.getWidth() - (((i + 1) * 50) / (velocityC / 10)), 
							((int) (valueListC.get(i + 1).getValue() / scaleC)) + this.getHeight() / 2 - ((int) (128 / scaleC)));
				}

				// Gooit het kopie van de grafische context weg
				g1.dispose();
			}
		}
		
		// Als kanaal B zichtbaar is
		if(visibleD) {
			// Gaat achterstevoren door de arraylist met de te tekenen waarde (nieuwste waarden eerst)
			for(int i = 0; i < valueListD.size() - 1; i++) {
				// Maakt een kopie van de grafische context
				Graphics g1 = g.create(); 
				
				// Tekent de lijnen met de meegegeven kleur van rechts naar links
				g1.setColor(colorD);
				// Als de pixel per value optie geselecteerd is dan wordt er per pixel getekend
				if(pixelPerValue) {
					g1.drawLine(this.getWidth() - ((i * 50) / (velocityD / 10)), 
							((int) (valueListD.get(i).getValue() / scaleD)) + this.getHeight() / 2 - ((int) (128 / scaleD)), 
							this.getWidth() - ((i * 50) / (velocityD / 10)), 
							((int) (valueListD.get(i).getValue() / scaleD)) + this.getHeight() / 2 - ((int) (128 / scaleD)));
				}
				// Anders wordt er een lijn tussen de punten getrokken
				else {
					g1.drawLine(this.getWidth() - ((i * 50) / (velocityD / 10)), 
							((int) (valueListD.get(i).getValue() / scaleD)) + this.getHeight() / 2 - ((int) (128 / scaleD)), 
							this.getWidth() - (((i + 1) * 50) / (velocityD / 10)), 
							((int) (valueListD.get(i + 1).getValue() / scaleD)) + this.getHeight() / 2 - ((int) (128 / scaleD)));
				}

				// Gooit het kopie van de grafische context weg
				g1.dispose();
			}
		}
	}
	
	// Krijgt de veranderende waarden binnen van het model
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Recorder) {
			// Cast naar de klasse van het model
			Recorder model = (Recorder) o;
			
			// Haalt de arraylist met de te tekenen waarden binnen
			valueListA = model.getStackedGraphList(0);
			valueListB = model.getStackedGraphList(1);
			valueListC = model.getStackedGraphList(2);
			valueListD = model.getStackedGraphList(3);
			
			// Haalt het actieve kanaal op 
			kanaal = model.getKanaal();
			
			// Haalt het te tekenen minimum en maximum op
			minValue = model.getMinValue();
			maxValue = model.getMaxValue();
			
			// Haalt de boolean op of er per pixel moet worden getekent of in grafiekvorm
			pixelPerValue = model.isPixelPerValue();
			
			// Haalt de booleans binnen of een kanaal zichtbaar is
			visibleA = model.isVisible(0);
			visibleB = model.isVisible(1);
			visibleC = model.isVisible(2);
			visibleD = model.isVisible(3);
			
			// Haalt de snelheid op
			velocityA = model.getVelocity(0);
			velocityB = model.getVelocity(1);
			velocityC = model.getVelocity(2);
			velocityD = model.getVelocity(3);
			
			// Haalt de schaal op
			scaleA = model.getScale(0);
			scaleB = model.getScale(1);
			scaleC = model.getScale(2);
			scaleD = model.getScale(3);
			
			// Haalt de kleuren binnen voor de te tekenen grafieken
			colorA = model.getColor(0);
			colorB = model.getColor(1);
			colorC = model.getColor(2);
			colorD = model.getColor(3);
			
			// Hertekent het model op het moment er nieuwe waarden binnen zijn
			this.repaint();
		}
		else {
			// Error voor als het model niet goed is aangemeld
			System.out.println("Error: GraphViewUpdateError");
		}
	}
}
