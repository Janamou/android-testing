package net.moudra.myapplicationwithtest;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// JUnit test for MainActivity
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mainActivity;
    private EditText nameEditText;
    private Button nameButton;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mainActivity = getActivity();
        nameEditText = (EditText) mainActivity.findViewById(R.id.name_edittext);
        nameButton = (Button) mainActivity.findViewById(R.id.name_btn);

        assertNotNull("Activity is null", mainActivity);
        assertNotNull("Name EditText is null", nameEditText);
    }

    // Updates edittext
    @UiThreadTest
    public void testEditText() {
        assertEquals(nameEditText.getText().toString(), "");

        nameEditText.setText("Jana Moudra");
        assertEquals(nameEditText.getText().toString(), "Jana Moudra");

        View origin = mainActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(origin, nameEditText);
    }

    // Opens DetailActivity and validates it
    public void testClickOnButton() {
        Instrumentation.ActivityMonitor activityMonitor =
                getInstrumentation().addMonitor(DetailActivity.class.getName(), null, false);
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                nameEditText.setText("Jana Moudra 2");
                nameButton.performClick();
            }
        });

        Activity detailActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 1000);
        assertNotNull(detailActivity);
        assertNotNull(detailActivity.getIntent());
        assertEquals(detailActivity.getIntent().getStringExtra(MainActivity.EXTRA_NAME), "Jana Moudra 2");
        detailActivity.finish();

        getInstrumentation().removeMonitor(activityMonitor);
    }
}
