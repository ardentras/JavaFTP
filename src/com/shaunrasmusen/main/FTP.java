package com.shaunrasmusen.main;

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
	
	public static void main(String[] args) {
		display = new Display();
		display.createGUI();
		
		FTP ftp = new FTP();
		ftp.run();
	}
	
	public void run() {		
		while (!display.isWindowClosing) {
			if (!connected) {
				if (display.usernameText.isFocusOwner() && display.usernameText.getText().equals("Enter username..."))
					display.usernameText.setText("");
				if (!display.usernameText.isFocusOwner() && display.usernameText.getText().equals(""))
					display.usernameText.setText("Enter username...");
				
				if (display.hostText.isFocusOwner() && display.hostText.getText().equals("Enter host..."))
					display.hostText.setText("");
				if (!display.hostText.isFocusOwner() && display.hostText.getText().equals(""))
					display.hostText.setText("Enter host...");
				
				if (display.login) {
					try {
						tryLogin();
					} catch (IOException e) {
						display.setLogin(false);
						System.out.println(e);
					}
				}
			} else {
				System.out.println("Success");
			}
		}
	}
	
	public void tryLogin() throws IOException {
		Socket socket = null;
		BufferedReader input = null;
		BufferedWriter output = null;
		String response = "";
		
		try {
			socket = new Socket(InetAddress.getByName(display.hostText.getText()), Integer.parseInt(display.portText.getText()));
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		response = input.readLine();
		
		if (response.startsWith("220 "))
			System.out.println("Initial connection to " + display.hostText.getText() + "successful! Trying login...");
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
			System.out.println("Username accepted. Sending password..." + passStr);
		else {
			display.setConnFailed(true);
			throw new IOException("Username rejected! Error " + response);
		}
		
		output.write("PASS " + passStr + "\r\n");
		output.flush();
		
		response = input.readLine();
		
		if (response.startsWith("230 "))
			System.out.println("Login successful!");
		else {
			display.setConnFailed(true);
			throw new IOException("Login failed! Error " + response);
		}
	}
}
