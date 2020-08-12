package decodifica;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainDecoder {
	public static void main(String[] args) throws IOException {
		if(args.length==0) {
			System.err.println("Use this format: java Main name_img");
			System.exit(1);
		}
		//java.awt.image.BufferedImage img = Image.read(args[0]);
		File f = new File(args[0]);
		java.awt.image.BufferedImage img2 = ImageIO.read(f);
		
		//File g = new File("Immagini/tramonto-mareProva.png");
		//BufferedImage img2 = ImageIO.read(g);
		Decoder decoder = new Decoder();
		decoder.decode(img2);
		
		System.exit(1);
	}
}
