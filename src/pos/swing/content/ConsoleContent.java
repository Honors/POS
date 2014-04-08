package pos.swing.content;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pos.core.OutputWindow;
import pos.core.ServerManager;
import pos.log.LogInfoGenerator;

public class ConsoleContent extends JPanel implements ActionListener, KeyListener, ChangeListener {

	private static final long serialVersionUID = 2817251225342772103L;
	
	private ArrayList<String> executedCommands;
	private int index;
	
	private JPanel searchBar;
	private JTextField textEntry;
	private JButton enter;
	private JTextArea textOutput;
	private JScrollPane scrollOutput;
	
	private ServerManager server;
	private OutputWindow parentWindow;
	
	public ConsoleContent(ServerManager server, OutputWindow parentWindow){
		this.server = server;
		this.parentWindow = parentWindow;
		executedCommands = new ArrayList<String>();
		index = -1;
		
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
		c.weightx = 1;
		add(searchBar, c);
		
		textEntry = new JTextField();
		textEntry.addActionListener(this);
		textEntry.addKeyListener(this);
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,5);
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
		
		textOutput = new JTextArea();
		textOutput.setEditable(false);
		textOutput.setFont(new Font("Courier New", Font.PLAIN, 14));
		textOutput.setBorder(new EmptyBorder(5,5,5,5));
		scrollOutput = new JScrollPane(textOutput);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5,5,5,5);
		c.weightx = 1;
		c.weighty = 1;
		add(scrollOutput, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		executedCommands.add(0, textEntry.getText());
		index = -1;
		consoleOut(LogInfoGenerator.generateServerCommandStatement(server.getUsername(), textEntry.getText(), server.exec(textEntry.getText())));
		textEntry.setText("");
		textEntry.requestFocus();
		
	}
	
	@Override
	public void stateChanged(ChangeEvent event) {
		textEntry.requestFocus();
	}
	
	public void consoleOut(String s){
		textOutput.append(s);
		parentWindow.writeToOutput(s);
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getExtendedKeyCode() == KeyEvent.VK_UP){
			index++;
			try{
			textEntry.setText(executedCommands.get(index));
			}catch(Exception e){
				index--;
				textEntry.setText(executedCommands.get(executedCommands.size() - 1));
			}
		}
		if(event.getExtendedKeyCode() == KeyEvent.VK_DOWN){
			index--;
			try{
			textEntry.setText(executedCommands.get(index));
			}catch(Exception e){
				index++;
				textEntry.setText(executedCommands.get(0));
			}
		}
		
	}

	public void keyTyped(KeyEvent e) {}	
	public void keyReleased(KeyEvent e) {}


}
