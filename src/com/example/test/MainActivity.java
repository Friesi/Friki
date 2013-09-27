package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

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
    
    public void btn_click(View view){
    	
	
    	EditText et = (EditText)findViewById(R.id.editText1);
    	String inputtext = et.getText().toString();
    	
    	TextView textview = (TextView) findViewById(R.id.textView2);
    	textview.setText(inputtext);
    	
    
    }
    
}
