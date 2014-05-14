package pos.swing.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import pos.core.ServerManager;
import pos.core.Keys;
import pos.core.OutputWindow;
import pos.core.UpdateableContent;
import pos.core.UpdateableContentController;
import pos.swing.content.ConsoleContent;
import pos.swing.content.InventoryMaintenanceContent;
import pos.swing.content.ReadableLogContent;
import pos.swing.content.ReturnMaintenanceContent;

public class MaintenanceGUI extends JFrame{

	private static final long serialVersionUID = -1245352016605793408L;

	private String path;

	private JTabbedPane tabs;

	private InventoryMaintenanceContent imc;
	private ReturnMaintenanceContent rmc;
	private ReadableLogContent rlc;
	
	private ArrayList<UpdateableContent> contentTabs;
	
	private ServerManager server;
	private OutputWindow parentWindow;
	private Keys keys;
	
	public MaintenanceGUI(ServerManager i, OutputWindow out, String p, Keys keys){
		server = i;
		parentWindow = out;
		this.keys = keys;
		path = p;

		contentTabs = new ArrayList<UpdateableContent>();
		
		tabs = new JTabbedPane();
		tabs.addTab("Inventory Maintenance", imc = new InventoryMaintenanceContent(server, parentWindow, keys, p));
		tabs.addTab("Return Maintenance", rmc = new ReturnMaintenanceContent(server, parentWindow, keys));
		tabs.addTab("Readable Log", rlc = new ReadableLogContent());
		tabs.addTab("Console", new ConsoleContent(server, parentWindow));
		
		contentTabs.add(imc);
		contentTabs.add(rmc);
		contentTabs.add(rlc);
		
		this.addWindowListener(new WindowListener(){
			public void windowClosing(WindowEvent e) {
				for(Object closingTab: contentTabs){
					UpdateableContentController.removeActiveContent((UpdateableContent)closingTab);
				}
			}

			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}

		});
		
		setPreferredSize(new Dimension(600,800));
		
		setTitle("Maintenance");
		setContentPane(tabs);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
}
