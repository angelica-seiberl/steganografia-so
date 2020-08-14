package decoding;

import java.awt.image.BufferedImage;

public class Decoder {
	public Decoder() {}
	
	public void decode(BufferedImage img2) {
		int w = img2.getWidth();
		 
		int[] firstRgb = img2.getRGB(0, 0, w, 1, null, 0, w);
		DecodeInteger prova = new DecodeInteger();
		for(int i = 0; i < 32; i ++) {
			prova.getBitValue(firstRgb[i], 0);
		}
		IntegerValue size = new IntegerValue(0);
		prova.decodeInteger(firstRgb, size);
		DecodeByte provaByte = new DecodeByte();
		ByteValue[] bytes = new ByteValue[size.getValue()];
		byte b[] = new byte[size.getValue()];
		for (int j = 0; j < size.getValue(); j ++) {
			bytes[j] = new ByteValue((byte)0);	
		}
		Thread[] threads = new Thread[size.getValue()];
		for(int i = 0; i < size.getValue(); i ++) {	 
			threads[i] = new Thread(new DecodeRow(img2.getRGB(0, i+1, w, 1, null, 0, w), bytes[i]));
		}
		for (Thread thread : threads) {
			thread.start();
		}
		try {
			for (Thread thread : threads) {
				thread.join();
			}
		}
		catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("InterruptedException!");
			System.exit(1);
		}
		 
		for(int i = 0; i < size.getValue(); i++) {
		 
			b[i] = bytes[i].getValue();
			 
		}
		
		String decoded = new String(b);
		System.out.println(decoded);
	}
}
