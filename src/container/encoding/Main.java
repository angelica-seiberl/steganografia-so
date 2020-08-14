package encoding;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args) throws IOException {
		//We control the correct insertion of the parameters on command line.
		//The parameter is the image which has to be modified.
		if(args.length==0) {
			System.err.println("Use this format: java Main name_img");
			System.exit(1);
		}
		
		//We read the image.
		File f = new File(args[0]);
		java.awt.image.BufferedImage img = ImageIO.read(f);
		
		//This is the item that we use to codify our text.
		Codifier codifier = new Codifier();
		codifier.codify(img);
       
        System.exit(0);
	}
}
