public class ArrayDequeTest{
	public static void main(String[] args) {
		System.out.println("Running tests.\n");

		ArrayDeque<String>a = new ArrayDeque<>();
		a.addFirst("a");
		a.addFirst("b");
		a.addFirst("c");
		a.addFirst("d");
		a.addFirst("e");
		a.addFirst("f");
		a.addFirst("g");
		a.addFirst("h");
		a.addFirst("i");
		a.addFirst("j");
		a.addFirst("k");
		a.addFirst("l");
		a.addFirst("m");
		a.addFirst("n");

		a.printDeque();

		a.removeFirst();
		a.removeFirst();
		a.removeFirst();
		a.removeLast();
		a.removeLast();
		a.removeLast();
		
		a.addFirst("haha");
		a.printDeque();
	}
}