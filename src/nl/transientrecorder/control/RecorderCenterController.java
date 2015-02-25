package nl.transientrecorder.control;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import nl.transientrecorder.model.Recorder;
import nl.transientrecorder.view.RecorderCenterView;

public class RecorderCenterController {
	private Recorder model;
	private RecorderCenterView recorderView;
	
	public RecorderCenterController(Recorder model , RecorderCenterView recorderView) {
		this.model = model;
		this.recorderView = recorderView;
		
		recorderView.addComponentListener(new RecorderCenterViewResizeHandler());
	}
	
	// ResizeHandler voor het aanpassen van de range voor min/max waarden bij het resizen van de grafiekview
	class RecorderCenterViewResizeHandler extends ComponentAdapter {
		// Vraagt de nieuwe hoogte op van de grafiekview en slaat deze op in het model
		@Override
		public void componentResized(ComponentEvent e) {
			model.setValueRange(recorderView.getHeight() / 2);
		}
	}
}
