package pos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

@SuppressWarnings("serial")
public class ProductInfoGUI extends JFramePOS implements ActionListener, Confirmable{
	
	private Item item;
	private JPanel content;
	private JComponent UPC, Name, Client, Date, Price, Cost, Quantity; 
	private JComponent Brand, Color, Size, Type, Gender;
	private JTextArea Notes;
	private JButton Submit, Delete;
	
	boolean update = false;
	boolean confirmed = false;
	
	public ProductInfoGUI(InventoryManager im, OutputWindow g, Item i, Keys _key, boolean isNew, boolean isEditable){
		super(im,g,_key);
		if (i.SKU > -1){
			update = true;
		}
		item = i;
		writeToOutput("\n\n::" + item.toStringUpdate());
		
		if(isNew)
			isEditable = true;
		
		content = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 5;
		c.insets = new Insets(5,5,0,5);
		c.gridx = 0;
		c.gridy = 0;
		content.add(new JLabel("Product Name:"), c);
		
		Name = isEditable ? new JTextField(item.name) : new JLabel(item.name);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 3;
		content.add(Name, c);

		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		content.add(new JLabel("UPC:"), c);
		
		UPC = isEditable ? new JTextField(item.UPC, 10) : new JLabel(item.UPC);
		c.gridx = 1;
		c.gridy = 1;
		content.add(UPC, c);
	
		c.gridx = 2;
		c.gridy = 1;
		content.add(new JLabel("Date:"), c);

		Date = isEditable ? new JTextField(item.date, 10) : new JLabel(item.date);
		c.gridx = 3;
		c.gridy = 1;
		content.add(Date, c);

		c.gridx = 0;
		c.gridy = 2;
		content.add(new JLabel("Price:"), c);

		Price = isEditable ? new JTextField(item.price) : new JLabel(item.price);
		c.gridx = 1;
		c.gridy = 2;
		content.add(Price, c);

		c.gridx = 2;
		c.gridy = 2;
		content.add(new JLabel("Cost:"), c);
		
		Cost = isEditable ? new JTextField(item.cost) : new JLabel(item.cost);
		c.gridx = 3;
		c.gridy = 2;
		content.add(Cost, c);
		
		c.gridx = 0;
		c.gridy = 3;
		content.add(new JLabel("Brand:"), c);
		
		Brand = isEditable ? new JComboBox<Object>(keys.brands.toArray()) : new JLabel(item.brand);
		if(item.brand != null && isEditable)
			((JComboBox<Object>)Brand).setSelectedItem(item.brand);
		c.gridx = 1;
		c.gridy = 3;
		content.add(Brand, c);
		
		c.gridx = 2;
		c.gridy = 3;
		content.add(new JLabel("Color:"), c);
		
		Color = isEditable ? new JComboBox<Object>(keys.colors.toArray()) : new JLabel(item.color);
		if(item.color != null && isEditable)
			((JComboBox<Object>)Color).setSelectedItem(item.color);
		c.gridx = 3;
		c.gridy = 3;
		content.add(Color, c);
		
		c.gridx = 0;
		c.gridy = 4;
		content.add(new JLabel("Client:"), c);
		
		Client = isEditable ? new JTextField(item.client) : new JLabel(item.client);
		c.gridx = 1;
		c.gridy = 4;
		content.add(Client, c);
		
		c.gridx = 2;
		c.gridy = 4;
		content.add(new JLabel("Gender:"), c);
		
		Gender = isEditable ? new JComboBox<Object>(keys.genders.toArray()) : new JLabel(item.gender);
		if(item.gender != null && isEditable)
			((JComboBox<Object>)Gender).setSelectedItem(item.gender);
		c.gridx = 3;
		c.gridy = 4;
		content.add(Gender, c);
		
		c.gridx = 0;
		c.gridy = 5;
		content.add(new JLabel("Type:"), c);
		
		Type = isEditable ? new JComboBox<Object>(keys.types.toArray()) : new JLabel(item.type);
		if(item.type != null && isEditable)
			((JComboBox<Object>)Type).setSelectedItem(item.type);
		c.gridx = 1;
		c.gridy = 5;
		content.add(Type, c);
		
		c.gridx = 2;
		c.gridy = 5;
		content.add(new JLabel("Size:"), c);
		
		Size = isEditable ? new JComboBox<Object>(keys.sizes.toArray()) : new JLabel(item.size);
		if(item.size != null && isEditable)
			((JComboBox<Object>)Size).setSelectedItem(item.size);
		c.gridx = 3;
		c.gridy = 5;
		content.add(Size, c);

		c.gridx = 0;
		c.gridy = 6;
		content.add(new JLabel("Quantity:"), c);
		
		Quantity = isEditable ? new JTextField(item.quantity) : new JLabel("" + item.quantity);
		c.gridx = 1;
		c.gridy = 6;
		content.add(Quantity, c);
		
		c.gridx = 0;
		c.gridy = 7;
		content.add(new JLabel("Notes:"), c);

		Notes = new JTextArea();
		Notes.setLineWrap(true);
		Notes.setText(item.notes);
		Notes.setEditable(isEditable);
		c.gridx = 0;
		c.gridy = 8;
		c.ipady = 100;
		c.gridwidth = 4;
		c.insets = new Insets(5,5,5,5);
		content.add(Notes, c);

		if(isEditable){
			Submit = new JButton("UPDATE PRODUCT INFO");
			Submit.addActionListener(this);
			
			if (isNew){
				Submit.setText("CREATE");
			}
			
			c.gridx = 0;
			c.gridy = 9;
			c.gridwidth = 1;
			c.ipady = 0;

			content.add(Submit, c);
	
			if (!isNew){
				Delete = new JButton("DELETE");
				Delete.addActionListener(this);
				c.gridx = 3;
				c.gridy = 9;
				content.add(Delete, c);
			}
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
			Item i = new Item(0, ((JTextField)UPC).getText(), ((JTextField)Name).getText(), ((JComboBox<Object>)Brand).getSelectedItem().toString(), ((JComboBox)Color).getSelectedItem().toString(), ((JComboBox<Object>)Size).getSelectedItem().toString(), ((JComboBox<Object>)Type).getSelectedItem().toString(), ((JComboBox<Object>)Gender).getSelectedItem().toString(), ((JTextField)Client).getText(), ((JTextField)Date).getText(), Notes.getText(), ((JTextField)Price).getText(), ((JTextField)Cost).getText(), Integer.parseInt(((JTextField)Quantity).getText()));
			if (update){ 
				writeToOutput("\n\n:::::" + inventory.searchInventory("UPC='" + i.UPC + "'").get(0).SKU);
				i.SKU = inventory.searchInventory("UPC='" + i.UPC + "'").get(0).SKU;
				if (((JTextField)UPC).getText().length() * ((JTextField)Name).getText().length() > 0){
					String r = inventory.updateInventoryItem(i);
					writeToOutput(r);
					writeToOutput("\n\n" + i.toStringFormatted());
					writeToOutput("\n" + i.toStringUpdate());
					this.setVisible(false);
				}
			}
			else{
				i.SKU = inventory.getMaxInventorySKU() + 1;
				if (((JTextField)UPC).getText().length() * ((JTextField)Name).getText().length() > 0){
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
			new SearchGUI(inventory, parentWindow, "UPC='" + ((JTextField)UPC).getText() + "'", keys);

		}
	}
	
	public void writeToOutput(String s){
		parentWindow.writeToOutput(s);
	}

}
