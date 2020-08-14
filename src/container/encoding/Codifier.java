package encoding;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Codifier {

		public Codifier () {}

		public void codify(BufferedImage img) throws IOException {
			int w = img.getWidth(); //image's width
			int h = img.getHeight(); //image's height
			
			//we want to receive the message from standard input.
			Scanner input = new Scanner (System.in);
			System.out.println("Insert your message!");
			String text = input.nextLine();
			
			//we want to know the new image's name.
			System.out.println("Insert new image's name!");
			String image = input.nextLine();
			input.close();
			
			//We control the length of the text because we memorize a byte for each pixel's row.
			int length = text.length();
			if(length > (h-1)) {
				System.err.println("Message too long for this picture");
				System.exit(1);
			}
			
			//we divide the text in bytes and create a thread for each byte.
			byte b[] = text.getBytes();
			Row[] rows = new Row[length + 1];
			Thread[] threads = new Thread[length + 1];
			//we create a list of pixel's rows
			ArrayList<int[]> rgb = new ArrayList<int[]>();
			//in the first pixel's row we save the length of the message, so row[0] is used for it.
			int[] firstRgb = img.getRGB(0, 0, w, 1, null, 0, w);
			rows[0] = new Row(firstRgb, length);
			threads[0] = new Thread(rows[0]);
			
			//We extract the other pixel's rows and for each byte we create a thread.
			for(int i=0; i<length; i++) {
				rgb.add(img.getRGB(0, i+1, w, 1, null, 0, w));
				rows[i+1] = new Row(rgb.get(i), b[i]); //row's index starts with the number 1, because the first row is 
				threads[i+1] = new Thread(rows[i+1]);       // reserved to the phrase's dimension.
			}
			
			//We codify the length of the text message in the first row of the image and the bytes in the other rows.
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
			
			for(int i = 0; i < length; i ++) {
				img.setRGB(0, i+1, w, 1, rgb.get(i), 0, w);
			}
			
			//We create the new image with the hidden text.
			File outputfile = new File(image+".png");
			ImageIO.write(img, "png", outputfile);
	        
		}
}
