package pos.swing.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import pos.core.ServerManager;
import pos.core.UpdateableContent;
import pos.lib.Reference;
import pos.log.ChangeLogger;

public class ChangeHistoryGUI extends JFrame implements UpdateableContent, ActionListener{

	private static final long serialVersionUID = 5042826653589702950L;

	private String[] collumnNames = {"Date", "UPC", "Collection", "Status", "Quantity"};
	
	private JPanel content, buttonBar, buttonTray;
	private JButton newButton, refreshButton, saveButton;
	private JTable changeTable;
	private JScrollPane changeScroll;
	private JDatePickerImpl fromPicker, toPicker;
	
	private ServerManager server;
	
	public ChangeHistoryGUI(ServerManager server){
		this.server = server;
		
		GridBagConstraints c_buttonBar = new GridBagConstraints();
		c_buttonBar.gridx = 0;
		c_buttonBar.gridy = 0;
		c_buttonBar.ipadx = 0;
		c_buttonBar.ipady = 0;
		c_buttonBar.weightx = 1;
		c_buttonBar.weighty = 0;
		c_buttonBar.gridheight = 1;
		c_buttonBar.gridwidth = 1;
		c_buttonBar.anchor = GridBagConstraints.FIRST_LINE_START;
		c_buttonBar.fill = GridBagConstraints.BOTH;
		c_buttonBar.insets = new Insets(0,0,0,0);
		
		GridBagConstraints c_buttonBarItem = new GridBagConstraints();
		c_buttonBarItem.gridx = 0;
		c_buttonBarItem.gridy = 0;
		c_buttonBarItem.ipadx = 0;
		c_buttonBarItem.ipady = 0;
		c_buttonBarItem.weightx = 0;
		c_buttonBarItem.weighty = 0;
		c_buttonBarItem.gridheight = 1;
		c_buttonBarItem.gridwidth = 1;
		c_buttonBarItem.anchor = GridBagConstraints.FIRST_LINE_START;
		c_buttonBarItem.fill = GridBagConstraints.NONE;
		c_buttonBarItem.insets = new Insets(10,10,10,10);
		
		GridBagConstraints c_table = new GridBagConstraints();
		c_table.gridx = 0;
		c_table.gridy = 1;
		c_table.ipadx = 0;
		c_table.ipady = 0;
		c_table.weightx = 1;
		c_table.weighty = 1;
		c_table.gridheight = 1;
		c_table.gridwidth = 1;
		c_table.anchor = GridBagConstraints.FIRST_LINE_START;
		c_table.fill = GridBagConstraints.BOTH;
		c_table.insets = new Insets(0,10,0,10);
		
		GridBagConstraints c_buttonTray = new GridBagConstraints();
		c_buttonTray.gridx = 0;
		c_buttonTray.gridy = 2;
		c_buttonTray.ipadx = 0;
		c_buttonTray.ipady = 0;
		c_buttonTray.weightx = 1;
		c_buttonTray.weighty = 0;
		c_buttonTray.gridheight = 1;
		c_buttonTray.gridwidth = 1;
		c_buttonTray.anchor = GridBagConstraints.FIRST_LINE_START;
		c_buttonTray.fill = GridBagConstraints.BOTH;
		c_buttonTray.insets = new Insets(0,0,0,0);
		
		GridBagConstraints c_buttonTrayItem = new GridBagConstraints();
		c_buttonTrayItem.gridx = 0;
		c_buttonTrayItem.gridy = 0;
		c_buttonTrayItem.ipadx = 0;
		c_buttonTrayItem.ipady = 0;
		c_buttonTrayItem.weightx = 1;
		c_buttonTrayItem.weighty = 0;
		c_buttonTrayItem.gridheight = 1;
		c_buttonTrayItem.gridwidth = 1;
		c_buttonTrayItem.anchor = GridBagConstraints.FIRST_LINE_START;
		c_buttonTrayItem.fill = GridBagConstraints.NONE;
		c_buttonTrayItem.insets = new Insets(10,10,10,10);
		
		content = new JPanel(new GridBagLayout());
		
		buttonBar = new JPanel(new GridBagLayout());
		
		buttonBar.add(new JLabel("From: "), c_buttonBarItem);

		fromPicker = new JDatePickerImpl(new JDatePanelImpl(null));
		c_buttonBarItem.gridx++;
		buttonBar.add(fromPicker);
		
		c_buttonBarItem.gridx++;
		buttonBar.add(new JLabel("To: "), c_buttonBarItem);
		
		toPicker = new JDatePickerImpl(new JDatePanelImpl(null));
		c_buttonBarItem.gridx++;
		buttonBar.add(toPicker);
		
		c_buttonBarItem.gridx++;
		c_buttonBarItem.weightx = 1;
		buttonBar.add(new JPanel(), c_buttonBarItem);
		
		newButton = new JButton("New...");
		newButton.addActionListener(this);
		c_buttonBarItem.gridx++;
		c_buttonBarItem.weightx = 0;
		buttonBar.add(newButton, c_buttonBarItem);
		
		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(this);
		c_buttonBarItem.gridx++;
		buttonBar.add(refreshButton, c_buttonBarItem);
		
		content.add(buttonBar, c_buttonBar);
		
		changeTable = new JTable(ChangeLogger.getLoggedChanges(new Date(), new Date()),collumnNames);
		changeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		changeScroll = new JScrollPane(changeTable);
		content.add(changeScroll, c_table);
		
		buttonTray = new JPanel();
		
		buttonTray.add(new JPanel(), c_buttonTrayItem);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		c_buttonTrayItem.gridx++;
		c_buttonTrayItem.weightx = 0;
		c_buttonTrayItem.insets = new Insets(10,10,10,10);
		buttonTray.add(saveButton, c_buttonTrayItem);
		
		content.add(buttonTray, c_buttonTray);
		
		setTitle("Change History");
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void update(String updateIdentifier, String info) {
		if(updateIdentifier.equals(UpdateableContent.CHANGE_LOG_UPDATED)){
			//TODO: Update Change Log
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(refreshButton)){
			Date start = new Date(toPicker.getJDateInstantPanel().getModel().getYear() - 1900,
								  toPicker.getJDateInstantPanel().getModel().getMonth() - 1,
								  toPicker.getJDateInstantPanel().getModel().getDay());
		}
	}

}
