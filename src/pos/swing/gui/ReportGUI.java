package pos.swing.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReportGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -3818721107019469865L;

	private JPanel content;
	
	public ReportGUI(){
		content = new JPanel();
		content.add(new JLabel("In Development..."));
		
		setTitle("Report");
		setContentPane(content);
		
		//pack();
		setSize(450,320);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
