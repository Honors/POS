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
import pos.log.ReadableLogger;
import pos.swing.JFramePOS;
import pos.core.Keys;
import pos.core.OutputWindow;
import pos.core.UpdatableWindow;

@SuppressWarnings("serial")
public class HomeGUI extends JFramePOS implements ActionListener, OutputWindow, UpdatableWindow{
	
	//TODO create logo
	private ImageIcon imgLogo = new ImageIcon(this.getClass().getResource("/resources/images/logo/logo.png"));
	
	private String path;
	
	private JMenuBar menuBar;
	private JMenu adminMenu;
	private JMenuItem maintinanceItem, loginsItem;
	
	private JPanel content;
	private JLabel logo;
	private JButton inventoryButton, searchButton, reportButton;
	private JTextArea output;
	private JScrollPane outputPane;
	
	
	public HomeGUI(ServerManager i, String p){
		super(i, null, new Keys(i));
		path = p;
 		
		menuBar = new JMenuBar();
		
		adminMenu = new JMenu("Admin");
		adminMenu.setEnabled(server.isAdmin());
		
		maintinanceItem = new JMenuItem("Maintinance");
		maintinanceItem.addActionListener(this);
		
		loginsItem = new JMenuItem("Manage Logins");
		loginsItem.addActionListener(this);
		
		adminMenu.add(maintinanceItem);
		adminMenu.add(loginsItem);
		
		menuBar.add(adminMenu);
		
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
			TransactionGUI maintinanceWindow = new TransactionGUI(server, this, keys);
		}
		
		if(event.getSource().equals(searchButton)){
			SearchGUI searchWindow = new SearchGUI(server, this, "", keys);
		}
		
		if(event.getSource().equals(reportButton)){
			ReportGUI reportWindow = new ReportGUI();
		}
		
		if(event.getSource().equals(maintinanceItem)){
			MaintinanceGUI maintinanceWindow = new MaintinanceGUI(server, this, path, keys);
		}
		
		if(event.getSource().equals(loginsItem)){
			LoginManagerGUI loginManagerWindow = new LoginManagerGUI(server, this);
		}
	}
	
	public void writeToOutput(String s){
		output.append(s);
		ReadableLogger.write(path, s);
	}
	
	public void clearOutput(){
		output.setText("");
	}
	
	@Override
	public void update(String command) {

	}
}