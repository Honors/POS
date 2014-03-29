package pos.swing.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pos.backup.BackupReader;
import pos.backup.BackupWriter;
import pos.lib.Reference;
import pos.log.Fetcher;
import pos.log.LogInfoGenerator;
import pos.log.TimeStamp;
import pos.core.ServerManager;
import pos.item.InventoryItem;
import pos.item.ReturnItem;
import pos.swing.JFramePOS;
import pos.core.Keys;
import pos.core.OutputWindow;
import pos.core.UpdateableContent;
import pos.filter.CSVFilter;
import pos.swing.SearchResult;
import pos.swing.content.ConsoleContent;
import pos.swing.content.InventoryMaintinanceContent;
import pos.swing.content.ReadableLogContent;
import pos.swing.content.ReturnMaintinanceContent;

public class MaintinanceGUI extends JFrame{

	private static final long serialVersionUID = -1245352016605793408L;

	private String path;

	private JTabbedPane tabs;

	private ServerManager server;
	private OutputWindow parentWindow;
	private Keys keys;
	
	public MaintinanceGUI(ServerManager i, OutputWindow out, String p, Keys keys){
		server = i;
		parentWindow = out;
		this.keys = keys;
		path = p;

		tabs = new JTabbedPane();
		tabs.addTab("Inventory Maintenance", new InventoryMaintinanceContent(server, parentWindow, keys, p));
		tabs.addTab("Return Maintenance", new ReturnMaintinanceContent(server, parentWindow, keys));
		tabs.addTab("Readable Log", new ReadableLogContent());
		tabs.addTab("Console", new ConsoleContent(server, parentWindow));
		
		setPreferredSize(new Dimension(600,800));
		
		setTitle("Maintinance");
		setContentPane(tabs);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
