package at.friki.aufgabe1;

/**
 * Created by Chris on 26.09.13.
 */
import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Messenger;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentMyRss extends ListFragment {

	private RssHandler rssHandler;
	
	String[] elements ={
            "MyListenelement 1",
            "MyListenelement 2",
            "MyListenelement 3",

    };
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // TODO: Eigene vorhandene RSS-Feeds vom internen Speicher auslesen und als "elements" einfügen/anzeigen
        
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, elements));
        
        rssHandler = new RssHandler(getActivity(), this);
    }
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
		
		ArrayList<String> locUrls = rssHandler.getUrls();
		
		if (locUrls.isEmpty()) {
	        Intent intent = new Intent(getActivity(), RssService.class); 
	        intent.putExtra(getResources().getString(R.string.RssName), "http://derStandard.at/?page=rss&ressort=Webstandard");		// TODO: Diese fixe Adresse muss ausgetauscht werden durch die, auf die im Fragment geklickt wurde
	        intent.putExtra(getResources().getString(R.string.RssHandler), new Messenger(this.rssHandler));
	        
	        getActivity().startService(intent);
		}
		else {
			if (!locUrls.get(position).startsWith("http://") && !locUrls.get(position).startsWith("https://"))
				locUrls.set(position, "http://" + locUrls.get(position));
			
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(locUrls.get(position)));
			startActivity(browserIntent);
		}
			
	}
	
	/*
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_my_rss, container, false);
    }*/
	
	
	
	// TODO: Brauchen wir das Unbind???
	
	/*
	@Override
    public void onPause() {
    	super.onPause();
    	unbindService()
    }*/
    

}