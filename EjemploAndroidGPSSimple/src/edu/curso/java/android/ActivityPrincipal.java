package edu.curso.java.android;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityPrincipal extends Activity {
	private Button btnActualizar;
	private Button btnDesactivar;
	private TextView lblLatitud; 
	private TextView lblLongitud;
	private TextView lblPrecision;
	private TextView lblEstado;
	
	private LocationManager locationManager;
	private LocationListener locationListener;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnActualizar = (Button)findViewById(R.id.BtnActualizar);
        btnDesactivar = (Button)findViewById(R.id.BtnDesactivar);
        lblLatitud = (TextView)findViewById(R.id.LblPosLatitud);
        lblLongitud = (TextView)findViewById(R.id.LblPosLongitud);
        lblPrecision = (TextView)findViewById(R.id.LblPosPrecision);
        lblEstado = (TextView)findViewById(R.id.LblEstado);
        
        btnActualizar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				actualizarPosicion();
			}
		});
        
        btnDesactivar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		    	locationManager.removeUpdates(locationListener);
			}
		});
    }
    
    private void actualizarPosicion()
    {
    	TextToSpeechService.getInstance().speak("Buscando posición");
    	//Obtenemos una referencia al LocationManager
    	locationManager = 
    		(LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	
    	List<String> listaProviders = locationManager.getAllProviders();
    	
    	LocationProvider provider = null;
    	
    	for(String nombre : listaProviders) {
    		Log.i("ActivityPrincipal", "Provider: " + nombre);
    		provider = locationManager.getProvider(nombre);
    		
    		Log.i("ActivityPrincipal", "precision: " + provider.getAccuracy());
    		Log.i("ActivityPrincipal", "obtieneAltitud: " + provider.supportsAltitude());
			Log.i("ActivityPrincipal", "consumoRecursos: " +  provider.getPowerRequirement());
			
    	}
    	
    	
    	Criteria req = new Criteria();
    	req.setAccuracy(Criteria.ACCURACY_FINE);
    	req.setAltitudeRequired(false);
    	String bestProvider = locationManager.getBestProvider(req, false);
    	Log.i("ActivityPrincipal", "bestProvider: " +  bestProvider);
    	
    	Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    	
    	muestraPosicion(location);
    	
    	//Nos registramos para recibir actualizaciones de la posición
    	locationListener = new LocationListener() {
	    	public void onLocationChanged(Location location) {
	    		muestraPosicion(location);
	    	}
	    	public void onProviderDisabled(String provider){
	    		lblEstado.setText("Provider OFF");
	    	}
	    	public void onProviderEnabled(String provider){
	    		lblEstado.setText("Provider ON");
	    	}
	    	public void onStatusChanged(String provider, int status, Bundle extras){
	    		Log.i("ActivityPrincipal", "Provider Status: " + status);
	    		lblEstado.setText("Provider Status: " + status);
	    	}
    	};
    	
    	locationManager.requestLocationUpdates(
    			LocationManager.GPS_PROVIDER, 15000, 0, locationListener);
    }
     
    private void muestraPosicion(Location loc) {
    	if(loc != null)
    	{
    		lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
    		lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
    		lblPrecision.setText("Precision: " + String.valueOf(loc.getAccuracy()));
    		Log.i("ActivityPrincipal", String.valueOf(loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
    	}
    	else
    	{
    		lblLatitud.setText("Latitud: (sin datos)");
    		lblLongitud.setText("Longitud: (sin datos)");
    		lblPrecision.setText("Precision: (sin datos)");
    	}
    }

}