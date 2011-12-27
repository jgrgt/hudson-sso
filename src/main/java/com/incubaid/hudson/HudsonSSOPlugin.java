package com.incubaid.hudson;

import java.util.logging.Logger;

import hudson.Plugin;
import hudson.tasks.BuildStep;

/**
* Plugin entry point.
*
* @plugin
*/
public class HudsonSSOPlugin extends Plugin {
	private static final Logger LOGGER = Logger.getLogger(HudsonSSOPlugin.class
			.getName());

	@Override
	public void start() throws Exception {
		LOGGER.info("Starting " + this.getClass().getSimpleName());
		super.start();
	}
}
