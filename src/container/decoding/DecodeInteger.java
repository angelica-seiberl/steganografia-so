package decoding;

public class DecodeInteger {

	// This method decodes an integer from a pixel's row (rgb). The decoded integer
	// is saved through the setter of the object IntegerValue
	public void decodeInteger(int[] rgb, IntegerValue size) {
		int numDecod = 0;
		int bitValue;

		for (int i = 0; i < 32; i++) {
			bitValue = getBitValue(rgb[i], 0);
			numDecod = numDecod | (setBitValue(0, i, bitValue));

		}
		size.setValue(numDecod);
	}

	// This method returns the bit value of the integer number n in a certain
	// location
	public int getBitValue(int n, int location) {
		int v = n & (int) Math.round(Math.pow(2, location));

		if (v == 0) {
			return 0;
		} else {
			return 1;
		}

	}

	// This method allows to set a new bit value (bit) in a certain location of the
	// integer number n
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
