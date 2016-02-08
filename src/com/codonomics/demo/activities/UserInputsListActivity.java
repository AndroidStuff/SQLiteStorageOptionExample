package com.codonomics.demo.activities;

import java.util.List;

import com.codonomics.demo.R;
import com.codonomics.demo.adapters.DBAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UserInputsListActivity extends Activity {
	private final String TAG = UserInputsListActivity.class.getSimpleName();

	private DBAdapter dbAdapter;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_inputs_list_activity);

		dbAdapter = new DBAdapter(this);
		dbAdapter.open();

		final List<String> allUserInputs = dbAdapter.getAllUserInputs();

		listView = (ListView) findViewById(R.id.user_input_list_view);
		listView.setAdapter(
				new ArrayAdapter<String>(this,
						android.R.layout.simple_selectable_list_item, //Note that this R instance is from Android and not local (check the namespace).
						allUserInputs)
				); //Ref.: Predefined item Layouts - http://developer.android.com/reference/android/R.layout.html
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String itemText = ( (TextView) view ).getText().toString();
				final String msg = String.format("Item %s at position %d is clicked", itemText, position);
				Log.d(TAG, msg);
				Toast.makeText(UserInputsListActivity.this, msg, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
