package at.friki.aufgabe1;

/**
 * Created by Chris on 26.09.13.
 */
import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Messenger;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentMyRss extends ListFragment {
	
	 private MyRssDataStore dataStore;
	
	  
     
	
	
	
	public static final String BROADCAST_FRAGMENT_MYRSS_CLICK = "BROADCAST_FRAGMENT_MYRSS_CLICK";
	
	/*String[] elements ={
            "MyListenelement 1",
            "MyListenelement 2",
            "derStandard.at",

    };*/
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        // MyRss-Daten Objekt anlegen
        dataStore = new MyRssDataStore();
        
        dataStore.readAllRssFeeds(getActivity());
        String[] elements = dataStore.getMyRssNames();
 
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, elements));
    }
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
		Intent postintent = new Intent(BROADCAST_FRAGMENT_MYRSS_CLICK);
		postintent.putExtra(getResources().getString(R.string.RssListPosition), position);
		LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(postintent);
	}
	
	@Override
    public void onResume() {
        super.onResume();
        // Set title
        getActivity().getActionBar().setTitle(R.string.titleFragmentMyRss);
    }
}