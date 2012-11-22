package com.bryanreinero.hum.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdatingMap<K, V> implements Map<K, V> {
	
	private HashMap <K, V> map = new HashMap<K, V>();
	private static Thread ConfigRetirerThread;
	private CacheCleanser cleanser;
	private static Logger logger = LogManager.getLogger( UpdatingMap.class.getName() );
	
	private class CacheCleanser implements Runnable {

		private static final long default_time_to_live = 86400000l;
		private DelayQueue<KillConfig> delayQueue = null;
		
		private class KillConfig implements Delayed {
			private long timeToLive_ms = default_time_to_live;
			private long inceptionDate_ms = System.currentTimeMillis();
			private K key;
			
			public K getConfigID() {
				return key;
			}

			public void setConfigID(K key) {
				this.key = key;
			}

			public void setTimeToLive_ms(long timeToLive_ms) {
				this.timeToLive_ms = timeToLive_ms;
			}

			@Override
			public int compareTo(Delayed arg0) {
				if(arg0.getDelay(TimeUnit.MILLISECONDS) > this.getDelay(TimeUnit.MILLISECONDS))
					return -1;
				if(arg0.getDelay(TimeUnit.MILLISECONDS) < this.getDelay(TimeUnit.MILLISECONDS))
					return 1;
				return 0;
			}

			@Override
			public long getDelay(TimeUnit arg0) {
				return arg0.convert(this.timeToLive_ms - (System.currentTimeMillis() - this.inceptionDate_ms), arg0);
			}
		}
		public CacheCleanser(){
			delayQueue = new DelayQueue<KillConfig>();
		}
		
		@Override
		public void run() {
			while(true){
				try {
					K key = delayQueue.take().getConfigID();
					map.remove(key);
				} catch (InterruptedException e) {
					logger.info( e.getMessage() );
				}
			}
		}
		
		public void enqueue(K key){
			enqueue(key, default_time_to_live );
		}
		
		public void enqueue(K key, long TTL){
			KillConfig kill = new KillConfig();
			kill.setConfigID(key);
			kill.setTimeToLive_ms(TTL);
			delayQueue.add(kill);
		}
	}
	
	public UpdatingMap(){
		
		// start thread which removes configs once they have expired
		// TODO: set the following threads as daemons from the context listner
		cleanser = new CacheCleanser();
		ConfigRetirerThread = new Thread(cleanser);
		ConfigRetirerThread.setName("CacheCleanser");
		ConfigRetirerThread.setDaemon(true);
		ConfigRetirerThread.start();
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object arg0) {
		return map.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		return map.containsValue(arg0);
	}

	@Override
	public Set<Entry <K, V>> entrySet() {
		return map.entrySet();
	}

	@Override
	public V get(Object arg0) {
		return map.get(arg0);
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public V put(K key, V value) {
		cleanser.enqueue(key);
		return map.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		
		for(Entry<? extends K, ? extends V> entry : arg0.entrySet()){
			cleanser.enqueue(entry.getKey());
			map.put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public V remove(Object arg0) {
		return map.remove(arg0);
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Collection<V> values() {
		return map.values();
	}
}
