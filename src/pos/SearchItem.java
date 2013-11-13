package pos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SearchItem extends JTextArea implements MouseListener{
	Item item;
	Keys key;
	JFramePOS parentWindow;
	
	public SearchItem(JFramePOS p, Item i, Keys _key){
		parentWindow = p;
		item = i;
		key = _key;
		setBorder(new EmptyBorder(5,5,5,5));
		setText(i.toStringFormatted());
		setEditable(false);
		this.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		new ProductInfoGUI(parentWindow.inventory, parentWindow.parentWindow, item, key, false);
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
