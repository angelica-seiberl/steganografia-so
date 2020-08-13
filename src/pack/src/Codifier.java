package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Codifier {

		public Codifier () {}

		public void codify(BufferedImage img) throws IOException {
			int w = img.getWidth();
			int h = img.getHeight();
			
			Scanner input = new Scanner (System.in);
			System.out.println("Insert your message!");
			String text = input.nextLine();
			input.close();
			
			int lenght = text.length();
			if(lenght > (h-1)) {
				System.err.println("Message too long for this picture");
				System.exit(1);
			}
			

			byte b[] = text.getBytes();
			Row[] rows = new Row[h];
			Thread[] threads = new Thread[lenght + 1];
			ArrayList<int[]> rgb = new ArrayList<int[]>();
			int[] firstRgb = img.getRGB(0, 0, w, 1, null, 0, w);
			rows[0] = new Row(firstRgb, lenght);
			threads[0] = new Thread(rows[0]);
			
			for(int i=0; i<lenght; i++) {
				 rgb.add(img.getRGB(0, i+1, w, 1, null, 0, w));
				rows[i+1] = new Row(rgb.get(i), b[i]); //row's index starts with the number 1, because the first row is 
				threads[i+1] = new Thread(rows[i+1]);       // reserved to the phrase's dimension.
			}
			
			//We codify the length of the text message in the first row of the picture
			try {
				for ( Thread t: threads) {
					t.start();
				}
				for ( Thread t: threads) {
					t.join();
				}
			}
			catch(InterruptedException e) {
				System.out.println("Interrupted exception!");
			}
			
			img.setRGB(0, 0, w, 1, firstRgb, 0, w);
			
			for(int i = 0; i < lenght; i ++) {
				img.setRGB(0, i+1, w, 1, rgb.get(i), 0, w);
			}
			
			
			File outputfile = new File("NewImage.png");
			ImageIO.write(img, "png", outputfile);
	        
		}
}
