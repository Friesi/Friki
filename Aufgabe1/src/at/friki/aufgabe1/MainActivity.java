package at.friki.aufgabe1;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
	
	private Handler handler;
	
	private String[] leftMenuTitles;
	private DrawerLayout drawerLayout;
	private ListView drawerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Left Slide Menu
        leftMenuTitles = getResources().getStringArray(R.array.left_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_menu);

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.left_menu, leftMenuTitles));	// Set the adapter for the list view
        drawerList.setOnItemClickListener(new DrawerItemClickListener());							// Set the list's click listener
        
        
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
        			   //.addToBackStack(null)
                       .replace(R.id.main_activity_container, new FragmentSubscribe())
                       .commit();
        
        handler = new Handler();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }


    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
    	
    	Fragment fragment;
    	
    	switch(position) {
	        case 0:
	        	fragment = new FragmentSubscribe();
	            break;
	        case 1:
	        	fragment = new FragmentMyRss();
	            break;
	        default:
	        	fragment = new FragmentSubscribe();
	    }
    	
        //Bundle args = new Bundle();
        //args.putInt(MyListFragment1.ARG_PLANET_NUMBER, position);
        //fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                       .replace(R.id.main_activity_container, fragment)
                       .commit();

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        setTitle(leftMenuTitles[position]);
        drawerLayout.closeDrawer(drawerList);
    }


    public void btnStart(View v) {
        startService(new Intent(this, RssService.class));
    }
}
