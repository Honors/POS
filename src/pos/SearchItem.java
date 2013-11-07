package pos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class SearchItem extends JTextArea implements MouseListener{
	Item item;
	Keys key;
	SearchGUI parentWindow;
	
	public SearchItem(SearchGUI p, Item i, Keys _key){
		parentWindow = p;
		item = i;
		key = _key;
		setText(i.toStringFormatted());
		setEditable(false);
		this.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		ProductInfoGUI p = new ProductInfoGUI(parentWindow.inventory, parentWindow.parentWindow, item, key, false);
		p.setSize(450, 400);
		p.setVisible(true);
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
