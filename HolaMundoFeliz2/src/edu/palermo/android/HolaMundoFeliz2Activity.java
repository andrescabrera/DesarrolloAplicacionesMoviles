package edu.palermo.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HolaMundoFeliz2Activity extends Activity {
	private static final String TAG = "ActivityPrincipal";
	private TextView textViewSaludo;
	private EditText editTextSaludo;
	private Button botonSaludar;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Log.i(this, "Cargando la app");
        Log.i(TAG, "Cargando la app");
        //Log.e(tag, msg);
        //this.getClass().getName();
        textViewSaludo = (TextView) findViewById(R.id.textViewHolaMundo);
        editTextSaludo = (EditText) findViewById(R.id.editTextMiTexto);
        botonSaludar = (Button) findViewById(R.id.botonSaludar);
        
        textViewSaludo.setText("Hola a todo");
        editTextSaludo.setText("Soy un EditText");  
        
        botonSaludar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View button) {
				// TODO Auto-generated method stub
				saludar();
			}
		});
    }
    
    public void saludar() {
    	//String texto = editTextSaludo.getText().toString();
    	//textViewSaludo.setText(texto);
    	Intent intent = new Intent(this, NuevaActivity.class);
    	intent.putExtra("saludo",editTextSaludo.getText().toString());
    	startActivity(intent);
    }
}