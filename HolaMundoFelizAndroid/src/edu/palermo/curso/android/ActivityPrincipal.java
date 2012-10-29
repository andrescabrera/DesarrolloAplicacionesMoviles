package edu.palermo.curso.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ActivityPrincipal extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        EditText editTextSaludo = (EditText) findViewById(R.id.textView1);
        editTextSaludo.setText("Hola Mundo");
    }
}