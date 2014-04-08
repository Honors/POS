package pos.swing.content;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pos.backup.BackupReader;
import pos.backup.BackupWriter;
import pos.core.Keys;
import pos.core.OutputWindow;
import pos.core.ServerManager;
import pos.core.UpdateableContent;
import pos.core.UpdateableContentController;
import pos.filter.CSVFilter;
import pos.item.InventoryItem;
import pos.item.ReturnItem;
import pos.lib.Reference;
import pos.log.LogInfoGenerator;
import pos.log.TimeStamp;
import pos.swing.SearchResult;
import pos.swing.gui.ProductInfoGUI;
import pos.swing.gui.RegisterGUI;

public class InventoryMaintinanceContent extends JPanel implements UpdateableContent, ActionListener{

	private static final long serialVersionUID = 3127563271754067755L;
	
	private JPanel searchBar, results;
	private JTextField textEntry;
	private JButton enter, newButton, backup, restore, register;
	private JScrollPane output;
	
	private ServerManager server;
	private OutputWindow parentWindow;
	private Keys keys;
	private String path;
	
	public InventoryMaintinanceContent(ServerManager server, OutputWindow parentWindow, Keys keys, String path){
		this.server = server;
		this.parentWindow = parentWindow;
		this.keys = keys;
		this.path = path;
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;
		c.ipady = 5;
		c.ipadx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,0,5);
		
		searchBar = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		add(searchBar, c);
		
		textEntry = new JTextField();
		textEntry.addActionListener(this);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		c.insets = new Insets(0,0,0,5);
		searchBar.add(textEntry, c);
		
		enter = new JButton("ENTER");
		enter.addActionListener(this);
		enter.setOpaque(true);
		enter.setBackground(new Color(0x98CC98));
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0;
		c.insets = new Insets(0,0,0,0);
		searchBar.add(enter, c);
		
		newButton = new JButton("NEW");
		newButton.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .5;
		c.insets = new Insets(5,5,0,5);
		add(newButton, c);
		
		/* Deprecated
		backup = new JButton("BACKUP");
		backup.addActionListener(this);
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5,0,0,5);
		add(backup, c);
		
		restore = new JButton("RESTORE");
		restore.addActionListener(this);
		c.gridx = 2;
		c.gridy = 1;
		add(restore, c);
		*/
		
		register = new JButton("REGISTER");
		register.addActionListener(this);
		c.gridx = 3;
		c.gridy = 1;
		add(register, c);
		
		results = new JPanel();
		results.setLayout(new GridBagLayout());
		
		output = new JScrollPane(results);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 4;
		c.weighty = 1;
		c.insets = new Insets(5,5,5,5);
		add(output, c);
		
		UpdateableContentController.addActiveContent(this);
		
		updateInventory();
	}

	public void updateInventory(){
		//Make Search for UPC, not SKU
		results.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		String search = textEntry.getText();
		ArrayList<InventoryItem> i;
		if (search.length() == 0){
			i = server.searchInventory("SKU > -1");
		} else {
			i = server.searchInventory("UPC = '" + search + "'");
		}
		boolean colorized = true;
		while (!i.isEmpty()){
			SearchResult s = new SearchResult(server, parentWindow, i.remove(0), keys, Reference.EDIT_PRODUCT);
			s.setOpaque(true);
			if(colorized)
				s.setBackground(new Color(0xD4EBF2));
			else
				s.setBackground(Color.WHITE);
			colorized = !colorized;
			results.add(s, c);
			c.gridy++;
		}
		c.weighty = 1;
		results.add(new JPanel(), c);
		this.paintAll(getGraphics());
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(textEntry) || event.getSource().equals((enter))){
			updateInventory();
			textEntry.requestFocus();
		}
		
		if(event.getSource().equals(newButton)){
			new ProductInfoGUI(server, parentWindow, null, new InventoryItem(), keys, Reference.NEW_PRODUCT);
		}
		
		if(event.getSource().equals(backup)){
			JFileChooser fileChooser = new JFileChooser(path);
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new CSVFilter());
			fileChooser.setSelectedFile(new File("POS_" + TimeStamp.sanitizedDateandTime() + ".csv"));
			int returnVal = fileChooser.showSaveDialog(new JFrame());
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();
				if(!file.getPath().endsWith(".csv"))
					file = new File(file.getPath() + ".csv");
				
				BackupWriter backup = new BackupWriter(file.getPath(), parentWindow, server);
				try{
					backup.exportInventoryToCSV();
					backup.exportReturnToCSV();
					backup.close();
					parentWindow.writeToOutput(LogInfoGenerator.generateServerBackupStatement(server.getUsername(), file.getAbsolutePath()));
				} catch (Exception r){
					System.out.println(r);
				}
			}
		}
		
		if (event.getSource().equals(restore)){
			int n = JOptionPane.showConfirmDialog(this, "Are you sure?\nThis will overwrite the current database and cannot be un-done.\nIt is reccomended that you make a backup first", "Restore", JOptionPane.YES_NO_OPTION);
			if(n == 0){
				JFileChooser fileChooser = new JFileChooser(path);
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(new CSVFilter());
				int returnVal = fileChooser.showOpenDialog(new JFrame());
				if(returnVal == JFileChooser.APPROVE_OPTION){
					BackupReader backup = new BackupReader(fileChooser.getSelectedFile().getPath());
					ArrayList<InventoryItem> inventoryItems = backup.readInventoryFromFile();
					ArrayList<ReturnItem> returnItems = backup.readReturnFromFile();
					backup.close();
					
					server.deleteAllInventory();
					server.deleteAllReturn();
					
					while(!inventoryItems.isEmpty()){
						server.insertInventoryItem(inventoryItems.remove(0));
					}
					
					while(!returnItems.isEmpty()){
						server.insertReturnItem(returnItems.remove(0));
					}
					
					parentWindow.writeToOutput(LogInfoGenerator.generateServerRestoreStatement(server.getUsername(), fileChooser.getSelectedFile().getAbsolutePath()));
				}
			}
		}
		
		if (event.getSource().equals(register)){
			new RegisterGUI(server, parentWindow, keys);
		}
		
	}

	@Override
	public void update(String updateIdentifier, String info) {
		if(updateIdentifier.equals(UpdateableContent.INVENTORY_UPDATED))
			updateInventory();
		
	}
}
