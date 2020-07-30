package decodifica;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestDecodifica {
public static void main(String[] args) throws IOException {
	 File g = new File("Immagini/tramonto-mareProva.png");
	 BufferedImage img2 = ImageIO.read(g);
	 int w = img2.getWidth();
	 
	 int[] firstRgb = img2.getRGB(0, 0, w, 1, null, 0, w);
	 DecodeInteger prova = new DecodeInteger();
	 for(int i = 0; i < 32; i ++) {
		 prova.getBitValue(firstRgb[i], 0);
	 }
	 prova.decodeInteger(firstRgb);
}
}
