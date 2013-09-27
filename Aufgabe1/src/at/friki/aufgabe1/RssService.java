package at.friki.aufgabe1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

public class RssService extends Service {
	//private static final Logger LOGGER = LoggerFactory.getLogger(RssService.class);
	public static final String INTENT_EXTRA_ITERATIONS = "INTENT_EXTRA_ITERATIONS";
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) { 
		Thread t = new Thread() { 
			
			@Override 
			public void run() { 
				SaxRssFeedParser rss = new SaxRssFeedParser("http://derStandard.at/?page=rss&ressort=Webstandard");
		        List<RssItem> items = new ArrayList<RssItem>();
		        
		        items = rss.parse();
		        
		        //TextView tv = (TextView) this.findViewById(R.id.textView);
		        //tv.setText("");
		        
		        String tv = "";
		
		        for(RssItem item: items) {
		            //tv.append(item.getTitle());
		        	tv += item.getTitle();
		        }
				
		        Toast toast = Toast.makeText(getApplicationContext(), tv, Toast.LENGTH_LONG);
		        toast.show();
				
				
				
				//int c = intent.getIntExtra(INTENT_EXTRA_ITERATIONS, -1); 
				//iterate(c); 
				//return START_NOT_STICKY; 
		        stopSelf();
				}
		        	 
				}; 
				
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
	}

