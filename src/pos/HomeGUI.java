package pos;
	 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

@SuppressWarnings("serial")
public class HomeGUI extends JFramePOS implements ActionListener, OutputWindow{
	
	//TODO create logo
	private ImageIcon imgLogo;
	
	private String path;
	private JPanel content;
	private JLabel logo;
	private JButton inventoryButton, searchButton, reportButton;
	private JTextArea output;
	private JScrollPane outputPane;
	
	
	public HomeGUI(InventoryManager i, String p){
		super(i, null, new Keys(p));
		path = p;
		
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
		c.ipady = 140;
		content.add(logo, c);
		
		inventoryButton = new JButton("INVENTORY");
		inventoryButton.addActionListener(this);
		inventoryButton.setActionCommand("inventory");
		inventoryButton.setMnemonic(KeyEvent.VK_A);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.ipady = 5;
		content.add(inventoryButton, c);
		
		searchButton = new JButton("SEARCH");
		searchButton.setActionCommand("search");
		searchButton.addActionListener(this);
		searchButton.setMnemonic(KeyEvent.VK_S);
		c.gridx = 1;
		c.insets = new Insets(5,0,0,5);
		content.add(searchButton, c);
		

		reportButton = new JButton("REPORT");
		reportButton.setActionCommand("report");
		reportButton.addActionListener(this);
		reportButton.setMnemonic(KeyEvent.VK_Q);
		c.gridx = 2;
		content.add(reportButton, c);
		
		output = new JTextArea();
		output.setEditable(false);
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		outputPane = new JScrollPane(output);
		c.gridx = 0;
		c.gridy = 2;
		c.ipadx = 449;
		c.ipady = 552;
		c.weighty = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5,5,5,5);
		content.add(outputPane, c);
		
		setTitle("POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		System.out.println(logo.getSize());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		
		if (s.equals("inventory")){
			new InventoryGUI(inventory, this, path, keys);
		}
		
		if (s.equals("search")){
			new SearchGUI(inventory, this, "", keys);
		}
		
		if (s.equals("report")){
			//TODO ReportGUI and systems
		}
	}
	
	public void writeToOutput(String s){
		output.append(s);
		//Log to the text file
	}
	
	public void clearOutput(){
		output.setText("");
	}

}