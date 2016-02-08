package com.shaunrasmusen.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Display extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	private static final Dimension WINDOW_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
	private FontMetrics tnr30FM;
	
	public void createGUI() {
		tnr30FM = getFontMetrics(new Font("Times New Roman", Font.PLAIN, 30));
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel header = makeHeader();
		JPanel container = makePane(gbc);
		
		Container content = getContentPane();
		content.setLayout(new GridBagLayout());
		//content.setPreferredSize(WINDOW_SIZE);
		
		gbc = setGBC(gbc, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 1.0F, 0.25F, 0, 0, 0, tnr30FM.getHeight() + 10);
		content.add(header, gbc);
		
		gbc = setGBC(gbc, GridBagConstraints.FIRST_LINE_START, 0, 1.0F, 0.75F, 0, 1, FRAME_WIDTH / 2, 100);
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
		header.setPreferredSize(new Dimension(FRAME_WIDTH, tnr30FM.getHeight() + 10));
		header.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JLabel title = new JLabel("JavaFTP", JLabel.LEFT);
		title.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		title.setAlignmentX(CENTER_ALIGNMENT);
		header.add(title);
		
		return header;
	}
	
	public JPanel makePane(GridBagConstraints gbc) {		
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setSize(new Dimension(200, 200));
		container.setAlignmentX(LEFT_ALIGNMENT);
		container.setAlignmentY(TOP_ALIGNMENT);
		
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridBagLayout());
		loginPanel.setPreferredSize(new Dimension(100, 100));
		loginPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		loginPanel.setAlignmentX(LEFT_ALIGNMENT);
		loginPanel.setAlignmentY(TOP_ALIGNMENT);
		
		JLabel username = newJLabel("Username:", 16);
		JLabel password = newJLabel("Password:", 16);
		JLabel host = newJLabel("Host:", 16);
		JLabel port = newJLabel("Port:", 16);
		JTextField usernameText = new JTextField("Enter username...");
		JPasswordField passwordText = new JPasswordField();
		JTextField hostText = new JTextField("Enter host...");
		JTextField portText = new JTextField("80");

		gbc = setGBC(gbc, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, 0.5F, 0.5F, 0, 0, 20, 0);
		loginPanel.add(username, gbc);
		
		gbc = setGBC(gbc, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 0.5F, 0.5F, 1, 0, 75, 0);
		loginPanel.add(usernameText, gbc);
		
		gbc = setGBC(gbc, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, 0.5F, 0.5F, 2, 0, 20, 0);
		loginPanel.add(password, gbc);
		
		gbc = setGBC(gbc, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 0.5F, 0.5F, 3, 0, 75, 0);
		loginPanel.add(passwordText, gbc);
		
		gbc = setGBC(gbc, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, 0.5F, 0.5F, 0, 1, 20, 0);
		loginPanel.add(host, gbc);
		
		gbc = setGBC(gbc, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 0.5F, 0.5F, 1, 1, 75, 0);
		loginPanel.add(hostText, gbc);
		
		gbc = setGBC(gbc, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, 0.5F, 0.5F, 2, 1, 20, 0);
		loginPanel.add(port, gbc);
		
		gbc = setGBC(gbc, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 0.5F, 0.5F, 3, 1, 75, 0);
		loginPanel.add(portText, gbc);
		
		gbc = setGBC(gbc, GridBagConstraints.FIRST_LINE_START, 0, 0.5F, 0.5F, 0, 0, FRAME_WIDTH / 2, 100);
		container.add(loginPanel, gbc);
		
		return container;
	}
	
	private GridBagConstraints setGBC(GridBagConstraints gbc, 
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
	
	public JLabel newJLabel(String text, int fontSize) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		
		return label;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
