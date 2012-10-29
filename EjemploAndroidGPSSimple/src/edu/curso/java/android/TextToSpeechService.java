package edu.curso.java.android;

import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources.Theme;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;

public class TextToSpeechService implements OnInitListener {
	private static TextToSpeech mTts;
	private static TextToSpeechService t;
	private static Context context;
	private boolean ready = false;

	private TextToSpeechService() {
	}

	private static final String TAG = "TextToSpeechService";

	public void setContext(Context context) {
		TextToSpeechService.context = context;
		
		if(mTts!=null) {
			this.stop();
		}
		mTts = new TextToSpeech(context, this);

	}

	public static TextToSpeechService getInstance() {
		if (t == null)
			t = new TextToSpeechService();
		return t;
	}

	public void stop() {
		// Don't forget to shutdown!
		if (mTts != null) {
			mTts.stop();
			mTts.shutdown();
		}

	}

	// Implements TextToSpeech.OnInitListener.
	public void onInit(int status) {
		// status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
		if (status == TextToSpeech.SUCCESS) {
			// Set preferred language to US english.
			// Note that a language may not be available, and the result will
			// indicate this.
			int result = mTts.setLanguage(new Locale("es"));
			// Try this someday for some interesting results.
			// int result mTts.setLanguage(Locale.FRANCE);
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				// Lanuage data is missing or the language is not supported.
				Log.e(TAG, "Language is not available.");
			} else {
				// Check the documentation for other possible result codes.
				// For example, the language may be available for the locale,
				// but not for the specified country and variant.

				// The TTS engine has been successfully initialized.
				// Allow the user to press the button for the app to speak
				// again.

				ready = true;
			}
		} else {
			// Initialization failed.
			Log.e(TAG, "Could not initialize TextToSpeech.");
		}
	}

	public void speak(String text) {
		// Select a random hello.
		
		if (ready) {
			mTts.speak(text, TextToSpeech.QUEUE_FLUSH, // Drop all pending
														// entries in the
														// playback queue.
					null);
		}
	}

}
