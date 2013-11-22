package pos.core;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import pos.dialog.DialogSingleTextInput;
import pos.model.Keys;

public class RegisterContent extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = -4333564621641889051L;
	
	private int identifier;
	
	private JLabel label;
	private JTree tree;
	private JScrollPane scrollTree;
	private JButton add, edit, delete;
	
	private Keys keys;
	
	public RegisterContent(Keys keys, int identifier){
		super(new GridBagLayout());
		this.keys = keys;
		this.identifier = identifier;
		
		GridBagConstraints c_label = new GridBagConstraints();
		c_label.gridx = 0;
		c_label.gridy = 0;
		c_label.ipadx = 0;
		c_label.ipady = 5;
		c_label.weightx = 0;
		c_label.weighty = 0;
		c_label.gridheight = 1;
		c_label.gridwidth = 1;
		c_label.anchor = GridBagConstraints.FIRST_LINE_START;
		c_label.fill = GridBagConstraints.BOTH;
		c_label.insets = new Insets(5,10,0,10);
		
		GridBagConstraints c_tree = new GridBagConstraints();
		c_tree.gridx = 0;
		c_tree.gridy = 1;
		c_tree.ipadx = 100;
		c_tree.ipady = 0;
		c_tree.weightx = 1;
		c_tree.weighty = 1;
		c_tree.gridheight = 4;
		c_tree.gridwidth = 1;
		c_tree.anchor = GridBagConstraints.FIRST_LINE_START;
		c_tree.fill = GridBagConstraints.BOTH;
		c_tree.insets = new Insets(5,10,10,0);
		
		GridBagConstraints c_add = new GridBagConstraints();
		c_add.gridx = 1;
		c_add.gridy = 1;
		c_add.ipadx = 100;
		c_add.ipady = 5;
		c_add.weightx = 0;
		c_add.weighty = 0;
		c_add.gridheight = 1;
		c_add.gridwidth = 1;
		c_add.anchor = GridBagConstraints.FIRST_LINE_START;
		c_add.fill = GridBagConstraints.BOTH;
		c_add.insets = new Insets(5,20,10,20);
		
		GridBagConstraints c_edit = new GridBagConstraints();
		c_edit.gridx = 1;
		c_edit.gridy = 2;
		c_edit.ipadx = 100;
		c_edit.ipady = 5;
		c_edit.weightx = 0;
		c_edit.weighty = 0;
		c_edit.gridheight = 1;
		c_edit.gridwidth = 1;
		c_edit.anchor = GridBagConstraints.FIRST_LINE_START;
		c_edit.fill = GridBagConstraints.BOTH;
		c_edit.insets = new Insets(10,20,10,20);
		
		GridBagConstraints c_delete = new GridBagConstraints();
		c_delete.gridx = 1;
		c_delete.gridy = 3;
		c_delete.ipadx = 100;
		c_delete.ipady = 5;
		c_delete.weightx = 0;
		c_delete.weighty = 0;
		c_delete.gridheight = 1;
		c_delete.gridwidth = 1;
		c_delete.anchor = GridBagConstraints.FIRST_LINE_START;
		c_delete.fill = GridBagConstraints.BOTH;
		c_delete.insets = new Insets(10,20,10,20);
		
		GridBagConstraints c_filler = new GridBagConstraints();
		c_filler.gridx = 1;
		c_filler.gridy = 4;
		c_filler.ipadx = 0;
		c_filler.ipady = 0;
		c_filler.weightx = 1;
		c_filler.weighty = 1;
		c_filler.gridheight = 1;
		c_filler.gridwidth = 1;
		c_filler.anchor = GridBagConstraints.FIRST_LINE_START;
		c_filler.fill = GridBagConstraints.BOTH;
		c_filler.insets = new Insets(0,0,0,0);
		
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
	    renderer.setOpenIcon(null);
	    renderer.setClosedIcon(null);
	    renderer.setLeafIcon(null);
	    renderer.setBorder(new EmptyBorder(1,3,1,0));
				
		label = new JLabel(getLabel());
		label.setFont(label.getFont().deriveFont(14.0f));
		add(label, c_label);

		tree = new JTree(new DefaultMutableTreeNode());
		tree.addMouseListener(this);
		tree.setRootVisible(false);
		tree.setCellRenderer(renderer);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		scrollTree = new JScrollPane(tree);
		add(scrollTree, c_tree);
		
		add = new JButton("ADD");
		add.addActionListener(this);
		add.setEnabled(true);
		add(add, c_add);
		
		edit = new JButton("EDIT");
		edit.addActionListener(this);
		edit.setEnabled(tree.getSelectionCount() != 0);
		add(edit, c_edit);
		
		delete = new JButton("DELETE");
		delete.addActionListener(this);
		delete.setEnabled(tree.getSelectionCount() != 0);
		add(delete, c_delete);
		
		updateList();
	}
	
	public String getLabel(){
		String label;
		if(identifier == Keys.BRAND){
			label = "Registered Brands:";
		} else if(identifier == Keys.TYPE){
			label = "Registered Types:";
		} else if(identifier == Keys.COLOR){
			label = "Registered Colors:";
		} else if(identifier == Keys.SIZE){
			label = "Registered Sizes:";
		} else if(identifier == Keys.GENDER){
			label = "Registered Genders:";
		} else if(identifier == Keys.CLIENT){
			label = "Registered Clients:";
		} else {
			label = "Registered:";
		}
		return label;
	}
	
	public ArrayList<String> getAssociatedList(){
		if(identifier == Keys.BRAND){
			return keys.brands;
		} else if(identifier == Keys.TYPE){
			return keys.types;
		} else if(identifier == Keys.COLOR){
			return keys.colors;
		} else if(identifier == Keys.SIZE){
			return keys.sizes;
		} else if(identifier == Keys.GENDER){
			return keys.genders;
		} else if(identifier == Keys.CLIENT){
			return keys.clients;
		} else {
			return null;
		}
	}
	
	public void updateList(){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		if(getAssociatedList() != null){
			for(int i = 0; i < getAssociatedList().size(); i++){
				root.add(new DefaultMutableTreeNode(getAssociatedList().get(i)));
			}
			tree.setModel(new DefaultTreeModel(root));
		}
		revalidate();
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(add)){
			DialogSingleTextInput addDialog = new DialogSingleTextInput(new JFrame(), "Add...", "", "", getAssociatedList());
			if(addDialog.getValidated()){
				getAssociatedList().add(addDialog.getValidatedInput());
				keys.rewriteAll(identifier);
				updateList();
			}
		}
		
		if(event.getSource().equals(edit)){
			String oldName = tree.getSelectionPath().getLastPathComponent().toString();
			DialogSingleTextInput addDialog = new DialogSingleTextInput(new JFrame(), "Edit...", "", oldName, getAssociatedList());
			if(addDialog.getValidated()){
				String newName = addDialog.getValidatedInput();
				getAssociatedList().set(getAssociatedList().indexOf(oldName), newName);
				keys.rewriteAll(identifier);
				updateList();
			}
		}
		
		if(event.getSource().equals(delete)){
			getAssociatedList().remove(tree.getSelectionPath().getLastPathComponent().toString());
			keys.rewriteAll(identifier);
			updateList();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(tree.getSelectionPath() != null){
			edit.setEnabled(true);
			delete.setEnabled(true);
		} else {
			edit.setEnabled(false);
			delete.setEnabled(false);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
