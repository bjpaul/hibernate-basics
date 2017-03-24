package org.hibernate.concurrency.locking;

public class Test {
	public static void execute(Runnable runnable){
		new Thread(runnable).start();
	}
}
