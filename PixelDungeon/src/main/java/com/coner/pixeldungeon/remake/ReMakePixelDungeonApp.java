package com.coner.pixeldungeon.remake;

import android.app.Application;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

@ReportsCrashes(mailTo = "cuneytoner@gmail.com", mode = ReportingInteractionMode.TOAST, resToastText = R.string.ReMakePixelDungeonApp_sendCrash)
public class ReMakePixelDungeonApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		if(!BuildConfig.DEBUG) {
			ACRA.init(this);
		}

		try {
			Class.forName("android.os.AsyncTask");
		} catch (Throwable ignore) {
		}
	}
}
