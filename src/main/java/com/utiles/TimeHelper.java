package com.utiles;

public class TimeHelper {

	// private List<Long> timePeriodsList = new ArrayList<Long>();
	private static long actualPeriodOfTime;
	private static long lastPeriodOfTime;
	private static long timeCount;

	public TimeHelper() {
	}

	/**
	 * returns true whenever specific amount of time has passed;
	 * 
	 * @param timeToPass
	 *            in miliseconds
	 * @return boolean
	 */
	public static boolean checkIfTimePasses(long startTime, long timeToPass) {
		if (actualPeriodOfTime == 0) {
			actualPeriodOfTime = startTime;
		}
		System.out.println(lastPeriodOfTime);
		updateTimePeriod();

		if (timeCount > timeToPass) {
			reset();
			return true;
		}
		return false;

	}

	private static void updateTimePeriod() {
		lastPeriodOfTime = actualPeriodOfTime;
		actualPeriodOfTime = System.currentTimeMillis();
		timeCount += (actualPeriodOfTime - lastPeriodOfTime);
		System.out.println("-------------------------------------------");
		System.out.println(lastPeriodOfTime);
		System.out.println(actualPeriodOfTime);
		System.out.println(timeCount);
	}

	private static void reset() {
		timeCount = 0;
		actualPeriodOfTime = 0;
		lastPeriodOfTime = 0;
	}

}
