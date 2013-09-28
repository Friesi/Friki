package at.friki.aufgabe1;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class RssHandler extends Handler {
	
	private TextView display;
	private Activity activity;
	
	public RssHandler(Activity activity, int textId) {
		display = (TextView) activity.findViewById(textId);
		this.activity = activity;
	}
	
	@Override
	public void handleMessage(Message msg) {
		
		String result = msg.getData().getString(activity.getResources().getString(R.string.RssReturnValue)); 
		
		
		display.setText(result);
	}
}
