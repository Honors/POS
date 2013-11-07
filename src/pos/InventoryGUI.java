package pos;
	 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import java.util.Date;

@SuppressWarnings("serial")
public class InventoryGUI extends JFrame implements ActionListener, OutputWindow, Confirmable{
	
	private String path;
	private InventoryManager inventory;
	private Keys key;
	private JPanel content;
	private JTextField textEntry;
	private JButton addButton;
	private JButton searchButton;
	private JButton dumpButton;
	private JButton restoreButton;
	private JButton queryButton;
	private JTextArea output;
	private JScrollPane outputPane;
	
	
	public InventoryGUI(InventoryManager i, String p){
		path = p;
		key = new Keys(path);
		inventory = i;
		
		content = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 5;
		c.insets = new Insets(5,5,0,5);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = .5;
		
		textEntry = new JTextField(30);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		c.anchor = GridBagConstraints.NORTH;
		content.add(textEntry, c);
		
		addButton = new JButton("ADD");
		addButton.addActionListener(this);
		addButton.setActionCommand("add");
		addButton.setMnemonic(KeyEvent.VK_A);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		content.add(addButton, c);
		
		searchButton = new JButton("SEARCH");
		searchButton.setActionCommand("search");
		searchButton.addActionListener(this);
		searchButton.setMnemonic(KeyEvent.VK_S);
		c.gridx = 1;
		c.insets = new Insets(5,3,0,5);
		content.add(searchButton, c);
		
		dumpButton = new JButton("DUMP");
		dumpButton.setActionCommand("dump");
		dumpButton.addActionListener(this);
		dumpButton.setMnemonic(KeyEvent.VK_D);
		c.gridx = 2;
		content.add(dumpButton, c);
		
		restoreButton = new JButton("RESTORE");
		restoreButton.setActionCommand("restore");
		restoreButton.addActionListener(this);
		restoreButton.setMnemonic(KeyEvent.VK_R);
		c.gridx = 3;
		content.add(restoreButton, c);
		
		queryButton = new JButton("QUERY");
		queryButton.setActionCommand("query");
		queryButton.addActionListener(this);
		queryButton.setMnemonic(KeyEvent.VK_Q);
		c.gridx = 4;
		content.add(queryButton, c);
		
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
		c.gridwidth = 5;
		c.insets = new Insets(5,5,5,5);
		content.add(outputPane, c);
		
		setTitle("POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("add")){
			new ProductInfoGUI(inventory, this, new Item(textEntry.getText(), key), key, true);
		}
		if (s.equals("search")){
			new SearchGUI(inventory, this, textEntry.getText(), key);
		}
		if (s.equals("dump")){
			int n = JOptionPane.showConfirmDialog(this, "Are you sure?\nThis will overwrite any existing dumps with the same name.", "Dump", JOptionPane.YES_NO_OPTION);
			if(n == 0)
				actionConfirmed("dump");
		}
		if (s.equals("restore")){
			int n = JOptionPane.showConfirmDialog(this, "Are you sure?\nThis will overwrite the current database and cannot be un-done.\nIt is reccomended that you make a backup first", "Restore", JOptionPane.YES_NO_OPTION);
			if(n == 0)
				actionConfirmed("restore");
		}
		if (s.equals("query")){
			int n = JOptionPane.showConfirmDialog(this, "Are you sure?\nThis cannot be un-done", "Query", JOptionPane.YES_NO_OPTION);
			if(n == 0)
				actionConfirmed("query");
		}
		textEntry.setText("");
		
	}
	
	public void writeToOutput(String s){
		output.append(s);
	}
	
	public void clearOutput(){
		output.setText("");
	}
	
	public void actionConfirmed(String action){
		if (action.equals("dump")){
			String f = textEntry.getText();
			if (f.length() < 1 || f.charAt(1) != ':'){
				f = path + "\\backups\\" + new Date().toString().replace(' ', '_').replace(':', '_') + ".csv";
			}
			BackupWriter backup = new BackupWriter(f, this, inventory);
			try{
				backup.dumpToCSV();
			} catch (Exception r){
				System.out.println(r);
			}
			backup.close();
		}
		if (action.equals("restore")){
			inventory.restoreFromBackup(textEntry.getText());
		}
		if (action.equals("query")){
			writeToOutput("\n" + inventory.exec(textEntry.getText()) + "\n");
		}
	}
	
}