package main;

import java.util.concurrent.LinkedBlockingQueue;

public abstract class ObjectPool<T> {
	private int coreSize;
	private int maxSize;
	private LinkedBlockingQueue<T> pool;
	
	public ObjectPool(int coreSize, int maxSize) {
		if(maxSize < 1 || coreSize > maxSize) {
			throw new Error("Invalid Parameters Error");
		}
		
		this.coreSize = coreSize;
		this.maxSize = maxSize;
		this.pool = new LinkedBlockingQueue<T>(this.maxSize);
		
		for(int i = 0; i < this.coreSize; i++) {
			this.pool.add(this.create());
		}
//		System.out.println("Pool made Successfully");
	}
	
	public T checkOut() {
		T t = this.pool.poll();
		return (t != null) ? t : this.create();
	}
	
	public void checkIn(T obj) {
		if(obj != null) {
			this.pool.offer(obj);
		}
	}
	
	public abstract T create();

	public int getCoreSize() {
		return this.coreSize;
	}
	
	public int getMaxSize() {
		return this.maxSize;
	}
	
	public int getCurrentSize() {
		return this.pool.size();
	}
	
	public void terminate() {
		this.pool.clear();
		this.pool = null;
	}
}