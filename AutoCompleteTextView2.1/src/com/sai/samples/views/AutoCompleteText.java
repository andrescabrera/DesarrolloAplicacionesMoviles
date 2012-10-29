package com.sai.samples.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteText extends Activity {
   
	String[] androidBooks = 
	{
		"Hello, Android - Ed Burnette",
		"Professional Android 2 App Dev - Reto Meier",
		"Unlocking Android - Frank Ableson",
		"Android App Development - Blake Meike",
		"Pro Android 2 - Dave MacLean",
		"Beginning Android 2 - Mark Murphy",
		"Android Programming Tutorials - Mark Murphy",
		"Android Wireless App Development - Lauren Darcey",
		"Pro Android Games - Vladimir Silva",
	};
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,androidBooks);
        AutoCompleteTextView acTextView = (AutoCompleteTextView)findViewById(R.id.AndroidBooks);
        acTextView.setThreshold(3);
        acTextView.setAdapter(adapter);
        
    }
}