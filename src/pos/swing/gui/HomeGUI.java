package pos.swing.gui;
	 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pos.core.ServerManager;
import pos.log.LogInfoGenerator;
import pos.swing.JFramePOS;
import pos.core.Keys;
import pos.core.OutputWindow;

@SuppressWarnings("serial")
public class HomeGUI extends JFramePOS implements ActionListener, OutputWindow{
	
	//TODO create logo
	private ImageIcon imgLogo = new ImageIcon(this.getClass().getResource("/resources/images/logo/logo.png"));
	
	private String path;
	
	private JMenuBar menuBar;
	private JMenu adminMenu, helpMenu;
	private JMenuItem maintenanceItem, loginsItem, changeHistoryItem, aboutItem;
	
	private JPanel content;
	private JLabel logo;
	private JButton inventoryButton, searchButton, reportButton;
	private JTextArea output;
	private JScrollPane outputPane;
	
	
	public HomeGUI(ServerManager i){
		super(i, null, new Keys(i));
 		
		menuBar = new JMenuBar();
		
		adminMenu = new JMenu("Admin");
		adminMenu.setEnabled(server.isAdmin());
		
		maintenanceItem = new JMenuItem("Maintenance");
		maintenanceItem.addActionListener(this);
		adminMenu.add(maintenanceItem);
		
		loginsItem = new JMenuItem("Manage Logins");
		loginsItem.addActionListener(this);
		adminMenu.add(loginsItem);
		
		changeHistoryItem = new JMenuItem("Change History");
		changeHistoryItem.addActionListener(this);
		adminMenu.add(changeHistoryItem);
		
		menuBar.add(adminMenu);
		
		helpMenu = new JMenu("Help");
		
		aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(this);
		helpMenu.add(aboutItem);
		
		menuBar.add(helpMenu);

		
		content = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 5;
		c.insets = new Insets(5,5,0,5);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = .5;
		
		logo = new JLabel(imgLogo);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		content.add(logo, c);
		
		inventoryButton = new JButton("TRANSACTION");
		inventoryButton.addActionListener(this);
		inventoryButton.setMnemonic(KeyEvent.VK_T);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx = .5;
		c.insets = new Insets(5,5,0,5);
		content.add(inventoryButton, c);
		
		searchButton = new JButton("SEARCH");
		searchButton.addActionListener(this);
		searchButton.setMnemonic(KeyEvent.VK_S);
		c.gridx = 1;
		c.insets = new Insets(5,0,0,5);
		content.add(searchButton, c);
		

		reportButton = new JButton("REPORT");
		reportButton.addActionListener(this);
		reportButton.setMnemonic(KeyEvent.VK_R);
		c.gridx = 2;
		content.add(reportButton, c);
		
		output = new JTextArea();
		output.setEditable(false);
		output.setFont(new Font("Courier New", Font.PLAIN, 14));
		output.setBorder(new EmptyBorder(5,5,5,5));
		outputPane = new JScrollPane(output);
		c.gridx = 0;
		c.gridy = 2;
		c.ipadx = 500;
		c.ipady = 400;
		c.weighty = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5,5,5,5);
		content.add(outputPane, c);
		
		setTitle("Point of Sale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		writeToOutput(LogInfoGenerator.generateProgramStartStatement(server.getUsername()));
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource().equals(inventoryButton)){
			new TransactionGUI(server, this, keys);
		}
		
		if(event.getSource().equals(searchButton)){
			new SearchGUI(server, this, "", keys);
		}
		
		if(event.getSource().equals(reportButton)){
			new ReportGUI();
		}
		
		if(event.getSource().equals(maintenanceItem)){
			new MaintenanceGUI(server, this, path, keys);
		}
		
		if(event.getSource().equals(loginsItem)){
			new LoginManagerGUI(server);
		}
		
		if(event.getSource().equals(changeHistoryItem)){
			new ChangeHistoryGUI(server);
		}
		
		if(event.getSource().equals(aboutItem)){
			String aboutMessage = "This is the inventory and transactions manager for" + "\n" +
								  "the Bishop Watterson High School Spirit Store" + "\n" +
								  "\n" +
								  "Version: 1.0.1_1" + "\n" +
								  "\n" +
								  "By: James Madden, Matt Neary" + "\n" +
								  "\n" + 
								  "Property of Bishop Watterson High School";
			
       	 	JOptionPane.showMessageDialog(new JFrame(),aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void writeToOutput(String s){
		output.append(s);
		output.append(System.lineSeparator() + System.lineSeparator());
	}
	
	public void clearOutput(){
		output.setText("");
	}
}