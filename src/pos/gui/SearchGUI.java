package pos.gui;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import pos.core.ServerManager;
import pos.model.Item;
import pos.core.JFramePOS;
import pos.model.Keys;
import pos.core.OutputWindow;
import pos.model.SearchItem;

@SuppressWarnings("serial")
public class SearchGUI extends JFramePOS implements ActionListener{
	
	private JPanel content;
	private JPanel resultsPanel;
	private JTextField searchBar;
	private JButton searchButton;
	private JScrollPane outputPane;
	private ArrayList<SearchItem> searchResults;
	
	public SearchGUI(ServerManager m, OutputWindow g, String query, Keys _key){
		super(m, g, _key);
		
		searchResults = new ArrayList<SearchItem>();
		
		content = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;
		c.ipady = 5;
		
		resultsPanel = new JPanel(new GridBagLayout());
		
		searchBar = new JTextField(30);
		searchBar.addActionListener(this);
		searchBar.setText(query);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.insets = new Insets(5,5,0,0);
		content.add(searchBar, c);
		
		searchButton = new JButton("SEARCH");
		searchButton.addActionListener(this);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0;
		c.insets = new Insets(5,5,0,5);
		content.add(searchButton, c);
		
		outputPane = new JScrollPane(resultsPanel);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.ipady = 500;
		c.weighty = 1;
		c.weightx = 1;
		c.insets = new Insets(5,5,5,5);
		content.add(outputPane, c);
		
		setTitle("Search...");
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		
		updateResults();
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(searchBar) || event.getSource().equals(searchButton)){
			updateResults();
		}
	}
	
	public void updateResults(){
		resultsPanel.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		if (searchBar.getText().length() < 2){
			searchBar.setText("SKU > -1");
		}
		ArrayList<Item> i = server.searchInventory(searchBar.getText());
		boolean colorized = true;
		while (!i.isEmpty()){
			SearchItem s = new SearchItem(this, i.remove(0), keys, Item.VIEW_PRODUCT);
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
	}
}
