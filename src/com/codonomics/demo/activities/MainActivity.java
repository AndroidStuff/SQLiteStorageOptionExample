package com.codonomics.demo.activities;

import com.codonomics.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends Activity {

	private final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	protected void saveToDB() {
		String text = fetchUserInput();
	}

	private String fetchUserInput() {
		return ((EditText) findViewById(R.id.user_input)).getText().toString();
	}
}
