package pos.swing.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import pos.core.ServerManager;
import pos.core.UpdateableContent;
import pos.dialog.DialogNewLogin;
import pos.lib.Reference;

public class LoginManagerGUI extends JFrame implements ActionListener, TableModelListener, UpdateableContent{

	private static final long serialVersionUID = -2146259808509575903L;
	
	String [] collumnNames = {"Username", "Password", "Authentification"};
	
	private ServerManager server;
	
	private JPanel content;
	private JButton newLogin, delLogin;
	private JTable loginTable;
	private JScrollPane loginScroll;
	
	public LoginManagerGUI(ServerManager s){
		server = s;
		
		GridBagConstraints c_table = new GridBagConstraints();
		c_table.gridx = 0;
		c_table.gridy = 0;
		c_table.ipadx = 0;
		c_table.ipady = 0;
		c_table.weightx = 1;
		c_table.weighty = 1;
		c_table.gridheight = 3;
		c_table.gridwidth = 1;
		c_table.anchor = GridBagConstraints.FIRST_LINE_START;
		c_table.fill = GridBagConstraints.BOTH;
		c_table.insets = new Insets(10,10,10,0);
		
		GridBagConstraints c_add = new GridBagConstraints();
		c_add.gridx = 1;
		c_add.gridy = 0;
		c_add.ipadx = 100;
		c_add.ipady = 5;
		c_add.weightx = 0;
		c_add.weighty = 0;
		c_add.gridheight = 1;
		c_add.gridwidth = 1;
		c_add.anchor = GridBagConstraints.FIRST_LINE_END;
		c_add.fill = GridBagConstraints.BOTH;
		c_add.insets = new Insets(10,20,10,20);
		
		GridBagConstraints c_delete = new GridBagConstraints();
		c_delete.gridx = 1;
		c_delete.gridy = 1;
		c_delete.ipadx = 100;
		c_delete.ipady = 5;
		c_delete.weightx = 0;
		c_delete.weighty = 0;
		c_delete.gridheight = 1;
		c_delete.gridwidth = 1;
		c_delete.anchor = GridBagConstraints.FIRST_LINE_END;
		c_delete.fill = GridBagConstraints.BOTH;
		c_delete.insets = new Insets(10,20,10,20);
		
		GridBagConstraints c_filler = new GridBagConstraints();
		c_filler.gridx = 1;
		c_filler.gridy = 2;
		c_filler.ipadx = 0;
		c_filler.ipady = 0;
		c_filler.weightx = 0;
		c_filler.weighty = 1;
		c_filler.gridheight = 1;
		c_filler.gridwidth = 1;
		c_filler.anchor = GridBagConstraints.FIRST_LINE_END;
		c_filler.fill = GridBagConstraints.BOTH;
		c_filler.insets = new Insets(0,0,0,0);
		
		content = new JPanel(new GridBagLayout());
		
		newLogin = new JButton("New");
		newLogin.addActionListener(this);
		content.add(newLogin, c_add);
		
		delLogin = new JButton("Delete");
		delLogin.addActionListener(this);
		content.add(delLogin, c_delete);
		
		loginTable = new JTable(server.getLoginData(), collumnNames);
		loginTable.getModel().addTableModelListener(this);
		loginTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		loginScroll = new JScrollPane(loginTable);
		content.add(loginScroll, c_table);
		
		content.add(new JPanel(), c_filler);
		
		TableColumn authColumn = loginTable.getColumnModel().getColumn(2);
		authColumn.setCellEditor(new DefaultCellEditor(new JComboBox<String>(Reference.AUTHENTIFICATIONS)));
		
		setTitle("Login Manager");
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void updateTable(){
		loginTable.setModel(new JTable(server.getLoginData(), collumnNames).getModel());
		
		TableColumn authColumn = loginTable.getColumnModel().getColumn(2);
		authColumn.setCellEditor(new DefaultCellEditor(new JComboBox<String>(Reference.AUTHENTIFICATIONS)));
		
		loginTable.revalidate();
		loginTable.repaint();
	} 
	
	public String[][] getTableData (JTable table) {
	    TableModel dtm = table.getModel();
	    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
	    String[][] tableData = new String[nRow][nCol];
	    for (int i = 0 ; i < nRow ; i++)
	        for (int j = 0 ; j < nCol ; j++)
	            tableData[i][j] = dtm.getValueAt(i,j).toString();
	    return tableData;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(newLogin)){
			DialogNewLogin newLoginDialog = new DialogNewLogin(new JFrame(), "New Login...", server.getLoginUsernames());
			if(newLoginDialog.isValidated()){
				server.insertLogin(newLoginDialog.getUsername(), newLoginDialog.getPassword(), newLoginDialog.isAdmin());
				updateTable();
			}
		}
		
		if(event.getSource().equals(delLogin)){
			String delUsername = loginTable.getModel().getValueAt(loginTable.getSelectedRow(), 0).toString();
			server.deleteLogin(delUsername);
			updateTable();
		}
		
	}

	@Override
	public void tableChanged(TableModelEvent event) {
		server.writeAllLogins(getTableData(loginTable));
	}

	@Override
	public void update(String updateIdentifier, String info) {
		if(updateIdentifier.equals(UpdateableContent.LOGIN_UPDATED)){
			updateTable();
		}
	}
}
