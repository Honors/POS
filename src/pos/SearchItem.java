package pos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SearchItem extends JTextArea implements MouseListener{
	
	private boolean isEditable;
	
	private Item item;
	private Keys key;
	private JFramePOS parentWindow;
	
	public SearchItem(JFramePOS p, Item i, Keys _key, boolean editable){
		parentWindow = p;
		item = i;
		key = _key;
		isEditable = editable;
		setBorder(new EmptyBorder(5,5,5,5));
		setText(i.toStringFormatted());
		setEditable(false);
		this.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		new ProductInfoGUI(parentWindow.inventory, parentWindow.parentWindow, item, key, false, isEditable);
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
