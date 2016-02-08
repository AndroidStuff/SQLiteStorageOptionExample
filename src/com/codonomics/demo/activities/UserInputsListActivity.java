package com.codonomics.demo.activities;

import java.util.List;

import com.codonomics.demo.R;
import com.codonomics.demo.adapters.DBAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserInputsListActivity extends Activity {
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
				); //Ref.:
	}
}
