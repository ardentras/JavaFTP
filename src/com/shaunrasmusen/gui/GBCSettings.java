package com.shaunrasmusen.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GBCSettings {
	protected static GridBagConstraints setGBC(GridBagConstraints gbc, 
			int anchor, int fill, float weightx, float weighty, int gridx, int gridy) {
		
		gbc.anchor = anchor;
		gbc.fill = fill;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		
		return gbc;
	}
	
	protected static GridBagConstraints setGBC(GridBagConstraints gbc, 
			int anchor, int fill, float weightx, float weighty, int gridx, int gridy, int ipadx, int ipady) {
		
		gbc.anchor = anchor;
		gbc.fill = fill;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		
		return gbc;
	}
	
	protected static GridBagConstraints setGBC(GridBagConstraints gbc, 
			int anchor, int fill, float weightx, float weighty, int gridx, int gridy,
			int ipadx, int ipady, int top, int left, int bottom, int right) {
		
		gbc.anchor = anchor;
		gbc.fill = fill;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		gbc.insets = new Insets(top, left, bottom, right);
		
		return gbc;
	}
}
