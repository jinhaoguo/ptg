package org.gdut.ptg.cache.impl;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.gdut.ptg.cache.ICache;

public class MemcachedCache implements ICache {
	private int timeOut=0;

	private MemcachedClient  memcachedClient;
	
	public void setTimeOut(int timeOut){
		this.timeOut=timeOut;
	}
	
	public void setMemcachedClient(MemcachedClient tmp){
		this.memcachedClient=tmp;
	}

	public synchronized  void add(String key, Object obj, int timeout) {
		try {
			memcachedClient.set(key, timeout, obj);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public synchronized  void delByKey(String key) {
		try {
			memcachedClient.delete(key);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public  void clearAll()  {
		try {
			memcachedClient.flushAll();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public synchronized  Object getByKey(String key) {
		try {
			return memcachedClient.get(key);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean containKey(String key) {
		Object obj= getByKey(key);
		if(obj ==null)
			return false;
		return true;
	}

	public synchronized  void add(String key, Object obj) {
		
		try {
			memcachedClient.set(key, timeOut, obj);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
