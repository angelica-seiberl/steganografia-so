package src;

public class MetodoByte {
	
	
	
	
	

	public int getBitValue(int n, int location) {
		int v = n & (int) Math.round(Math.pow(2, location));
		return v == 0 ? 0 : 1;
		
	}

	public int setBitValue(int n, int location, int bit) {
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
	
	int w, h;
	
	private void embedMessage(int[] rgb, String mess) {
		int leng = mess.length();
		if(leng>800) {
			System.out.println("Message too long");
		}
		embedInteger(rgb, leng, 0);
		byte b[] = mess.getBytes();
		for(int i=0;i<b.length;i++) {
			embedByte(rgb, b[i], i*8+32);
		}
	}
}
