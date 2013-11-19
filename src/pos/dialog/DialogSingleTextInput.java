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

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * This class creates a dialog that asks for a one text input
 * 
 * @author James Madden
 */
public class DialogSingleTextInput extends JDialog implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = -3358204359691096586L;

	private String input;
	private String typedText = null;
	private String enter = "Enter";
	private String cancel = "Cancel";
	private boolean validated;
	private ArrayList<String> conflicts;
	
	private JTextField textField;
	private JOptionPane optionPane;
	
	public DialogSingleTextInput(JFrame frame, String title, String message, String initialTextArea, ArrayList<String> conflicts){
		super(frame, true);
		setTitle(title);
		setResizable(false);
		
		textField = new JTextField(10);
		textField.setText(initialTextArea);
		
		validated = false;
		this.conflicts = conflicts;
		
		Object[] array = {message, textField};
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
                textField.requestFocusInWindow();
            }
        });
		
        textField.addActionListener(this);
        optionPane.addPropertyChangeListener(this);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
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
                 typedText = textField.getText();
                 if(typedText.isEmpty()){
                	 JOptionPane.showMessageDialog(new JFrame(),"Must give a valid input", "ERROR", JOptionPane.ERROR_MESSAGE);
                	 return;
                 }
                 if(conflicts != null){
                	 if(isConflict(typedText)){
                		 JOptionPane.showMessageDialog(new JFrame(),"Input cannot duplicate previously generated information", "ERROR", JOptionPane.ERROR_MESSAGE);
                		 return;
                	 }
                 }
                 input = typedText;
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
        textField.setText(null);
        setVisible(false);
    }
	
	public void setValidated(boolean b){
		validated = b;
	}

	public boolean getValidated(){
		return validated;
	}
	
	public boolean isConflict(String check){
		for(int i = 0; i < conflicts.size(); i++){
			if(conflicts.get(i).equals(check))
				return true;
		}
		return false;
	}
}
