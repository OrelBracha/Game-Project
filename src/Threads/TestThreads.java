package Threads;

public class TestThreads {

	public static void main(String[] a) {

		MyTread t0 = new MyTread("a");

		MyTread t1 = new MyTread("b");

	//	t0.run();

	//	t1.run();

		t0.start();

		t1.start();

		System.out.println("done main");

	}

	

}