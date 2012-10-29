package edu.curso.android.rest;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class Entrada extends Activity {

	private static final String TAG = "Principal";
	private RestAPIService restAPIService = new RestAPIService();
	private AutoCompleteTextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entrada);

		restAPIService.setServer("192.168.1.100:8080");

		List<Interest> intereses = restAPIService.recuperarIntereses();
		textView = (AutoCompleteTextView) findViewById(R.id.autocompletar_intereses);
		ArrayAdapter<Interest> adapterIntereses = new ArrayAdapter<Interest>(
				this, R.layout.list_item, intereses);
		textView.setAdapter(adapterIntereses);
		textView.setThreshold(1);
		textView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Interest seleccionado = (Interest) arg0.getItemAtPosition(arg2);
				Log.i(TAG, "Interes seleccionado: " + seleccionado.getId());
				verPublicacionesDelInteres(seleccionado);
			}
		});

	}

	private void verPublicacionesDelInteres(Interest interest) {
		Intent intent = new Intent(this, ListadoPublicacionesInteres.class);
		intent.putExtra("interestId", interest.getId());
		this.startActivity(intent);
	}
}
