package com.seven.aidlserver;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AIDLServer extends Service {
	private static final String TAG = "MYTAG";
	private static final int TIME = 1;
	private Timer mTimer = null;
	private int i = 0;
	
	private AIDLServerBinder serviceBinder = new AIDLServerBinder();
	class AIDLServerBinder extends ICountService.Stub{
		@Override
		public int getCount() throws RemoteException {
			return i;
		}
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "AIDLServer.onCreate()...");
		mTimer = new Timer();
		mTimer.schedule(new MyTimerTask(), 0,TIME * 1000);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "AIDLServer.onDestroy()...");
		if(mTimer!=null){
			mTimer.cancel();
			mTimer = null;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "AIDLServer.onBind()...");
		return serviceBinder;
	}
	
	class MyTimerTask extends TimerTask{

		@Override
		public void run() {
			if(i==100){
				i=0;
			}
			i++;
		}
	}
}