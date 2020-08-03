package decodifica;

public class DecodeRow implements Runnable {
	private int[] rgbArray;
	private ByteValue b;
	private boolean done = false;
	
	
	public DecodeRow(int[] rgb, ByteValue b) {
		// TODO Auto-generated constructor stub
		this.rgbArray = rgb;
		this.b = b;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			int numDecod = 0;
			int bitValue;

			for (int i = 0; i < 8; i++) {
				bitValue = getBitValue(rgbArray[i], 0);
				numDecod = numDecod | setBitValue(numDecod, i, bitValue); // faccio or tra numero decodificato fin a quel momento
																		// e
			} // numero con tutti 0 e valore 0 o 1 nella posizione analizzata
		 // a seconda del bit corrente decodificato (bitValue)
		
			b.setValue((byte)numDecod);
		
		
	}
	
	private int getBitValue(int n, int location) {
		int v = n & (int) Math.round(Math.pow(2, location));

		return v == 0 ? 0 : 1;

	}

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
