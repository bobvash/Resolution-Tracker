package com.bobvash.resolutiontracker.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMFSingleton {
	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	private PMFSingleton() {
	}

	public static PersistenceManagerFactory getInstance() {
		return pmfInstance;
	}
}