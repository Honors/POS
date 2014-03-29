package pos.swing.content;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import pos.core.UpdateableContent;
import pos.core.UpdateableContentController;
import pos.lib.Reference;
import pos.log.Fetcher;

public class ReadableLogContent extends JPanel implements UpdateableContent{

	private JTextArea textOutput;
	private JScrollPane scrollOutput;
	
	public ReadableLogContent(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;
		c.ipady = 5;
		c.ipadx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,5,5);
		
		textOutput = new JTextArea();
		textOutput.setEditable(false);
		textOutput.setFont(new Font("Courier New", Font.PLAIN, 14));
		textOutput.setBorder(new EmptyBorder(5,5,5,5));
		scrollOutput = new JScrollPane(textOutput);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		add(scrollOutput, c);
		
		UpdateableContentController.addActiveContent(this);
	}
	
	public void updateLog(){
		textOutput.setText("Loading...");

		(new Thread(new Runnable() {
			public void run() {
				ArrayList<String> logItems = Fetcher.read(Reference.READABLE_LOG_IDENTIFIER);
				textOutput.setText("");
				for(int i = 0; i < logItems.size(); i++){
					textOutput.append(logItems.get(logItems.size() - 1 - i));
				}
			}
		})).start();
	}

	@Override
	public void update(String updateIdentifier, String info) {
		if(updateIdentifier.equals(Reference.READABLE_LOG_UPDATED))
			updateLog();
		
	}
}
