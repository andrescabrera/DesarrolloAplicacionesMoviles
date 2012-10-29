		SharedPreferences settings = getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("server", editTextServer.getText().toString());
		editor.commit();


package edu.curso.android.rest;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends Activity {

	private static final String TAG = "Principal";
	private static final String PREFS_NAME = "PreferenciasREST";
	private RestAPIService restAPIService = new RestAPIService();
	private Button buttonBuscarPorId;
	private Button buttonGuardar;
	private EditText editTextIdBuscar;
	private EditText editTextNombre;
	private EditText editTextServer;
	private TextView textViewNombre;
	private TextView textViewId;
	private ProgressDialog progressDialog;
	private CategoriaProducto categoriaProducto;
	private Spinner spinnerCategorias;

	@Override
	protected void onStop() {
		super.onStop();
		SharedPreferences settings = getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("server", editTextServer.getText().toString());
		editor.commit();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		buttonBuscarPorId = (Button) findViewById(R.id.buttonBuscarPorId);

		buttonGuardar = (Button) findViewById(R.id.buttonGuardar);
		editTextIdBuscar = (EditText) findViewById(R.id.editTextIdBuscar);
		editTextNombre = (EditText) findViewById(R.id.editTextNombre);
		editTextServer = (EditText) findViewById(R.id.editTextServer);
		textViewNombre = (TextView) findViewById(R.id.textViewNombre);
		textViewId = (TextView) findViewById(R.id.textViewId);
		spinnerCategorias = (Spinner) findViewById(R.id.spinnerCategorias);

		SharedPreferences prefs = getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		String server = prefs.getString("usuario", "Sin Definir");
		editTextServer.setText(server);
		restAPIService.setServer(server);

		editTextServer.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// XXX do something
			}

			@Override
			public void afterTextChanged(Editable s) {
				restAPIService.setServer(editTextServer.getText().toString());

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}
		});

		buttonBuscarPorId.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				visualizarDatos();
			}
		});

		buttonGuardar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				guardarDatos();
			}
		});

		// final int NOTIF_ID = 1234;
		//
		// NotificationManager notifManager = (NotificationManager)
		// getSystemService(NOTIFICATION_SERVICE);
		// Notification note = new Notification(R.drawable.icon, "Saludos",
		// System.currentTimeMillis());
		//
		// PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(
		// this, Principal.class), 0);
		//
		// note.setLatestEventInfo(this, "New E-mail",
		// "You have one unread message.", intent);
		//
		// notifManager.notify(NOTIF_ID, note);
		// notifManager.cancel(NOTIF_ID);

	}

	private void cargarSpinner() {
		List<CategoriaProducto> categorias = restAPIService
				.recuperarCategorias();

		ArrayAdapter<CategoriaProducto> adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, categorias);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerCategorias.setFocusableInTouchMode(true);

		spinnerCategorias.setAdapter(adapter);
	}

	private void guardarDatos() {
		categoriaProducto = null;

		progressDialog = ProgressDialog.show(this, "Trabajando..",
				"Recuperando datos", true, false);

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					categoriaProducto = new CategoriaProducto();
					categoriaProducto.setNombre(editTextNombre.getText()
							.toString());
					Long id = restAPIService
							.guardarNuevaCategoriaProducto(categoriaProducto);
				

				
					handlerGeneral.sendEmptyMessage(RESULT_OK);
				} catch (Exception e) {
					Log.e(TAG,
							"Error en la llamada JSON", e);
					handlerGeneral.sendEmptyMessage(RESULT_CANCELED);
				}

			}
		});
		thread.start();
	}

	private void visualizarDatos() {
		categoriaProducto = null;

		progressDialog = ProgressDialog.show(this, "Trabajando..",
				"Recuperando datos", true, false);

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					long id = Long.parseLong(editTextIdBuscar.getText()
							.toString());
					categoriaProducto = restAPIService
							.buscarCategoriaProductoPorId(id);

					handlerGeneral.sendEmptyMessage(RESULT_OK);
				} catch (Exception e) {
					Log.e(TAG,"Error en la llamada JSON", e);
					handlerGeneral.sendEmptyMessage(RESULT_CANCELED);
				}

			}
		});
		thread.start();
	}

	private void visualizarAlert(String titulo, String msg) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false).setTitle(titulo)
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		AlertDialog alert = builder.create();
		alert.show();
	}

	private Handler handlerGeneral = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			if (msg.what == RESULT_OK) {
				if (categoriaProducto != null) {
					textViewId.setText(categoriaProducto.getId().toString());
					textViewNombre.setText(categoriaProducto.getNombre());
				}
			} else if (msg.what == RESULT_CANCELED) {
				visualizarAlert("Error", "Error en la llamada JSON");
			}

			progressDialog.dismiss();

		}
	};

}