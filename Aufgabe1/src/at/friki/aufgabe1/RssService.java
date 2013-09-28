package at.friki.aufgabe1;

import java.net.URL;
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
		Messenger messenger=(Messenger) intent.getExtras().get(getResources().getString(R.string.RssHandler)); 
		String rssName = intent.getStringExtra(getResources().getString(R.string.RssName));
		
		RssSaxFeedParser rss = new RssSaxFeedParser(rssName);
        List<RssItem> items = new ArrayList<RssItem>();
        
        items = rss.parse();	// Kompletten RSS Feed parsen
        
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<String> links = new ArrayList<String>();

        for(RssItem item: items) {
        	titles.add(item.getTitle());
        	links.add(item.getLink().toString());
        }        
        
        // Ergebniss per Handle and Activity/Fragment senden
        Message msg = Message.obtain();
        Bundle data = new Bundle();
        data.putStringArrayList(getResources().getString(R.string.RssTitleValue), titles);
        data.putStringArrayList(getResources().getString(R.string.RssLinkValue), links);
        msg.setData(data);
       
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
	}
}
