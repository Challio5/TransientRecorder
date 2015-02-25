package nl.transientrecorder.control;


/**
 * Dummy implementation for testing purposed
 * With this you don't need the rxtx libs while developing
 * Note: this dummy does NOT implement all the methods of the rxtx SerialPortEvent
 * @author jan
 *
 */
public class DummySerialPortEvent  {
	public static final int DATA_AVAILABLE = 1;
	
	private DummySerialPort source;
	private int eventType;
	private boolean oldStatus;
	private boolean newStatus;
	
	public DummySerialPortEvent( DummySerialPort aSource, int anEventType, boolean anOldStatus, boolean aNewStatus ) {
		source = aSource;
		eventType = anEventType;
		oldStatus = anOldStatus;
		newStatus = aNewStatus;
	}
	
	public DummySerialPort getSource() {
		return source;
	}
	
	public int getEventType() {
		return eventType;
	}
	
	public boolean getOldStatus() {
		return oldStatus;
	}
	public boolean getNewStatus() {
		return newStatus;
	}
}
