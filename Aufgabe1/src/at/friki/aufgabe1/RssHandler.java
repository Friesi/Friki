package at.friki.aufgabe1;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RssHandler extends Handler {
	
	private Activity activity;
	private ListFragment fragment;
	
	public RssHandler(Activity activity, ListFragment fragment) {
		this.activity = activity;
		this.fragment = fragment;
	}
	
	@Override
	public void handleMessage(Message msg) {
		
		//String result = msg.getData().getString(activity.getResources().getString(R.string.RssReturnValue)); 
		//display.setText(result);
		
		ArrayList<String> result = msg.getData().getStringArrayList(activity.getResources().getString(R.string.RssReturnValue)); 
		
		fragment.setListAdapter(new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, result));  
	}
}
