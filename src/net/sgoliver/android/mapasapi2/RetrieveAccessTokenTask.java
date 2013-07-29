package net.sgoliver.android.mapasapi2;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


public class RetrieveAccessTokenTask extends AsyncTask<Uri, Void, Boolean> {

	private Context context;
	private OAuthProvider provider;
	private OAuthConsumer consumer;
	private SharedPreferences prefs;
	private String text;

	public RetrieveAccessTokenTask(Context context, OAuthConsumer consumer,
			OAuthProvider provider, SharedPreferences prefs, String text) {
		this.context = context;
		this.consumer = consumer;
		this.provider = provider;
		this.prefs = prefs;
		this.text = text;
	}

	/**
	 * Retrieve the oauth_verifier, and store the oauth and
	 * oauth_token_secret for future API calls.
	 */
	@Override
	protected Boolean doInBackground(Uri... params) {
		final Uri uri = params[0];
		final String oauth_verifier = uri
				.getQueryParameter(OAuth.OAUTH_VERIFIER);

		try {
			Log.i("MGL","Obtained oAuth Verifier: " + oauth_verifier);
			
			provider.retrieveAccessToken(consumer, oauth_verifier);

			final Editor edit = prefs.edit();
			edit.putString(OAuth.OAUTH_TOKEN, consumer.getToken());
			edit.putString(OAuth.OAUTH_TOKEN_SECRET, consumer.getTokenSecret());
			edit.commit();

			String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
			String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
			if( secret == null || token == null || secret.equals("") || token.equals("") ) return false;
			consumer.setTokenWithSecret(token, secret);
			context.startActivity(new Intent(context, MainActivity.class));

			Log.i("MGL", "OAuth - Access Token Retrieved");

		} catch (Exception e) {
			Log.e("MGL", "OAuth - Access Token Retrieval Error", e);
		}

		return true;
	}
	@Override
	protected void onPostExecute(Boolean result) {
		//Log.d("tuit", this.text);
        if(result){
			String tuit = this.text ;
			Log.d("tuit", tuit);
			new SendTuitTask(tuit, prefs).execute();

            Toast.makeText(this.context,
            		"Acceso a twitter conseguido!",
                    Toast.LENGTH_SHORT).show();
        }else{
        	Toast.makeText(this.context,
            		"Acceso a twitter NO conseguido! :(",
                    Toast.LENGTH_SHORT).show();
        }
   }		
}
