package at.friki.aufgabe1;

/**
 * Created by Chris on 26.09.13.
 */
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentSubscribe extends ListFragment {

    String[] elements ={
            "Listenelement 1",
            "Listenelement 2",
            "Listenelement 3",

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListAdapter myListAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                elements);
        setListAdapter(myListAdapter);

    }
    
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subscribe, container, false);
    }*/

    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        /*String selection = l.getItemAtPosition(position).toString();

        TextView mTextView = (TextView) v.findViewById(R.id.detailfragmenttext);
        mTextView.setText(selection);*/

        FragmentManager man = getFragmentManager();
        FragmentTransaction trans = man.beginTransaction();
        
        trans.replace(R.id.main_activity_container, new FragmentMyRss());	// add
        trans.addToBackStack(null);
        trans.commit();
        //getFragmentManager().beginTransaction().add(MyListFragment2.this, "hallo");
        
      // String selection = l.getItemAtPosition(position).toString();


        //View V = inflater.inflate(R.layout.testclassfragment, container, false);
        //ImageView imageView = (ImageView)V.findViewById(R.id.my_image);

        //TextView tv = (TextView) this.findViewById(R.id.textView);
        //(TextView) getView().findViewById(R.id.detailfragmenttext);

       // TextView mTextView = (TextView) findViewById(R.id.detailfragmenttext);
        //mTextView.setText(selection);


       /* Toast.makeText(
                getActivity(),
                getListView().getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG).show();*/
    }
}