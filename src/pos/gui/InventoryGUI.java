package pos.gui;
//test
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pos.backup.BackupWriter;
import pos.core.Confirmable;
import pos.core.InventoryManager;
import pos.model.Item;
import pos.core.JFramePOS;
import pos.model.Keys;
import pos.core.OutputWindow;
import pos.model.SearchItem;

public class InventoryGUI extends JFramePOS implements OutputWindow, ActionListener, Confirmable, ChangeListener {

	private static final long serialVersionUID = -1245352016605793408L;

	private String path;
		
	private JTextArea ICOutput;
	private JScrollPane IMOutput, RMOutput;
	private JScrollPane ICOutputPane;
	private JTextField ICTextEntry, IMTextEntry, RMTextEntry;
	private JPanel ICContent, IMContent, RMContent, IMResults, RMResults, ICSearchBar, IMSearchBar, RMSearchBar;
	private JTabbedPane tabs;
	private JToggleButton ICModeIncoming, ICModeOutgoing, ICModeReturn;
	private JButton IMBackup, IMRestore, RMBackup, IMNew, ICEnter, IMEnter, RMEnter;
	private ButtonGroup ICModes;
	
	public InventoryGUI(InventoryManager i, OutputWindow out, String p, Keys keys){
		super(i,out,keys);
		path = p;
		
		ICContent = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 5;
		c.insets = new Insets(7,5,0,5);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;
		
		ICSearchBar = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		ICContent.add(ICSearchBar, c);	
		
		ICTextEntry = new JTextField();
		ICTextEntry.addActionListener(this);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		c.insets = new Insets(0,0,0,5);
		ICSearchBar.add(ICTextEntry, c);
		
		ICEnter = new JButton("ENTER");
		ICEnter.addActionListener(this);
		ICEnter.setOpaque(true);
		ICEnter.setBackground(new Color(0x98CC98));
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0;
		c.insets = new Insets(0,0,0,0);
		ICSearchBar.add(ICEnter, c);
		
		ICModes = new ButtonGroup();
		
		ICModeIncoming = new JToggleButton("INCOMING");
		ICModeIncoming.setSelected(true);
		ICModeIncoming.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .5;
		c.insets = new Insets(5,5,0,5);
		ICModes.add(ICModeIncoming);
		ICContent.add(ICModeIncoming, c);
		
		ICModeOutgoing = new JToggleButton("OUTGOING");
		ICModeOutgoing.addActionListener(this);
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5,0,0,5);
		ICModes.add(ICModeOutgoing);
		ICContent.add(ICModeOutgoing, c);
		
		ICModeReturn = new JToggleButton("RETURN");
		ICModeReturn.addActionListener(this);
		c.gridx = 2;
		c.gridy = 1;
		ICModes.add(ICModeReturn);
		ICContent.add(ICModeReturn, c);
		
		ICOutput = new JTextArea();
		ICOutput.setLineWrap(true);
		ICOutput.setWrapStyleWord(true);
		ICOutputPane = new JScrollPane(ICOutput);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.ipadx = 450;
		c.ipady = 550;
		c.insets = new Insets(5,5,5,5);
		ICContent.add(ICOutputPane, c);
		

		IMContent = new JPanel(new GridBagLayout());
		c.ipady = 5;
		c.ipadx = 0;
		c.gridwidth = 1;
		c.insets = new Insets(6,5,0,5);
		
		IMSearchBar = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		IMContent.add(IMSearchBar, c);
		
		IMTextEntry = new JTextField();
		IMTextEntry.addActionListener(this);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		c.insets = new Insets(0,0,0,5);
		IMSearchBar.add(IMTextEntry, c);
		
		IMEnter = new JButton("ENTER");
		IMEnter.addActionListener(this);
		IMEnter.setOpaque(true);
		IMEnter.setBackground(new Color(0x98CC98));
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0;
		c.insets = new Insets(0,0,0,0);
		IMSearchBar.add(IMEnter, c);
		
		IMNew = new JButton("NEW");
		IMNew.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .5;
		c.insets = new Insets(5,5,0,5);
		IMContent.add(IMNew, c);
		
		IMBackup = new JButton("BACKUP");
		IMBackup.addActionListener(this);
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5,0,0,5);
		IMContent.add(IMBackup, c);
		
		IMRestore = new JButton("RESTORE");
		IMRestore.addActionListener(this);
		c.gridx = 2;
		c.gridy = 1;
		IMContent.add(IMRestore, c);
		
		IMResults = new JPanel();
		IMResults.setLayout(new GridBagLayout());
		
		IMOutput = new JScrollPane(IMResults);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.weighty = 1;
		c.insets = new Insets(5,5,5,5);
		IMContent.add(IMOutput, c);
		
		
		RMContent = new JPanel(new GridBagLayout());
		c.ipady = 5;
		c.ipadx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.insets = new Insets(6,5,0,5);
		
		RMSearchBar = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		RMContent.add(RMSearchBar, c);
		
		RMTextEntry = new JTextField();
		RMTextEntry.addActionListener(this);
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,5);
		c.weightx = 1;
		RMSearchBar.add(RMTextEntry, c);
		
		RMEnter = new JButton("ENTER");
		RMEnter.addActionListener(this);
		RMEnter.setOpaque(true);
		RMEnter.setBackground(new Color(0x98CC98));
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		RMSearchBar.add(RMEnter, c);
		
		RMBackup = new JButton("BACKUP");
		RMBackup.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .5;
		c.insets = new Insets(5,5,0,5);
		RMContent.add(RMBackup, c);
		
		RMResults = new JPanel();
		RMResults.setLayout(new BoxLayout(RMResults, 1));
		
		RMOutput = new JScrollPane(RMResults);
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 1;
		c.insets = new Insets(5,5,5,5);
		RMContent.add(RMOutput, c);
		
		tabs = new JTabbedPane();
		tabs.addChangeListener(this);
		tabs.addTab("Inventory Control", ICContent);
		tabs.addTab("Inventory Maintenance", IMContent);
		tabs.addTab("Returns Maintenance", RMContent);
		
		setTitle("Inventory");
		setContentPane(tabs);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		updateInventory();
		ICTextEntry.requestFocus();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		ICTextEntry.requestFocus();
		
		if(event.getSource().equals(ICTextEntry) || event.getSource().equals(ICEnter)){
			if(ICModeIncoming.isSelected()){
				actionConfirmed("incoming");
			}
			
			if(ICModeOutgoing.isSelected()){
				actionConfirmed("outgoing");
			}
			
			if(ICModeReturn.isSelected()){
				actionConfirmed("return");
			}
		}
		
		if(event.getSource().equals(IMTextEntry)){
			actionConfirmed("IMsearch");
		}
		
		if(event.getSource().equals(IMNew)){
			actionConfirmed("new");
		}
		
		if(event.getSource().equals(IMBackup)){
			int n = JOptionPane.showConfirmDialog(this, "Are you sure?\nThis will overwrite any existing exports with the same name.", "Backup", JOptionPane.YES_NO_OPTION);
			if(n == 0)
				actionConfirmed("backup");
		}
		
		if (event.getSource().equals(IMRestore)){
			int n = JOptionPane.showConfirmDialog(this, "Are you sure?\nThis will overwrite the current database and cannot be un-done.\nIt is reccomended that you make a backup first", "Restore", JOptionPane.YES_NO_OPTION);
			if(n == 0)
				actionConfirmed("restore");
		}
	}
	
	@Override
	public void actionConfirmed(String action) {
		if(action.equals("incoming")){
			
		}
		
		if(action.equals("outgoing")){
			
		}
		
		if(action.equals("return")){
			
		}
		
		if(action.equals("IMsearch")){
			updateInventory();
		}
		
		if(action.equals("new")){
			new ProductInfoGUI(inventory, this, null, new Item("", keys), keys, Item.NEW_PRODUCT);
		}
		
		if (action.equals("backup")){
			String f = IMTextEntry.getText();
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
			//TODO rewrite import (this doesn't work and i have no idea how it worked in the first place)
			//inventory.restoreFromBackup(textEntry.getText());
		}
	}

	@Override
	public void writeToOutput(String s) {
		ICOutput.append(s);
		parentWindow.writeToOutput(s);
	}

	@Override
	public void clearOutput() {
		ICOutput.setText("");
		
	}

	
	public void updateInventory(){
		//Make Search for UPC, not SKU
		IMResults.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		String search = IMTextEntry.getText();
		ArrayList<Item> i;
		if (search.length() == 0){
			i = inventory.searchInventory("SKU > -1");
		} else {
			i = inventory.searchInventory("UPC = '" + search + "'");
		}
		boolean colorized = true;
		while (!i.isEmpty()){
			SearchItem s = new SearchItem(this, i.remove(0), keys, Item.EDIT_PRODUCT);
			s.setOpaque(true);
			if(colorized)
				s.setBackground(new Color(0xD4EBF2));
			else
				s.setBackground(Color.WHITE);
			colorized = !colorized;
			IMResults.add(s, c);
			c.gridy++;
		}
		c.weighty = 1;
		IMResults.add(new JPanel(), c);
		this.paintAll(getGraphics());
	}
	
	
	@Override
	public void stateChanged(ChangeEvent event) {
		if(event.getSource().equals(ICContent)){
			ICTextEntry.requestFocus();
		}
		if(event.getSource().equals(IMContent)){
			updateInventory();
			IMTextEntry.requestFocus();
		}
		if(event.getSource().equals(RMContent)){
			RMTextEntry.requestFocus();
		}
		
	}

	@Override
	public void update() {
		updateInventory();
		
	}
}
