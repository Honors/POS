package pos.model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import pos.core.Item;
import pos.core.JFramePOS;
import pos.gui.ProductInfoGUI;

@SuppressWarnings("serial")
public class SearchItem extends JTextArea implements MouseListener{
	
	private Item item;
	private Keys key;
	private JFramePOS parentWindow;
	
	private int itemStatus;
	
	public SearchItem(JFramePOS p, Item i, Keys _key, int status){
		parentWindow = p;
		item = i;
		key = _key;
		itemStatus = status;
		setBorder(new EmptyBorder(5,5,5,5));
		setText(i.toStringFormatted());
		setEditable(false);
		this.addMouseListener(this);
	}
	
	public void updateItem(InventoryItem i){
		item = i;
		setText(i.toStringFormatted());
	}
	
	public void delete(){
		removeMouseListener(this);
		setEnabled(false);
		setVisible(false);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		new ProductInfoGUI(parentWindow.server, parentWindow.parentWindow, this, item, key, itemStatus);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
			
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
