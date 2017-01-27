package Alpha;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Breath
{
	static public int geckoWaitTime;
	static public int breathSec;
	static public int hahaTime;
	static private boolean useSleep = true;
	static private boolean logStateFull = true;
	static private boolean longNaps;
	static private int silentCounterEyes = 0;
	static public int chosenSleepCycle;
	static public int DEFAULT_SLEEP_CYCLE = 100;
	static public int DEFAULT_GECKO_WAIT_TIME = 15;
	static public int DEFAULT_BREATH_SEC = 4;
	static public int DEFAULT_HAHA_TIME = 60;
	 
	

	Breath() {
	}

	Breath(boolean currUseSleep, boolean currLogStateFull) {

	}

	static private int getSilentCounter() {
		return silentCounterEyes;
	};

	static private void setSilentCounter(int count) {
		silentCounterEyes = count;
	};

	static public void makeZeroSilentCounter() {
		setSilentCounter(0);
	}

	/*
	 * static public void init(Logging usedLogging){ // ourLogging =
	 * usedLogging; }
	 * 
	 */

	static public void setChosenSleepCycle(int newCycle) {
		if ((newCycle < 1) || (newCycle > 10000)) {
			chosenSleepCycle = DEFAULT_SLEEP_CYCLE;
		}
		chosenSleepCycle = newCycle;
	}

	static public void breath() throws InterruptedException {
		// sleeps for the configured time + impro
		int sleepTime = randInt(breathSec, 2* breathSec);
		if (useSleep) {
			TimeUnit.SECONDS.sleep(sleepTime);
			if (logStateFull) {

				Logging.slog(".");

			}
		}
	}

	static public void deepBreath() throws InterruptedException {

		for (int i = 0; i < 3; i++) {
			breath();
		}
	}

	static public void nap() throws InterruptedException {

		if (longNaps) {
			Logging.slog("Zzzzzzzzzz");
			//int sleepTime = randInt(180, 300);
			int sleepTime = 5 * hahaTime;
			TimeUnit.SECONDS.sleep(sleepTime);
		} else {
			// short naps
			Logging.slog("Zzz");
			if (useSleep) {
				TimeUnit.SECONDS.sleep(hahaTime);
			}
		}
	}

	static public void breathToMissleadThem() throws InterruptedException {
		int sleepTime = randInt(5, hahaTime);
		Logging.slog((new String("Ha Ha ")).concat(String.valueOf(sleepTime)));
		TimeUnit.SECONDS.sleep(sleepTime);
	}

	static public void silentCount() throws InterruptedException {
		setSilentCounter(1+getSilentCounter());
		if (getSilentCounter() > (chosenSleepCycle+10)) {
			Logging.slog("Shshshshsh we are trying to sleep here");
			// extend the nap time
			longNaps = true;
		} else {
			longNaps = false;
		}
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}
