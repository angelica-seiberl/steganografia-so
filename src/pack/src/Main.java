package src;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args) throws IOException {
		if(args.length==0) {
			System.err.println("Use this format: java Main name_img");
			System.exit(1);
		}
		//java.awt.image.BufferedImage img = Image.read(args[0]);
		File f = new File(args[0]);
		java.awt.image.BufferedImage img = ImageIO.read(f);
		
		//*This is another way to obtain the picture.*/
		//File f = new File("Immagini/tramonto-mareImg.jpg");
		//java.awt.image.BufferedImage img = ImageIO.read(f);
		
		Codifier codifier = new Codifier();
		codifier.codify(img);
       
        System.exit(0);
        
        
	}
}
