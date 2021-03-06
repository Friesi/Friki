package at.friki.aufgabe1;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	private String[] leftMenuTitles;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence drawerTitle;
    private CharSequence title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        title = drawerTitle = getTitle();
        
        // Erzeuge Left Slide Menu
        leftMenuTitles = getResources().getStringArray(R.array.left_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_menu);

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.left_menu, leftMenuTitles));	// Set the adapter for the list view
        drawerList.setOnItemClickListener(new DrawerItemClickListener());							// Set the list's click listener
        
        
        // �ffne FragmentSubscribe als Startscreen
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
        			   //.addToBackStack(null)
                       .replace(R.id.main_activity_container, new FragmentSubscribe())
                       .commit();
        
        /** Action Bar Zeug */
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	/** Diverse Broadcast Listener */   
        LocalBroadcastManager.getInstance(this).registerReceiver(SubscribeReceiver,	
      	      new IntentFilter(FragmentSubscribe.BROADCAST_FRAGMENT_SUBSCRIBE_CLICK));
        
        LocalBroadcastManager.getInstance(this).registerReceiver(PostReceiver,
        	      new IntentFilter(FragmentMyRss.BROADCAST_FRAGMENT_MYRSS_CLICK));
    }
    
    /**	Fragment Postings Broadcast abfangen und Fragment aufrufen */
    
    private BroadcastReceiver PostReceiver = new BroadcastReceiver() {
    	@Override
    	public void onReceive(Context context, Intent intent) {    		  
    		  
    		setTitle(getResources().getStringArray(R.array.left_menu)[1]);
  
    		int position = intent.getIntExtra(getString(R.string.RssListPosition), 0);
    		
			String[] elements = MyRssDataStore.getInstance().getMyRssNames(context);
			String[] urls = MyRssDataStore.getInstance().getMyRssUrls(context);
    		  
    		Fragment fragment = new FragmentPostings();
    	        
	        Bundle args = new Bundle();
	        args.putString(getResources().getString(R.string.RssName), elements[position]);
			args.putString(getResources().getString(R.string.RssAdress), urls[position]);	//http://derStandard.at/?page=rss&ressort=Webstandard
			fragment.setArguments(args);
			
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
			               .replace(R.id.main_activity_container, fragment)
			               .addToBackStack(null)
			               .commit();
    	}
  	};
    	 
    
    
  	
  	/**	Fragment Subscribe Broadcast abfangen und Fragment aufrufen */

    private BroadcastReceiver SubscribeReceiver = new BroadcastReceiver() {
    	@Override
    	public void onReceive(Context context, Intent intent) {
    		String txtSubscribeName = intent.getStringExtra(getString(R.string.txtSubscribeName));
      		String txtSubscribeUrl = intent.getStringExtra(getString(R.string.txtSubscribeUrl));
      		
      		MyRssDataStore.getInstance().saveNewRssFeed(context, txtSubscribeName, txtSubscribeUrl, true);
      		
      		setTitle(getResources().getStringArray(R.array.left_menu)[1]);
        	
        	FragmentManager man = getFragmentManager();											// Fragment Eigene Feeds anzeigen, Funktion zum Hinzuf�gen selbst FEHLT
            FragmentTransaction trans = man.beginTransaction();
            
            trans.replace(R.id.main_activity_container, new FragmentMyRss());					// Eigene Feeds anzeigen, Funktion zum Hinzuf�gen selbst FEHLT
            trans.addToBackStack(null);
            trans.commit();
            
            changeHighlight();
    	}
	};

    
    /* ClickListener f�r Nav Drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    
    /* Geklicktes ListItem des Nav Drawers ausw�hlen und anschlie�end daf�r gew�hltes Fragment aufrufen */
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
    

    /** Action Bar Zeug - nur f�r sch�nere Optik ^^ */
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}
   
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
        }
		return super.onOptionsItemSelected(item);
	}
    
    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getActionBar().setTitle(title);
    }
    
    
    public void changeHighlight(){				//nach �bergang von RSS Subscribe auf MySubscribe Highlight richtig setzen
    	drawerList.setItemChecked(1, true);
    	
    }
    
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	// Unregister since the activity is about to be closed.
    	LocalBroadcastManager.getInstance(this).unregisterReceiver(SubscribeReceiver);
      	LocalBroadcastManager.getInstance(this).unregisterReceiver(PostReceiver);
    }

}




