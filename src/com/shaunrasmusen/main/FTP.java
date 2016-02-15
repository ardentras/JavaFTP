package com.shaunrasmusen.main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import com.shaunrasmusen.gui.Display;

public class FTP {
	public boolean connected = false;	
	public static Display display = null;
	
	private Socket socket = null;
	private BufferedReader input = null;
	private BufferedWriter output = null;
	private String response = "";
	
	public static void main(String[] args) {
		display = new Display();
		display.createGUI();
		
		FTP ftp = new FTP();
		ftp.run();
	}
	
	public void run() {
		// TODO Actually make this a condition, not just true... -_-
		while (true) {
			if (!connected) {
				if (display.usernameText.isFocusOwner() && display.usernameText.getText().equals("Enter username..."))
					display.usernameText.setText("");
				if (!display.usernameText.isFocusOwner() && display.usernameText.getText().equals(""))
					display.usernameText.setText("Enter username...");
				
				if (display.hostText.isFocusOwner() && display.hostText.getText().equals("Enter host..."))
					display.hostText.setText("");
				if (!display.hostText.isFocusOwner() && display.hostText.getText().equals(""))
					display.hostText.setText("Enter host...");
				
				if (display.getLogin()) {
					try {
						tryLogin();
					} catch (IOException e) {
						display.setInfoText(e.toString(), true);
					}
					
					if (display.getConnFailed()) {
						display.setLogin(false);
						connected = false;
					}
				}
			} else {
				if (display.getLogout()) {
					try {
						tryLogout();
					} catch (IOException e) {
						display.setInfoText(e.toString(), true);
					}
				}
			}
		}
	}
	
	private void tryLogout() throws IOException {
		display.setInfoText("Aborting connection...", false);
		output.write("QUIT\r\n");
		output.flush();
		
		response = input.readLine();
		
		if (response == null) {
			socket = null;
			output = null;
			input = null;
			
			display.setInfoText("Logout successful!", false);
			display.setLoginButtonText("Login");
			display.hostText.setEditable(true);
			display.hostText.setBackground(Color.WHITE);
			display.passwordText.setEditable(true);
			display.passwordText.setBackground(Color.WHITE);
			display.portText.setEditable(true);
			display.portText.setBackground(Color.WHITE);
			display.usernameText.setEditable(true);
			display.usernameText.setBackground(Color.WHITE);
			display.setConnFailed(true);
			connected = false;
		} else {
			throw new IOException("Unknown error occured! Error " + response);
		}
	}

	public void tryLogin() throws IOException {		
		try {
			socket = new Socket(InetAddress.getByName(display.hostText.getText()), 
					Integer.parseInt(display.portText.getText()));
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		response = input.readLine();
		
		if (response.startsWith("220 "))
			display.setInfoText("Initial connection to " + display.hostText.getText() 
									+ " successful! Trying login...", false);
		else {
			display.setConnFailed(true);
			throw new IOException("Connection failed! Error " + response);
		}
		
		output.write("USER " + display.usernameText.getText() + "\r\n");
		output.flush();
		
		response = input.readLine();
		
		char[] password = display.passwordText.getPassword();
		String passStr = "";
		for (int i = 0; i < password.length; i++)
			passStr += password[i];
		
		if (response.startsWith("331 "))
			display.setInfoText("Username accepted. Sending password...", false);
		else {
			display.setConnFailed(true);
			throw new IOException("Username rejected! Error " + response);
		}
		
		output.write("PASS " + passStr + "\r\n");
		output.flush();
		
		response = input.readLine();
		
		if (response.startsWith("230 ")) {
			display.setInfoText("Login successful!", false);
			display.setLoginButtonText("Logout");
			display.hostText.setEditable(false);
			display.hostText.setBackground(Color.LIGHT_GRAY);
			display.passwordText.setEditable(false);
			display.passwordText.setBackground(Color.LIGHT_GRAY);
			display.portText.setEditable(false);
			display.portText.setBackground(Color.LIGHT_GRAY);
			display.usernameText.setEditable(false);
			display.usernameText.setBackground(Color.LIGHT_GRAY);
			display.setConnFailed(false);
			connected = true;
		} else {
			display.setConnFailed(true);
			display.setInfoText("Login failed! Error " + response, true);
		}
	}
}
