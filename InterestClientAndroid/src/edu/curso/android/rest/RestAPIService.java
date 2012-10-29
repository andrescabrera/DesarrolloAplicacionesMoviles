package edu.curso.android.rest;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class RestAPIService {

	private String server;

	public Interest buscarInteresPorId(Long id) {
		Gson gson = new Gson();
		InputStream source = this.retrieveGetStream(server + "/interest/" + id
				+ ".html");
		Reader reader = new InputStreamReader(source);
		Interest interest = gson.fromJson(reader, Interest.class);

		Log.d(this.getClass().getName(), "Id: " + interest.getId());
		Log.d(this.getClass().getName(),
				"Description: " + interest.getDescription());

		return interest;
	}

	// Solo envio la descripción del interes
	public Long crearInteres(Interest interest) {
		Gson gson = new Gson();

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("description", interest
				.getDescription()));
		InputStream source = this.retrievePostStream(server + "/interest",
				nameValuePairs);
		Reader reader = new InputStreamReader(source);
		Long id = gson.fromJson(reader, Long.class);
		interest.setId(id);

		Log.d(this.getClass().getName(), "Id: " + interest.getId());
		Log.d(this.getClass().getName(), "Nombre: " + interest.getDescription());

		return id;
	}

	public User buscarAutorDeLaPublicacion(Long idPublicacion) {
		User user = null;
		Gson gson = new Gson();
		InputStream source = this.retrieveGetStream(server + "/post/"
				+ idPublicacion + "/autor.html");
		Reader reader = new InputStreamReader(source);
		user = gson.fromJson(reader, User.class);
		Log.d(this.getClass().getName(), "Autor Id: " + user.getId());
		Log.d(this.getClass().getName(), "Autor Nombre: " + user.getName());
		Log.d(this.getClass().getName(), "Autor Apellido: " + user.getSurname());
		return user;
	}

	public List<Post> buscarPublicacionesPorInteres(Long idInteres) {
		Gson gson = new Gson();
		InputStream source = this.retrieveGetStream(server
				+ "/posts/search/interest/" + idInteres.toString() + ".html");
		Reader reader = new InputStreamReader(source);

		Type listType = new TypeToken<ArrayList<Post>>() {
		}.getType();

		ArrayList<Post> posts = gson.fromJson(reader, listType);

		for (Post p : posts) {

			Log.d(this.getClass().getName(), "Id: " + p.getId());
			Log.d(this.getClass().getName(), "Nombre: " + p.getMessage());
		}

		return posts;
	}

	public List<Interest> recuperarIntereses() {
		Gson gson = new Gson();
		InputStream source = this.retrieveGetStream(server + "/interests.html");
		Reader reader = new InputStreamReader(source);

		Type listType = new TypeToken<ArrayList<Interest>>() {
		}.getType();

		ArrayList<Interest> intereses = gson.fromJson(reader, listType);

		for (Interest c : intereses) {

			Log.d(this.getClass().getName(), "Id: " + c.getId());
			Log.d(this.getClass().getName(), "Nombre: " + c.getDescription());
		}

		return intereses;

	}

	private InputStream retrievePostStream(String url,
			List<NameValuePair> nameValuePairs) {

		DefaultHttpClient client = new DefaultHttpClient();

		HttpPost postRequest = new HttpPost(url);

		try {

			postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse getResponse = client.execute(postRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();

		} catch (IOException e) {
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
				Log.w(getClass().getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();

		} catch (IOException e) {
			getRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}

		return null;

	}

	public void setServer(String server) {
		this.server = "http://" + server + "/EjemploSpringMVC";
	}
}
