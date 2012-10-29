package edu.curso.java.android;


import java.util.ArrayList;
import android.app.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class ListadoActivity extends Activity {

	private ListView listViewClientes;
	private ArrayAdapter<Cliente> clientesAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listado);
		listViewClientes = (ListView) findViewById(R.id.ListViewClientes);


		listViewClientes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Cliente cliente = (Cliente) adapterView.getItemAtPosition(position);
				mostrarDatosCliente(cliente);
			}
		});
		
		cargarListadoClientes();

	}
	
	private void mostrarDatosCliente(Cliente cliente) {
		Toast toast = Toast.makeText(this,cliente.getId() + " " +  cliente.getNombre(), Toast.LENGTH_SHORT);
		toast.show();
	}

	private void cargarListadoClientes() {
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			clientes.add(new Cliente(1, "Juan", "Perez"));
			clientes.add(new Cliente(2, "Maria", "Lopez"));
			
			clientesAdapter = new ArrayAdapter<Cliente>(this,
					android.R.layout.simple_list_item_1, clientes);
			listViewClientes.setAdapter(clientesAdapter);
		
	}
	
	
}
