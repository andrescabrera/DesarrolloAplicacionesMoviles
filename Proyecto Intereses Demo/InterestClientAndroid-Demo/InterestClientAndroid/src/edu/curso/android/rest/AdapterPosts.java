package edu.curso.android.rest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterPosts extends ArrayAdapter<Post> {

	private static final String TAG = "Principal";
	Activity context;
	List<Post> datos;

	public AdapterPosts(Activity context, int textViewResourceId, List<Post> datos) {
		super(context, textViewResourceId, datos);
		this.context = context;
		this.datos = datos;
		Log.i(TAG, "AdapterConstructor");
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i(TAG, "getView");
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_item_post, null);
		}
		TextView lblTitulo = (TextView) v.findViewById(R.id.lblTitulo);
		lblTitulo.setText(datos.get(position).getMessage());

		TextView lblSubtitulo = (TextView) v.findViewById(R.id.lblSubTitulo);
		lblSubtitulo.setText("Creador: " + datos.get(position).getUser().getName() + " "
				+ datos.get(position).getUser().getSurname());

		return (v);
	}

}
