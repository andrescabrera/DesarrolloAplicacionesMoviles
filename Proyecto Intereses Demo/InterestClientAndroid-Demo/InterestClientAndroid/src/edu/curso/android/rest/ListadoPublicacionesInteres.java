package edu.curso.android.rest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListadoPublicacionesInteres extends Activity {

	private static final String TAG = "Principal";
	private RestAPIService restAPIService = new RestAPIService();
	private ListView listInterestPosts;
	private Runnable viewPosts;
	private List<Post> publicacionesInteres = null;
	private ProgressDialog mProgressDialog = null;
	private AdapterPosts adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_list);
		final Long interestId = getIntent().getExtras().getLong("interestId");
		publicacionesInteres = new ArrayList<Post>();		
		restAPIService.setServer("192.168.1.100:8080");
		Log.i(TAG, "publicaciones del interes id: " + interestId);

		listInterestPosts = (ListView) findViewById(R.id.listPosts);
		
		adapter = new AdapterPosts(this, R.id.listPosts, publicacionesInteres);
		listInterestPosts.setAdapter(adapter);
		listInterestPosts.setEnabled(true);
		listInterestPosts.setVisibility(0x00000000);
		listInterestPosts.setTextFilterEnabled(true);
		
		viewPosts = new Runnable() {
			@Override
			public void run() {
				getPosts(interestId);
			}
		};

		Thread thread = new Thread(null, viewPosts, "RetrievingPostBackground");
		thread.start();
		mProgressDialog = ProgressDialog.show(
				ListadoPublicacionesInteres.this, "Por favor espera...",
				"Obteniendo datos...", true);

		// lv.setOnItemClickListener(new OnItemClickListener() {
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// // When clicked, show a toast with the TextView text
		// Toast.makeText(getApplicationContext(),
		// ((TextView) view).getText(), Toast.LENGTH_SHORT)
		// .show();
		// }
		// });
	}

	private Runnable returnRes = new Runnable() {
		@Override
		public void run() {
			if (publicacionesInteres != null && publicacionesInteres.size() > 0) {
				adapter.notifyDataSetChanged();
				for (int i = 0; i < publicacionesInteres.size(); i++)
					adapter.add(publicacionesInteres.get(i));
			}
			mProgressDialog.dismiss();
			adapter.notifyDataSetChanged();
		}
	};

	private void getPosts(Long interestId) {
		publicacionesInteres = restAPIService
				.buscarPublicacionesPorInteres(interestId);
		for (Post p : publicacionesInteres) {
			p.setUser(restAPIService.buscarAutorDeLaPublicacion(p.getId()));
		}
		runOnUiThread(returnRes);
	}
}