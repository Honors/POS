package pos;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import javax.swing.*;

@SuppressWarnings("serial")
public class ConfirmWindow extends JFrame implements ActionListener{
	Container pane;
	JPanel panel;
	Confirmable parent;
	String action1;
	String action2;
	JTextArea message;
	JButton confirm;
	JButton cancel;
	
	public ConfirmWindow(Confirmable _parent, String _message, String _action){
		super("Confirm Action");
		this.setSize(300, 150);
		
		parent = _parent;
		action1 = _action;
		
		pane = this.getContentPane();
		panel = new JPanel(new MigLayout());
		pane.add(panel);
		
		message = new JTextArea(_message);
		message.setEditable(false);
		panel.add(message, "span");
		
		confirm = new JButton("CONFIRM");
		confirm.setActionCommand("confirm");
		confirm.addActionListener(this);
		panel.add(confirm);
		
		action2 = "CANCEL";
		cancel = new JButton("CANCEL");
		cancel.setActionCommand(action2);
		cancel.addActionListener(this);
		panel.add(cancel);
		
		this.setVisible(true);
		
	}
	
	public ConfirmWindow(Confirmable _parent, String _message, String bText1, String _action1, String bText2, String _action2){
		super("Confirm Action");
		this.setSize(300, 150);
		
		parent = _parent;
		action1 = _action1;
		action2 = _action2;
		
		pane = this.getContentPane();
		panel = new JPanel(new MigLayout());
		pane.add(panel);
		
		message = new JTextArea(_message);
		message.setEditable(false);
		panel.add(message, "span");
		
		confirm = new JButton(bText1);
		confirm.setActionCommand("confirm");
		confirm.addActionListener(this);
		panel.add(confirm);
		
		cancel = new JButton(bText2);
		cancel.setActionCommand("action2");
		cancel.addActionListener(this);
		panel.add(cancel);
		
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent a){
		String s = a.getActionCommand();
		if (s.equals("confirm")){
			parent.actionConfirmed(action1);
		}
		if (s.equals("cancel")){
			parent.actionConfirmed("CANCEL");
		}
		if (s.equals("action2")){
			parent.actionConfirmed(action2);
		}
		this.setVisible(false);
	}
}
