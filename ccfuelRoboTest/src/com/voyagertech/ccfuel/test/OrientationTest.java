package com.voyagertech.ccfuel.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.robotium.solo.Solo;

/**
 * Test orientation at-least 30 times.
 * 
 * @author msghotra
 * 
 */

@SuppressWarnings("rawtypes")
public class OrientationTest extends ActivityInstrumentationTestCase2 {

	private static final String TAG = "RoboTester_Orient";

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
	public OrientationTest(String name) {
		super(launcherActivityClass);
		setName(name);
	}

	@SuppressWarnings("unchecked")
	public OrientationTest() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	private Solo solo;

	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	/**
	 * Test if app is stable when the orientation changes [Landscape, Portrait]
	 */
	public void testOrientBruteForceWay() {
		solo.assertCurrentActivity("Wrong Activity", "HomeActivity");
		clickHomeListView();

		// Robotium will sleep for the specified time.
		solo.sleep(5000);

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
		solo.sleep(5000);

		int counter;
		for (counter = 1; counter < 30; counter++) {
			solo.setActivityOrientation(Solo.LANDSCAPE);

			// Robotium will sleep for the specified time.
			solo.sleep(1000);

			solo.setActivityOrientation(Solo.PORTRAIT);

			// Robotium will sleep for the specified time.
			solo.sleep(1000);

			solo.setActivityOrientation(Solo.LANDSCAPE);

			// Robotium will sleep for the specified time.
			solo.sleep(1000);

			solo.setActivityOrientation(Solo.PORTRAIT);

			// Robotium will sleep for the specified time.
			solo.sleep(1000);

			Log.d(TAG, "Count is - " + counter);
		}

		Log.d(TAG, "Success");
		assertEquals(30, counter);

		// Robotium will sleep for the specified time.
		solo.sleep(5000);
	}

	/**
	 * Test if app is stable when the orientation changes [Landscape, Portrait]
	 */
	public void OrientWhileNav() {
		solo.assertCurrentActivity("Wrong Activity", "HomeActivity");

		clickHomeListView();

		// Robotium will sleep for the specified time.
		solo.sleep(5000);

		solo.setActivityOrientation(Solo.LANDSCAPE);

		// Robotium will sleep for the specified time.
		solo.sleep(1000);

		solo.setActivityOrientation(Solo.PORTRAIT);

		// Robotium will sleep for the specified time.
		solo.sleep(1000);

		// Robotium will sleep for the specified time.
		solo.sleep(1000);
		
		solo.setActivityOrientation(Solo.LANDSCAPE);

		// Robotium will sleep for the specified time.
		solo.sleep(1000);

		solo.setActivityOrientation(Solo.PORTRAIT);

		// Robotium will sleep for the specified time.
		solo.sleep(1000);

	}

	private void clickHomeListView() {
		solo.clickInList(0);

	}

	@Override
	public void tearDown() throws Exception {
		try {
			solo.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		getActivity().finish();
		super.tearDown();
	}

}
