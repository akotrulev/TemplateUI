package helper;

import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

/**
 * This class enables us to display the logs for each test in the assertion error thrown.
 * This is done so when running the tests in parallel, the proper logs are displayed in the proper
 * test case tab, making for a better readability and html reports.
 * <p>
 * Note that with this, logs will be displayed only when a test fails
 */
public class CustomAssert extends SoftAssert {
    @Override
    public void assertAll() {
        StringBuffer stringBuilder = new StringBuffer();
        Reporter.getOutput(Reporter.getCurrentTestResult()).forEach(s -> stringBuilder.append(s).append("\n"));
        super.assertAll(stringBuilder.toString());
    }

    /**
     * @param actual   the actual value to be asserted
     * @param expected the expected value to be asserted
     * @param <T>      the type of the variables. This makes it so we have a single implementation for all data types
     */
    public static <T> void areEqual(final T actual, final T expected) {
        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(actual, expected);
        customAssert.assertAll();
    }

    /**
     * Used to fail a test when an exception is thrown
     *
     * @param message information about the reason for the fail
     */
    public static void failTest(String message) {
        CustomAssert customAssert = new CustomAssert();
        customAssert.fail(message);
        customAssert.assertAll();
    }
}
