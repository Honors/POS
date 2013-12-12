package pos.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JToggleButton;

public class JToggleEnableButton extends JToggleButton implements ActionListener{

	private static final long serialVersionUID = 5049795357561233170L;

	private ArrayList<Component> children;
	
	public JToggleEnableButton(String text, boolean isSelected){
		super(text);
		super.setSelected(isSelected);
		addActionListener(this);
		setOpaque(true);
		setBackground(new Color(0xc5c1b8));
		children = new ArrayList<Component>();
		updateChildren();
	}
	
	@Override
	public void setSelected(boolean isSelected){
		super.setSelected(isSelected);
		updateChildren();
	}
	
	@Override
	public Component add(Component c){
		if(children.add(c)){
			updateChildren();
			return c;
		} else
			return null;
	}
	
	public void updateChildren(){
		for(Component c : children){
			c.setEnabled(isSelected());
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		updateChildren();
	}
}
