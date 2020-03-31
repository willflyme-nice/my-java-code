package mypackt;


public class HelloClass {
	private int umber;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("HelloPackate");
	}

	public int getNum() {
		return umber;
	}

	public void setNum(int num) {
		this.umber = num;
	}
	
	public void printout() {
		System.out.print("something");
	}
	public void context() {
		printout();
	}

}

class Parent extends HelloClass{
	private String name;
}
