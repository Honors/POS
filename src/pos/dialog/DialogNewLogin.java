package pos.dialog;
/*
 * DialogSingleTextInput.java
 * James Madden
 * @DATE@
 * 
 * This is the dialog to add a single text input.
 */


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pos.lib.Reference;


public class DialogNewLogin extends JDialog implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = -3358204359691096586L;

	
	private String username;
	private String password;
	boolean isAdmin;
	
	private String enter = "Enter";
	private String cancel = "Cancel";
	private boolean validated;
	private ArrayList<String> conflicts;
	
	private JPanel content;
	private JTextField usernameField, passwordField;
	private JComboBox<String> authentificationBox;
	private JOptionPane optionPane;
	
	public DialogNewLogin(JFrame frame, String title, ArrayList<String> conflicts){
		super(frame, true);
		setTitle(title);
		setResizable(false);

		
		validated = false;
		this.conflicts = conflicts;
		
		content = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 0, 5);
		
		content.add(new JLabel("Username: "), c);
		
		usernameField = new JTextField();
        usernameField.addActionListener(this);
        c.gridx = 1;
        c.ipadx = 50;
        c.weightx = 1;
        c.insets = new Insets(5, 0, 0, 5);
        content.add(usernameField, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 0;
        c.weightx = 0;
        c.insets = new Insets(5, 5, 0, 5);
        content.add(new JLabel("Password: "), c);
        
		passwordField = new JTextField();
		passwordField.addActionListener(this);
        c.gridx = 1;
        c.ipadx = 50;
        c.weightx = 1;
        c.insets = new Insets(5, 0, 0, 5);
        content.add(passwordField, c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.ipadx = 0;
        c.weightx = 0;
        c.insets = new Insets(5, 5, 5, 5);
        content.add(new JLabel("Authentification: "), c);
        
		authentificationBox = new JComboBox<String>(Reference.AUTHENTIFICATIONS);
        c.gridx = 1;
        c.ipadx = 50;
        c.weightx = 1;
        c.insets = new Insets(5, 0, 5, 5);
        content.add(authentificationBox, c);
		
		Object[] array = {content};
		Object[] options = {enter, cancel};
		
		optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[0]);
        optionPane.addPropertyChangeListener(this);
        	
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    optionPane.setValue(new Integer(
                                        JOptionPane.CLOSED_OPTION));
            }
        });
        
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                usernameField.requestFocusInWindow();
            }
        });
		
		setContentPane(optionPane);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String prop = event.getPropertyName();
		 
        if (isVisible()
         && (event.getSource() == optionPane)
         && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
             JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();
 
            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }
 
            optionPane.setValue(
                 JOptionPane.UNINITIALIZED_VALUE);
 
            if (enter.equals(value)) {
                 String username = usernameField.getText();
                 String password = passwordField.getText();
                 String authentification = authentificationBox.getSelectedItem().toString();
                 
                 if(username.isEmpty() || password.isEmpty()){
                	 JOptionPane.showMessageDialog(new JFrame(),"Must give a valid input for each field", "ERROR", JOptionPane.ERROR_MESSAGE);
                	 return;
                 }
                 
                 if(conflicts != null){
                	 if(isConflict(username)){
                		 JOptionPane.showMessageDialog(new JFrame(),"New username cannot duplicate\na previously assigned username", "ERROR", JOptionPane.ERROR_MESSAGE);
                		 return;
                	 }
                 }
                 
                 this.username = username;
                 this.password = password;
                 this.isAdmin = authentification.equals(Reference.ADMIN);
                 
                 setValidated(true);
                 clearAndHide();
            } else {
            	clearAndHide();
            }
        }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		optionPane.setValue(enter);
		
	}
	
	public void clearAndHide() {
        usernameField.setText(null);
        passwordField.setText(null);
        setVisible(false);
    }
	
	public void setValidated(boolean b){
		validated = b;
	}

	public boolean isValidated(){
		return validated;
	}

	public boolean isConflict(String check){
		for(int i = 0; i < conflicts.size(); i++){
			if(conflicts.get(i).equals(check))
				return true;
		}
		return false;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public boolean isAdmin(){
		return isAdmin;
	}
}
