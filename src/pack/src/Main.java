package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args) throws IOException {
		if(args.length==0) {
			System.err.println("Use this format: java Main name_img");
			System.exit(1);
		}
		//java.awt.image.BufferedImage img = Image.read(args[0]);
		File f = new File("Immagini/tramonto-mareImg.jpg");
		java.awt.image.BufferedImage img = ImageIO.read(f);
		
		int w = img.getWidth();
		int h = img.getHeight();
		int imgSize = w * h;
		
		Scanner input = new Scanner (System.in);
		String text = input.nextLine();
		input.close();
		
		int lenght = text.length();
		if(lenght > (h-1)) {
			System.err.println("Message too long for this picture");
			System.exit(1);
		}
		
		Row[] rows = new Row[h];
		Thread[] threads = new Thread[h];
		
		for(int i=0; i<lenght; i++) {
			int[] rgb = img.getRGB(0, i, w, 1, null, 0, w);
			rows[i] = new Row(rgb, text.charAt(i));
			threads[i] = new Thread(rows[i]);
		}
		
		
		
		
		
        File g = new File("Immagini/tramonto-mareProva.jpg");
        ImageIO.write(img, "jpg", g);
        BufferedImage img2 = ImageIO.read(g);
	}
}
