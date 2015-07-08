package net.moudra.myapplicationwithtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import net.moudra.myapplicationwithtest.R;

public class DetailActivity extends Activity {
    TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String labelText = intent.getStringExtra(MainActivity.EXTRA_NAME);
        nameTextView = (TextView) findViewById(R.id.name_textview);
        nameTextView.setText(labelText);
    }
}
