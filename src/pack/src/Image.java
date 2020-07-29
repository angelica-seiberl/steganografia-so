package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	private Image() {
		
	}
	
	public static BufferedImage read(String fileName) throws IOException{
		File f = new File(fileName);
		return ImageIO.read(f);
	}
}
