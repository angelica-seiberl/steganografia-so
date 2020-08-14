package decoding;

public class DecodeRow implements Runnable {
	private int[] rgbArray;
	private ByteValue b;
	
	
//Creates a runnable object that decodes a byte from a pixel's row (rgb). The decoded byte is saved through the setter of the object ByteValue
	public DecodeRow(int[] rgb, ByteValue b) {
		// TODO Auto-generated constructor stub
		this.rgbArray = rgb;
		this.b = b;
	}
	
	
//decodes the value of a byte from the pixel's row and saves this value in the object ByteValue
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			int numDecod = 0;
			int bitValue;

			for (int i = 0; i < 8; i++) {
				bitValue = getBitValue(rgbArray[i], 0);
				numDecod = numDecod | setBitValue(numDecod, i, bitValue); 
			} 

		
			b.setValue((byte)numDecod);
		
		
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
		if (bv == bit) {

			return n;

		}

		if (bv == 0 && bit == 1)
			n |= toggle;
		else if (bv == 1 && bit == 0)
			n ^= toggle;

		return n;
	}

	

}
