package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args) throws IOException {
	/*	if(args.length==0) {
			System.err.println("Use this format: java Main name_img");
			System.exit(1);
		}*/
		//java.awt.image.BufferedImage img = Image.read(args[0]);
	
		File f = new File("Immagini/tramonto-mareImg.jpg");
		java.awt.image.BufferedImage img = ImageIO.read(f);
		
		int w = img.getWidth();
		int h = img.getHeight();
		int imgSize = w * h;
		
		Scanner input = new Scanner (System.in);
		System.out.println("Inserisci il messaggio!");
		String text = input.nextLine();
		input.close();
		
		int lenght = text.length();
		if(lenght > (h-1)) {
			System.err.println("Message too long for this picture");
			System.exit(1);
		}
		

		byte b[] = text.getBytes();
		Row[] rows = new Row[h];
		Thread[] threads = new Thread[h];
		int[] firstRgb = img.getRGB(0, 0, w, 1, null, 0, w);
		rows[0] = new Row(firstRgb, lenght);
		
		for(int i=0; i<lenght; i++) {
			int[] rgb = img.getRGB(0, i+1, w, 1, null, 0, w);
			rows[i+1] = new Row(rgb, b[i]); //gli indici delle righe  non partono da 0 perch� la prima riga
			threads[i] = new Thread(rows[i]);       // � riservata alla dimensione della frase
		}
		
		//codifico lunghezza messaggio nella prima riga di pixel
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
		
		File outputfile = new File("Immagini/tramonto-mareProva.png");
		ImageIO.write(img, "png", outputfile);
        
       
        System.exit(0);
        
        
	}
}
