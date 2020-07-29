package src;

public class MetodoInteger {


	public void embedInteger(int[] rgb, int n, int storageBit) {
		for (int i = 0; i < rgb.length; i++) {

			int bit = getBitValue(n, i);
			rgb[i] = setBitValue(rgb[i], storageBit, bit);
			i++;

		}
	}

	public int getBitValue(int n, int location) {
		int v = n & (int) Math.round(Math.pow(2, location));
		return v == 0 ? 0 : 1;
	}

	public int setBitValue(int n, int location, int bit) {
		int toggle = (int) Math.pow(2, location), bv = getBitValue(n, location);
		if (bv == bit)
			return n;
		if (bv == 0 && bit == 1)
			n |= toggle;
		else if (bv == 1 && bit == 0)
			n ^= toggle;
		return n;
	}

	public static void main(String[] args) {
		int[] prova = {1, 2, 3};
		
		MetodoInteger prova1 = new MetodoInteger();
		System.out.println("\n bit values:");
		for (int i : prova) {
			System.out.println("\n" + prova1.getBitValue(i, 0));
		}
		System.out.println("\n new bit values");
		prova1.embedInteger(prova, 0, 0);
		for (int i : prova) {
			System.out.println("\n" + prova1.getBitValue(i, 0));
		}
		
 }
}
