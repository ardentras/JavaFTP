package com.shaunrasmusen.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Display extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	
	public void createGUI() {		
		JPanel header = makeHeader();
		JTabbedPane tabbedPane = makePane();
		
		Container content = getContentPane();
		content.add(header, BorderLayout.NORTH);
		content.add(tabbedPane);
		
		this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		this.setTitle("JavaFTP");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	public JPanel makeHeader() {
		FontMetrics tnr30FM = getFontMetrics(new Font("Times New Roman", Font.PLAIN, 30));

		JPanel header = new JPanel();
		header.setPreferredSize(new Dimension(FRAME_WIDTH, tnr30FM.getHeight() + 10));
		header.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JLabel title = new JLabel("JavaFTP", JLabel.LEFT);
		title.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		title.setAlignmentX(CENTER_ALIGNMENT);
		header.add(title);
		
		return header;
	}
	
	public JTabbedPane makePane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(FRAME_WIDTH, 400));
		tabbedPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(0, 2));
		loginPanel.setPreferredSize(new Dimension(100, 100));
		loginPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JLabel username = newJLabel("Username:", 20);
		JLabel password = newJLabel("Password:", 20);
		JTextField usernameText = new JTextField("Enter username...");
		
		loginPanel.add(username);
		loginPanel.add(password);
		loginPanel.add(usernameText);
		
		tabbedPane.addTab("Login", loginPanel);
		
		return tabbedPane;
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
