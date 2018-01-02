package com.gsww.utils;

import javax.persistence.ManyToOne;

/**
 *Generator ID
 * 
 * 
 */
public class ID {
	@ManyToOne
	static IDGenerator IDG = null;

	static {
		try {
			String format[] = new String[] { "AN4", "AN4" };
			IDG = new IDGenerator(format, "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 *Usage sample: String yourID=Faimis.getID();
	 */
	public static String getID() {
		return IDG.generateID();
	}
}