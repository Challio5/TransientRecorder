package nl.transientrecorder.control;


import java.util.EventListener;

public interface DummySerialPortEventListener extends EventListener {
	public void serialEvent( DummySerialPortEvent event );
}
