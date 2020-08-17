package encoding;

public class Row implements Runnable {
	private int[] rgbArray;
	private byte b;
	private int size;
	private boolean integer = false;
	
	
//This constructor is used to create a runnable object that embed a new byte in the pixel's array
	public Row(int[] a, byte b) {
		rgbArray = a;
		this.b=b;
	}

//This constructor is used to create a runnable object that embed an integer in the pixel's array
	public Row(int[] a, int size) {
		rgbArray = a;
		this.integer = true;
		this.size = size;
	}

//If the thread is created to embed an integer (this object is instantiated through the second constructor) it embeds the message length,
//Otherwise it embeds a byte
	public void run() {
		if(integer) {
			embedInteger(rgbArray, size, 0);
		}
		else {
			embedByte(rgbArray, b ,0);
		}
	}
//This method allows to embed an integer (n) in a pixel's array	 (rgb), the storageBit is the pixel's that has to be modified.
	private void embedInteger(int[] rgb, int n, int storageBit) {
		for (int i = 0; i < 32; i++) {
			
			int bit = getBitValue(n, i);
			rgb[i] = setBitValue(rgb[i], storageBit, bit);
			
		}
		
	}
	
	
//	This method allows to embed a byte (n) in a pixel's array (rgb), the storageBit is the pixel's bit that has to be modified.	
	private void embedByte(int rgb[], byte n, int storageBit) {
		for(int i=0; i<8; i++) {
			int bit = getBitValue(n,i);
			rgb[i] = setBitValue(rgb[i], storageBit, bit);
		}
	}
	
// This method returns the bit value of the integer number n in a certain location 	
	private int getBitValue(int n, int location) {
		int v = n & (int) Math.round(Math.pow(2, location));
	 if(v == 0) {
		return 0;
		}
		else {
			return 1;
		}
		
	}

// This method allows to set a new bit value (bit) in a certain location of the integer number n
	private int setBitValue(int n, int location, int bit) {
		int toggle = (int) Math.pow(2, location), bv = getBitValue(n, location);
		if (bv == bit){
			
			return n;
		}
		if (bv == 0 && bit == 1)
			n |= toggle;
		else if (bv == 1 && bit == 0)
			n ^= toggle;
		
		return n;
	}
}
