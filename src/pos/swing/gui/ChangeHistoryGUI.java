package pos.swing.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pos.core.ServerManager;
import pos.core.UpdateableContent;
import pos.lib.Reference;

public class ChangeHistoryGUI extends JFrame implements UpdateableContent{

	private static final long serialVersionUID = 5042826653589702950L;

	private ServerManager server;
	
	private JPanel content;
	private JLabel inventoryTableLabel, returnTableLabel;
	
	public ChangeHistoryGUI(ServerManager server){
		this.server = server;
		
		GridBagConstraints c_inventoryTableLabel = new GridBagConstraints();
		c_inventoryTableLabel.gridx = 0;
		c_inventoryTableLabel.gridy = 1;
		c_inventoryTableLabel.ipadx = 0;
		c_inventoryTableLabel.ipady = 0;
		c_inventoryTableLabel.weightx = .5;
		c_inventoryTableLabel.weighty = 0;
		c_inventoryTableLabel.gridheight = 1;
		c_inventoryTableLabel.gridwidth = 1;
		c_inventoryTableLabel.anchor = GridBagConstraints.FIRST_LINE_START;
		c_inventoryTableLabel.fill = GridBagConstraints.BOTH;
		c_inventoryTableLabel.insets = new Insets(10,10,10,0);
		
		GridBagConstraints c_returnTableLabel = new GridBagConstraints();
		c_inventoryTableLabel.gridx = 1;
		c_inventoryTableLabel.gridy = 1;
		c_inventoryTableLabel.ipadx = 0;
		c_inventoryTableLabel.ipady = 0;
		c_inventoryTableLabel.weightx = .5;
		c_inventoryTableLabel.weighty = 0;
		c_inventoryTableLabel.gridheight = 1;
		c_inventoryTableLabel.gridwidth = 1;
		c_inventoryTableLabel.anchor = GridBagConstraints.FIRST_LINE_START;
		c_inventoryTableLabel.fill = GridBagConstraints.BOTH;
		c_inventoryTableLabel.insets = new Insets(10,10,10,0);
		
		GridBagConstraints c_inventoryChangesTable = new GridBagConstraints();
		c_inventoryChangesTable.gridx = 0;
		c_inventoryChangesTable.gridy = 2;
		c_inventoryChangesTable.ipadx = 0;
		c_inventoryChangesTable.ipady = 0;
		c_inventoryChangesTable.weightx = .5;
		c_inventoryChangesTable.weighty = 1;
		c_inventoryChangesTable.gridheight = 1;
		c_inventoryChangesTable.gridwidth = 1;
		c_inventoryChangesTable.anchor = GridBagConstraints.FIRST_LINE_START;
		c_inventoryChangesTable.fill = GridBagConstraints.BOTH;
		c_inventoryChangesTable.insets = new Insets(10,10,10,0);
		
		GridBagConstraints c_returnChangesTable = new GridBagConstraints();
		c_returnChangesTable.gridx = 1;
		c_returnChangesTable.gridy = 2;
		c_returnChangesTable.ipadx = 0;
		c_returnChangesTable.ipady = 0;
		c_returnChangesTable.weightx = .5;
		c_returnChangesTable.weighty = 1;
		c_returnChangesTable.gridheight = 1;
		c_returnChangesTable.gridwidth = 1;
		c_returnChangesTable.anchor = GridBagConstraints.FIRST_LINE_START;
		c_returnChangesTable.fill = GridBagConstraints.BOTH;
		c_returnChangesTable.insets = new Insets(10,0,10,10);
		
		content = new JPanel(new GridBagLayout());
		
		inventoryTableLabel = new JLabel("Inventory");
		content.add(inventoryTableLabel, c_inventoryTableLabel);
		
		returnTableLabel = new JLabel("Return");
		content.add(returnTableLabel, c_returnTableLabel);
		
		setTitle("Change History");
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void update(String updateIdentifier, String info) {
		if(updateIdentifier.equals(Reference.CHANGE_LOG_UPDATED)){
			//TODO: Update Change Log
		}
		
	}


}
