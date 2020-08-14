package decoding;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainDecoder {
	public static void main(String[] args) throws IOException {
		if(args.length==0) {
			System.err.println("Use this format: java Main name_img");
			System.exit(1);
		}
		
		File f = new File(args[0]);
		java.awt.image.BufferedImage img2 = ImageIO.read(f);
		
		Decoder decoder = new Decoder();
		decoder.decode(img2);
		
		System.exit(1);
	}
}
