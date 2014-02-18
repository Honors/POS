package pos;

import javax.swing.*;

import pos.core.ServerManager;
import pos.swing.gui.HomeGUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;


public class PointOfSale extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 4617631829653620264L;
	
	private JPanel content;
	private JTextField fieldUrl, fieldUser;
	private JPasswordField fieldPass;
	private JLabel labelUrl, labelUser, labelPass, logging;
	private JButton submit, cancel;
	
	/**
	 * Builds the initial login GUI for the entire POS program
	 */
	public PointOfSale(){
		content = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 5;
		c.insets = new Insets(5,5,0,5);

		labelUser = new JLabel("Username: ");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.insets = new Insets(3,5,0,5);
		content.add(labelUser, c);
		
		fieldUser = new JTextField(17);
		fieldUser.addActionListener(this);
		fieldUser.setActionCommand("submit");
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		content.add(fieldUser, c);
		
		labelPass = new JLabel("Password: ");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		content.add(labelPass, c);
		
		fieldPass = new JPasswordField(17);	
		fieldPass.addActionListener(this);
		fieldPass.setActionCommand("submit");
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		content.add(fieldPass, c);
		
		logging = new JLabel("Logging in...");
		logging.setForeground(logging.getBackground());
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		content.add(logging, c);
		
		submit = new JButton("LOGIN");
		submit.addActionListener(this);
		submit.setActionCommand("submit");
		submit.setPreferredSize(new Dimension(10,25));
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.5;
		c.insets = new Insets(5,5,5,3);
		c.fill= GridBagConstraints.BOTH;
		content.add(submit, c);
		
		cancel = new JButton("CANCEL");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		cancel.setPreferredSize(new Dimension(10,25));
		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(5,3,5,5);
		content.add(cancel, c);
		
		setTitle("POS Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		//autoLogin();
	}
	
	
	/**
	 * Auto enters text into the login text fields and presses the login button
	 * (for quick login while debugging)
	 */
	public void autoLogin(){
		fieldUser.setText("watterson");
		fieldPass.setText("eagles");
		submit.doClick();
	}
	
	public static void main(String[] args) {
		new PointOfSale();
	}

	public void actionPerformed(ActionEvent event){
		if(event.getActionCommand().equals("submit")){
			logging.setForeground(new JLabel("Text").getForeground());
			logging.revalidate();
			revalidate();
			logging.repaint();
			repaint();
			ServerManager server = new ServerManager(fieldUser.getText(), new String(fieldPass.getPassword()));
			if(server.connected()){
				if(server.validLogin()){
					new HomeGUI(server, "c:\\POS");
					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Invalid Host directory or URL", "Login Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(event.getActionCommand().equals("cancel")){
			this.setVisible(false);
			System.exit(0);
		}
	}

}

