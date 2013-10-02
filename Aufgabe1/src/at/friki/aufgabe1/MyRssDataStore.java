package at.friki.aufgabe1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class MyRssDataStore {

	private static final String prefName = "at.friki.aufgabe1";
	private ArrayList<String> myRssNames;
	private ArrayList<String> myRssUrls;
	
	public MyRssDataStore() {
		myRssNames = new ArrayList<String>();
		myRssUrls = new ArrayList<String>();
	}
	
	
	public String[] getMyRssNames() {
		return (String[]) myRssNames.toArray();		// TODO: Hier tritt der Fehler auf ...
	}
	
	public String[] getMyRssUrls() {
		return (String[]) myRssUrls.toArray();		// TODO: Hier tritt der Fehler auf ...
	}
	
	public void saveNewRssFeed(Context context, String Name, String Url) {
		SharedPreferences prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
		
		int anz = prefs.getInt("Anz", 0);
		
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt("Anz", ++anz);
		editor.putString("Name" + anz, Name);
		editor.putString("Url" + anz, Url);
		
		editor.commit();
	}
	
	public void readAllRssFeeds(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
		
		int maxAnz = prefs.getInt("Anz", 0);	// Anzahl der gespeicherten Werte
 
    	myRssNames = new ArrayList<String>();
    	myRssUrls = new ArrayList<String>();
    	
    	for(int i=0; i<maxAnz; i++){										// und anschließend ins StringArray schreiben
    		myRssNames.add(prefs.getString("Name"+i,"leer"));
    		myRssUrls.add(prefs.getString("Url"+i,"leer"));
    	}
	}
	
	
}
