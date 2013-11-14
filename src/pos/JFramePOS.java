package pos;

import javax.swing.JFrame;

public class JFramePOS extends JFrame {

	private static final long serialVersionUID = 6285187346014347499L;
	
	public InventoryManager inventory;
	public OutputWindow parentWindow;
	public Keys keys;
	
	public JFramePOS(InventoryManager inventory, OutputWindow parentWindow, Keys keys){
		this.inventory = inventory;
		this.parentWindow = parentWindow;
		this.keys = keys;
	}
	
}
