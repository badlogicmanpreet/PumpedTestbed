package com.voyagertech.ccfuel.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.robotium.solo.Solo;
import com.robotium.solo.SoloWithMaps;

/**
 * 
 * Test application stability by running open/close
 * 
 * @author msghotra
 * 
 */

@SuppressWarnings("rawtypes")
public class OpenCloseTest extends ActivityInstrumentationTestCase2 {

	private static final String TAG = "RoboTester_OC";

	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.voyagertech.ccfuel.activity.HomeActivity";
	private static Class launcherActivityClass;

	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public OpenCloseTest(String name) {
		super(launcherActivityClass);
		setName(name);
	}

	@SuppressWarnings("unchecked")
	public OpenCloseTest() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	private Solo solo;
	private SoloWithMaps soloWithMaps;

	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		soloWithMaps = new SoloWithMaps(getInstrumentation(), getActivity());
	}

	/**
	 * Test open/close
	 */
	public void testOpenClose() {
		solo.assertCurrentActivity("Wrong Activity", "HomeActivity");

		// Robotium will sleep for the specified time.
		solo.sleep(3000);

		clickHomeListView();

		// Robotium will sleep for the specified time.
		solo.sleep(1000);

		// 1. Set Preferences to retrieve smaller results
		solo.setNavigationDrawer(Solo.OPENED);

		solo.clickOnText("Preferences");

		solo.clearEditText(0);
		solo.sleep(300);
		solo.enterText(0, "2");

		solo.sleep(300);
		solo.pressSpinnerItem(0, 0);

		solo.sleep(300);
		solo.pressSpinnerItem(1, -2);

		solo.sleep(300);
		solo.clickOnCheckBox(0);

		solo.sleep(300);
		solo.clearEditText(1);
		solo.sleep(300);
		solo.enterText(1, "2");

		solo.clickOnButton("Save");

		solo.sleep(300);
		solo.setNavigationDrawer(Solo.OPENED);
		solo.clickOnText("Home");

		// Robotium will sleep for the specified time.
		solo.sleep(300);

		int counter;
		for (counter = 1; counter < 5; counter++) {
			// callMeForOpenClose();
			Log.d(TAG, "Called... " + counter);
		}

		Log.d(TAG, "counter is - " + counter);

		assertEquals(5, counter);
	}

	@SuppressWarnings("unchecked")
	public void testOpenClose_1() {
		// Robotium will sleep for the specified time.
		solo.sleep(3000);

		solo.setNavigationDrawer(Solo.OPENED);

		solo.sleep(300);

		solo.clickOnText("About");

		solo.sleep(300);

		solo.goBack();

		solo.sleep(300);

		// 2. Click ListView
		clickHomeListView();

		solo.sleep(300);

		solo.goBack();

		solo.sleep(300);

		solo.goBack();

		// relaunch your app by calling the same Activity as in the constructor
		// of your ActivityInstrumentationTestCase2
		this.launchActivity("com.voyagertech.ccfuel", launcherActivityClass, null);

		solo.sleep(1000);

		solo.setNavigationDrawer(Solo.OPENED);

		solo.sleep(300);

		solo.clickOnText("Home");

		solo.sleep(300);

		solo.setNavigationDrawer(Solo.OPENED);

		solo.sleep(300);

		solo.clickOnText("Logout");
	}

	private void clickHomeListView() {
		solo.clickInList(0);
	}

	@Override
	public void tearDown() {
		solo.finishOpenedActivities();
	}

}
