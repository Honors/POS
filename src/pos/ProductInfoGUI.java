package pos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ProductInfoGUI extends JFramePOS implements ActionListener, Confirmable{
	
	private Item item;
	private JPanel content;
	private JLabel labelUpc, labelName, labelClient, labelDate, labelPrice, labelCost;
	private JLabel labelQuantity, labelBrand, labelColor, labelSize, labelType, labelGender;
	private JLabel labelNotes;
	private JComponent upc, name, client, date, price, cost, quantity; 
	private JComponent brand, color, size, type, gender;
	private JTextArea notes;
	private JButton Submit, Delete;
	
	private boolean update = false;
	private boolean confirmed = false;
	
	public ProductInfoGUI(InventoryManager im, OutputWindow g, Item i, Keys _key, boolean isNew, boolean isEditable){
		super(im,g,_key);
		if (i.SKU > -1){
			update = true;
		}
		item = i;
		writeToOutput("\n\n::" + item.toStringUpdate());
		
		if(isNew)
			isEditable = true;
		
		
		GridBagConstraints lLabelCollumn = new GridBagConstraints();
		lLabelCollumn.anchor = GridBagConstraints.LINE_END;
		lLabelCollumn.fill = isEditable ? GridBagConstraints.HORIZONTAL : GridBagConstraints.BOTH;
		lLabelCollumn.ipady = 5;
		lLabelCollumn.insets = new Insets(5,5,0,5);
		lLabelCollumn.gridx = 0;
		lLabelCollumn.weightx = 0;
		
		GridBagConstraints lInfoCollumn = new GridBagConstraints();
		lInfoCollumn.anchor = GridBagConstraints.LINE_END;
		lInfoCollumn.fill = isEditable ? GridBagConstraints.HORIZONTAL : GridBagConstraints.BOTH;
		lInfoCollumn.ipady = 5;
		lInfoCollumn.ipadx = 10;
		lInfoCollumn.insets = new Insets(5,5,0,20);
		lInfoCollumn.gridx = 1;
		lInfoCollumn.weightx = 0;
		
		GridBagConstraints rLabelCollumn = new GridBagConstraints();
		rLabelCollumn.anchor = GridBagConstraints.LINE_END;
		rLabelCollumn.fill = isEditable ? GridBagConstraints.HORIZONTAL : GridBagConstraints.BOTH;
		rLabelCollumn.ipady = 5;
		rLabelCollumn.insets = new Insets(5,20,0,5);
		rLabelCollumn.gridx = 2;
		rLabelCollumn.weightx = 0;
		
		GridBagConstraints rInfoCollumn = new GridBagConstraints();
		rInfoCollumn.anchor = GridBagConstraints.LINE_END;
		rInfoCollumn.fill = isEditable ? GridBagConstraints.HORIZONTAL : GridBagConstraints.BOTH;
		rInfoCollumn.ipady = 5;
		lInfoCollumn.ipadx = 10;
		rInfoCollumn.insets = new Insets(5,5,0,20);
		rInfoCollumn.gridx = 3;
		rInfoCollumn.weightx = 0.5;
		
		
		content = new JPanel(new GridBagLayout());
		
		labelName = new JLabel("Product Name:");
		labelName.setHorizontalAlignment(JLabel.RIGHT);
		lLabelCollumn.gridy = 0;
		content.add(labelName, lLabelCollumn);
		
		name = isEditable ? new JTextField(item.name) : new JLabel(item.name);
		name.setBorder(new JTextField().getBorder());
		name.setOpaque(true);
		name.setBackground(new JTextField().getBackground());
		lInfoCollumn.gridy = 0;
		lInfoCollumn.gridwidth = 3;
		content.add(name, lInfoCollumn);
		lInfoCollumn.gridwidth = 1;

		labelUpc = new JLabel("UPC:");
		labelUpc.setHorizontalAlignment(JLabel.RIGHT);
		lLabelCollumn.gridy = 1;
		content.add(labelUpc, lLabelCollumn);
		
		upc = isEditable ? new JTextField(item.UPC, 10) : new JLabel(item.UPC);
		upc.setBorder(new JTextField().getBorder());
		upc.setOpaque(true);
		upc.setBackground(new JTextField().getBackground());
		lInfoCollumn.gridy = 1;
		content.add(upc, lInfoCollumn);
	
		labelDate = new JLabel("Date:");
		labelDate.setHorizontalAlignment(JLabel.RIGHT);
		rLabelCollumn.gridy = 1;
		content.add(labelDate, rLabelCollumn);

		date = isEditable ? new JTextField(item.date, 10) : new JLabel(item.date);
		date.setBorder(new JTextField().getBorder());
		date.setOpaque(true);
		date.setBackground(new JTextField().getBackground());
		rInfoCollumn.gridy = 1;
		content.add(date, rInfoCollumn);

		labelPrice = new JLabel("Price:");
		labelPrice.setHorizontalAlignment(JLabel.RIGHT);
		lLabelCollumn.gridy = 2;
		content.add(labelPrice, lLabelCollumn);

		price = isEditable ? new JTextField(item.price) : new JLabel(item.price);
		price.setBorder(new JTextField().getBorder());
		price.setOpaque(true);
		price.setBackground(new JTextField().getBackground());	
		lInfoCollumn.gridy = 2;
		content.add(price, lInfoCollumn);

		labelCost = new JLabel("Cost:");
		labelCost.setHorizontalAlignment(JLabel.RIGHT);
		rLabelCollumn.gridy = 2;
		content.add(labelCost, rLabelCollumn);
		
		cost = isEditable ? new JTextField(item.cost) : new JLabel(item.cost);
		cost.setBorder(new JTextField().getBorder());
		cost.setOpaque(true);
		cost.setBackground(new JTextField().getBackground());
		rInfoCollumn.gridy = 2;
		content.add(cost, rInfoCollumn);
		
		labelBrand = new JLabel("Brand:");
		labelBrand.setHorizontalAlignment(JLabel.RIGHT);
		lLabelCollumn.gridy = 3;
		content.add(labelBrand, lLabelCollumn);
		
		brand = isEditable ? new JComboBox<Object>(keys.brands.toArray()) : new JLabel(item.brand);
		if(isEditable){
			if(item.brand != null)
				((JComboBox<Object>)brand).setSelectedItem(item.brand);
		} else {
			brand.setBorder(new JTextField().getBorder());
			brand.setOpaque(true);
			brand.setBackground(new JTextField().getBackground());
		}
		lInfoCollumn.gridy = 3;
		content.add(brand, lInfoCollumn);
		
		labelColor = new JLabel("Color:");
		labelColor.setHorizontalAlignment(JLabel.RIGHT);
		rLabelCollumn.gridy = 3;
		content.add(labelColor, rLabelCollumn);
		
		color = isEditable ? new JComboBox<Object>(keys.colors.toArray()) : new JLabel(item.color);
		if(isEditable){
			if(item.color != null)
				((JComboBox<Object>)color).setSelectedItem(item.color);
		} else {
			color.setBorder(new JTextField().getBorder());
			color.setOpaque(true);
			color.setBackground(new JTextField().getBackground());
		}
		rInfoCollumn.gridy = 3;
		content.add(color, rInfoCollumn);
		
		labelClient = new JLabel("Client:");
		labelClient.setHorizontalAlignment(JLabel.RIGHT);
		lLabelCollumn.gridy = 4;
		content.add(labelClient, lLabelCollumn);
		
		client = isEditable ? new JTextField(item.client) : new JLabel(item.client);
		client.setBorder(new JTextField().getBorder());
		client.setOpaque(true);
		client.setBackground(new JTextField().getBackground());
		lInfoCollumn.gridy = 4;
		content.add(client, lInfoCollumn);
		
		labelGender = new JLabel("Gender:");
		labelGender.setHorizontalAlignment(JLabel.RIGHT);
		rLabelCollumn.gridy = 4;
		content.add(labelGender, rLabelCollumn);
		
		gender = isEditable ? new JComboBox<Object>(keys.genders.toArray()) : new JLabel(item.gender);
		if(isEditable){
			if(item.gender != null)
				((JComboBox<Object>)gender).setSelectedItem(item.gender);
		} else {
			gender.setBorder(new JTextField().getBorder());
			gender.setOpaque(true);
			gender.setBackground(new JTextField().getBackground());
		}
		rInfoCollumn.gridy = 4;
		content.add(gender, rInfoCollumn);
		
		labelType = new JLabel("Type:");
		labelType.setHorizontalAlignment(JLabel.RIGHT);
		lLabelCollumn.gridy = 5;
		content.add(labelType, lLabelCollumn);
		
		type = isEditable ? new JComboBox<Object>(keys.types.toArray()) : new JLabel(item.type);
		if(isEditable){
			if(item.type != null)
				((JComboBox<Object>)type).setSelectedItem(item.type);
		} else {
			type.setBorder(new JTextField().getBorder());
			type.setOpaque(true);
			type.setBackground(new JTextField().getBackground());
		}
		lInfoCollumn.gridy = 5;
		content.add(type, lInfoCollumn);
		
		labelSize = new JLabel("Size:");
		labelSize.setHorizontalAlignment(JLabel.RIGHT);
		rLabelCollumn.gridy = 5;
		content.add(labelSize, rLabelCollumn);
		
		size = isEditable ? new JComboBox<Object>(keys.sizes.toArray()) : new JLabel(item.size);
		if(isEditable){
			if(item.size != null)
				((JComboBox<Object>)size).setSelectedItem(item.size);
		} else {
			size.setBorder(new JTextField().getBorder());
			size.setOpaque(true);
			size.setBackground(new JTextField().getBackground());
		}
		rInfoCollumn.gridy = 5;
		content.add(size, rInfoCollumn);

		labelQuantity = new JLabel("Quantity:");
		labelQuantity.setHorizontalAlignment(JLabel.RIGHT);
		lLabelCollumn.gridy = 6;
		content.add(labelQuantity, lLabelCollumn);
		
		quantity = isEditable ? new JTextField(item.quantity) : new JLabel("" + item.quantity);
		quantity.setBorder(new JTextField().getBorder());
		quantity.setOpaque(true);
		quantity.setBackground(new JTextField().getBackground());
		lInfoCollumn.gridy = 6;
		content.add(quantity, lInfoCollumn);
		
		lLabelCollumn.gridy = 7;
		content.add(new JLabel("Notes:"), lLabelCollumn);

		notes = new JTextArea();
		notes.setLineWrap(true);
		notes.setText(item.notes);
		notes.setEditable(isEditable);
		notes.setBorder(new JTextField().getBorder());
		notes.setOpaque(true);
		notes.setBackground(new JTextField().getBackground());
		lLabelCollumn.gridy = 8;
		lLabelCollumn.ipady = 100;
		lLabelCollumn.gridwidth = 4;
		content.add(notes, lLabelCollumn);

		if(isEditable){
			Submit = new JButton("UPDATE");
			Submit.addActionListener(this);
			
			if (isNew){
				Submit.setText("CREATE");
			}
			
			lLabelCollumn.gridy = 9;
			lLabelCollumn.gridwidth = 1;
			lLabelCollumn.ipady = 0;

			content.add(Submit, lLabelCollumn);
	
			if (!isNew){
				Delete = new JButton("DELETE");
				Delete.addActionListener(this);
				rInfoCollumn.gridx = 3;
				rInfoCollumn.gridy = 9;
				content.add(Delete, rInfoCollumn);
			}
		}
		
		for(Component n : content.getComponents()){
			n.setFont(n.getFont().deriveFont(14.0f));
		}
		
		setTitle("Add...");
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(Submit)){
			Item i = new Item(0, ((JTextField)upc).getText(), ((JTextField)name).getText(), ((JComboBox<Object>)brand).getSelectedItem().toString(), ((JComboBox)color).getSelectedItem().toString(), ((JComboBox<Object>)size).getSelectedItem().toString(), ((JComboBox<Object>)type).getSelectedItem().toString(), ((JComboBox<Object>)gender).getSelectedItem().toString(), ((JTextField)client).getText(), ((JTextField)date).getText(), notes.getText(), ((JTextField)price).getText(), ((JTextField)cost).getText(), Integer.parseInt(((JTextField)quantity).getText()));
			if (update){ 
				writeToOutput("\n\n:::::" + inventory.searchInventory("UPC='" + i.UPC + "'").get(0).SKU);
				i.SKU = inventory.searchInventory("UPC='" + i.UPC + "'").get(0).SKU;
				if (((JTextField)upc).getText().length() * ((JTextField)name).getText().length() > 0){
					String r = inventory.updateInventoryItem(i);
					writeToOutput(r);
					writeToOutput("\n\n" + i.toStringFormatted());
					writeToOutput("\n" + i.toStringUpdate());
					this.setVisible(false);
				}
			}
			else{
				i.SKU = inventory.getMaxInventorySKU() + 1;
				if (((JTextField)upc).getText().length() * ((JTextField)name).getText().length() > 0){
					if (inventory.searchInventory("UPC='" + i.UPC + "'").size() > 0 && !confirmed){
						Object[] options = {"CREATE NEW", "SEE CONFLICTS"};
						int n = JOptionPane.showOptionDialog(new JFrame(), "Duplicate UPC/product.\nPress \"CREATE NEW\" to create new entry.\n Press \"SEE CONFLICTS\" to see a list of\nconflicting products", "Notice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
						if(n == 0){
							actionConfirmed("create_new");
						} else {
							actionConfirmed("see_conflicts");
						}
					}
					else{
						String r = inventory.insertInventoryItem(i);
						if (r.contains("SUCCESS")){
							writeToOutput(i.toStringFormatted());
							writeToOutput("\n" + i.toStringUpdate());
							this.setVisible(false);
						}
						else{
							writeToOutput(r);
						}
					}
				}
			}
			
		}
		if (event.getSource().equals(Delete)){
			int n = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you wish to delete this product record?\nThis action is PERMANENT.", "Delete...", JOptionPane.YES_NO_OPTION);
			if(n == 0){
				actionConfirmed("delete");
			}
			
		}
		
	}
	
	public void actionConfirmed(String action){
		if (action.equals("delete")){
			writeToOutput(inventory.removeInventoryItem(item));
			this.setVisible(false);
		}
		if (action.equals("create_new")){
			confirmed = true;
			actionPerformed(new ActionEvent(Submit, 1, "submit"));
		}
		if (action.equals("see_conflicts")){
			writeToOutput("conflict");
			new SearchGUI(inventory, parentWindow, "UPC='" + ((JTextField)upc).getText() + "'", keys);

		}
	}
	
	public void writeToOutput(String s){
		parentWindow.writeToOutput(s);
	}

}
