package com.the99tests.photon.platforms;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.the99tests.photon.DataStore;
import com.the99tests.photon.PhotonSession;

public class FirefoxManager extends PlatformManager {
	@Override
	public DesiredCapabilities setupCapabilities(URL hub, String platform, DataStore store) {
		DesiredCapabilities capability=DesiredCapabilities.firefox();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		capability.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		capability.setCapability(CapabilityType.PLATFORM, platform);
		
		return capability;
	}
	
	@Override
	public void setupDriver(URL hub, DesiredCapabilities capabilities) {
		super.setupDriver(hub, capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Override
	public void logCheckpoint(String checkpoint) {
		driver.executeScript("console.log('-- CHECKPOINT: "+checkpoint+" --');");
	}

	@Override
	public LogEntries getLogs() {
		return null;
	}
}
