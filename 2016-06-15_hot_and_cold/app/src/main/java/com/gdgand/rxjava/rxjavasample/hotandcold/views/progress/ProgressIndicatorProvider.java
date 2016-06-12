package com.gdgand.rxjava.rxjavasample.hotandcold.views.progress;

import android.app.Activity;
import android.app.ProgressDialog;

import com.gdgand.rxjava.rxjavasample.hotandcold.R;

public class ProgressIndicatorProvider {

	private Activity activity;

	public ProgressIndicatorProvider(Activity activity) {
		this.activity = activity;
	}

	public ProgressDialog provide() {
		return provide(activity.getString(R.string.message_progress));
	}

	public ProgressDialog provide(String message) {
		ProgressDialog progressDialog = new ProgressDialog(activity);
		progressDialog.setCancelable(false);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage(message);
		return progressDialog;
	}

}
