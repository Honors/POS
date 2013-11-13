package pos;

import javax.swing.JFrame;

public class JFramePOS extends JFrame {

	public static InventoryManager inventory;
	public static OutputWindow parentWindow;
	public static Keys keys;
	
	public JFramePOS(InventoryManager inventory, OutputWindow parentWindow, Keys keys){
		this.inventory = inventory;
		this.parentWindow = parentWindow;
		this.keys = keys;
	}
	
}
