package net.moudra.myapplicationwithtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.moudra.myapplicationwithtest.R;


public class MainActivity extends Activity {
    public static final String EXTRA_NAME = "net.moudra.extra.name";
    EditText nameEditText;
    Button nameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = (EditText) findViewById(R.id.name_edittext);
        nameButton = (Button) findViewById(R.id.name_btn);
    }

    public void updateText(View view) {
        String text = nameEditText.getText().toString();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_NAME, text);
        startActivity(intent);
    }
}
