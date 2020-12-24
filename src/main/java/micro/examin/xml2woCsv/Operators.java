package micro.examin.xml2woCsv;

public enum Operators {
	equals("=="), divide("/"),	add("+");


	public final String label;


	private Operators(String label) {
		this.label = label;
	}
}
