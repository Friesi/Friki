package at.friki.aufgabe1;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
	
	private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        FragmentManager man = getFragmentManager();
        FragmentTransaction trans = man.beginTransaction();
        
        trans.replace(R.id.main_activity_container, new MyListFragment1());	// add
        trans.addToBackStack(null);
        
        
        trans.commit();
        
        
        
        handler = new Handler();	// Test laptop 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void btnStart(View v) {

        

        
        startService(new Intent(this, RssService.class));
        
        
       
    }
}
