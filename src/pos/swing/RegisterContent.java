package pos.swing;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

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
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import pos.core.OutputWindow;
import pos.core.ServerManager;
import pos.dialog.DialogSingleComboBox;
import pos.dialog.DialogSingleTextInput;
import pos.lib.Reference;
import pos.log.LogInfoGenerator;
import pos.core.Keys;

public class RegisterContent extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = -4333564621641889051L;
	
	private int identifier;
	
	private JPanel filler;
	private JLabel label;
	private JTree tree;
	private JScrollPane scrollTree;
	private JButton add, edit, delete, shiftUp, shiftDown;
	
	private ServerManager server;
	private OutputWindow parentWindow;
	private Keys keys;
		
	/**
	 * Creates a JPanel formatted for a tab of RegisterGUI
	 *  
	 * @param keys Lists of identification elements
	 * @param identifier A Reference constant value indicating which list this content will display and edit
	 */
	public RegisterContent(ServerManager server, OutputWindow parentWindow, Keys keys, int identifier){
		super(new GridBagLayout());
		this.server = server;
		this.parentWindow = parentWindow;
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
		c_tree.gridheight = 6;
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
		c_add.anchor = GridBagConstraints.FIRST_LINE_END;
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
		c_edit.anchor = GridBagConstraints.FIRST_LINE_END;
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
		c_delete.anchor = GridBagConstraints.FIRST_LINE_END;
		c_delete.fill = GridBagConstraints.BOTH;
		c_delete.insets = new Insets(10,20,10,20);
		
		GridBagConstraints c_shiftUp = new GridBagConstraints();
		c_shiftUp.gridx = 1;
		c_shiftUp.gridy = 4;
		c_shiftUp.ipadx = 100;
		c_shiftUp.ipady = 5;
		c_shiftUp.weightx = 0;
		c_shiftUp.weighty = 0;
		c_shiftUp.gridheight = 1;
		c_shiftUp.gridwidth = 1;
		c_shiftUp.anchor = GridBagConstraints.FIRST_LINE_END;
		c_shiftUp.fill = GridBagConstraints.BOTH;
		c_shiftUp.insets = new Insets(20,20,10,20);
		
		GridBagConstraints c_shiftDown = new GridBagConstraints();
		c_shiftDown.gridx = 1;
		c_shiftDown.gridy = 5;
		c_shiftDown.ipadx = 100;
		c_shiftDown.ipady = 5;
		c_shiftDown.weightx = 0;
		c_shiftDown.weighty = 0;
		c_shiftDown.gridheight = 1;
		c_shiftDown.gridwidth = 1;
		c_shiftDown.anchor = GridBagConstraints.FIRST_LINE_END;
		c_shiftDown.fill = GridBagConstraints.BOTH;
		c_shiftDown.insets = new Insets(10,20,10,20);
		
		GridBagConstraints c_filler = new GridBagConstraints();
		c_filler.gridx = 1;
		c_filler.gridy = 6;
		c_filler.ipadx = 0;
		c_filler.ipady = 0;
		c_filler.weightx = 0;
		c_filler.weighty = 1;
		c_filler.gridheight = 1;
		c_filler.gridwidth = 1;
		c_filler.anchor = GridBagConstraints.FIRST_LINE_END;
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
		add.setMnemonic(KeyEvent.VK_A);
		add.setEnabled(true);
		add(add, c_add);
		
		edit = new JButton("EDIT");
		edit.addActionListener(this);
		edit.setMnemonic(KeyEvent.VK_E);
		edit.setEnabled(tree.getSelectionCount() != 0);
		add(edit, c_edit);
		
		delete = new JButton("DELETE");
		delete.addActionListener(this);
		delete.setMnemonic(KeyEvent.VK_D);
		delete.setEnabled(tree.getSelectionCount() != 0);
		add(delete, c_delete);
		
		shiftUp = new JButton("SHIFT UP");
		shiftUp.addActionListener(this);
		shiftUp.setMnemonic(KeyEvent.VK_UP);
		shiftUp.setEnabled(tree.getSelectionCount() != 0);
		add(shiftUp, c_shiftUp);
		
		shiftDown = new JButton("SHIFT DOWN");
		shiftDown.addActionListener(this);
		shiftDown.setMnemonic(KeyEvent.VK_DOWN);
		shiftDown.setEnabled(tree.getSelectionCount() != 0);
		add(shiftDown, c_shiftDown);
			
		filler = new JPanel();
		add(filler, c_filler);
		updateList();
	}
	
	/**
	 * Creates a string for the label of the content 
	 * 
	 * @return a string correctly named for the content displayed
	 */
	public String getLabel(){
		String label;
		if(identifier == Reference.BRAND){
			label = "Registered Brands:";
		} else if(identifier == Reference.TYPE){
			label = "Registered Types:";
		} else if(identifier == Reference.COLOR){
			label = "Registered Colors:";
		} else if(identifier == Reference.SIZE){
			label = "Registered Sizes:";
		} else if(identifier == Reference.GENDER){
			label = "Registered Genders:";
		} else if(identifier == Reference.CLIENT){
			label = "Registered Clients:";
		} else {
			label = "Registered:";
		}
		return label;
	}
	
	/**
	 * Gets the Keys list that this content is displaying and editing
	 * 
	 * @return the Keys list associated with this content
	 */
	public ArrayList<String> getAssociatedList(){
		if(identifier == Reference.BRAND){
			return keys.brands;
		} else if(identifier == Reference.TYPE){
			return keys.types;
		} else if(identifier == Reference.COLOR){
			return keys.colors;
		} else if(identifier == Reference.SIZE){
			return keys.sizes;
		} else if(identifier == Reference.GENDER){
			return keys.genders;
		} else if(identifier == Reference.CLIENT){
			return keys.clients;
		} else {
			return null;
		}
	}
	
	public String getType(){
		String label;
		if(identifier == Reference.BRAND){
			label = "Brand";
		} else if(identifier == Reference.TYPE){
			label = "Type";
		} else if(identifier == Reference.COLOR){
			label = "Color";
		} else if(identifier == Reference.SIZE){
			label = "Size";
		} else if(identifier == Reference.GENDER){
			label = "Gender";
		} else if(identifier == Reference.CLIENT){
			label = "Client";
		} else {
			label = "";
		}
		return label;
	}
	
	/**
	 * Resets all the data in the list from the associated Keys list
	 */
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
	
	/**
	 * Finds the TreePath containing a specific string
	 * 
	 * @param root the root of the tree
	 * @param s the string to find the TreePath of
	 * @return a TreePath containing the searched string, 's'
	 */
	private TreePath find(DefaultMutableTreeNode root, String s) {
	    @SuppressWarnings("unchecked")
	    Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
	    while (e.hasMoreElements()) {
	        DefaultMutableTreeNode node = e.nextElement();
	        if (node.toString().equalsIgnoreCase(s)) {
	            return new TreePath(node.getPath());
	        }
	    }
	    return null;
	}
	
	/**
	 * Updates the logic for all the buttons
	 */
	public void updateButtons(){
		edit.setEnabled(tree.getSelectionPath() != null);
		delete.setEnabled(tree.getSelectionPath() != null);
		shiftUp.setEnabled(tree.getSelectionPath() != null ? tree.getSelectionRows()[0] != 0 : false);
		shiftDown.setEnabled(tree.getSelectionPath() != null ? tree.getSelectionRows()[0] != getAssociatedList().size()-1 : false);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(add)){
			DialogSingleTextInput addDialog = new DialogSingleTextInput(new JFrame(), "Add...", "", "", getAssociatedList());
			if(addDialog.getValidated()){
				getAssociatedList().add(addDialog.getValidatedInput().trim());
				keys.write(identifier);
				parentWindow.writeToOutput(LogInfoGenerator.generateElementNewStatement(getType(), addDialog.getValidatedInput()));
				updateList();
		
				TreePath pathToNewElement = find((DefaultMutableTreeNode)tree.getModel().getRoot(), addDialog.getValidatedInput());
				tree.setSelectionPath(pathToNewElement);
				tree.scrollPathToVisible(pathToNewElement);
				tree.requestFocus();
				updateButtons();
			}
		}
		
		if(event.getSource().equals(edit)){
			String oldName = tree.getSelectionPath().getLastPathComponent().toString();
			DialogSingleTextInput addDialog = new DialogSingleTextInput(new JFrame(), "Edit...", "", oldName, getAssociatedList());
			if(addDialog.getValidated()){
				String newName = addDialog.getValidatedInput().trim();
				getAssociatedList().set(getAssociatedList().indexOf(oldName), newName);
				keys.write(identifier);
				server.updateInventoryElement(identifier, oldName, newName);
				parentWindow.writeToOutput(LogInfoGenerator.generateElementEditStatement(getType(), oldName, addDialog.getValidatedInput()));
				updateList();
				
				TreePath pathToNewElement = find((DefaultMutableTreeNode)tree.getModel().getRoot(), newName);
				tree.setSelectionPath(pathToNewElement);
				tree.scrollPathToVisible(pathToNewElement);
				tree.requestFocus();
				updateButtons();
				
				parentWindow.update("");
			}
		}
		
		if(event.getSource().equals(delete)){
			String toDelete = tree.getSelectionPath().getLastPathComponent().toString();
			ArrayList<String> listWithoutToDelete = new ArrayList<String>(getAssociatedList().subList(0, getAssociatedList().indexOf(toDelete)));
			listWithoutToDelete.addAll(getAssociatedList().subList(getAssociatedList().indexOf(toDelete) + 1, getAssociatedList().size()));
			DialogSingleComboBox dialog = new DialogSingleComboBox(new JFrame(), "Migrate...", "Please indicate what element the items containing\nthe deleting element should migrate to", listWithoutToDelete);
			if(dialog.getValidated()){
				getAssociatedList().remove(toDelete);
				keys.write(identifier);
				server.updateInventoryElement(identifier, toDelete, dialog.getValidatedInput());
				parentWindow.writeToOutput(LogInfoGenerator.generateElementDeleteStatement(getType(), toDelete, dialog.getValidatedInput()));
				updateList();
				
				tree.requestFocus();
				updateButtons();
				
				parentWindow.update("");
			}
		}
		
		if(event.getSource().equals(shiftUp)){
			String selectedElement = tree.getSelectionPath().getLastPathComponent().toString();
			int index1 = getAssociatedList().indexOf(selectedElement);
			int index2 = index1 - 1;
			Collections.swap(getAssociatedList(), index1, index2);
			keys.write(identifier);
			updateList();
			
			TreePath pathToSelectedElement = find((DefaultMutableTreeNode)tree.getModel().getRoot(), selectedElement);
			tree.setSelectionPath(pathToSelectedElement);
			tree.scrollPathToVisible(pathToSelectedElement);
			tree.requestFocus();
			updateButtons();
		}
		
		if(event.getSource().equals(shiftDown)){
			String selectedElement = tree.getSelectionPath().getLastPathComponent().toString();
			int index1 = getAssociatedList().indexOf(selectedElement);
			int index2 = index1 + 1;
			Collections.swap(getAssociatedList(), index1, index2);
			keys.write(identifier);
			updateList();
			
			TreePath pathToSelectedElement = find((DefaultMutableTreeNode)tree.getModel().getRoot(), selectedElement);
			tree.setSelectionPath(pathToSelectedElement);
			tree.scrollPathToVisible(pathToSelectedElement);
			tree.requestFocus();
			updateButtons();
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
		updateButtons();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
