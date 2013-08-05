package com.seminario.android.campusmap;



import android.content.SharedPreferences;
import android.os.AsyncTask;

public class SendTuitTask extends AsyncTask<Void, Void, Void> {

	private String tuit;
	private SharedPreferences prefs;

	public SendTuitTask( String tuit, SharedPreferences prefs){
		this.tuit = tuit;
		this.prefs = prefs;
	}

	@Override
	protected Void doInBackground(Void... params) {
		TwitterUtils.mandaTuit( this.tuit, this.prefs );
		return null;
	}

}