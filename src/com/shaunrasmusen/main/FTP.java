package com.shaunrasmusen.main;

import com.shaunrasmusen.gui.Display;

public class FTP {
	public static void main(String[] args) {
		Display display = new Display();
		display.createGUI();
		
		FTP ftp = new FTP();
		ftp.run(display);
	}
	
	public void run(Display display) {
		while (!display.isWindowClosing) {
			if (display.usernameText.isFocusOwner() && display.usernameText.getText().equals("Enter username..."))
				display.usernameText.setText("");
			if (!display.usernameText.isFocusOwner() && display.usernameText.getText().equals(""))
				display.usernameText.setText("Enter username...");
			
			if (display.hostText.isFocusOwner() && display.hostText.getText().equals("Enter host..."))
				display.hostText.setText("");
			if (!display.hostText.isFocusOwner() && display.hostText.getText().equals(""))
				display.hostText.setText("Enter host...");
		}
	}
}
