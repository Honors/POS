package pos.swing.content;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pos.core.Keys;
import pos.core.OutputWindow;
import pos.core.ServerManager;
import pos.item.ReturnItem;
import pos.lib.Reference;
import pos.swing.JFramePOS;
import pos.swing.SearchResult;

public class ReturnMaintinanceContent extends JPanel implements ActionListener, ChangeListener {

	private static final long serialVersionUID = -713707744470247384L;
	
	private JPanel searchBar, results;
	private JTextField textEntry;
	private JButton enter;
	private JScrollPane output;
	
	private ServerManager server;
	private OutputWindow parentWindow;
	private Keys keys;
	
	public ReturnMaintinanceContent(ServerManager server, OutputWindow parentWindow, Keys keys){
		this.server = server;
		this.parentWindow = parentWindow;
		this.keys = keys;
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_START;
		c.ipady = 5;
		c.ipadx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,0,5);
		
		searchBar = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		add(searchBar, c);
		
		textEntry = new JTextField();
		textEntry.addActionListener(this);
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,5);
		c.weightx = 1;
		searchBar.add(textEntry, c);
		
		enter = new JButton("ENTER");
		enter.addActionListener(this);
		enter.setOpaque(true);
		enter.setBackground(new Color(0x98CC98));
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		searchBar.add(enter, c);
		
		results = new JPanel();
		results.setLayout(new GridBagLayout());
		
		output = new JScrollPane(results);
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		c.weightx = 1;
		c.insets = new Insets(5,5,5,5);
		add(output, c);
		
		updateReturn();
	}

	public void updateReturn(){
		//Make Search for UPC, not SKU
		results.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		String search = textEntry.getText();
		ArrayList<ReturnItem> i;
		if (search.length() == 0){
			i = server.searchReturn("SKU > -1");
		} else {
			i = server.searchReturn("UPC = '" + search + "'");
		}
		boolean colorized = true;
		while (!i.isEmpty()){
			SearchResult s = new SearchResult(server, parentWindow, i.remove(0), keys, Reference.RETURN_PRODUCT);
			s.setOpaque(true);
			if(colorized)
				s.setBackground(new Color(0xf2dbd4));
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
	public void actionPerformed(ActionEvent e) {
		updateReturn();
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		updateReturn();
		textEntry.requestFocus();
	}
}
