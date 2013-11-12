package pos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class InventoryGUI extends JFrame implements OutputWindow, ActionListener, Confirmable {

	private static final long serialVersionUID = -1245352016605793408L;

	private JTextArea ICoutput;
	private JScrollPane ICoutputPane;
	private JTextField ICtextEntry;
	private JPanel ICContent, IMContent, RMContent;
	private JTabbedPane tabs;
	private JToggleButton ICmodeAdd, ICmodeSubtract, ICmodeReturn;
	private ButtonGroup ICmodes;
	
	private Keys key;
	private InventoryManager inventory;
	
	public InventoryGUI(InventoryManager i, Keys keys){
		inventory = i;
		key = keys;
		
		ICContent = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 5;
		c.insets = new Insets(5,5,0,5);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = .5;
		
		ICtextEntry = new JTextField();
		ICtextEntry.addActionListener(this);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		ICContent.add(ICtextEntry, c);
		
		ICmodes = new ButtonGroup();
		
		ICmodeAdd = new JToggleButton("Add");
		ICmodeAdd.setSelected(true);
		ICmodeAdd.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		ICmodes.add(ICmodeAdd);
		ICContent.add(ICmodeAdd, c);
		
		ICmodeSubtract = new JToggleButton("Subtract");
		ICmodeSubtract.addActionListener(this);
		c.gridx = 1;
		c.gridy = 1;
		ICmodes.add(ICmodeSubtract);
		c.insets = new Insets(5,0,0,5);
		ICContent.add(ICmodeSubtract, c);
		
		ICmodeReturn = new JToggleButton("Return");
		ICmodeReturn.addActionListener(this);
		c.gridx = 2;
		c.gridy = 1;
		ICmodes.add(ICmodeReturn);
		ICContent.add(ICmodeReturn, c);
		
		ICoutput = new JTextArea();
		ICoutput.setLineWrap(true);
		ICoutput.setWrapStyleWord(true);
		ICoutputPane = new JScrollPane(ICoutput);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.ipadx = 449;
		c.ipady = 552;
		c.insets = new Insets(5,5,5,5);
		ICContent.add(ICoutputPane, c);
		
		IMContent = new JPanel(new GridBagLayout());
		//TODO build IMContent
		
		RMContent = new JPanel(new GridBagLayout());
		//TODO build RMContent
		
		tabs = new JTabbedPane();
		tabs.addTab("Inventory Control", ICContent);
		tabs.addTab("Inventory Maintenance", IMContent);
		tabs.addTab("Returns Maintenance", RMContent);
		
		setTitle("Inventory");
		setContentPane(tabs);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		ICtextEntry.requestFocus();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		ICtextEntry.requestFocus();
		
		if(event.getSource().equals(ICtextEntry)){
			if(ICmodeAdd.isSelected()){
				actionConfirmed("add");
			}
			
			if(ICmodeSubtract.isSelected()){
				actionConfirmed("subtract");
			}
			
			if(ICmodeReturn.isSelected()){
				actionConfirmed("return");
			}
		}
	}
	
	@Override
	public void actionConfirmed(String action) {
		if(action.equals("add")){
			
		}
		
		if(action.equals("subtract")){
			
		}
		
		if(action.equals("return")){
			
		}
	}

	@Override
	public void writeToOutput(String s) {
		ICoutput.append(s);
		
	}

	@Override
	public void clearOutput() {
		ICoutput.setText("");
		
	}
}
