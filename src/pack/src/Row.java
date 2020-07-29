package src;

public class Row implements Runnable {
	private int[] rgbArray;
	private char s;
	private boolean done = false;
	private boolean integer = false;
	
	public Row(int[] a, char s) {
		rgbArray = a;
		this.s=s;
	}
	
	public Row(int[] a, int size) {
		this.integer = true;
		//if integer==true ...
		//chiamare embedInteger del nostro amico
	}
	
	public void run() {
		//if integer==false ...
		//chiamare embedByte del nostro amico
		done= true;
	}
}
