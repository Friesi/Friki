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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentMyRss extends ListFragment {
	
	String[] elements ={
            "MyListenelement 1",
            "MyListenelement 2",
            "derStandard.at",

    };
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // TODO: Eigene vorhandene RSS-Feeds vom internen Speicher auslesen und als "elements" einfügen/anzeigen
        
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, elements));
    }
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
		getActivity().setTitle(getResources().getStringArray(R.array.left_menu)[1]);
    	
		/*
    	FragmentManager man = getFragmentManager();
        FragmentTransaction trans = man.beginTransaction();
        
        trans.replace(R.id.main_activity_container, new FragmentPostings());
        trans.addToBackStack(null);
        trans.commit();	*/
        
        
        Fragment fragment = new FragmentPostings();
        
        Bundle args = new Bundle();
        args.putString(getResources().getString(R.string.RssName), elements[position]);
		args.putString(getResources().getString(R.string.RssAdress), "http://derStandard.at/?page=rss&ressort=Webstandard");	// TODO: Diese fixe Adresse muss ausgetauscht werden durch die, auf die im Fragment geklickt wurde
		fragment.setArguments(args);
		
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
		               .replace(R.id.main_activity_container, fragment)
		               .addToBackStack(null)
		               .commit();
	}
	
	@Override
    public void onResume() {
        super.onResume();
        // Set title
        getActivity().getActionBar().setTitle(R.string.titleFragmentMyRss);
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