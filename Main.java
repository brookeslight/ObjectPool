package main;

public class Main {

	public static void main(String[] args) {
		ParserPool pool = new ParserPool(10, 15);
		Parser p = pool.checkOut();
		System.out.println(p.num);
		pool.checkIn(p);
		pool.terminate();
	}

}