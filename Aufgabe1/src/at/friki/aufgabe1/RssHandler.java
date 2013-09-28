package at.friki.aufgabe1;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class RssHandler extends Handler {
	
	private TextView display;
	
	public RssHandler(Activity activity, int textId) {
		display = (TextView) activity.findViewById(textId);;
	}
	
	@Override
	public void handleMessage(Message msg) {
		display.setText(String.valueOf(msg.obj));
	}
}
