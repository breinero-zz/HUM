package com.bryanreinero.hum.treeStore;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ConfigKiller implements Runnable {

	private static ConfigKiller _instance = null;
	private static final long default_time_to_live = 86400000l;
	private static DelayQueue<KillConfig> delayQueue = null;
	
	private class KillConfig implements Delayed {
		private long timeToLive_ms = default_time_to_live;
		private long inceptionDate_ms = System.currentTimeMillis();
		private String configID;
		
		public String getConfigID() {
			return configID;
		}

		public void setConfigID(String configID) {
			this.configID = configID;
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

	public synchronized static ConfigKiller getInstance(){
		if(_instance == null)
			_instance = new ConfigKiller();
		
		return _instance;
	}
	
	private ConfigKiller(){
		delayQueue = new DelayQueue<KillConfig>();
	}
	
	@Override
	public void run() {
		while(true){
			try {
				String configID = delayQueue.take().getConfigID();
				TreeStore.getInstance().flushConfig(configID);
			} catch (InterruptedException e) {
				// TODO log the interruption
				e.printStackTrace();
			}
		}
	}
	
	public void enqueue(String configID){
		KillConfig kill = new KillConfig();
		kill.setConfigID(configID);
		delayQueue.add(kill);
	}
	
	public void enqueue(String configID, long TTL){
		KillConfig kill = new KillConfig();
		kill.setConfigID(configID);
		kill.setTimeToLive_ms(TTL);
		delayQueue.add(kill);
	}
}
