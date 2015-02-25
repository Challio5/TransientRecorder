package nl.transientrecorder.control;
//import gnu.io.*;
import java.io.InputStream;
import java.util.TooManyListenersException;


/**
 * Simulates an Serial port, but simplified. It provides an inputstream and an supports an exceptionlistener
 * In the stream is simulates incoming messages coming from an STK500 or other device
 * It only supports notifyOnDataAvailable, more you dont need for now
 * A lot of the SerialPort and Comport methods are just dummies, so be sure not to use them
 * Note the protocol of data: 'm timestamp value unit/n'
 * m tells whatt kind of line it is, 
 * timestamp gives a clue to when the value was attained
 * value is the measered value, it should be transformed, giving the right unit value so bin 255 = 2,55 Vdc
 * unit gives meaning to the value but is optional. 
 * Note: this dummy does NOT implement all the methods of the rxtx SerialPort
 * 
 * @author Eelco Jannink
 */
public class DummySerialPort implements Runnable {
	
	private DummyInputStream inputStream = new DummyInputStream();
	private DummySerialPortEventListener listener = null;
	private boolean notifyOnDataAvailable = false;
	
	//You may want to change this to an arraylist of bytes when dealing with bin data
	private StringBuffer buffer = new StringBuffer();
	private int counter = 0; // counts timeticks 

	private Thread thread;
	private boolean running = true;
	
	public DummySerialPort() {
		thread = new Thread(this);
		thread.start();		
	}
	public DummySerialPort(int seed) {
		this.counter = seed;
		this.thread = new Thread(this);
		this.thread.start();		
	}
	
	

	/**
	 * return an measured value every tenth second
	 * the value is the sin of count/10.0
	 */
	@Override
	public void run() {
		//System.out.println("Looping");
		int[] sinus = new int[] { 140,152,165,176,188,198,209,218,226,234,240,245,250,253,254,255,254,253,250,245,240,234,226,218,208,198,188,176,165,152,140,128,115,103,90,79,67,57,47,37,29,21,15,10,5,2,1,0,1,2,5,10,15,21,29,37,47,57,67,79,90,103,115,128 };
		
		try {
			while ( running ) {
				// generate channel a data
				if(false) //orriginal
					buffer.append( "A" + (char) (127+127*Math.sin( counter )) ); // meaning: A=channel a, value
				else 
					buffer.append("A" + (char) sinus[counter%64] );
				// generate channel b data
				buffer.append("B" + (char) (127+100*Math.cos( counter/1.4 )) ); // meaning: B=channel b, value
				buffer.append("C" + (char) (127+120*Math.cos( counter/22.8 )) );
				buffer.append("D" + (char) (127+50*Math.sin( counter/3.3 )) ); 
				
				if ( notifyOnDataAvailable && listener != null ) {
					listener.serialEvent( new DummySerialPortEvent( this, DummySerialPortEvent.DATA_AVAILABLE, false, true ) );
				}
				counter++;
				Thread.sleep(10); // generate a message every 10 ms
			}
		} catch (InterruptedException e) {}
		
		
	}
	
	public void close() {
		running = false;
	}
	
	public void stop() {
		close();
	}
	
	public InputStream getInputStream() {
		return this.inputStream;
	}
	
	public void addEventListener( DummySerialPortEventListener listener ) throws TooManyListenersException {
		if ( this.listener == null ) {
			this.listener = listener;
		} else {
			//System.out.println("Throwing");
			throw new TooManyListenersException();
		}
	}
	
	public void removeEventListener() {
		listener = null;
	}
	
	public void notifyOnDataAvailable( boolean notify ) {
		this.notifyOnDataAvailable = notify;
	}
	
	
/** from SerialPort, dummy implements... not supported here 
	
	public void setSerialPortParams( int b, int d, int s, int p ) throws UnsupportedCommOperationException {}
	public int getBaudRate() { return 0; } // dummy
	public int getDataBits() { return 0; }
	public int getStopBits() { return 0; }
	public int getParity() { return 0; }
	public void setFlowControlMode( int flowcontrol ) throws UnsupportedCommOperationException {}
	public int getFlowControlMode() { return 0; }
	public boolean isDTR() { return false; }
	public void setDTR( boolean state ) {}
	public void setRTS( boolean state ) {}
	public boolean isCTS() { return false; }
	public boolean isDSR() { return false; }
	public boolean isCD() { return false; }
	public boolean isRI() { return false; }
	public boolean isRTS() { return false; }
	public void sendBreak( int duration ) {}
	//public void addEventListener( SerialPortEventListener lsnr ) throws TooManyListenersException {}
	//public void removeEventListener() {}
	//public void notifyOnDataAvailable( boolean enable ) {}
	public void notifyOnOutputEmpty( boolean enable ) {}
	public void notifyOnCTS( boolean enable ) {}
	public void notifyOnDSR( boolean enable ) {}
	public void notifyOnRingIndicator( boolean enable ) {}
	public void notifyOnCarrierDetect( boolean enable ) {}
	public void notifyOnOverrunError( boolean enable ) {}
	public void notifyOnParityError( boolean enable ) {}
	public void notifyOnFramingError( boolean enable ) {}
	public void notifyOnBreakInterrupt( boolean enable ) {}

	public byte getParityErrorChar( ) throws UnsupportedCommOperationException { return 0; }
	public boolean setParityErrorChar( byte b ) throws UnsupportedCommOperationException  { return false; }
	public byte getEndOfInputChar( ) throws UnsupportedCommOperationException  { return 0; }
	public boolean setEndOfInputChar( byte b ) throws UnsupportedCommOperationException { return false; }
	public boolean setUARTType(String type, boolean test) throws UnsupportedCommOperationException { return false; }
	public String getUARTType() throws UnsupportedCommOperationException  { return null; }
	public boolean setBaudBase(int BaudBase) throws UnsupportedCommOperationException,	IOException { return false; }
	public int getBaudBase() throws UnsupportedCommOperationException, IOException { return 0; }
	public boolean setDivisor(int Divisor) throws UnsupportedCommOperationException, IOException { return false; }
	public int getDivisor() throws UnsupportedCommOperationException, IOException { return 0; }
	public boolean setLowLatency() throws UnsupportedCommOperationException { return false; }
	public boolean getLowLatency() throws UnsupportedCommOperationException { return false; }
	public boolean setCallOutHangup(boolean NoHup) throws UnsupportedCommOperationException { return false; }
	public boolean getCallOutHangup() throws UnsupportedCommOperationException { return false; }

// from CommPort 
	public void enableReceiveFraming( int f ) throws UnsupportedCommOperationException {}
	public void disableReceiveFraming() {}
	public boolean isReceiveFramingEnabled() { return false; }
	public int getReceiveFramingByte() { return 0; }
	public void disableReceiveTimeout() {}
	public void enableReceiveTimeout( int time ) throws UnsupportedCommOperationException {}
	public boolean isReceiveTimeoutEnabled() { return false; }
	public int getReceiveTimeout() { return 0; }
	public void enableReceiveThreshold( int thresh ) throws UnsupportedCommOperationException {}
	public void disableReceiveThreshold() {}
	public int getReceiveThreshold() { return 0; }
	public boolean isReceiveThresholdEnabled() { return false; }
	public void setInputBufferSize( int size ) {}
	public int getInputBufferSize() { return 0; }
	public void setOutputBufferSize( int size ) {}
	public int getOutputBufferSize() { return 0; }
	
	//public InputStream getInputStream() throws IOException;
	public OutputStream getOutputStream() throws IOException { return null; }
*/
	
	
	
	/**************************/
	
	private class DummyInputStream extends InputStream {
		
		
		@Override
		public int available() {
			return buffer.length();
		}
		
		@Override
		public int read() {
			while ( buffer.length() < 1 ) { // blocks until some character is available !
			}
			int character = buffer.charAt( 0 );
			buffer.deleteCharAt( 0 );
			return character; // no eof (-1) is possible
		}
	}
	
}
