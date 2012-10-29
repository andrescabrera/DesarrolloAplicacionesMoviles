package edu.curso.up.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityEjemplo extends Activity {
	
	private static final String TAG = "ActivityEjemplo"; 
	private Button buttonSaludar;
	private TextView textViewResultado;
	private static final int LED_ID = 12345;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buttonSaludar = (Button)findViewById(R.id.buttonSaludar);
        textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        
        buttonSaludar.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View arg0)
            {
            	mostrarDialog();
            }
        });
        
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
        
        NotificationManager nm = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );
        Notification notif = new Notification();
        notif.ledARGB = Color.RED;
        notif.flags = Notification.FLAG_SHOW_LIGHTS;
        notif.ledOnMS = 100; 
        notif.ledOffMS = 100; 
        nm.notify(LED_ID, notif);
        
        displayNotification("jajajaj");
    }
    
    public void displayNotification(String msg)
    {
    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    Notification notification = new Notification(R.drawable.icon, msg, System.currentTimeMillis());

    // The PendingIntent will launch activity if the user selects this notification
    PendingIntent contentIntent = PendingIntent.getActivity(this, 1234, new Intent(this, ActivityEjemplo.class), 0);

    notification.setLatestEventInfo(this, "Title here", ".. And here's some more details..", contentIntent);

    manager.notify(345345, notification);

    }
    private void mostrarDialog() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	
		builder.setMessage("Todo ok?").setCancelable(false)
		.setPositiveButton("SI", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				
				textViewResultado.setText("SI");
			}
		})
		.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				textViewResultado.setText("NO");
			}
		});
		
		AlertDialog alert = builder.create();
		alert.show();

    }
    
    private void mostrarToast() {
    	Log.d(TAG, "buttonSaludar onClick");
    	Toast toast = Toast.makeText(getApplicationContext(), "Hola a todos: ",
    			Toast.LENGTH_SHORT);
    	toast.show();
    }
}