package decoding;

public class IntegerValue {
	private int value;

//This object memorizes an integer value and allows to modify it through a setter
	public IntegerValue(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public void setValue(int newValue) {
		this.value = newValue;
	}

	public int getValue() {
		return value;
	}

}
