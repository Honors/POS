package pos.swing.gui;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

import pos.swing.JFramePOS;
import pos.core.OutputWindow;
import pos.lib.Reference;
import pos.swing.content.RegisterContent;
import pos.core.ServerManager;
import pos.core.Keys;
import pos.core.UpdateableContentController;
public class RegisterGUI extends JFramePOS{

	private static final long serialVersionUID = 6343961546615340321L;
;
	private JTabbedPane tabs;
	
	private ArrayList<RegisterContent> contentTabs;
	private RegisterContent brandTab, typeTab, colorTab, sizeTab, genderTab, clientTab;
	
	public RegisterGUI(ServerManager server, OutputWindow parentWindow, Keys keys){
		super(server, parentWindow, keys);

		contentTabs = new ArrayList<RegisterContent>();
		
		tabs = new JTabbedPane();
		tabs.addTab("Brand", brandTab = new RegisterContent(server, parentWindow, keys, Reference.BRAND));
		tabs.addTab("Type", typeTab = new RegisterContent(server, parentWindow, keys, Reference.TYPE));
		tabs.addTab("Color", colorTab = new RegisterContent(server, parentWindow, keys, Reference.COLOR));
		tabs.addTab("Size", sizeTab = new RegisterContent(server, parentWindow, keys, Reference.SIZE));
		tabs.addTab("Gender", genderTab = new RegisterContent(server, parentWindow, keys, Reference.GENDER));
		tabs.addTab("Client", clientTab = new RegisterContent(server, parentWindow, keys, Reference.CLIENT));
		
		contentTabs.add(brandTab);
		contentTabs.add(typeTab);
		contentTabs.add(colorTab);
		contentTabs.add(sizeTab);
		contentTabs.add(genderTab);
		contentTabs.add(clientTab);
		
		this.addWindowListener(new WindowListener(){
			public void windowClosing(WindowEvent e) {
				for(RegisterContent closingTab: contentTabs){
					UpdateableContentController.removeActiveContent(closingTab);
				}
			}

			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}

		});
		
		setTitle("Register");
		setContentPane(tabs);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
