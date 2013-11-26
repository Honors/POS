package pos.gui;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import pos.core.JToggleEnableButton;
import pos.core.Reference;
import pos.core.ServerManager;
import pos.model.InventoryItem;
import pos.core.JFramePOS;
import pos.model.Keys;
import pos.core.OutputWindow;
import pos.model.SearchResult;

@SuppressWarnings("serial")
public class SearchGUI extends JFramePOS implements ActionListener{
	
	private JPanel content, searchFields, resultsPanel;
	private JToggleEnableButton brandLabel, sizeLabel, typeLabel, genderLabel, colorLabel, clientLabel;
	private JToggleEnableButton nameLabel, upcLabel;
	private JTextField nameField, upcField;
	private JComboBox<Object> brandBox, sizeBox, typeBox, genderBox, colorBox, clientBox;
	private JButton searchButton;
	private JScrollPane outputPane;
	private ArrayList<SearchResult> searchResults;
	
	public SearchGUI(ServerManager m, OutputWindow g, String query, Keys _key){
		super(m, g, _key);
		
		searchResults = new ArrayList<SearchResult>();
		
		GridBagConstraints c_fields = new GridBagConstraints();
		c_fields.gridx = 0;
		c_fields.gridy = 0;
		c_fields.ipadx = 0;
		c_fields.ipady = 0;
		c_fields.weightx = 1;
		c_fields.weighty = 0;
		c_fields.gridwidth = 4;
		c_fields.gridheight = 1;
		c_fields.anchor = GridBagConstraints.LINE_START;
		c_fields.fill = GridBagConstraints.HORIZONTAL;
		c_fields.insets = new Insets(0,0,0,0);
		
		GridBagConstraints c_nameLabel = new GridBagConstraints();
		c_nameLabel.gridx = 0;
		c_nameLabel.gridy = 0;
		c_nameLabel.ipadx = 0;
		c_nameLabel.ipady = 5;
		c_nameLabel.weightx = 0;
		c_nameLabel.weighty = 0;
		c_nameLabel.gridwidth = 1;
		c_nameLabel.gridheight = 1;
		c_nameLabel.anchor = GridBagConstraints.PAGE_START;
		c_nameLabel.fill = GridBagConstraints.BOTH;
		c_nameLabel.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_nameField = new GridBagConstraints();
		c_nameField.gridx = 1;
		c_nameField.gridy = 0;
		c_nameField.ipadx = 0;
		c_nameField.ipady = 5;
		c_nameField.weightx = 1;
		c_nameField.weighty = 0;
		c_nameField.gridwidth = 2;
		c_nameField.gridheight = 1;
		c_nameField.anchor = GridBagConstraints.PAGE_START;
		c_nameField.fill = GridBagConstraints.BOTH;
		c_nameField.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_searchButton = new GridBagConstraints();
		c_searchButton.gridx = 3;
		c_searchButton.gridy = 0;
		c_searchButton.ipadx = 0;
		c_searchButton.ipady = 0;
		c_searchButton.weightx = 0;
		c_searchButton.weighty = 0;
		c_searchButton.gridwidth = 1;
		c_searchButton.gridheight = 2;
		c_searchButton.anchor = GridBagConstraints.LINE_START;
		c_searchButton.fill = GridBagConstraints.BOTH;
		c_searchButton.insets = new Insets(5,5,5,5);
		
		GridBagConstraints c_upcLabel = new GridBagConstraints();
		c_upcLabel.gridx = 0;
		c_upcLabel.gridy = 1;
		c_upcLabel.ipadx = 0;
		c_upcLabel.ipady = 5;
		c_upcLabel.weightx = 0;
		c_upcLabel.weighty = 0;
		c_upcLabel.gridwidth = 1;
		c_upcLabel.gridheight = 1;
		c_upcLabel.anchor = GridBagConstraints.PAGE_END;
		c_upcLabel.fill = GridBagConstraints.BOTH;
		c_upcLabel.insets = new Insets(5,5,5,5);
		
		GridBagConstraints c_upcField = new GridBagConstraints();
		c_upcField.gridx = 1;
		c_upcField.gridy = 1;
		c_upcField.ipadx = 0;
		c_upcField.ipady = 5;
		c_upcField.weightx = 1;
		c_upcField.weighty = 0;
		c_upcField.gridwidth = 2;
		c_upcField.gridheight = 1;
		c_upcField.anchor = GridBagConstraints.PAGE_END;
		c_upcField.fill = GridBagConstraints.BOTH;
		c_upcField.insets = new Insets(5,5,5,5);

		GridBagConstraints c_brandLabel = new GridBagConstraints();
		c_brandLabel.gridx = 0;
		c_brandLabel.gridy = 1;
		c_brandLabel.ipadx = 0;
		c_brandLabel.ipady = 5;
		c_brandLabel.weightx = 0;
		c_brandLabel.weighty = 0;
		c_brandLabel.gridwidth = 1;
		c_brandLabel.gridheight = 1;
		c_brandLabel.anchor = GridBagConstraints.LINE_START;
		c_brandLabel.fill = GridBagConstraints.HORIZONTAL;
		c_brandLabel.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_brandBox = new GridBagConstraints();
		c_brandBox.gridx = 1;
		c_brandBox.gridy = 1;
		c_brandBox.ipadx = 0;
		c_brandBox.ipady = 5;
		c_brandBox.weightx = 0.5;
		c_brandBox.weighty = 0;
		c_brandBox.gridwidth = 1;
		c_brandBox.gridheight = 1;
		c_brandBox.anchor = GridBagConstraints.LINE_START;
		c_brandBox.fill = GridBagConstraints.HORIZONTAL;
		c_brandBox.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_sizeLabel = new GridBagConstraints();
		c_sizeLabel.gridx = 2;
		c_sizeLabel.gridy = 1;
		c_sizeLabel.ipadx = 0;
		c_sizeLabel.ipady = 5;
		c_sizeLabel.weightx = 0;
		c_sizeLabel.weighty = 0;
		c_sizeLabel.gridwidth = 1;
		c_sizeLabel.gridheight = 1;
		c_sizeLabel.anchor = GridBagConstraints.LINE_START;
		c_sizeLabel.fill = GridBagConstraints.HORIZONTAL;
		c_sizeLabel.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_sizeBox = new GridBagConstraints();
		c_sizeBox.gridx = 3;
		c_sizeBox.gridy = 1;
		c_sizeBox.ipadx = 0;
		c_sizeBox.ipady = 5;
		c_sizeBox.weightx = 0.5;
		c_sizeBox.weighty = 0;
		c_sizeBox.gridwidth = 1;
		c_sizeBox.gridheight = 1;
		c_sizeBox.anchor = GridBagConstraints.LINE_START;
		c_sizeBox.fill = GridBagConstraints.HORIZONTAL;
		c_sizeBox.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_typeLabel = new GridBagConstraints();
		c_typeLabel.gridx = 0;
		c_typeLabel.gridy = 2;
		c_typeLabel.ipadx = 0;
		c_typeLabel.ipady = 5;
		c_typeLabel.weightx = 0;
		c_typeLabel.weighty = 0;
		c_typeLabel.gridwidth = 1;
		c_typeLabel.gridheight = 1;
		c_typeLabel.anchor = GridBagConstraints.LINE_START;
		c_typeLabel.fill = GridBagConstraints.HORIZONTAL;
		c_typeLabel.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_typeBox = new GridBagConstraints();
		c_typeBox.gridx = 1;
		c_typeBox.gridy = 2;
		c_typeBox.ipadx = 0;
		c_typeBox.ipady = 5;
		c_typeBox.weightx = 0.5;
		c_typeBox.weighty = 0;
		c_typeBox.gridwidth = 1;
		c_typeBox.gridheight = 1;
		c_typeBox.anchor = GridBagConstraints.LINE_START;
		c_typeBox.fill = GridBagConstraints.HORIZONTAL;
		c_typeBox.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_genderLabel = new GridBagConstraints();
		c_genderLabel.gridx = 2;
		c_genderLabel.gridy = 2;
		c_genderLabel.ipadx = 0;
		c_genderLabel.ipady = 5;
		c_genderLabel.weightx = 0;
		c_genderLabel.weighty = 0;
		c_genderLabel.gridwidth = 1;
		c_genderLabel.gridheight = 1;
		c_genderLabel.anchor = GridBagConstraints.LINE_START;
		c_genderLabel.fill = GridBagConstraints.HORIZONTAL;
		c_genderLabel.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_genderBox = new GridBagConstraints();
		c_genderBox.gridx = 3;
		c_genderBox.gridy = 2;
		c_genderBox.ipadx = 0;
		c_genderBox.ipady = 5;
		c_genderBox.weightx = 0.5;
		c_genderBox.weighty = 0;
		c_genderBox.gridwidth = 1;
		c_genderBox.gridheight = 1;
		c_genderBox.anchor = GridBagConstraints.LINE_START;
		c_genderBox.fill = GridBagConstraints.HORIZONTAL;
		c_genderBox.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_colorLabel = new GridBagConstraints();
		c_colorLabel.gridx = 0;
		c_colorLabel.gridy = 3;
		c_colorLabel.ipadx = 0;
		c_colorLabel.ipady = 5;
		c_colorLabel.weightx = 0;
		c_colorLabel.weighty = 0;
		c_colorLabel.gridwidth = 1;
		c_colorLabel.gridheight = 1;
		c_colorLabel.anchor = GridBagConstraints.LINE_START;
		c_colorLabel.fill = GridBagConstraints.HORIZONTAL;
		c_colorLabel.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_colorBox = new GridBagConstraints();
		c_colorBox.gridx = 1;
		c_colorBox.gridy = 3;
		c_colorBox.ipadx = 0;
		c_colorBox.ipady = 5;
		c_colorBox.weightx = 0.5;
		c_colorBox.weighty = 0;
		c_colorBox.gridwidth = 1;
		c_colorBox.gridheight = 1;
		c_colorBox.anchor = GridBagConstraints.LINE_START;
		c_colorBox.fill = GridBagConstraints.HORIZONTAL;
		c_colorBox.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_clientLabel = new GridBagConstraints();
		c_clientLabel.gridx = 2;
		c_clientLabel.gridy = 3;
		c_clientLabel.ipadx = 0;
		c_clientLabel.ipady = 5;
		c_clientLabel.weightx = 0;
		c_clientLabel.weighty = 0;
		c_clientLabel.gridwidth = 1;
		c_clientLabel.gridheight = 1;
		c_clientLabel.anchor = GridBagConstraints.LINE_START;
		c_clientLabel.fill = GridBagConstraints.HORIZONTAL;
		c_clientLabel.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_clientBox = new GridBagConstraints();
		c_clientBox.gridx = 3;
		c_clientBox.gridy = 3;
		c_clientBox.ipadx = 0;
		c_clientBox.ipady = 5;
		c_clientBox.weightx = 0.5;
		c_clientBox.weighty = 0;
		c_clientBox.gridwidth = 1;
		c_clientBox.gridheight = 1;
		c_clientBox.anchor = GridBagConstraints.LINE_START;
		c_clientBox.fill = GridBagConstraints.HORIZONTAL;
		c_clientBox.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_outputPane = new GridBagConstraints();
		c_outputPane.gridx = 0;
		c_outputPane.gridy = 4;
		c_outputPane.ipadx = 500;
		c_outputPane.ipady = 500;
		c_outputPane.weightx = 1;
		c_outputPane.weighty = 1;
		c_outputPane.gridwidth = 4;
		c_outputPane.gridheight = 1;
		c_outputPane.anchor = GridBagConstraints.LINE_START;
		c_outputPane.fill = GridBagConstraints.BOTH;
		c_outputPane.insets = new Insets(5,5,5,5);
		
		content = new JPanel(new GridBagLayout());
		
		searchFields = new JPanel(new GridBagLayout());
		
		nameLabel = new JToggleEnableButton("Name:", false);
		searchFields.add(nameLabel, c_nameLabel);
		
		nameField = new JTextField(10);
		nameLabel.add(nameField);
		searchFields.add(nameField, c_nameField);
		
		searchButton = new JButton("SEARCH");
		searchButton.addActionListener(this);
		searchButton.setOpaque(true);
		searchButton.setBackground(new Color(0x98CC98));
		searchFields.add(searchButton, c_searchButton);
		
		upcLabel = new JToggleEnableButton("UPC:", false);
		searchFields.add(upcLabel, c_upcLabel);
		
		upcField = new JTextField(10);
		upcLabel.add(upcField);
		searchFields.add(upcField, c_upcField);
		
		content.add(searchFields, c_fields);
		
		brandLabel = new JToggleEnableButton("Brand:", false);
		content.add(brandLabel, c_brandLabel);
		
		brandBox = new JComboBox<Object>(keys.brands.toArray());
		brandLabel.add(brandBox);
		content.add(brandBox, c_brandBox);
		
		sizeLabel = new JToggleEnableButton("Size:", false);
		content.add(sizeLabel, c_sizeLabel);
		
		sizeBox = new JComboBox<Object>(keys.sizes.toArray());
		sizeLabel.add(sizeBox);
		content.add(sizeBox, c_sizeBox);
		
		typeLabel = new JToggleEnableButton("Type:", false);
		content.add(typeLabel, c_typeLabel);
		
		typeBox = new JComboBox<Object>(keys.types.toArray());
		typeLabel.add(typeBox);
		content.add(typeBox, c_typeBox);
		
		genderLabel = new JToggleEnableButton("Gender:", false);
		content.add(genderLabel, c_genderLabel);
		
		genderBox = new JComboBox<Object>(keys.genders.toArray());
		genderLabel.add(genderBox);
		content.add(genderBox, c_genderBox);
		
		colorLabel = new JToggleEnableButton("Color:", false);
		content.add(colorLabel, c_colorLabel);
		
		colorBox = new JComboBox<Object>(keys.colors.toArray());
		colorLabel.add(colorBox);
		content.add(colorBox, c_colorBox);
		
		clientLabel = new JToggleEnableButton("Client:", false);
		content.add(clientLabel, c_clientLabel);
		
		clientBox = new JComboBox<Object>(keys.clients.toArray());
		clientLabel.add(clientBox);
		content.add(clientBox, c_clientBox);
		
		resultsPanel = new JPanel(new GridBagLayout());
		outputPane = new JScrollPane(resultsPanel);
		content.add(outputPane, c_outputPane);
		
		setTitle("Search...");
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		
		updateResults();
	}
	
	public void actionPerformed(ActionEvent event) {

		if (event.getSource().equals(searchButton)){
			updateResults();
		}
		
	}
	
	public void updateResults(){
		/*TODO redo all search logic*/
		resultsPanel.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		ArrayList<InventoryItem> i = server.searchInventory(getSearchCommand());
		boolean colorized = true;
		while (!i.isEmpty()){
			SearchResult s = new SearchResult(this, i.remove(0), keys, Reference.VIEW_PRODUCT);
			s.setOpaque(true);
			if(colorized)
				s.setBackground(new Color(0xD4EBF2));
			else
				s.setBackground(Color.WHITE);
			colorized = !colorized;
			searchResults.add(s);
			resultsPanel.add(s, c);
			c.gridy++;
		}
		c.weighty = 1;
		resultsPanel.add(new JPanel(), c);
		this.paintAll(getGraphics());
		//*/
	}
	
	public String getSearchCommand(){
		String command = new String();
		
		if(nameField.isEnabled() && !nameField.getText().isEmpty()){
			String nameCommand = "'%" + nameField.getText() + "%'";
			nameCommand = nameCommand.replace(" ", "%' AND UPPER(NAME) LIKE '%").toUpperCase();
			command += "UPPER(NAME) LIKE " + nameCommand;
		}
		
		if(upcField.isEnabled() && !upcField.getText().isEmpty()){
			if(!command.isEmpty())
				command += " AND ";
			command += "UPC='" + upcField.getText() + "'";
		}
		
		if(brandBox.isEnabled()){
			if(!command.isEmpty())
				command += " AND ";
			command += "BRAND='" + brandBox.getSelectedItem().toString() + "'";
		}
		
		if(sizeBox.isEnabled()){
			if(!command.isEmpty())
				command += " AND ";
			command += "SIZE='" + sizeBox.getSelectedItem().toString() + "'";
		}
		
		if(typeBox.isEnabled()){
			if(!command.isEmpty())
				command += " AND ";
			command += "TYPE='" + typeBox.getSelectedItem().toString() + "'";
		}

		if(genderBox.isEnabled()){
			if(!command.isEmpty())
				command += " AND ";
			command += "GENDER='" + genderBox.getSelectedItem().toString() + "'";
		}
		
		if(colorBox.isEnabled()){
			if(!command.isEmpty())
				command += " AND ";
			command += "COLOR='" + colorBox.getSelectedItem().toString() + "'";
		}
		
		if(clientBox.isEnabled()){
			if(!command.isEmpty())
				command += " AND ";
			command += "CLIENT='" + clientBox.getSelectedItem().toString() + "'";
		}
		
		if(command.isEmpty()){
			command = "SKU>-1";
		}
		
		System.out.println(command);
		
		return command;
	}
}
