package at.friki.aufgabe1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
	
	//es funnktioniert!!!!
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void btnStart(View v) {

        SaxRssFeedParser rss = new SaxRssFeedParser("http://derStandard.at/?page=rss&ressort=Webstandard");
        List<RssItem> items = new ArrayList<RssItem>();

        items = rss.parse();

        TextView tv = (TextView) this.findViewById(R.id.textView);
        tv.setText("");

        for(RssItem item: items) {
            tv.append(item.getTitle());
        }
    }
}
