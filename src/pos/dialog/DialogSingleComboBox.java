package pos.dialog;
/*
 * DialogSingleTextInput.java
 * James Madden
 * @DATE@
 * 
 * This is the dialog to add a single text input.
 */


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
import javax.swing.JOptionPane;


/**
 * This class creates a dialog that asks for a single drob box selection
 * 
 * @author James Madden
 */
public class DialogSingleComboBox extends JDialog implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = -3358204359691096586L;

	private String input;
	private String enter = "Enter";
	private String cancel = "Cancel";
	private boolean validated;
	
	private JComboBox<Object> dropBox;
	private JOptionPane optionPane;
	
	/**
	 * Creates a dialog that contains a single drop box
	 * 
	 * @param frame The frame which the dialog is displayed
	 * @param title The title of the frame
	 * @param message The message of the frame
	 * @param list The contents of the drop box
	 */
	public DialogSingleComboBox(JFrame frame, String title, String message, ArrayList<String> list){
		super(frame, true);
		setTitle(title);
		setResizable(false);
		dropBox = new JComboBox<Object>(list.toArray());
		
		validated = false;
		
		Object[] array = {message, dropBox};
		Object[] options = {enter, cancel};
		
		optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[0]);
		
		setContentPane(optionPane);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    optionPane.setValue(new Integer(
                                        JOptionPane.CLOSED_OPTION));
            }
        });
        
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                dropBox.requestFocusInWindow();
            }
        });
		
        optionPane.addPropertyChangeListener(this);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	/**
	 * Gets the selected drop box element
	 * 
	 * @return a String of the text input
	 */
	public String getValidatedInput(){
		return input;
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
                 input = dropBox.getSelectedItem().toString();
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
	
	/**
	 * Disposes the dialog
	 */
	public void clearAndHide() {
        setVisible(false);
    }
	
	/**
	 * Sets the validation of the drop box selection
	 * 
	 * @param b {@code true} if the text input is validated
	 */
	public void setValidated(boolean b){
		validated = b;
	}

	/**
	 * Gets whether or not the drop box select is valid
	 * 
	 * @return {@code true} if the dialog input has been validated, {@code false} if the dialog input is not
	 */
	public boolean getValidated(){
		return validated;
	}
}
