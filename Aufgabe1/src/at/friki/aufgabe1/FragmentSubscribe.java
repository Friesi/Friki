package at.friki.aufgabe1;

/**
 * Created by Chris on 26.09.13.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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
        ListAdapter myListAdapter = new ArrayAdapter<String>(							// Liste erzeugen
                getActivity(),
                android.R.layout.simple_list_item_1,
                elements);
        setListAdapter(myListAdapter);

    }

    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {				// bei Klick auf ListItem

    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	builder.setMessage("RSS Abonnieren?").setPositiveButton("Ja", dialogClickListener).setNegativeButton("Nein", dialogClickListener).show();		//Dialog zeigen
    	
    	
    	
    	
    	
        /*String selection = l.getItemAtPosition(position).toString();

        TextView mTextView = (TextView) v.findViewById(R.id.detailfragmenttext);
        mTextView.setText(selection);*/

        
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
    
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
            case DialogInterface.BUTTON_POSITIVE:													//wenn Dialogantwort JA
            	getActivity().setTitle(getResources().getStringArray(R.array.left_menu)[1]);
            	
            	FragmentManager man = getFragmentManager();
                FragmentTransaction trans = man.beginTransaction();
                
                trans.replace(R.id.main_activity_container, new FragmentMyRss());					// Eigene Feeds anzeigen, Funktion zum Hinzufügen selbst FEHLT
                trans.addToBackStack(null);
                trans.commit();
                
                //Highlight auf Element 2 der Nav Draw setzen!!!
               //getActivity().changeHighlight();
                
                ((MainActivity) getActivity()).changeHighlight();
               
        
                
                break;

            case DialogInterface.BUTTON_NEGATIVE:													//wenn Dialogantwort NEIN
 
                break;
            }
        }
    };
    
    
    
    @Override
    public void onResume() {
        super.onResume();

        getActivity().getActionBar().setTitle(R.string.titleFragmentSubscribe);						//Titel setzen
    }
    
    
   // private DataPullingInterface mHostInterface;
    

    
    
    
}