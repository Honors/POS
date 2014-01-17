package pos.swing.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import pos.core.UpdatableWindow;
import pos.lib.Reference;

public class ReportGUI extends JFrame implements ActionListener, UpdatableWindow {

	private static final long serialVersionUID = -3818721107019469865L;

	private JPanel content, buttonBar, dateBar, spacer;
	private JDatePickerImpl fromPicker, toPicker;
	private JLabel reportTypeLabel, fromLabel, toLabel;
	private JComboBox<Object> reportTypes;
	private JButton exportPDF, exportExcel, generate;
	private JTextArea info;
	private JScrollPane infoPane;
	
	public ReportGUI(){
		GridBagConstraints c_buttonBar = new GridBagConstraints();
		c_buttonBar.gridx = 0;
		c_buttonBar.gridy = 0;
		c_buttonBar.ipadx = 0;
		c_buttonBar.ipady = 0;
		c_buttonBar.weightx = 1;
		c_buttonBar.weighty = 0;
		c_buttonBar.gridwidth = 1;
		c_buttonBar.gridheight = 1;
		c_buttonBar.anchor = GridBagConstraints.LINE_START;
		c_buttonBar.fill = GridBagConstraints.NONE;
		c_buttonBar.insets = new Insets(5,5,0,5);
		
		GridBagConstraints c_dateBar = new GridBagConstraints();
		c_dateBar.gridx = 0;
		c_dateBar.gridy = 1;
		c_dateBar.ipadx = 0;
		c_dateBar.ipady = 0;
		c_dateBar.weightx = 1;
		c_dateBar.weighty = 0;
		c_dateBar.gridwidth = 1;
		c_dateBar.gridheight = 1;
		c_dateBar.anchor = GridBagConstraints.LINE_START;
		c_dateBar.fill = GridBagConstraints.NONE;
		c_dateBar.insets = new Insets(0,5,0,5);
		
		GridBagConstraints c_infoArea = new GridBagConstraints();
		c_infoArea.gridx = 0;
		c_infoArea.gridy = 2;
		c_infoArea.ipadx = 0;
		c_infoArea.ipady = 250;
		c_infoArea.weightx = 1;
		c_infoArea.weighty = 1;
		c_infoArea.gridwidth = 1;
		c_infoArea.gridheight = 1;
		c_infoArea.anchor = GridBagConstraints.CENTER;
		c_infoArea.fill = GridBagConstraints.BOTH;
		c_infoArea.insets = new Insets(0,5,5,5);
		
		content = new JPanel(new GridBagLayout());
		
		buttonBar = new JPanel(new FlowLayout());
		
		reportTypeLabel = new JLabel("Report Type: ");
		buttonBar.add(reportTypeLabel);
		
		reportTypes = new JComboBox<Object>(Reference.REPORT_TYPES.toArray());
		reportTypes.addActionListener(this);
		buttonBar.add(reportTypes);
		
		spacer = new JPanel();
		spacer.setSize(15, 0);
		buttonBar.add(spacer);
		
		exportPDF = new JButton("Export PDF...");
		exportPDF.addActionListener(this);
		buttonBar.add(exportPDF);
		
		exportExcel = new JButton("Export Excel...");
		exportExcel.addActionListener(this);
		buttonBar.add(exportExcel);
		
		spacer = new JPanel();
		spacer.setSize(15, 0);
		buttonBar.add(spacer);
		
		generate = new JButton("Generate");
		generate.addActionListener(this);

		buttonBar.add(generate);
		
		dateBar = new JPanel(new FlowLayout());
		
		fromLabel = new JLabel("From: ");
		dateBar.add(fromLabel);
		
		fromPicker = new JDatePickerImpl(new JDatePanelImpl(null));
		fromPicker.setBorder(new EmptyBorder(0, 0, 0, 10));
		dateBar.add(fromPicker);
		
		toLabel = new JLabel("To: ");
		dateBar.add(toLabel);
		
		toPicker = new JDatePickerImpl(new JDatePanelImpl(null));
		dateBar.add(toPicker);
		
		info = new JTextArea();
		info.setBorder(new EmptyBorder(5,5,5,5));
		infoPane = new JScrollPane(info);
		
		content.add(buttonBar, c_buttonBar);
		content.add(dateBar, c_dateBar);
		content.add(infoPane, c_infoArea);
		
		setTitle("Report");
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
	}

	@Override
	public void update(String command) {
		
	}
}
