package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0;
	private final int maxRetryCount = 2; // Retry max 2 times

	@Override
	public boolean retry(ITestResult result) {

		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}

}
