package edu.curso.java.android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class PrincipalActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void guardarCliente(View button) {
		Toast toast = Toast.makeText(this,"El cliente fue guardado", Toast.LENGTH_SHORT);
		toast.show();
    }
    
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_general, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	
		case R.id.ItemMenuSalir:
			this.finish();
			return true;
			
		case R.id.ItemMenuListado:
			Intent intent = new Intent(this, ListadoActivity.class);
			this.startActivity(intent);
			
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}