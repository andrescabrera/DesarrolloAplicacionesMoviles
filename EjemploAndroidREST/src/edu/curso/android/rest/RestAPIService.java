package edu.curso.android.rest;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RestAPIService {

	private String server;
	

	public CategoriaProducto buscarCategoriaProductoPorId(Long id) {
        Gson gson = new Gson();
        InputStream source = this.retrieveGetStream(server + "/categorias/ver/" + id + ".html");        
        Reader reader = new InputStreamReader(source);
        CategoriaProducto categoriaProducto = gson.fromJson(reader, CategoriaProducto.class);
        Log.d(this.getClass().getName(), "Id: " + categoriaProducto.getId());	
        Log.d(this.getClass().getName(), "Nombre: " + categoriaProducto.getNombre());
		return categoriaProducto;
		
	}
	
	
	public Long guardarNuevaCategoriaProducto(CategoriaProducto categoriaProducto) {
        Gson gson = new Gson();
        
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("nombre", categoriaProducto.getNombre()));
      
        InputStream source = this.retrievePostStream(server + "/categorias/guardar.html", nameValuePairs);        
        Reader reader = new InputStreamReader(source);
        Long id = gson.fromJson(reader, Long.class);
        categoriaProducto.setId(id);
        
        Log.d(this.getClass().getName(), "Id: " + categoriaProducto.getId());	
        Log.d(this.getClass().getName(), "Nombre: " + categoriaProducto.getNombre());
        
		return id;
		
	}
	
	
	public List<CategoriaProducto> recuperarCategorias() {
        Gson gson = new Gson();
        InputStream source = this.retrieveGetStream(server + "/categorias/listar.html");        
        Reader reader = new InputStreamReader(source);
        
        
    	Type listType = new TypeToken<ArrayList<CategoriaProducto>>() {}.getType();

    	ArrayList<CategoriaProducto> categorias = gson.fromJson(reader, listType);
    	
    	for (CategoriaProducto c : categorias) {
    		 Log.d(this.getClass().getName(), "Id: " + c.getId());	
    	     Log.d(this.getClass().getName(), "Nombre: " + c.getNombre());
		}
       
		return categorias;
		
	}
	
	  private InputStream retrievePostStream(String url, List<NameValuePair> nameValuePairs ) {
	        
	        DefaultHttpClient client = new DefaultHttpClient(); 
	        
	        HttpPost postRequest = new HttpPost(url);
	          
	        try {
	        
	        	postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    	
	           HttpResponse getResponse = client.execute(postRequest);
	           final int statusCode = getResponse.getStatusLine().getStatusCode();
	           
	           if (statusCode != HttpStatus.SC_OK) { 
	              Log.w(getClass().getSimpleName(), 
	                  "Error " + statusCode + " for URL " + url); 
	              return null;
	           }

	           HttpEntity getResponseEntity = getResponse.getEntity();
	           return getResponseEntity.getContent();
	           
	        } 
	        catch (IOException e) {
	        	postRequest.abort();
	           Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
	        }
	        
	        return null;
	        
	     }
	
    private InputStream retrieveGetStream(String url) {
        
        DefaultHttpClient client = new DefaultHttpClient(); 
        
        HttpGet getRequest = new HttpGet(url);
          
        try {
        
           HttpResponse getResponse = client.execute(getRequest);
           final int statusCode = getResponse.getStatusLine().getStatusCode();
           
           if (statusCode != HttpStatus.SC_OK) { 
              Log.w(getClass().getSimpleName(), 
                  "Error " + statusCode + " for URL " + url); 
              return null;
           }

           HttpEntity getResponseEntity = getResponse.getEntity();
           return getResponseEntity.getContent();
           
        } 
        catch (IOException e) {
           getRequest.abort();
           Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }
        
        return null;
        
     }


	public void setServer(String server) {
		this.server = "http://" + server +"/EjemploSpringMVC";
	}
}
