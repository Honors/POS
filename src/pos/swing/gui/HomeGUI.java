package pos.swing.gui;
	 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pos.core.ServerManager;
import pos.log.LogInfoGenerator;
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
	private JMenuItem maintinanceItem;
	
	private JPanel content, executeBar;
	private JLabel logo;
	private JButton inventoryButton, searchButton, reportButton, executeButton;
	private JTextField executeField;
	private JTextArea output;
	private JScrollPane outputPane;
	
	
	public HomeGUI(ServerManager i, String p){
		super(i, null, new Keys(p));
		path = p;
 		
		menuBar = new JMenuBar();
		
		adminMenu = new JMenu("Admin");
		
		maintinanceItem = new JMenuItem("Maintinance");
		maintinanceItem.addActionListener(this);
		
		adminMenu.add(maintinanceItem);
		
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
		
		executeBar = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 5;
		c.gridwidth = 3;
		content.add(executeBar, c);
		
		executeField = new JTextField();
		executeField.addActionListener(this);
		executeField.setFont(new Font("Courier New", Font.PLAIN, 12));
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0,0,0,5);
		executeBar.add(executeField, c);
		
		executeButton = new JButton("EXECUTE");
		executeButton.addActionListener(this);
		executeButton.setOpaque(true);
		executeButton.setBackground(new Color(0xFF3A3A));
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		executeBar.add(executeButton, c);
		
		inventoryButton = new JButton("TRANSACTION");
		inventoryButton.addActionListener(this);
		inventoryButton.setMnemonic(KeyEvent.VK_T);
		c.gridx = 0;
		c.gridy = 2;
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
		c.gridy = 3;
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
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(executeField) || event.getSource().equals(executeButton)){
			writeToOutput(LogInfoGenerator.generateServerCommandStatement(executeField.getText(), server.exec(executeField.getText())) + "\n\n");
			executeField.setText("");
		}
		
		if (event.getSource().equals(inventoryButton)){
			TransactionGUI maintinanceWindow = new TransactionGUI(server, this, keys);
		}
		
		if (event.getSource().equals(searchButton)){
			SearchGUI searchWindow = new SearchGUI(server, this, "", keys);
		}
		
		if (event.getSource().equals(reportButton)){
			ReportGUI reportWindow = new ReportGUI();
		}
		
		if (event.getSource().equals(maintinanceItem)){
			MaintinanceGUI maintinanceWindow = new MaintinanceGUI(server, this, path, keys);
		}
	}
	
	public void writeToOutput(String s){
		output.append(s);
		//Log to the text file
	}
	
	public void clearOutput(){
		output.setText("");
	}
	
	@Override
	public void update(String command) {

	}
}