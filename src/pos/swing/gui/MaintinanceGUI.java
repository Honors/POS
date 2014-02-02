package pos.swing.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pos.backup.BackupReader;
import pos.backup.BackupWriter;
import pos.lib.Reference;
import pos.log.LogInfoGenerator;
import pos.log.TimeStamp;
import pos.core.ServerManager;
import pos.item.InventoryItem;
import pos.item.ReturnItem;
import pos.swing.JFramePOS;
import pos.core.Keys;
import pos.core.OutputWindow;
import pos.core.UpdatableWindow;
import pos.filter.CSVFilter;
import pos.swing.SearchResult;

public class MaintinanceGUI extends JFramePOS implements ActionListener, ChangeListener, UpdatableWindow, OutputWindow, KeyListener{

	private static final long serialVersionUID = -1245352016605793408L;

	private String path;
	private ArrayList<String> executedCommands;
	private int index;
		
	private JScrollPane IMOutput, RMOutput, CScrollOutput;
	private JTextField IMTextEntry, RMTextEntry, CTextEntry;
	private JPanel IMContent, RMContent, IMResults, RMResults, IMSearchBar, RMSearchBar, CContent, CSearchBar;
	private JTabbedPane tabs;
	private JButton IMBackup, IMRestore, IMNew, IMEnter, RMEnter, IMRegister, CEnter;
	private JTextArea CTextOutput;
	
	public MaintinanceGUI(ServerManager i, OutputWindow out, String p, Keys keys){
		super(i,out,keys);
		path = p;
		executedCommands = new ArrayList<String>();
		index = -1;
		
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 5;
		c.insets = new Insets(5,5,0,5);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;
		
		IMContent = new JPanel(new GridBagLayout());
		c.ipady = 5;
		c.ipadx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,0,5);
		
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
		c.ipadx = 550;
		c.ipady = 550;
		c.insets = new Insets(5,5,5,5);
		IMContent.add(IMOutput, c);
		
		
		RMContent = new JPanel(new GridBagLayout());
		c.ipady = 5;
		c.ipadx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,0,5);
		
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
		
		RMResults = new JPanel();
		RMResults.setLayout(new GridBagLayout());
		
		RMOutput = new JScrollPane(RMResults);
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		c.insets = new Insets(5,5,5,5);
		RMContent.add(RMOutput, c);
		
		CContent = new JPanel();
		CContent.setLayout(new GridBagLayout());
		c.ipady = 5;
		c.ipadx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,0,5);
		
		CSearchBar = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		CContent.add(CSearchBar, c);
		
		CTextEntry = new JTextField();
		CTextEntry.addActionListener(this);
		CTextEntry.addKeyListener(this);
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,5);
		CSearchBar.add(CTextEntry, c);
		
		CEnter = new JButton("ENTER");
		CEnter.addActionListener(this);
		CEnter.setOpaque(true);
		CEnter.setBackground(new Color(0x98CC98));
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		CSearchBar.add(CEnter, c);
		
		CTextOutput = new JTextArea();
		CTextOutput.setEditable(false);
		CTextOutput.setFont(new Font("Courier New", Font.PLAIN, 14));
		CTextOutput.setBorder(new EmptyBorder(5,5,5,5));
		CScrollOutput = new JScrollPane(CTextOutput);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5,5,5,5);
		c.weightx = 1;
		c.weighty = 1;
		CContent.add(CScrollOutput, c);
		
		
		tabs = new JTabbedPane();
		tabs.addChangeListener(this);
		tabs.addTab("Inventory Maintenance", IMContent);
		tabs.addTab("Return Maintenance", RMContent);
		tabs.addTab("Console", CContent);
		
		setTitle("Maintinance");
		setContentPane(tabs);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		updateInventory();
		updateReturn();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource().equals(IMTextEntry) || event.getSource().equals((IMEnter))){
			updateInventory();
			IMTextEntry.requestFocus();
		}
		
		if(event.getSource().equals(IMNew)){
			new ProductInfoGUI(server, parentWindow, null, new InventoryItem(), keys, Reference.NEW_PRODUCT);
		}
		
		if(event.getSource().equals(IMBackup)){
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
					parentWindow.writeToOutput(LogInfoGenerator.generateServerBackupStatement(file.getAbsolutePath()));
				} catch (Exception r){
					System.out.println(r);
				}
			}
		}
		
		if (event.getSource().equals(IMRestore)){
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
					
					parentWindow.writeToOutput(LogInfoGenerator.generateServerRestoreStatement(fileChooser.getSelectedFile().getAbsolutePath()));
				}
			}
		}
		
		if (event.getSource().equals(IMRegister)){
			new RegisterGUI(server, parentWindow, keys);
		}
		
		if (event.getSource().equals(CTextEntry) || event.getSource().equals(CEnter)){
			executedCommands.add(0, CTextEntry.getText());
			index = -1;
			writeToOutput(LogInfoGenerator.generateServerCommandStatement(CTextEntry.getText(), server.exec(CTextEntry.getText())));
			CTextEntry.setText("");
			CTextEntry.requestFocus();
		}
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
			SearchResult s = new SearchResult(this, parentWindow, i.remove(0), keys, Reference.EDIT_PRODUCT);
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
			SearchResult s = new SearchResult(this, parentWindow, i.remove(0), keys, Reference.RETURN_PRODUCT);
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
		//if(event.getSource().equals(ICContent)){
		//	ICTextEntry.requestFocus();
		//}
		if(event.getSource().equals(IMContent)){
			updateInventory();
			IMTextEntry.requestFocus();
		}
		if(event.getSource().equals(RMContent)){
			RMTextEntry.requestFocus();
		}
		
	}

	@Override
	public void update(String command) {
		if(command.equals("inventory") || command.equals(""))
			updateInventory();
		if(command.equals("return") || command.equals(""))
			updateReturn();
	}

	@Override
	public void writeToOutput(String s) {
		CTextOutput.append(s);
		parentWindow.writeToOutput(s);
	}

	@Override
	public void clearOutput() {
		CTextOutput.setText("");
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getExtendedKeyCode() == KeyEvent.VK_UP){
			index++;
			try{
			CTextEntry.setText(executedCommands.get(index));
			}catch(Exception e){
				index--;
				CTextEntry.setText(executedCommands.get(executedCommands.size() - 1));
			}
		}
		if(event.getExtendedKeyCode() == KeyEvent.VK_DOWN){
			index--;
			try{
			CTextEntry.setText(executedCommands.get(index));
			}catch(Exception e){
				index++;
				CTextEntry.setText(executedCommands.get(0));
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {}

	@Override
	public void keyTyped(KeyEvent event) {}
}
