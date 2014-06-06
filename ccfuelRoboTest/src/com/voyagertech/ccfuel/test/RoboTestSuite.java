package com.voyagertech.ccfuel.test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for running test-cases in specific order.
 * 
 * @author msghotra
 * 
 */

public class RoboTestSuite {

	/**
	 * Test Orientation Changes, Navigation Workflow, Open/Close App
	 * 
	 * @return
	 */
	public static final Test suite() {
		TestSuite testSuite = new TestSuite();
		testSuite.addTest(new NavWorkFlowTest("testNavWorkflow"));
		testSuite.addTest(new OrientationTest("testOrientBruteForceWay"));

		// current way of testing open/close... will write custom code later
		testSuite.addTest(new OpenCloseTest("testOpenClose"));
		testSuite.addTest(new OpenCloseTest("testOpenClose_1"));
		testSuite.addTest(new OpenCloseTest("testOpenClose"));
		testSuite.addTest(new OpenCloseTest("testOpenClose_1"));
		testSuite.addTest(new OpenCloseTest("testOpenClose"));

		return testSuite;
	}
}
