package pos.gui;


import javax.swing.JTabbedPane;

import pos.core.JFramePOS;
import pos.core.OutputWindow;
import pos.core.RegisterContent;
import pos.core.ServerManager;
import pos.model.Keys;

public class RegisterGUI extends JFramePOS{

	private static final long serialVersionUID = 6343961546615340321L;
;
	private JTabbedPane tabs;
	
	public RegisterGUI(ServerManager server, OutputWindow parentWindow, Keys keys){
		super(server, parentWindow, keys);

		tabs = new JTabbedPane();
		tabs.addTab("Brand", new RegisterContent(keys, Keys.BRAND));
		tabs.addTab("Type", new RegisterContent(keys, Keys.TYPE));
		tabs.addTab("Color", new RegisterContent(keys, Keys.COLOR));
		tabs.addTab("Size", new RegisterContent(keys, Keys.SIZE));
		tabs.addTab("Gender", new RegisterContent(keys, Keys.GENDER));
		tabs.addTab("Client", new RegisterContent(keys, Keys.CLIENT));
		
		setTitle("Register");
		setContentPane(tabs);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
