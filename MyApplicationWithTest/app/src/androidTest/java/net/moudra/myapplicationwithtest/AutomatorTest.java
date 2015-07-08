package net.moudra.myapplicationwithtest;

import android.os.SystemClock;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;
import android.view.KeyEvent;

// UiAutomator library test
public class AutomatorTest extends InstrumentationTestCase {
    private UiDevice uiDevice;

    @Override
    public void setUp() {
        uiDevice = UiDevice.getInstance(getInstrumentation());
        uiDevice.pressHome();
    }

    public void testCalculator() throws Exception {
        uiDevice.findObject(new UiSelector().descriptionContains("Apps")).clickAndWaitForNewWindow();

        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        appViews.setAsHorizontalList();

        UiObject calculatorApp = appViews.getChildByText(new UiSelector()
                .className(android.widget.TextView.class.getName()), "Calculator");
        calculatorApp.clickAndWaitForNewWindow();

        // Calculator app
        UiObject threeButton = uiDevice.findObject(new UiSelector().text("3"));
        threeButton.click();

        UiObject plusButton = uiDevice.findObject(new UiSelector().text("+"));
        plusButton.click();

        UiObject fiveButton = uiDevice.findObject(new UiSelector().text("5"));
        fiveButton.click();

        UiObject equalsButton = uiDevice.findObject(new UiSelector().text("="));
        equalsButton.click();

        UiObject display = uiDevice.findObject(new UiSelector()
                .resourceId("com.android.calculator2:id/display"));
        UiObject displayNumber = display.getChild(new UiSelector().index(0));

        assertEquals(displayNumber.getText(), "8");

        uiDevice.pressHome();
    }

    public void testBrowserApp() throws Exception {
        uiDevice.findObject(new UiSelector().descriptionContains("Apps")).clickAndWaitForNewWindow();

        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        appViews.setAsHorizontalList();

        UiObject browserApp = appViews.getChildByText(new UiSelector()
                .className(android.widget.TextView.class.getName()), "Browser");
        browserApp.clickAndWaitForNewWindow();

        // Browser App set url
        UiObject urlForm = uiDevice.findObject(new UiSelector()
                .resourceId("com.android.browser:id/url"));
        urlForm.setText("http://www.google.cz");
        //uiDevice.pressKeyCode(KeyEvent.KEYCODE_ENTER);
        uiDevice.pressEnter();

        // Wait to load page
        SystemClock.sleep(10000);

        // Click on webview to lose focus from form
        UiObject webView = uiDevice.findObject(new UiSelector()
                .className("android.webkit.WebView"));
        webView.click();

        uiDevice.pressMenu();

        // Sleep to show the menu
        SystemClock.sleep(1000);
        UiObject refreshButton = uiDevice.findObject(new UiSelector()
                .text("Refresh"));
        refreshButton.click();
    }
}
