package com.shaunrasmusen.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Display extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 800;
//	private static final int FRAME_HEIGHT = 600;
//	private static final Dimension WINDOW_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
	public boolean isWindowClosing = false;
	
	public JTextField usernameText, hostText;
	
	public void createGUI() {
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel header = makeHeader();
		JPanel container = makePane(gbc);
		
		Container content = getContentPane();
		content.setLayout(new GridBagLayout());
		//content.setPreferredSize(WINDOW_SIZE);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 1.0F, 0.1F, 0, 0, 0, 23);
		content.add(header, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.NORTHWEST, 0, 1.0F, 0.4F, 0, 1);
		content.add(container, gbc);
		
		//this.setSize(WINDOW_SIZE);
		this.setTitle("JavaFTP");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public JPanel makeHeader() {
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
	
	public JPanel makePane(GridBagConstraints gbc) {		
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setAlignmentX(LEFT_ALIGNMENT);
		container.setAlignmentY(TOP_ALIGNMENT);
		
		JPanel loginPanel = getLoginPanel(gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.FIRST_LINE_START, 0, 0.5F, 0.5F, 0, 0, 0, 0, 0, 0, 0, 0);
		container.add(loginPanel, gbc);
		
		return container;
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
		
		usernameText = new JTextField("Enter username...", 15);
		usernameText.addActionListener(this);
		usernameText.setActionCommand("user textfield");
		JPasswordField passwordText = new JPasswordField(10);
		hostText = new JTextField("Enter host...", 20);
		hostText.addActionListener(this);
		hostText.setActionCommand("host textfield");
		JTextField portText = new JTextField("80", 2);

		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0.3F, 0.1F, 0, 0, 0, 0, 0, 2, 2, 3);
		loginPanel.add(username, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0.3F, 0.5F, 0, 1);
		loginPanel.add(usernameText, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0.3F, 0.1F, 1, 0);
		loginPanel.add(password, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0.3F, 0.5F, 1, 1);
		loginPanel.add(passwordText, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0.3F, 0.1F, 2, 0);
		loginPanel.add(host, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0.3F, 0.5F, 2, 1);
		loginPanel.add(hostText, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0.0F, 0.1F, 3, 0);
		loginPanel.add(port, gbc);
		
		gbc = GBCSettings.setGBC(gbc, GridBagConstraints.WEST, 0, 0.0F, 0.5F, 3, 1);
		loginPanel.add(portText, gbc);
		
		return loginPanel;
	}

	protected static JLabel newJLabel(String text, int fontSize) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, fontSize));
		
		return label;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		isWindowClosing = true;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
