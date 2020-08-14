package decoding;

import java.awt.image.BufferedImage;

public class Decoder {
	public Decoder() {
	}

	public void decode(BufferedImage img2) {
		// Image's width
		int w = img2.getWidth();

		// The first pixel's row contains the size of the message, so it is decoded from
		// an object DecodeInteger.
		// This object saves the size's value in an object IntegerValue
		int[] firstRgb = img2.getRGB(0, 0, w, 1, null, 0, w);
		DecodeInteger integerDecoder = new DecodeInteger();
		IntegerValue size = new IntegerValue(0);
		integerDecoder.decodeInteger(firstRgb, size);

		// We instantiates an array of ByteValue, these objects will save the values of
		// the bytes decoded through the threads
		ByteValue[] bytes = new ByteValue[size.getValue()];
		// Array of bytes that will save the byte decoded and it is useful to recreate
		// the hidden message
		byte b[] = new byte[size.getValue()];
		// Initialization of the ByteValue's array
		for (int j = 0; j < size.getValue(); j++) {
			bytes[j] = new ByteValue((byte) 0);
		}
		// We instantiates an array of threads, this threads will decodify each byte
		Thread[] threads = new Thread[size.getValue()];
		for (int i = 0; i < size.getValue(); i++) {
			threads[i] = new Thread(new DecodeRow(img2.getRGB(0, i + 1, w, 1, null, 0, w), bytes[i]));
		}
		for (Thread thread : threads) {
			thread.start();
		}
		try {
			for (Thread thread : threads) {
				thread.join();
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("InterruptedException!");
			System.exit(1);
		}

		// Every value of the array of bytes is set with the value of the corresponding
		// object ByteValue
		for (int i = 0; i < size.getValue(); i++) {

			b[i] = bytes[i].getValue();

		}
		// Generation of the decoded message
		String decoded = new String(b);
		System.out.println(decoded);
	}
}
