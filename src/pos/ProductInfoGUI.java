package pos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

@SuppressWarnings("serial")
public class ProductInfoGUI extends JFrame implements ActionListener, Confirmable{
	
	private Item item;
	private Keys key;
	private OutputWindow parentWindow;
	private InventoryManager inventory;
	private JPanel content;
	private JTextField UPC, Name, Client, Date, Price, Cost, Quantity; 
	private JComboBox<Object> Brand, Color, Size, Type, Gender;
	private JTextArea Notes;
	private JButton Submit, Delete;
	
	boolean update = false;
	boolean confirmed = false;
	
	public ProductInfoGUI(InventoryManager im, OutputWindow g, Item i, Keys _key, boolean isNew){
		//super(i.name);
		if (i.SKU > -1){
			update = true;
		}
		inventory = im;
		parentWindow = g;
		item = i;
		key = _key;
		writeToOutput("\n\n::" + item.toStringUpdate());
		
		content = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 5;
		c.insets = new Insets(5,5,0,5);
		
		c.gridx = 0;
		c.gridy = 0;
		content.add(new JLabel("Product Name"), c);
		
		Name = new JTextField();
		Name.setText(item.name);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 3;
		content.add(Name, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		content.add(new JLabel("UPC"), c);
		
		UPC = new JTextField(10);
		UPC.setText(item.UPC);
		c.gridx = 1;
		c.gridy = 1;
		content.add(UPC, c);
	
		c.gridx = 2;
		c.gridy = 1;
		content.add(new JLabel("Date"), c);

		Date = new JTextField(10);
		Date.setText(item.date);
		c.gridx = 3;
		c.gridy = 1;
		content.add(Date, c);

		c.gridx = 0;
		c.gridy = 2;
		content.add(new JLabel("Price"), c);

		Price = new JTextField();
		Price.setText(item.price);
		c.gridx = 1;
		c.gridy = 2;
		content.add(Price, c);

		c.gridx = 2;
		c.gridy = 2;
		content.add(new JLabel("Cost"), c);
		
		Cost = new JTextField();
		Cost.setText(item.cost);
		c.gridx = 3;
		c.gridy = 2;
		content.add(Cost, c);
		
		c.gridx = 0;
		c.gridy = 3;
		content.add(new JLabel("Brand"), c);
		
		Brand = new JComboBox<Object>(key.brands.toArray());
		if(item.brand != null)
			Brand.setSelectedItem(item.brand);
		c.gridx = 1;
		c.gridy = 3;
		content.add(Brand, c);
		
		c.gridx = 2;
		c.gridy = 3;
		content.add(new JLabel("Color"), c);
		
		Color = new JComboBox<Object>(key.colors.toArray());
		if(item.color != null)
			Color.setSelectedItem(item.color);
		c.gridx = 3;
		c.gridy = 3;
		content.add(Color, c);
		
		c.gridx = 0;
		c.gridy = 4;
		content.add(new JLabel("Client"), c);
		
		Client = new JTextField();
		Client.setText(item.client);
		c.gridx = 1;
		c.gridy = 4;
		content.add(Client, c);
		
		c.gridx = 2;
		c.gridy = 4;
		content.add(new JLabel("Gender"), c);
		
		Gender = new JComboBox<Object>(key.genders.toArray());
		if(item.gender != null)
			Gender.setSelectedItem(item.gender);
		c.gridx = 3;
		c.gridy = 4;
		content.add(Gender, c);
		
		c.gridx = 0;
		c.gridy = 5;
		content.add(new JLabel("Type"), c);
		
		Type = new JComboBox<Object>(key.types.toArray());
		if(item.type != null)
			Type.setSelectedItem(item.type);
		c.gridx = 1;
		c.gridy = 5;
		content.add(Type, c);
		
		c.gridx = 2;
		c.gridy = 5;
		content.add(new JLabel("Size"), c);
		
		Size = new JComboBox<Object>(key.sizes.toArray());
		if(item.size != null)
			Size.setSelectedItem(item.size);
		c.gridx = 3;
		c.gridy = 5;
		content.add(Size, c);

		c.gridx = 0;
		c.gridy = 6;
		content.add(new JLabel("Quantity"), c);
		
		Quantity = new JTextField();
		Quantity.setText("" + item.quantity);
		c.gridx = 1;
		c.gridy = 6;
		content.add(Quantity, c);
		
		c.gridx = 0;
		c.gridy = 7;
		content.add(new JLabel("Notes"), c);

		Notes = new JTextArea();
		Notes.setLineWrap(true);
		Notes.setText(item.notes);
		c.gridx = 0;
		c.gridy = 8;
		c.ipady = 100;
		c.gridwidth = 4;
		content.add(Notes, c);

		Submit = new JButton("UPDATE PRODUCT INFO");
		Submit.setActionCommand("submit");
		Submit.addActionListener(this);
		if (isNew){
			Submit.setText("CREATE");
		}
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		c.ipady = 0;
		c.insets = new Insets(5,5,5,5);
		content.add(Submit, c);

		if (!isNew){
			Delete = new JButton("DELETE");
			Delete.setActionCommand("delete");
			Delete.addActionListener(this);
			c.gridx = 3;
			c.gridy = 9;
			content.add(Delete, c);
		}
		
		setTitle("Add...");
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("submit")){
			Item i = new Item(0, UPC.getText(), Name.getText(), Brand.getSelectedItem().toString(), Color.getSelectedItem().toString(), Size.getSelectedItem().toString(), Type.getSelectedItem().toString(), Gender.getSelectedItem().toString(), Client.getText(), Date.getText(), Notes.getText(), Price.getText(), Cost.getText(), Integer.parseInt(Quantity.getText()));
			if (update){
				writeToOutput("\n\n:::::" + inventory.search("UPC='" + i.UPC + "'").get(0).SKU);
				i.SKU = inventory.search("UPC='" + i.UPC + "'").get(0).SKU;
				if (UPC.getText().length() * Name.getText().length() > 0){
					String r = inventory.updateItem(i);
					writeToOutput(r);
					writeToOutput("\n\n" + i.toStringFormatted());
					writeToOutput("\n" + i.toStringUpdate());
					this.setVisible(false);
				}
			}
			else{
				i.SKU = inventory.getMaxSKU() + 1;
				if (UPC.getText().length() * Name.getText().length() > 0){
					if (inventory.search("UPC='" + i.UPC + "'").size() > 0 && !confirmed){
						Object[] options = {"CREATE NEW", "SEE CONFLICTS"};
						int n = JOptionPane.showOptionDialog(new JFrame(), "Duplicate UPC/product.\nPress \"CREATE NEW\" to create new entry.\n Press \"SEE CONFLICTS\" to see a list of\nconflicting products", "Notice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
						if(n == 0){
							actionConfirmed("create_new");
						} else {
							actionConfirmed("see_conflicts");
						}
					}
					else{
						String r = inventory.insertItem(i);
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
		if (s.equals("delete")){
			int n = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you wish to delete this product record?\nThis action is PERMANENT.", "Delete...", JOptionPane.YES_NO_OPTION);
			if(n == 0){
				actionConfirmed("delete");
			}
			
		}
		
	}
	
	public void actionConfirmed(String action){
		if (action.equals("delete")){
			writeToOutput(inventory.removeItem(item));
			this.setVisible(false);
		}
		if (action.equals("create_new")){
			confirmed = true;
			actionPerformed(new ActionEvent(Submit, 1, "submit"));
		}
		if (action.equals("see_conflicts")){
			writeToOutput("conflict");
			new SearchGUI(inventory, parentWindow, "UPC='" + UPC.getText() + "'", key);

		}
	}
	
	public void writeToOutput(String s){
		parentWindow.writeToOutput(s);
	}

}
