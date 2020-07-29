package src;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		if(args.length==0) {
			System.err.println("Use this format: java Main name_img");
			System.exit(1);
		}
		java.awt.image.BufferedImage img = Image.read(args[0]);
		int w = img.getWidth();
		int h = img.getHeight();
	}
}
