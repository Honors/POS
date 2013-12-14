package pos.swing.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pos.backup.BackupWriter;
import pos.core.Confirmable;
import pos.lib.Reference;
import pos.log.TimeStamp;
import pos.core.ServerManager;
import pos.item.InventoryItem;
import pos.item.ReturnItem;
import pos.swing.JFramePOS;
import pos.swing.JToggleEnableButton;
import pos.core.Keys;
import pos.core.OutputWindow;
import pos.dialog.DialogSingleComboBox;
import pos.swing.SearchResult;

public class InventoryGUI extends JFramePOS implements OutputWindow, ActionListener, Confirmable, ChangeListener {

	private static final long serialVersionUID = -1245352016605793408L;

	private String path;
		
	private JTextArea ICOutput;
	private JScrollPane IMOutput, RMOutput;
	private JScrollPane ICOutputPane;
	private JSpinner ICMultipleText;
	private JTextField ICTextEntry, IMTextEntry, RMTextEntry;
	private JPanel ICContent, IMContent, RMContent, IMResults, RMResults, ICSearchBar, IMSearchBar, RMSearchBar;
	private JTabbedPane tabs;
	private JToggleButton ICModeIncoming, ICModeOutgoing, ICModeReturn;
	private JButton IMBackup, IMRestore, IMNew, ICEnter, IMEnter, RMEnter, IMRegister;
	private JToggleEnableButton ICMultiple;
	private ButtonGroup ICModes;
	
	public InventoryGUI(ServerManager i, OutputWindow out, String p, Keys keys){
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
		c.gridwidth = 5;
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
		ICModeIncoming.addActionListener(this);
		ICModeIncoming.setOpaque(true);
		ICModeIncoming.setBackground(new Color(0xc5c1b8));
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .5;
		c.insets = new Insets(5,5,0,0);
		ICModes.add(ICModeIncoming);
		ICContent.add(ICModeIncoming, c);
		
		ICModeOutgoing = new JToggleButton("OUTGOING");
		ICModeOutgoing.setSelected(true);
		ICModeOutgoing.addActionListener(this);
		ICModeOutgoing.setOpaque(true);
		ICModeOutgoing.setBackground(new Color(0xc5c1b8));
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5,0,0,0);
		ICModes.add(ICModeOutgoing);
		ICContent.add(ICModeOutgoing, c);
		
		ICModeReturn = new JToggleButton("RETURN");
		ICModeReturn.addActionListener(this);
		ICModeReturn.setOpaque(true);
		ICModeReturn.setBackground(new Color(0xc5c1b8));
		c.gridx = 2;
		c.gridy = 1;
		ICModes.add(ICModeReturn);
		ICContent.add(ICModeReturn, c);
		
		ICMultiple = new JToggleEnableButton("MULTIPLE", false);
		c.gridx = 3;
		c.gridy = 1;
		c.weightx = 0;
		c.insets = new Insets(5,10,0,0);
		ICContent.add(ICMultiple, c);
		
		SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 1000, 1);
		ICMultipleText = new JSpinner(model);
		c.gridx = 4;
		c.gridy = 1;
		c.weightx = .2;
		c.insets = new Insets(5,5,0,5);
		ICMultiple.add(ICMultipleText);
		ICContent.add(ICMultipleText, c);
		
		ICOutput = new JTextArea();
		ICOutput.setFont(ICOutput.getFont().deriveFont(14f));
		ICOutput.setLineWrap(true);
		ICOutput.setWrapStyleWord(true);
		ICOutput.setBorder(new EmptyBorder(5,5,5,5));
		ICOutputPane = new JScrollPane(ICOutput);
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 1;
		c.gridwidth = 5;
		c.ipadx = 500;
		c.ipady = 550;
		c.insets = new Insets(5,5,5,5);
		ICContent.add(ICOutputPane, c);
		

		IMContent = new JPanel(new GridBagLayout());
		c.ipady = 5;
		c.ipadx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.insets = new Insets(6,5,0,5);
		
		IMSearchBar = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
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
		
		IMRegister = new JButton("REGISTER");
		IMRegister.addActionListener(this);
		c.gridx = 3;
		c.gridy = 1;
		IMContent.add(IMRegister, c);
		
		IMResults = new JPanel();
		IMResults.setLayout(new GridBagLayout());
		
		IMOutput = new JScrollPane(IMResults);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 4;
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
		
		/*
		RMBackup = new JButton("BACKUP");
		RMBackup.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .5;
		c.insets = new Insets(5,5,0,5);
		RMContent.add(RMBackup, c);
		*/
		
		RMResults = new JPanel();
		RMResults.setLayout(new GridBagLayout());
		
		RMOutput = new JScrollPane(RMResults);
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		c.insets = new Insets(5,5,5,5);
		RMContent.add(RMOutput, c);
		
		tabs = new JTabbedPane();
		tabs.addChangeListener(this);
		tabs.addTab("Inventory Control", ICContent);
		tabs.addTab("Inventory Maintenance", IMContent);
		tabs.addTab("Return Maintenance", RMContent);
		
		setTitle("Inventory");
		setContentPane(tabs);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		updateInventory();
		updateReturn();
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
		
		if(event.getSource().equals(IMTextEntry) || event.getSource().equals((IMEnter))){
			actionConfirmed("IMsearch");
		}
		
		if(event.getSource().equals(IMNew)){
			actionConfirmed("new");
		}
		
		if(event.getSource().equals(IMBackup)){
			int n = JOptionPane.showConfirmDialog(this, "Are you sure?\nThis will overwrite any existing exports with the same name.", "Backup", JOptionPane.YES_NO_OPTION);
			if(n == 0)
				actionConfirmed("inventory_backup");
		}
		
		if (event.getSource().equals(IMRestore)){
			int n = JOptionPane.showConfirmDialog(this, "Are you sure?\nThis will overwrite the current database and cannot be un-done.\nIt is reccomended that you make a backup first", "Restore", JOptionPane.YES_NO_OPTION);
			if(n == 0)
				actionConfirmed("restore");
		}
		
		if (event.getSource().equals(IMRegister)){
			new RegisterGUI(server, this, keys);
		}
	}
	
	@Override
	public void actionConfirmed(String action) {
		if(action.equals("incoming")){
			ArrayList<InventoryItem> toChange = server.searchInventory("UPC='" + ICTextEntry.getText().trim() + "'");
			int oldVal;
			int change;
			if(toChange.size() < 1){
				JOptionPane.showMessageDialog(new JFrame(),"Scanned item does not exist in the inventory", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if(toChange.size() == 1){
				oldVal = toChange.get(0).quantity;
				if(ICMultiple.isSelected()){
					change = Integer.parseInt(ICMultipleText.getValue().toString());
					toChange.get(0).quantity += change;
					ICMultipleText.setValue(1);
					ICMultiple.setSelected(false);
				} else {
					change = 1;
					toChange.get(0).quantity += change;
				}
				server.updateInventoryItem(toChange.get(0));
				updateInventory();
				
				change = toChange.get(0).quantity - oldVal;
				
				String statement = "[+] (" + TimeStamp.simpleDateAndTime() + ") UPC:\"" + toChange.get(0).UPC + "\", Name:\"" + toChange.get(0).name + "\":  " + oldVal + "  ->  " + toChange.get(0).quantity;
				writeToOutput(statement + "\n\n");
				//TODO log the change
			} else if(toChange.size() > 1){
				JOptionPane.showMessageDialog(new JFrame(),"Scanned item has duplicates", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			ICTextEntry.setText("");
		}
		
		if(action.equals("outgoing")){
			ArrayList<InventoryItem> toChange = server.searchInventory("UPC='" + ICTextEntry.getText().trim() + "'");
			int oldVal;
			int change;
			if(toChange.size() < 1){
				JOptionPane.showMessageDialog(new JFrame(),"Scanned item does not exist in the inventory", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if(toChange.size() == 1){
				if(toChange.get(0).quantity > 0){
					oldVal = toChange.get(0).quantity;
					if(ICMultiple.isSelected()){
						change = Integer.parseInt(ICMultipleText.getValue().toString());
						toChange.get(0).quantity -= change;
						if(toChange.get(0).quantity < 0){
							toChange.get(0).quantity = 0;
							JOptionPane.showMessageDialog(new JFrame(),"Scanned item's quantity cannot decrease below 0\nThe quantity for the scanned item is 0", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						ICMultipleText.setValue(1);
						ICMultiple.setSelected(false);
					} else {
						change = 1;
						toChange.get(0).quantity -= 1;
					}
					server.updateInventoryItem(toChange.get(0));
					updateInventory();
					
					change = toChange.get(0).quantity - oldVal;
					
					String statement = "[-] (" + TimeStamp.simpleDateAndTime() + ") UPC:\"" + toChange.get(0).UPC + "\", Name:\"" + toChange.get(0).name + "\":  " + oldVal + "  ->  " + toChange.get(0).quantity;
					writeToOutput(statement + "\n\n");
					//TODO log the change
				} else {
					JOptionPane.showMessageDialog(new JFrame(),"Scanned item's quantity cannot decrease below 0\nThe quantity for the scanned item is 0", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} else if(toChange.size() > 1){
				JOptionPane.showMessageDialog(new JFrame(),"Scanned item has duplicates", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			ICTextEntry.setText("");
		}
		
		if(action.equals("return")){
			ArrayList<InventoryItem> toReturn = server.searchInventory("UPC='" + ICTextEntry.getText().trim() + "'");
			if(toReturn.size() < 1){
				JOptionPane.showMessageDialog(new JFrame(),"Scanned item does not exist in the inventory", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (toReturn.size() == 1){
				DialogSingleComboBox statusDialog = new DialogSingleComboBox(new JFrame(), "Status...", "Please select the return status:", Reference.STATUSES);
				if(statusDialog.getValidated()){
					String status = statusDialog.getValidatedInput();
					ReturnItem item = new ReturnItem(toReturn.get(0), status);
					item.SKU = server.getMaxReturnSKU() + 1;
					item.date = TimeStamp.simpleDate();
					if(ICMultiple.isEnabled()){
						item.quantity = Integer.parseInt(ICMultipleText.getValue().toString());
					}
					
					server.insertReturnItem(item);
					updateReturn();
					if(item.status == Reference.STATUS_TO_INVENTORY){
						toReturn.get(0).quantity += item.quantity;
						server.updateInventoryItem(toReturn.get(0));
						updateInventory();
					}
					
					String statement = "[*] (" + TimeStamp.simpleDateAndTime() + ") UPC:\"" + item.UPC + "\", Name:\"" + item.name + "\":  " + item.status;
					writeToOutput(statement + "\n\n");
					//TODO log change
				}
			} else if (toReturn.size() > 1){
				JOptionPane.showMessageDialog(new JFrame(),"Scanned item has duplicates", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			ICTextEntry.setText("");
		}
		
		if(action.equals("IMsearch")){
			updateInventory();
		}
		
		if(action.equals("new")){
			new ProductInfoGUI(server, this, null, new InventoryItem(), keys, Reference.NEW_PRODUCT);
		}
		
		if (action.equals("inventory_backup")){
			String f = IMTextEntry.getText();
			if (f.length() < 1 || f.charAt(1) != ':'){
				f = path + "\\backups\\" + new Date().toString().replace(' ', '_').replace(':', '_') + ".csv";
			}
			BackupWriter backup = new BackupWriter(f, this, server);
			try{
				backup.exportInventoryToCSV();
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
		ArrayList<InventoryItem> i;
		if (search.length() == 0){
			i = server.searchInventory("SKU > -1");
		} else {
			i = server.searchInventory("UPC = '" + search + "'");
		}
		boolean colorized = true;
		while (!i.isEmpty()){
			SearchResult s = new SearchResult(this, this, i.remove(0), keys, Reference.EDIT_PRODUCT);
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
	
	public void updateReturn(){
		//Make Search for UPC, not SKU
		RMResults.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		String search = RMTextEntry.getText();
		ArrayList<ReturnItem> i;
		if (search.length() == 0){
			i = server.searchReturn("SKU > -1");
		} else {
			i = server.searchReturn("UPC = '" + search + "'");
		}
		boolean colorized = true;
		while (!i.isEmpty()){
			SearchResult s = new SearchResult(this, this, i.remove(0), keys, Reference.RETURN_PRODUCT);
			s.setOpaque(true);
			if(colorized)
				s.setBackground(new Color(0xf2dbd4));
			else
				s.setBackground(Color.WHITE);
			colorized = !colorized;
			RMResults.add(s, c);
			c.gridy++;
		}
		c.weighty = 1;
		RMResults.add(new JPanel(), c);
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
		System.out.println("InventoryGUI updated");
		updateInventory();
		updateReturn();
	}
}
