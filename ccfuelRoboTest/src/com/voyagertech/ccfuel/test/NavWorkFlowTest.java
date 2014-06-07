package com.voyagertech.ccfuel.test;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.maps.MapView;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapFragment;
import com.robotium.solo.Solo;
import com.robotium.solo.SoloWithMaps;

/**
 * 
 * Test describing the normal navigation workflow.
 * 
 * @author msghotra
 * 
 */

@SuppressWarnings("rawtypes")
public class NavWorkFlowTest extends ActivityInstrumentationTestCase2 {

	private static final String TAG = "RoboTester_Nav";

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
	public NavWorkFlowTest(String name) {
		super(launcherActivityClass);
		setName(name);
	}

	@SuppressWarnings("unchecked")
	public NavWorkFlowTest() throws ClassNotFoundException {
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
	 * Test navigation workflow
	 */
	public void testNavWorkflow() {
		solo.assertCurrentActivity("Wrong Activity", "HomeActivity");

		// Robotium will sleep for the specified time.
		solo.sleep(5000);

		// 1. Set Preferences
		solo.setNavigationDrawer(Solo.OPENED);

		solo.clickOnText("Preferences");

		solo.clearEditText(0);
		solo.sleep(300);
		solo.enterText(0, "2");

		solo.sleep(300);
		solo.pressSpinnerItem(0, 0);

		solo.sleep(300);
		// solo.get
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

		// 2. Click ListView
		clickHomeListView();

		// Robotium will sleep for the specified time.
		solo.sleep(2000);

		// 3. Click Marker... on Map
		// TODO: Will come back later to this.

		solo.goBack();

		solo.sleep(2000);

		solo.setNavigationDrawer(solo.OPENED);

		solo.sleep(2000);

		solo.clickOnText("About");

		solo.sleep(1500);

		solo.goBack();

		solo.sleep(3000);
	}

	/**
	 * Returns a Fragment with a given tag or id.
	 * 
	 * @param tag
	 *            the tag of the Fragment or null if no tag
	 * @param id
	 *            the id of the Fragment
	 * @return a SupportFragment with a given tag or id
	 */

	private android.app.Fragment getFragment(String tag, int id) {

		try {
			if (tag == null)
				return solo.getCurrentActivity().getFragmentManager().findFragmentById(id);
			else
				return solo.getCurrentActivity().getFragmentManager().findFragmentByTag(tag);
		} catch (Throwable ignored) {
		}

		return null;
	}

	private void willReuseThisCode() {
		// Log.d(TAG, solo.getCurrentActivity().getLocalClassName() + " " +
		// solo.getCurrentActivity().getPackageName());
		// for (View view : solo.getViews()) {
		// if (view instanceof RelativeLayout) {
		// Log.d(TAG, view.toString());
		// }
		// }
		//
		// int id =
		// solo.getCurrentActivity().getResources().getIdentifier("addressDetailsLayout",
		// "id", solo.getCurrentActivity().getPackageName());
		// Log.d(TAG, "My ID is..." + id);

		// solo.clickOnView(solo.getView(id));

		// Activity activity = solo.getCurrentActivity();

		// Robotium will sleep for the specified time.
		// solo.sleep(5000);

		// 1. Set preferences
		// int id_menu = activity.getResources().getIdentifier("main", "menu",
		// solo.getCurrentActivity().getPackageName());
		// Log.d(TAG, "Menu ID is..." + id_menu);
		// Log.d(TAG, "Menu name is..." +
		// activity.getResources().getLayout(id_menu).getName());

		// int id_menu = activity.getResources().getIdentifier("drawer_layout",
		// "id", solo.getCurrentActivity().getPackageName());
		// Log.d(TAG, "Lay ID is..." + id_menu);
		// Log.d(TAG, "Lay name is..." +
		// activity.getResources().getLayout(id_menu));
		// solo.clickOnView((View) activity.getResources().getLayout(id_menu));

		// int id_pref = activity.getResources().getIdentifier("preferences",
		// "id", solo.getCurrentActivity().getPackageName());
		// Log.d(TAG, "My ID is..." + id_pref);
		// solo.clickOnActionBarHomeButton();
		// solo.clickOnActionBarItem(1);
		// solo.clickOnText(activity.getResources().getString(id_pref));
		// String pref = activity.getResources().getString(id_pref);
		// Log.d(TAG, "Pref is..." + pref);

		// solo.clickOnActionBarHomeButton();
		// solo.sleep(2000);
		// solo.clickOnActionBarItem(id_pref);
		// solo.clickOnMenuItem("preferences");

		/**
		 * //3. Click the Markers on Map (raised bug...
		 * http://stackoverflow.com/
		 * questions/23968815/how-to-load-resources-to-test
		 * -project-which-is-using-robotium) int id =
		 * activity.getResources().getIdentifier("googleMap", "id",
		 * solo.getCurrentActivity().getPackageName()); Log.d(TAG, "My ID is..."
		 * + id);
		 * 
		 * Log.d(TAG, solo.getCurrentActivity().getPackageName());
		 * 
		 * int id_lay = activity.getResources().getIdentifier("map_view",
		 * "layout", solo.getCurrentActivity().getPackageName()); Log.d(TAG,
		 * "My Lay ID is..." + id_lay); Log.d(TAG, "Layout is..." +
		 * activity.getResources().getLayout(R.layout.map_view).getText());
		 * 
		 * //Log.d(TAG, "Fragment is..." + solo.waitForFragmentById(id));
		 * //Log.d(TAG, "Layout is..." +
		 * activity.getResources().getLayout(2130903076).getName());
		 * //Log.d(TAG, "Frag is..." +
		 * activity.getFragmentManager().findFragmentById(0x7f080061));
		 * Log.d(TAG, "Frag is..." +
		 * activity.getResources().getResourceEntryName(id)); Log.d(TAG,
		 * "Frag is..." + activity.getResources().getResourceTypeName(id));
		 * //Log.d(TAG, "My F Tag is..." + fragment.getTag()); //Log.d(TAG,
		 * "My F is..." + fragment.toString());
		 **/

		// MapFragment map = (MapFragment)
		// solo.getCurrentActivity().getFragmentManager().findFragmentById();
		// GoogleMap gMap = map.getMap();
		// Log.d(TAG, "My Location..." + gMap.getMyLocation().getLatitude());
		//
		// Log.d(TAG, "Starting to debug mapview...");
		// ArrayList<View> views = solo.getViews();
		// Iterator<View> it = views.iterator();
		// while(it.hasNext()){
		// Log.d(TAG, "Printing views..." + it.next());
		// }

		// soloWithMaps.zoomInOnMap();

		// Activity activity = solo.getCurrentActivity();
		// int id =
		// activity.getResources().getIdentifier("ic_fuel_station__loc", "id",
		// activity.getPackageName());
		// Toast.makeText(activity, activity.toString(),
		// Toast.LENGTH_LONG).show();
		// solo.clickOnImage(id);

		// 4. Skip step 3 for now and open activity manually
	}

	private void clickHomeListView() {
		solo.clickInList(0);
	}

	@Override
	public void tearDown() {
		solo.finishOpenedActivities();
	}

}
