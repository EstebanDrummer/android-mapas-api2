package com.seminario.android.campusmap;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

/**
 * An asynchronous task that communicates with Twitter to retrieve a request
 * token. (OAuthGetRequestToken)
 * 
 * After receiving the request token from Twitter, pop a browser to the user
 * to authorize the Request Token. (OAuthAuthorizeToken)
 * 
 */
public class OAuthRequestTokenTask extends AsyncTask<Void, Void, Void> {

	private static final String TAG = "MGL";
	private Context context;
	private OAuthProvider provider;
	private OAuthConsumer consumer;

	/**
	 * 
	 * We pass the OAuth consumer and provider.
	 * 
	 * @param context
	 *            Required to be able to start the intent to launch the
	 *            browser.
	 * @param provider
	 *            The OAuthProvider object
	 * @param consumer
	 *            The OAuthConsumer object
	 */
	public OAuthRequestTokenTask(Context context, OAuthConsumer consumer,
			OAuthProvider provider) {
		Log.i("MGL", "depues1 adentro de mandar");
		this.context = context;
		this.consumer = consumer;
		this.provider = provider;
		
	}

	/**
	 * 
	 * Retrieve the OAuth Request Token and present a browser to the user to
	 * authorize the token.
	 * 
	 */
	@Override
	protected Void doInBackground(Void... params) {

		try {
			Log.i(TAG, "Retrieving request token from twitter servers");
			Log.i("MGL", "depues222 adentro de mandar");
			String call= TwitterData.CALLBACK_URL;
			Log.i("MGL", "call back pasa");
			Log.d("MGL", provider.retrieveRequestToken(consumer,call));
			
			final String url = provider.retrieveRequestToken(consumer,call);
			Log.i(TAG, "Popping a browser with the authorize URL : " + url);
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url))
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
							| Intent.FLAG_ACTIVITY_NO_HISTORY
							| Intent.FLAG_FROM_BACKGROUND);
			Log.i("MGL", "lanzare el int");
			context.startActivity(intent);
			Log.i("MGL", "ya lo lance");
		} catch (Exception e) {
			Log.e(TAG, "Error during OAUth retrieve request token", e);
		}

		return null;
	}

}
