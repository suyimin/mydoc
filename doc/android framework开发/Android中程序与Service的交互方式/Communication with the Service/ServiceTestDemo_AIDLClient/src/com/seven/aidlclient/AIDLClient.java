package com.seven.aidlclient;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.seven.aidlserver.ICountService;

public class AIDLClient extends Activity {
    private static final String TAG = "MYTAG";
    private static final int TIME = 1;
    private Button startBtn = null;
    private Button stopBtn = null;
    private TextView mTextView = null;
    private ProgressBar mProgressBar = null;
    private boolean mIsBind;
    private Intent intent;
    private Timer mTimer = null;
    private ICountService iCountService = null;
    
    Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			try {
				int count =  iCountService.getCount();
				mTextView.setText(count+"%");
				mProgressBar.setProgress(count);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTimer = new Timer();
        intent = new Intent("com.seven.aidlserver");
        mTextView = (TextView)findViewById(R.id.loading_Tv);
        mProgressBar = (ProgressBar)findViewById(R.id.myProgressBar);
        mProgressBar.setMax(100);
        startBtn = (Button)findViewById(R.id.start_Btn);
        stopBtn = (Button)findViewById(R.id.stop_Btn);
        startBtn.setOnClickListener(new ButtonClickListener());
        stopBtn.setOnClickListener(new ButtonClickListener());
    }
    
    @Override
	protected void onDestroy() {
		super.onDestroy();
		if(mTimer!=null){
			mTimer.cancel();
			mTimer = null;
		}
	}

	private ServiceConnection serConn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			iCountService = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(TAG, "AIDLClient.onServiceConnected()...");
			iCountService = ICountService.Stub.asInterface(service);
		}
	};
    
    class ButtonClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(startBtn==v){
				Log.i(TAG, "start button click.");
				mIsBind = bindService(intent, serConn, BIND_AUTO_CREATE);
				mTimer.schedule(new MyTimerTask(), 1000 ,TIME * 1000);
			}else if (stopBtn==v) {
				if(mIsBind){
					unbindService(serConn);
				}
			}
		}
    	
    }
    
    class MyTimerTask extends TimerTask{
		@Override
		public void run() {
			mHandler.sendMessage(mHandler.obtainMessage());
		}
    }
}