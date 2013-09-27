package at.friki.aufgabe1;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Mathias on 24.09.13.
 */
public class SubscribeToRSS extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root,
                             Bundle bundle) {
        View v = inflater.inflate(R.layout.subscribetorss, root, false);
        //setListAdapter(dummy);
        return v;
    }

    // listitem clicks are observed using this method 
    @Override
    public void onListItemClick(ListView listView, View view, int position, long id)
    {
        // ArrayAdapter: returns the item at the click position CursorAdapter:
        // jumps to position in cursor (default) we will learn how to return an
        // item from this call though ;)
        getListAdapter().getItem(position);
    }
}
