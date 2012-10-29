package edu.palermo.intereses;

import android.content.Context;
import android.widget.Toast;

public class GuiUtils {

	public static void mostrarToast(Context context, int idString, String extra) {
		String msg = context.getString(idString);
		Toast toast = Toast.makeText(context, msg + ( extra != null ? "\n" + extra : ""),
				Toast.LENGTH_SHORT);
		toast.show();
	}

	public static void mostrarToast(Context context, String msg) {
		Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		toast.show();	
	}
	
}

