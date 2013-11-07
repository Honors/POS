package pos;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SearchGUI extends JFrame implements ActionListener{
	
	public OutputWindow parentWindow;
	public InventoryManager inventory;
	
	private JPanel content;
	private JPanel resultsPanel;
	private JTextField searchBar;
	private JButton searchButton;
	private JScrollPane outputPane;
	private ArrayList<SearchItem> searchResults;
	private Keys key;
	
	public SearchGUI(InventoryManager m, OutputWindow g, String query, Keys _key){
		super("Search Window");
		
		parentWindow = g;
		inventory = m;
		searchResults = new ArrayList<SearchItem>();
		key = _key;
		
		content = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;
		c.ipady = 5;
		
		resultsPanel = new JPanel();
		resultsPanel.setLayout(new BoxLayout(resultsPanel, 1));
		resultsPanel.setBorder(new EmptyBorder(5,5,5,5));
		
		searchBar = new JTextField(30);
		searchBar.setText(query);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.insets = new Insets(5,5,0,0);
		content.add(searchBar, c);
		
		searchButton = new JButton("SEARCH");
		searchButton.addActionListener(this);
		searchButton.setActionCommand("search");
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
	
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("search")){
			updateResults();
		}
	}
	
	public void updateResults(){
		resultsPanel.removeAll();
		if (searchBar.getText().length() < 2){
			searchBar.setText("SKU > -1");
		}
		ArrayList<Item> i = inventory.search(searchBar.getText());
		while (!i.isEmpty()){
			SearchItem s = new SearchItem(this, i.remove(0), key);
			searchResults.add(s);
			resultsPanel.add(s);
		}
		this.paintAll(getGraphics());
	}
}
