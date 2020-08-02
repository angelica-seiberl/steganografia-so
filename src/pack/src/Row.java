package src;

public class Row implements Runnable {
	private int[] rgbArray;
	private char s;
	private int size;
	private boolean done = false;
	private boolean integer = false;
	private String message;
	
	public Row(int[] a, char s) {
		rgbArray = a;
		this.s=s;
	}
	
	public Row(int[] a, int size) {
		rgbArray = a;
		this.integer = true;
		this.size = size;
		//if integer==true ...
		//chiamare embedInteger del nostro amico
	}
	
	public void run() {
		if(integer) {
			embedInteger(rgbArray, size, 0);
			embedMessage(rgbArray, message);
		}
		else {
			
		}
		//if integer==false ...
		//chiamare embedByte del nostro amico
		done= true;
	}
	
	private void embedInteger(int[] rgb, int n, int storageBit) {
		for (int i = 0; i < 32; i++) {
			
			int bit = getBitValue(n, i);
			rgb[i] = setBitValue(rgb[i], storageBit, bit);
			
		}
		
	}
	
	private void embedByte(int rgb[], byte n, int storageBit) {
		for(int i=0; i<32; i++) {
			int bit = getBitValue(n,i);
			rgb[i] = setBitValue(rgb[i], storageBit, bit);
		}
	}
	
	private void embedMessage(int[] rgb, String mess) {
		int leng = mess.length();
		embedInteger(rgb, leng, 0);
		byte b[] = mess.getBytes();
		for(int i=0;i<b.length;i++) {
			embedByte(rgb, b[i], 0);
		}
	}
	
	private int getBitValue(int n, int location) {
		int v = n & (int) Math.round(Math.pow(2, location));
		return v == 0 ? 0 : 1;
	}

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
