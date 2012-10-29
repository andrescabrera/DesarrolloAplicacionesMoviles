package edu.palermo.intereses;

import java.util.List;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;

import edu.palermo.intereses.dao.DatabaseHelper;
import edu.palermo.intereses.dao.Interes;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Listado extends Activity {
	
	private static final String TAG = "Listado";
	private static final int REQUEST_CODE_FORM_INTERES = 1;
	private ListView listViewIntereses;
	private ArrayAdapter<Interes> interesesAdapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado);
        listViewIntereses = (ListView) findViewById(R.id.ListViewIntereses);
        registerForContextMenu(listViewIntereses);
        listViewIntereses.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Interes interes = (Interes) adapterView
						.getItemAtPosition(position);
				mostrarDatosInteres(interes);
			}
		});

		cargarListadoIntereses();

    }

	private void cargarListadoIntereses() {
		OrmLiteSqliteOpenHelper helper = null;
		try {
			helper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
			Dao<Interes, Integer> interesDao = helper.getDao(Interes.class);
			List<Interes> intereses = interesDao.queryForAll();
			interesesAdapter = new ArrayAdapter<Interes>(this,
					android.R.layout.simple_list_item_1, intereses);
			listViewIntereses.setAdapter(interesesAdapter);	
		} catch (Exception e) {
			Log.e(TAG, "Error al cargar los clientes", e);
			GuiUtils.mostrarToast(this, R.string.operacion_error, null);
		} finally {
			try {
				OpenHelperManager.releaseHelper();
			} catch (Exception e2) {
				Log.e(TAG, "Error en el release", e2);
			}
		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_general, menu);
		return true;
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.ListViewIntereses) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.context_menu_intereses, menu);
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.ItemMenuAgregar:
			Intent intent = new Intent(this, Form.class);
			this.startActivityForResult(intent, REQUEST_CODE_FORM_INTERES);
			
			return true;

		case R.id.ItemMenuSalir:
			this.finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public boolean onContextItemSelected(MenuItem item) {
		
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();

		Interes interes = interesesAdapter.getItem(info.position);

		switch (menuItemIndex) {
		case R.id.ItemMenuBorrar:
			OrmLiteSqliteOpenHelper helper = null;
			try {
				helper = OpenHelperManager
						.getHelper(this, DatabaseHelper.class);
				Dao<Interes, Integer> interesesDao = helper.getDao(Interes.class);
				interesesDao.delete(interes);
				interesesAdapter.remove(interes);
			} catch (Exception e) {
				Log.e(TAG, "Error al listar los intereses", e);
				GuiUtils.mostrarToast(this, R.string.operacion_error, null);
			} finally {
				try {
					OpenHelperManager.releaseHelper();
				} catch (Exception e2) {
				}

			}

			return true;
		case R.id.ItemMenuEditar:
			mostrarDatosInteres(interes);
			return true;
		default:
			return super.onContextItemSelected(item);
		}

	}

	private void mostrarDatosInteres(Interes interes) {
		Intent intent = new Intent(this, Form.class);
        intent.putExtra("interes", interes);
		this.startActivityForResult(intent, REQUEST_CODE_FORM_INTERES);		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==  REQUEST_CODE_FORM_INTERES && resultCode == RESULT_OK){
        	cargarListadoIntereses(); 
        }
    }
	
}