package pos.swing.gui;


import javax.swing.JTabbedPane;

import pos.swing.JFramePOS;
import pos.core.OutputWindow;
import pos.lib.Reference;
import pos.swing.RegisterContent;
import pos.core.ServerManager;
import pos.core.Keys;

public class RegisterGUI extends JFramePOS{

	private static final long serialVersionUID = 6343961546615340321L;
;
	private JTabbedPane tabs;
	
	public RegisterGUI(ServerManager server, OutputWindow parentWindow, Keys keys){
		super(server, parentWindow, keys);

		tabs = new JTabbedPane();
		tabs.addTab("Brand", new RegisterContent(server, parentWindow, keys, Reference.BRAND));
		tabs.addTab("Type", new RegisterContent(server, parentWindow, keys, Reference.TYPE));
		tabs.addTab("Color", new RegisterContent(server, parentWindow, keys, Reference.COLOR));
		tabs.addTab("Size", new RegisterContent(server, parentWindow, keys, Reference.SIZE));
		tabs.addTab("Gender", new RegisterContent(server, parentWindow, keys, Reference.GENDER));
		tabs.addTab("Client", new RegisterContent(server, parentWindow, keys, Reference.CLIENT));
		
		setTitle("Register");
		setContentPane(tabs);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
