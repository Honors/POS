package pos;

import javax.swing.JFrame;

public class JFramePOS extends JFrame {

	public static InventoryManager inventory;
	public static OutputWindow parentWindow;
	
	public JFramePOS(InventoryManager inventory, OutputWindow parentWindow){
		this.inventory = inventory;
		this.parentWindow = parentWindow;
	}
	
}
