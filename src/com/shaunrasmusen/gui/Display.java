package com.shaunrasmusen.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Display extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 800;
	private JTextArea info = null;
	private long lastButtonPress = System.currentTimeMillis();
//	private static final int FRAME_HEIGHT = 600;
//	private static final Dimension WINDOW_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
	
	private boolean login = false;
	private boolean logout = false;
	// If the program just started, technically the connection failed
	private boolean connFailed = true;
	private JButton loginButton = null;
	
	public JTextField usernameText, hostText, portText;
	public JPasswordField passwordText;
	
	public void createGUI() {
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel header = makeHeader();
		JPanel container = makePane(gbc);
		
		Container content = getContentPane();
		content.setLayout(new GridBagLayout());
		//content.setPreferredSize(WINDOW_SIZE);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
									1.0F, 0.1F, 0, 0, 0, 23, 0, 0, 0, 0);
		content.add(header, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.NORTHWEST, 0, 1.0F, 0.1F, 0, 1);
		content.add(container, gbc);
		
		//this.setSize(WINDOW_SIZE);
		this.setTitle("JavaFTP");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JPanel makeHeader() {
		JPanel header = new JPanel();
		header.setPreferredSize(new Dimension(FRAME_WIDTH, 23));
		// For debugging panel size
		header.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
		
		JLabel title = new JLabel("JavaFTP", JLabel.LEFT);
		title.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		title.setAlignmentX(CENTER_ALIGNMENT);
		header.add(title);
		
		return header;
	}
	
	private JPanel makePane(GridBagConstraints gbc) {		
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setAlignmentX(LEFT_ALIGNMENT);
		container.setAlignmentY(TOP_ALIGNMENT);
		
		JPanel loginPanel = getLoginPanel(gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.NORTHWEST, 0, 0.5F, 0.5F, 0, 0, 0, 0, 0, 0, 0, 0);
		container.add(loginPanel, gbc);
		
		JPanel infoPanel = getInfoPanel(gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, 0.5F, 0.5F, 1, 0,
								0, 0, 0, 4, 0, 0);
		container.add(infoPanel, gbc);
		
		return container;
	}
	
	private JPanel getInfoPanel(GridBagConstraints gbc) {
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridBagLayout());
		// For debugging panel size
		infoPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
		infoPanel.setAlignmentX(LEFT_ALIGNMENT);
		infoPanel.setAlignmentY(TOP_ALIGNMENT);
		
		info = new JTextArea("");
		info.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		info.setPreferredSize(new Dimension(168, 49));
		info.setColumns(10);
		info.setRows(3);
		info.setEditable(false);
		info.setBackground(Color.LIGHT_GRAY);
		 
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0.5F, 0.5F, 0, 0, 0, 0, 0, 1, 0, 0);
		infoPanel.add(info, gbc);
		
		return infoPanel;
	}
	
	private JPanel getLoginPanel(GridBagConstraints gbc) {
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridBagLayout());
		// For debugging panel size
		loginPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
		loginPanel.setAlignmentX(LEFT_ALIGNMENT);
		loginPanel.setAlignmentY(TOP_ALIGNMENT);
		
		JLabel username = newJLabel("Username", 14);
		JLabel password = newJLabel("Password", 14);
		JLabel host = newJLabel("Host", 14);
		JLabel port = newJLabel("Port", 14);
		
		usernameText = new JTextField("Enter username...", 14);
		passwordText = new JPasswordField(10);
		hostText = new JTextField("Enter host...", 20);
		portText = new JTextField("21", 2);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		loginButton.setActionCommand("loginbutton");
		loginButton.setPreferredSize(new Dimension(76, 19));

		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 3);
		loginPanel.add(username, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0, 0, 0, 1);
		loginPanel.add(usernameText, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0, 0, 1, 0);
		loginPanel.add(password, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0, 0, 1, 1);
		loginPanel.add(passwordText, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0, 0, 2, 0);
		loginPanel.add(host, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0, 0, 2, 1);
		loginPanel.add(hostText, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0, 0, 3, 0);
		loginPanel.add(port, gbc);
				
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0, 0, 3, 1);
		loginPanel.add(portText, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0, 0, 4, 1);
		loginPanel.add(loginButton, gbc);
		
		return loginPanel;
	}

	private static JLabel newJLabel(String text, int fontSize) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, fontSize));
		
		return label;
	}
	
	public void showError(Exception e) {
		JOptionPane.showMessageDialog(this, e, "An Error Occured!", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lastButtonPress = System.currentTimeMillis() - lastButtonPress;
		if (e.getActionCommand() == "loginbutton" && lastButtonPress > 10) {
			if (connFailed) {
				login = true;
				logout = false;
			} else {
				login = false;
				logout = true;
			}
		}
	}
	
	public void setInfoText(String text, boolean err) {
		info.setText(text);
		if (err)
			info.setForeground(Color.RED);
		else
			info.setForeground(Color.BLACK);
	}
	
	public void setConnFailed(boolean b) {
		connFailed = b;
	}
	
	public boolean getConnFailed() {
		return connFailed;
	}
	
	public void setLogin(boolean b) {
		login = b;
	}
	
	public boolean getLogin() {
		return login;
	}
	
	public void setLogout(boolean b) {
		logout = b;
	}
	
	public boolean getLogout() {
		return logout;
	}
	
	public void setLoginButtonText(String text) {
		loginButton.setText(text);
	}
}
