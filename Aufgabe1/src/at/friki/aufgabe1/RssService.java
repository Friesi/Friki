package at.friki.aufgabe1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.TextView;
import android.widget.Toast;



public class RssService extends IntentService {
	public RssService() {
		super("RssService");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		String rssName = intent.getStringExtra(getResources().getString(R.string.RssName));
		
		Messenger messenger=(Messenger) intent.getExtras().get(getResources().getString(R.string.RssHandler)); 
		
		
		RssSaxFeedParser rss = new RssSaxFeedParser(rssName);
        List<RssItem> items = new ArrayList<RssItem>();
        
        items = rss.parse();
        
        //TextView tv = (TextView) this.findViewById(R.id.textView);
        //tv.setText("");
        
        String tv = "";

        for(RssItem item: items) {
            //tv.append(item.getTitle());
        	tv = item.getTitle();
        }
		
        Toast toast = Toast.makeText(getApplicationContext(), tv, Toast.LENGTH_LONG);
        toast.show();
        
        
        
        Message msg = Message.obtain();
        Bundle data = new Bundle();
        data.putString(getResources().getString(R.string.RssReturnValue), "value" + System.currentTimeMillis() );
        msg.setData(data);
       
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
	}
}








/*

public class RssService extends Service {
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) { 
		
		final String rssName = intent.getStringExtra(getResources().getString(R.string.RssName));
		
		Thread t = new Thread(new Runnable() { 
			
			String name = rssName;
			
			@Override 
			public void run() { 
				RssSaxFeedParser rss = new RssSaxFeedParser(name);
		        List<RssItem> items = new ArrayList<RssItem>();
		        
		        items = rss.parse();
		        
		        //TextView tv = (TextView) this.findViewById(R.id.textView);
		        //tv.setText("");
		        
		        String tv = "";
		
		        for(RssItem item: items) {
		            //tv.append(item.getTitle());
		        	tv = item.getTitle();
		        }
				
		        Toast toast = Toast.makeText(getApplicationContext(), tv, Toast.LENGTH_LONG);
		        toast.show();
				/*
		        handler.post(new Runnable() {
		        	@Override
		        	public void run() {
		        	progressBar.setProgress(value);
		        	 }
		        	});//*
				
				//int c = intent.getIntExtra(INTENT_EXTRA_ITERATIONS, -1); 
				//iterate(c); 
				//return START_NOT_STICKY; 
		        stopSelf();
			}
		});
			
		t.start(); 
		return START_NOT_STICKY;
	} 
	
	public void iterate(final int c) { 
		Thread t = new Thread() { 
	
			@Override 
			public void run() { 
				//LOGGER.error("ITERATING: "+ c); 
				
				
		        
				stopSelf(); 
				} 
			}; 
			t.start(); 
			} 
	}*/

