package at.friki.aufgabe1;

/**
 * Created by Chris on 26.09.13.
 */
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

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
        ListAdapter myListAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                elements);
        setListAdapter(myListAdapter);
        
        
        rssHandler = new RssHandler(getActivity(), R.id.detailfragmenttext);
        
        
        Intent intent = new Intent(getActivity(), RssService.class); 
        intent.putExtra(getResources().getString(R.string.RssName), "http://derStandard.at/?page=rss&ressort=Webstandard");		// TODO: Diese fixe Adresse muss ausgetauscht werden durch die, auf die im Fragment geklickt wurde
        intent.putExtra(getResources().getString(R.string.RssHandler), new Messenger(rssHandler));
        
        getActivity().startService(intent);

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