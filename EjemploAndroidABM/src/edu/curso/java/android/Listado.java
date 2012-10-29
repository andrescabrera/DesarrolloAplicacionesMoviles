package edu.curso.java.android;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Listado extends Activity {

	private static final String TAG = "Listado";
	private ListView listViewClientes;
	private ArrayAdapter<String> clientesAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listado);
		listViewClientes = (ListView) findViewById(R.id.ListViewClientes);


		listViewClientes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				String texto = (String) adapterView.getItemAtPosition(position);
				mostrarDatosCliente(texto);
			}
		});
		
		cargarListadoClientes();

	}
	
	private void mostrarDatosCliente(String texto) {
		Toast toast = Toast.makeText(this,texto, Toast.LENGTH_SHORT);
		toast.show();
	}

	private void cargarListadoClientes() {
			ArrayList<String> clientes = new ArrayList<String>();
			clientes.add("Juan");
			clientes.add("Maria");
			
			clientesAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, clientes);
			listViewClientes.setAdapter(clientesAdapter);
		
	}	
}