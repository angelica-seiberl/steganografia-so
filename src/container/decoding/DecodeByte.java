package decoding;

public class DecodeByte {
	public void decodeByte(int[] rgb, ByteValue b) {
		int numDecod = 0;
		int bitValue;

		for (int i = 0; i < 8; i++) {
			bitValue = getBitValue(rgb[i], 0);
			numDecod = numDecod | setBitValue(numDecod, i, bitValue); // faccio or tra numero decodificato fin a quel momento
																	// e
		} // numero con tutti 0 e valore 0 o 1 nella posizione analizzata
	 // a seconda del bit corrente decodificato (bitValue)
	
		b.setValue((byte)numDecod);
	}
	
	public int getBitValue(int n, int location) {
		int v = n & (int) Math.round(Math.pow(2, location));

		return v == 0 ? 0 : 1;

	}

	private int setBitValue(int n, int location, int bit) {
		int toggle = (byte) Math.pow(2, location), bv = getBitValue(n, location);
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
