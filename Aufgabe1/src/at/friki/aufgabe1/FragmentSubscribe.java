package at.friki.aufgabe1;

/**
 * Created by Chris on 26.09.13.
 */

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentSubscribe extends Fragment implements OnClickListener {

	public static final String BROADCAST_FRAGMENT_SUBSCRIBE_CLICK = "BROADCAST_FRAGMENT_SUBSCRIBE_CLICK";
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.subscribe_layout, container, false);
      
      Button btnSave = (Button) view.findViewById(R.id.btnSubscribeSave);
      btnSave.setOnClickListener(this);
      
      return view;
    }
    
    @Override
    public void onClick(View v) {
    	//Intent subscribeintent = new Intent(BROADCAST_FRAGMENT_SUBSCRIBE_CLICK);
    	//LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(subscribeintent);
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	builder.setMessage("RSS Abonnieren?").setPositiveButton("Ja", dialogClickListener).setNegativeButton("Nein", dialogClickListener).show();		//Dialog zeigen
    }  
          

		
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
            case DialogInterface.BUTTON_POSITIVE:													//wenn Dialogantwort JA
            	
            	
            	 /** BroadcastManager erzeugt neuen Broadcast */
            	
            	
            	Intent subscribeintent = new Intent(BROADCAST_FRAGMENT_SUBSCRIBE_CLICK);
            	LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(subscribeintent);
            	
            
                //((MainActivity) getActivity()).changeHighlight();									// Highlight Fehler beheben --> 2ter Men�punkt wird hervorgehoben, ALTE METHODE

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