package at.friki.aufgabe1;

/**
 * Created by Chris on 26.09.13.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
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

public class FragmentSubscribe extends Fragment {

	public static final String BROADCAST_FRAGMENT_SUBSCRIBE_CLICK = "BROADCAST_FRAGMENT_SUBSCRIBE_CLICK";
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.subscribe_layout, container, false);
      return view;
    }
          

	//AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	//builder.setMessage("RSS Abonnieren?").setPositiveButton("Ja", dialogClickListener).setNegativeButton("Nein", dialogClickListener).show();		//Dialog zeigen
    	
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
            case DialogInterface.BUTTON_POSITIVE:													//wenn Dialogantwort JA
            	
            	
            	 /** BroadcastManager erzeugt neuen Broadcast */
            	
            	
            	Intent subscribeintent = new Intent(BROADCAST_FRAGMENT_SUBSCRIBE_CLICK);
            	LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(subscribeintent);
            	
            
                //((MainActivity) getActivity()).changeHighlight();									// Highlight Fehler beheben --> 2ter Menüpunkt wird hervorgehoben, ALTE METHODE

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