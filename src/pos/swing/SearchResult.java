package pos.swing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import pos.item.Item;
import pos.core.Keys;
import pos.core.OutputWindow;
import pos.core.ServerManager;
import pos.swing.JFramePOS;
import pos.swing.gui.ProductInfoGUI;

public class SearchResult extends JTextArea implements MouseListener{

	private static final long serialVersionUID = 2992374815754805458L;
	
	private Item item;
	private Keys key;
	private ServerManager server;
	private OutputWindow outWindow;
	
	private int itemStatus;
	
	public SearchResult(ServerManager server, OutputWindow out, Item i, Keys _key, int status){
		this.server = server;
		outWindow = out;
		item = i;
		key = _key;
		itemStatus = status;
		setBorder(new EmptyBorder(5,5,5,5));
		setText(i.toStringFormatted());
		setEditable(false);
		this.addMouseListener(this);
	}
	
	public void updateItem(Item i){
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
		new ProductInfoGUI(server, outWindow, this, item, key, itemStatus);
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
