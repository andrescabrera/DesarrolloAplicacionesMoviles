package edu.palermo.edu.android.app2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidApp2Activity extends Activity {
	
	private static final int LED_ID = 123456;
	private static final String TAG= "ActivityEjemplo";
	private Button buttonSaludar;
	private TextView textViewResultado;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Vibrator unVibrador = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE); 
        unVibrador.vibrate(1000);
        NotificationManager unNotManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification unaNotificacion = new Notification();
        unaNotificacion.ledARGB = 0xFF00ff00;
        unaNotificacion.ledOnMS = 100;
        unaNotificacion.ledOffMS = 100;
        unNotManager.notify(LED_ID, unaNotificacion); 
        displayNotification("jajaja");
        textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        buttonSaludar = (Button)findViewById(R.id.buttonSaludar);
        buttonSaludar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//mostrarToast();
				mostrarDialog();
			}
		});        
        // this.finish();
        
    }
    public void recuperarPropiedades() {
        SharedPreferences prefs = getSharedPreferences("MisDatos", Context.MODE_PRIVATE);
        String usuario = prefs.getString("usuario", "Sin Definir");
        textViewResultado.setText(usuario);
    }
    public void guardarPropiedades() {
        SharedPreferences settings = getSharedPreferences("MisDatos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("usuario", "Juan");
        editor.commit();
    }
    
    private void mostrarDialog() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Todo ok?").setCancelable(false).setPositiveButton("Si", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				textViewResultado.setText("SI");
			}
		}).setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				textViewResultado.setText("NO");
			}
		});
    	
    	AlertDialog alert = builder.create();
    	alert.show();
    }
    @SuppressWarnings("unused")
	private void mostrarToast() {
		Log.d(TAG, "buttonSaludar onClick");
		Toast toast1 = Toast.makeText(getApplicationContext(), "Hola a todos", Toast.LENGTH_SHORT);
		toast1.setDuration(30);
		toast1.show();
    }
    
    public void displayNotification(String msg){
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.icon, msg, System.currentTimeMillis());

        // The PendingIntent will launch activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 1234, new Intent(this, AndroidApp2Activity.class), 0);

        notification.setLatestEventInfo(this, "Te re cabio", ".. de la esquina vino en carton..", contentIntent);

        manager.notify(345345, notification);


    }
}