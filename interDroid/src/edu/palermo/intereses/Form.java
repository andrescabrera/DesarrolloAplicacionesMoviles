package edu.palermo.intereses;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import edu.palermo.intereses.dao.DatabaseHelper;
import edu.palermo.intereses.dao.Interes;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Form extends Activity {
	private static final String TAG = "Principal";
	private EditText editTextNombreFormInteres;
	private Interes interes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_interes);
		interes = null;
		editTextNombreFormInteres = (EditText) super.findViewById(R.id.editTextNombreInteres);
		Bundle bundle = this.getIntent().getExtras();
		if(bundle != null && bundle.containsKey("interes")){
			interes = (Interes) bundle.getSerializable("interes");
		}
		if(interes != null) {
			editTextNombreFormInteres.setText(interes.getNombre());
		}
	}
	
	public void guardarInteres(View button) {
		OrmLiteSqliteOpenHelper helper = null;
		try {
			helper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
			Dao<Interes, Integer> interesDao = helper.getDao(Interes.class);
			if(interes == null)
				interes = new Interes();
			interes.setNombre(editTextNombreFormInteres.getText().toString());
			if(interes.getId() == null)
				interesDao.create(interes);
			else
				interesDao.update(interes);
			GuiUtils.mostrarToast(this, R.string.operacion_ok,
					"ID: " + interes.getId());
		} catch (Exception e) {
			GuiUtils.mostrarToast(this, R.string.operacion_error, null);
			Log.e(TAG, "Error al guardar un interes", e);
		} finally {
			try {
				OpenHelperManager.releaseHelper();
			} catch (Exception e2) {
			}
		}
		
		Intent result = new Intent();
		this.setResult(Activity.RESULT_OK, result);
		this.finish();
	}
}

