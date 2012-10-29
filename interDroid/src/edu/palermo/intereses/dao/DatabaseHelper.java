package edu.palermo.intereses.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "basegeneral.db";
	private static final int DATABASE_VERSION = 1;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTableIfNotExists(connectionSource, Interes.class);
			TableUtils.createTableIfNotExists(connectionSource, Persona.class);
			TableUtils.createTableIfNotExists(connectionSource, Post.class);
			TableUtils.createTableIfNotExists(connectionSource, Suscripcion.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "No se puede crear la base de datos " + DATABASE_NAME, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
			int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Interes.class, true);
			TableUtils.dropTable(connectionSource, Persona.class, true);
			TableUtils.dropTable(connectionSource, Post.class, true);
			TableUtils.dropTable(connectionSource, Suscripcion.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "No se puede borrar la base de datos " + DATABASE_NAME, e);
			throw new RuntimeException(e);
		}
	}
}