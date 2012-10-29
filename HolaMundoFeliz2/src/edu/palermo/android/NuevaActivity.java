package edu.palermo.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NuevaActivity extends Activity {
	private TextView textViewSaludo2;
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		Bundle datos = this.getIntent().getExtras();
		
		textViewSaludo2 = (TextView)findViewById(R.id.textViewSaludo2);
		textViewSaludo2.setText(datos.getString("saludo"));
	}
	
}
