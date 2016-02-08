package com.codonomics.demo.activities;

import com.codonomics.demo.R;
import com.codonomics.demo.adapters.DBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

	private final String TAG = MainActivity.class.getSimpleName();
	private DBAdapter dbAdapter;
	@Bind(R.id.user_input) EditText userInputEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.setDebug(true);
		ButterKnife.bind(this);

		dbAdapter = new DBAdapter(this);
		dbAdapter.open();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbAdapter.close();
	}

	@OnClick(R.id.submit)
	public void saveToDB(View v) {
		final String userInput = fetchUserInput();
		if(dbAdapter.exists(userInput)) {
			toast("This one is saved already!");
			return;
		}
		dbAdapter.insertUserInput(userInput);
		toast("Save OK!");
		clearUserInputTextField();
	}

	@OnClick(R.id.listUserInputs)
	public void listUserInputs(View v) {
		final Intent intent = new Intent(this, UserInputsListActivity.class);
		startActivity(intent);
	}

	private void clearUserInputTextField() {
		userInputEditText.setText("");
	}

	private void toast(String message) {
		Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}

	private String fetchUserInput() {
		return userInputEditText.getText().toString();
	}
}
