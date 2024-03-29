package pos.swing;

import javax.swing.JFrame;

import pos.core.OutputWindow;
import pos.core.ServerManager;
import pos.core.Keys;

public class JFramePOS extends JFrame {

	private static final long serialVersionUID = 6285187346014347499L;
	
	public ServerManager server;
	public OutputWindow parentWindow;
	public Keys keys;
	
	public JFramePOS(ServerManager server, OutputWindow parentWindow, Keys keys){
		this.server = server;
		this.parentWindow = parentWindow;
		this.keys = keys;
	}
	
}
