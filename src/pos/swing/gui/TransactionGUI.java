package pos.swing.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import pos.core.Keys;
import pos.core.OutputWindow;
import pos.core.ServerManager;
import pos.dialog.DialogSingleComboBox;
import pos.item.InventoryItem;
import pos.item.ReturnItem;
import pos.lib.Reference;
import pos.log.ChangeLogger;
import pos.log.LogInfoGenerator;
import pos.log.TimeStamp;
import pos.swing.JFramePOS;
import pos.swing.JToggleEnableButton;

public class TransactionGUI extends JFramePOS implements ActionListener, OutputWindow{

	private static final long serialVersionUID = -1552006867264720975L;
	
	private JPanel ICContent, ICSearchBar;
	private JTextField ICTextEntry;
	private JButton ICEnter;
	private ButtonGroup ICModes;
	private JToggleButton ICModeIncoming, ICModeOutgoing, ICModeReturn;
	private JSpinner ICMultipleText;
	private JTextArea ICOutput;
	private JScrollPane ICOutputPane;
	
	private JToggleEnableButton ICMultiple;

	public TransactionGUI(ServerManager i, OutputWindow out, Keys keys){
		super(i,out,keys);
		
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
		ICOutput.setEditable(false);
		ICOutput.setFont(new Font("Courier New", Font.PLAIN, 14));
		ICOutput.setBorder(new EmptyBorder(5,5,5,5));
		ICOutputPane = new JScrollPane(ICOutput);
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 1;
		c.gridwidth = 5;
		c.ipadx = 700;
		c.ipady = 550;
		c.insets = new Insets(5,5,5,5);
		ICContent.add(ICOutputPane, c);
		
		setTitle("Transaction");
		setContentPane(ICContent);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		ICTextEntry.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		ICTextEntry.requestFocus();
		
		if(event.getSource().equals(ICTextEntry) || event.getSource().equals(ICEnter)){
			if(ICModeIncoming.isSelected()){
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
					//updateInventory();
					
					change = toChange.get(0).quantity - oldVal;
					
					String statement = LogInfoGenerator.generateTransactionIncomingItemStatement(server.getUsername(), toChange.get(0).UPC, toChange.get(0).name, oldVal, toChange.get(0).quantity);
					writeToOutput(statement);

					ChangeLogger.write(Reference.INVENTORY_IDENTIFIER, TimeStamp.sanitizedDateandTime(), toChange.get(0).SKU, Reference.STATUS_INCOMING, change);
					
				} else if(toChange.size() > 1){
					JOptionPane.showMessageDialog(new JFrame(),"Scanned item has duplicates", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				ICTextEntry.setText("");
			}
			
			if(ICModeOutgoing.isSelected()){
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
						//updateInventory();
						
						change = oldVal - toChange.get(0).quantity;
						
						String statement = LogInfoGenerator.generateTransactionOutgoingItemStatement(server.getUsername(), toChange.get(0).UPC, toChange.get(0).name, oldVal, toChange.get(0).quantity);
						writeToOutput(statement);

						ChangeLogger.write(Reference.INVENTORY_IDENTIFIER, TimeStamp.sanitizedDateandTime(), toChange.get(0).SKU, Reference.STATUS_OUTGOING, change);

						
					} else {
						JOptionPane.showMessageDialog(new JFrame(),"Scanned item's quantity cannot decrease below 0\nThe quantity for the scanned item is 0", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} else if(toChange.size() > 1){
					JOptionPane.showMessageDialog(new JFrame(),"Scanned item has duplicates", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				ICTextEntry.setText("");
			}
			
			if(ICModeReturn.isSelected()){
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
						item.quantity = 1;
						if(ICMultiple.isSelected()){
							item.quantity = Integer.parseInt(ICMultipleText.getValue().toString());
							ICMultipleText.setValue(1);
							ICMultiple.setSelected(false);
						}
						
						server.insertReturnItem(item);

						String statement = LogInfoGenerator.generateTransactionReturnItemStatement(server.getUsername(), item.UPC, item.name, item.quantity, item.status);
						if(item.status == Reference.STATUS_TO_INVENTORY){
							int oldVal = toReturn.get(0).quantity;
							toReturn.get(0).quantity += item.quantity;
							int change = toReturn.get(0).quantity - oldVal;
							server.updateInventoryItem(toReturn.get(0));
							statement += LogInfoGenerator.generateTransactionIncomingItemStatement(server.getUsername(), item.UPC, item.name, oldVal, toReturn.get(0).quantity);
							ChangeLogger.write(Reference.INVENTORY_IDENTIFIER, TimeStamp.sanitizedDateandTime(), toReturn.get(0).SKU, Reference.STATUS_INCOMING, change);
						}
						writeToOutput(statement);
						ChangeLogger.write(Reference.RETURN_IDENTIFIER, TimeStamp.sanitizedDateandTime(), item.SKU, status, item.quantity);
					}
				} else if (toReturn.size() > 1){
					JOptionPane.showMessageDialog(new JFrame(),"Scanned item has duplicates", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				ICTextEntry.setText("");
			}
		}
		
	}

	@Override
	public void writeToOutput(String s) {
		ICOutput.append(s);
		ICOutput.append(System.lineSeparator() + System.lineSeparator());
		parentWindow.writeToOutput(s);
	}

	@Override
	public void clearOutput() {
		ICOutput.setText("");
		
	}
}
