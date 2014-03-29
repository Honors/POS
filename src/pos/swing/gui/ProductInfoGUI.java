package pos.swing.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pos.filter.PDFFilter;
import pos.item.Item;
import pos.item.ReturnItem;
import pos.label.PDFLabelGenerator;
import pos.label.UPCGenerator;
import pos.lib.Reference;
import pos.log.ChangeLogger;
import pos.log.LogInfoGenerator;
import pos.log.TimeStamp;
import pos.core.ServerManager;
import pos.item.InventoryItem;
import pos.swing.JFramePOS;
import pos.core.Keys;
import pos.core.OutputWindow;
import pos.core.UpdateableContent;
import pos.swing.SearchResult;

public class ProductInfoGUI extends JFramePOS implements ActionListener, UpdateableContent{
	
	private static final long serialVersionUID = 2663648763612880306L;
	
	private Item item;
	private SearchResult source;
	private JPanel content;
	private JLabel labelUpc, labelName, labelClient, labelDate, labelPrice, labelCost;
	private JLabel labelQuantity, labelBrand, labelColor, labelSize, labelType, labelGender;
	private JLabel labelNotes, labelReturnStatus;
	private JComponent upc, name, client, date, price, cost, quantity; 
	private JComponent brand, color, size, type, gender;
	private JComponent returnStatus;
	private JTextArea notes;
	private JButton Submit, generateLabels;
	private JScrollPane notesPane;
	
	private boolean isNew, isEditable, isReturn;
	private boolean update = false;
	private boolean confirmed = false;
	private int status;
	
	@SuppressWarnings("unchecked")
	public ProductInfoGUI(ServerManager im, OutputWindow g, SearchResult s, Item i, Keys _key, int status){
		//TODO allow certain changes for different types of returns (Pending, allow status change.  To Vender/To Inventory, NO CHANGES)
		super(im,g,_key);
		if (i.SKU > -1){
			update = true;
		}
		item = i;
		source = s;
		
		this.status = status;
		if(status == Reference.NEW_PRODUCT){
			isNew = true;
			isEditable = true;
			isReturn = false;
			setTitle("New...");
		} else if(status == Reference.EDIT_PRODUCT){
			isNew = false;
			isEditable = true;
			isReturn = false;
			setTitle("Edit...");
		} else if(status == Reference.VIEW_PRODUCT){
			isNew = false;
			isEditable = false;
			isReturn = false;
			setTitle("View...");
		} else if(status == Reference.RETURN_PRODUCT){
			isNew = false;
			isEditable = false;
			isReturn = true;
			setTitle("Return...");
		}
		
		GridBagConstraints lLabelCollumn = new GridBagConstraints();
		lLabelCollumn.anchor = GridBagConstraints.LINE_START;
		lLabelCollumn.fill = isEditable ? GridBagConstraints.HORIZONTAL : GridBagConstraints.BOTH;
		lLabelCollumn.ipady = 5;
		lLabelCollumn.insets = new Insets(5,20,0,5);
		lLabelCollumn.gridx = 0;
		lLabelCollumn.weightx = 0;
		
		GridBagConstraints lInfoCollumn = new GridBagConstraints();
		lInfoCollumn.anchor = GridBagConstraints.LINE_START;
		lInfoCollumn.fill = isEditable ? GridBagConstraints.HORIZONTAL : GridBagConstraints.BOTH;
		lInfoCollumn.ipady = 5;
		lInfoCollumn.ipadx = 5;
		lInfoCollumn.insets = new Insets(5,5,0,20);
		lInfoCollumn.gridx = 1;
		lInfoCollumn.weightx = .5;
		
		GridBagConstraints rLabelCollumn = new GridBagConstraints();
		rLabelCollumn.anchor = GridBagConstraints.LINE_START;
		rLabelCollumn.fill = isEditable ? GridBagConstraints.HORIZONTAL : GridBagConstraints.BOTH;
		rLabelCollumn.ipady = 5;
		rLabelCollumn.insets = new Insets(5,20,0,5);
		rLabelCollumn.gridx = 2;
		rLabelCollumn.weightx = 0;
		
		GridBagConstraints rInfoCollumn = new GridBagConstraints();
		rInfoCollumn.anchor = GridBagConstraints.LINE_START;
		rInfoCollumn.fill = isEditable ? GridBagConstraints.HORIZONTAL : GridBagConstraints.BOTH;
		rInfoCollumn.ipady = 5;
		rInfoCollumn.ipadx = 5;
		rInfoCollumn.insets = new Insets(5,5,0,20);
		rInfoCollumn.gridx = 3;
		rInfoCollumn.weightx = .5;
		
		
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
		
		upc = isNew ? new JLabel(server.generateInventoryUPC()) : new JLabel(item.UPC);
		upc.setBorder(new JTextField().getBorder());
		upc.setOpaque(true);
		upc.setBackground(new JTextField().getBackground());
		lInfoCollumn.gridy = 1;
		content.add(upc, lInfoCollumn);
	
		labelDate = new JLabel("Date:");
		labelDate.setHorizontalAlignment(JLabel.RIGHT);
		rLabelCollumn.gridy = 1;
		content.add(labelDate, rLabelCollumn);

		date = (isNew) ? new JTextField(item.date, 10) : new JLabel(item.date);
		if(isNew){
			((JTextField)date).setText(TimeStamp.simpleDate());
		}
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
			((JComboBox<Object>)brand).addActionListener(this);
			((JComboBox<Object>)brand).setActionCommand("updateName");
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
			((JComboBox<Object>)color).addActionListener(this);
			((JComboBox<Object>)color).setActionCommand("updateName");
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
		
		client = isEditable ? new JComboBox<Object>(keys.clients.toArray()) : new JLabel(item.client);
		if(isEditable){
			if(item.client != null)
				((JComboBox<Object>)client).setSelectedItem(item.client);
		} else {
			client.setBorder(new JTextField().getBorder());
			client.setOpaque(true);
			client.setBackground(new JTextField().getBackground());
		}
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
			((JComboBox<Object>)gender).addActionListener(this);
			((JComboBox<Object>)gender).setActionCommand("updateName");
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
			((JComboBox<Object>)type).addActionListener(this);
			((JComboBox<Object>)type).setActionCommand("updateName");
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
			((JComboBox<Object>)size).addActionListener(this);
			((JComboBox<Object>)size).setActionCommand("updateName");
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
		
		quantity = (isNew) ? new JTextField(item.quantity) : new JLabel("" + item.quantity);
		if(isNew)
			((JTextField)quantity).setText("1");
		quantity.setBorder(new JTextField().getBorder());
		quantity.setOpaque(true);
		quantity.setBackground(new JTextField().getBackground());
		lInfoCollumn.gridy = 6;
		content.add(quantity, lInfoCollumn);
		
		labelNotes = new JLabel("Notes:");
		lLabelCollumn.gridy = 7;
		content.add(labelNotes, lLabelCollumn);
		
		notes = new JTextArea();
		notes.setLineWrap(true);
		notes.setText(item.notes);
		notes.setEditable(isEditable || isReturn);
		notes.setBorder(new JTextField().getBorder());
		notes.setOpaque(true);
		notes.setBackground(new JTextField().getBackground());
		notes.setBorder(new EmptyBorder(5,5,5,5));
		lLabelCollumn.gridy = 8;
		lLabelCollumn.ipady = 100;
		lLabelCollumn.gridwidth = 4;
		lLabelCollumn.insets = new Insets(5,20,10,20);	
		notesPane = new JScrollPane(notes);
		content.add(notesPane, lLabelCollumn);


		if(isEditable || isReturn){
			Submit = new JButton("UPDATE");
			Submit.addActionListener(this);
			if (isNew){
				Submit.setText("CREATE");
			}
			lLabelCollumn.gridy = 9;
			lLabelCollumn.gridwidth = 1;
			lLabelCollumn.ipady = 5;
			lLabelCollumn.insets = new Insets(0,20,10,10);
			content.add(Submit, lLabelCollumn);
	
			if(isReturn){
				labelReturnStatus = new JLabel("Status:");
				labelReturnStatus.setHorizontalAlignment(JLabel.RIGHT);
				rLabelCollumn.gridy = 6;
				content.add(labelReturnStatus, rLabelCollumn);
				

				if(((ReturnItem)item).status.equals(Reference.STATUS_PENDING)){
					returnStatus = new JComboBox<Object>(Reference.STATUSES.toArray());
					((JComboBox<Object>)returnStatus).setSelectedItem(((ReturnItem)item).status);
					rInfoCollumn.gridy = 6;
					content.add(returnStatus, rInfoCollumn);
				} else {
					returnStatus = new JLabel(((ReturnItem)item).status);
					returnStatus.setBorder(new JTextField().getBorder());
					returnStatus.setOpaque(true);
					returnStatus.setBackground(new JTextField().getBackground());
					rInfoCollumn.gridy = 6;
					content.add(returnStatus, rInfoCollumn);
				}
			}
			
			if(isEditable){
				generateLabels = new JButton("GENERATE LABELS");
				generateLabels.addActionListener(this);
				lInfoCollumn.gridy = 9;
				lInfoCollumn.gridwidth = 2;
				lInfoCollumn.anchor = GridBagConstraints.CENTER;
				lInfoCollumn.insets = new Insets(0,20,10,20);
				content.add(generateLabels, lInfoCollumn);
			}
		}
		
		for(Component n : content.getComponents()){
			n.setFont(n.getFont().deriveFont(14.0f));
		}
		
		setContentPane(content);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public String arrayToSentence(ArrayList<String> array){
		String sentence = new String();
		boolean isFirst = true;
		for(int i = 0; i < array.size(); i++){
			if(isFirst){
				sentence += array.get(i);
				isFirst = false;
			} else {
				sentence += " " + array.get(i);
			}
		}
		return sentence;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(Submit)){
			if(status == Reference.EDIT_PRODUCT){
				if (update){
					InventoryItem i = new InventoryItem(item.SKU, item.UPC, ((JTextField)name).getText(), ((JComboBox<Object>)brand).getSelectedItem().toString(), ((JComboBox<Object>)color).getSelectedItem().toString(), ((JComboBox<Object>)size).getSelectedItem().toString(), ((JComboBox<Object>)type).getSelectedItem().toString(), ((JComboBox<Object>)gender).getSelectedItem().toString(), ((JComboBox<Object>)client).getSelectedItem().toString(), item.date, notes.getText(), ((JTextField)price).getText(), ((JTextField)cost).getText(), item.quantity);
					if (item.UPC.length() * ((JTextField)name).getText().length() > 0){
						server.updateInventoryItem(i);
						writeToOutput(LogInfoGenerator.generateInventoryEditItemStatement(server.getUsername(), (InventoryItem)item, i));
						source.updateItem(i);
						this.setVisible(false);
					}
				}
			}
			if(status == Reference.NEW_PRODUCT){
				//Errors
				if(((JTextField)quantity).getText().isEmpty()){
					JOptionPane.showMessageDialog(new JFrame(),"Quantity must have a input", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try{
					if(Integer.parseInt(((JTextField)quantity).getText()) < 0){
	                	JOptionPane.showMessageDialog(new JFrame(),"Quantity must be greater than or equal to zero", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e){
					JOptionPane.showMessageDialog(new JFrame(),"Quantity must be a numeric input", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				InventoryItem i = new InventoryItem(0, ((JLabel)upc).getText(), ((JTextField)name).getText(), ((JComboBox<Object>)brand).getSelectedItem().toString(), ((JComboBox<Object>)color).getSelectedItem().toString(), ((JComboBox<Object>)size).getSelectedItem().toString(), ((JComboBox<Object>)type).getSelectedItem().toString(), ((JComboBox<Object>)gender).getSelectedItem().toString(), ((JComboBox<Object>)client).getSelectedItem().toString(), ((JTextField)date).getText(), notes.getText(), ((JTextField)price).getText(), ((JTextField)cost).getText(), Integer.parseInt(((JTextField)quantity).getText()));
				i.SKU = server.getMaxInventorySKU() + 1;
				if (((JLabel)upc).getText().length() * ((JTextField)name).getText().length() > 0){
					if (server.searchInventory("UPC='" + i.UPC + "'").size() > 0 && !confirmed){
						Object[] options = {"CREATE NEW", "SEE CONFLICTS"};
						int n = JOptionPane.showOptionDialog(new JFrame(), "Duplicate UPC/product.\nPress \"CREATE NEW\" to create new entry.\n Press \"SEE CONFLICTS\" to see a list of\nconflicting products", "Notice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
						if(n == 0){
							confirmed = true;
							actionPerformed(new ActionEvent(Submit, 1, "submit"));
						} else {
							new SearchGUI(server, parentWindow, "UPC='" + ((JLabel)upc).getText() + "'", keys);
						}
					}
					else{
						String r = server.insertInventoryItem(i);
						if (r.contains("SUCCESS")){
							writeToOutput(LogInfoGenerator.generateInventoryNewItemStatement(server.getUsername(), i));
							ChangeLogger.write(Reference.INVENTORY_IDENTIFIER, TimeStamp.sanitizedDateandTime(), i.SKU, Reference.STATUS_NEW_ITEM, i.quantity);
							this.setVisible(false);
						}
					}
				}
			}
			if(status == Reference.RETURN_PRODUCT){
				if(((ReturnItem)item).status.equals(Reference.STATUS_PENDING)){
					ReturnItem i = new ReturnItem(item.SKU, item.UPC, item.name, item.brand, item.color, item.size, item.type, item.gender, item.client, item.date, notes.getText(), item.price, item.cost, item.quantity, ((JComboBox<Object>)returnStatus).getSelectedItem().toString());
					if(!i.status.equals(((ReturnItem)item).status)){
						writeToOutput(LogInfoGenerator.generateReturnEditItemStatement(server.getUsername(), i.UPC, i.name, i.quantity, ((ReturnItem)item).status, i.status));
						ChangeLogger.write(Reference.RETURN_IDENTIFIER, TimeStamp.sanitizedDateandTime(), item.SKU, Reference.STATUS_PENDING, -i.quantity);
						ChangeLogger.write(Reference.RETURN_IDENTIFIER, TimeStamp.sanitizedDateandTime(), item.SKU, i.status, i.quantity);
					}
					if(i.status.equals(Reference.STATUS_TO_INVENTORY)){
						ArrayList<InventoryItem> returnedTo = server.searchInventory("UPC='" + i.UPC + "'");
						int oldVal = returnedTo.get(0).quantity;
						returnedTo.get(0).quantity += item.quantity;
						writeToOutput(LogInfoGenerator.generateTransactionIncomingItemStatement(server.getUsername(), returnedTo.get(0).UPC, returnedTo.get(0).name, oldVal, returnedTo.get(0).quantity));
						ChangeLogger.write(Reference.INVENTORY_IDENTIFIER, TimeStamp.sanitizedDateandTime(), returnedTo.get(0).SKU, Reference.STATUS_INCOMING, item.quantity);
						server.updateInventoryItem(returnedTo.get(0));
					}

					writeToOutput("\n");
					
					server.updateReturnItem(i);					
					source.updateItem(i);
					this.setVisible(false);
				} else {
					ReturnItem i = new ReturnItem(item.SKU, item.UPC, item.name, item.brand, item.color, item.size, item.type, item.gender, item.client, item.date, notes.getText(), item.price, item.cost, item.quantity, ((ReturnItem)item).status);
				
					server.updateReturnItem(i);
					source.updateItem(i);
					this.setVisible(false);
				}
			}
		}
		
		if (event.getSource().equals(generateLabels)){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new PDFFilter());
			fileChooser.setSelectedFile(new File(((JTextField)name).getText() + " Labels.pdf"));
			int returnVal = fileChooser.showSaveDialog(new JFrame());
			if(returnVal == JFileChooser.APPROVE_OPTION){
				String filePath = fileChooser.getSelectedFile().getAbsolutePath();
				if(!filePath.endsWith(".pdf"))
					filePath += ".pdf";
				PDFLabelGenerator labelGen = new PDFLabelGenerator(0.5f, 0.9f, 6, 3, 1.5f, 2.0f, 1.7f, 2.35f);
				try {
					labelGen.generateSingleItemLabelPage(fileChooser.getSelectedFile().getAbsolutePath(), ((JTextField)name).getText(), UPCGenerator.generateBarcode(item.UPC), item.UPC);
					writeToOutput(LogInfoGenerator.generateLabelStatement(server.getUsername(), ((JTextField)name).getText(), item.UPC, filePath));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		if (event.getActionCommand().equals("updateName")){
			ArrayList<String> generatedName = new ArrayList<String>();
			if(!((JComboBox<Object>)color).getSelectedItem().toString().equalsIgnoreCase("No Color")){
				generatedName.add(((JComboBox<Object>)color).getSelectedItem().toString());
			}
			if(!((JComboBox<Object>)brand).getSelectedItem().toString().equalsIgnoreCase("No Brand")){
				generatedName.add(((JComboBox<Object>)brand).getSelectedItem().toString());
			}
			if(!((JComboBox<Object>)type).getSelectedItem().toString().equalsIgnoreCase("No Type")){
				generatedName.add(((JComboBox<Object>)type).getSelectedItem().toString());
			}
			if(!((JComboBox<Object>)size).getSelectedItem().toString().equalsIgnoreCase("No Size")){
				generatedName.add("(" + ((JComboBox<Object>)size).getSelectedItem().toString() + ")");
			}
			if(!((JComboBox<Object>)gender).getSelectedItem().toString().equalsIgnoreCase("No Gender")){
				generatedName.add(((JComboBox<Object>)gender).getSelectedItem().toString());
			}
			((JTextField)name).setText(arrayToSentence(generatedName));
		}
	}
	
	public void writeToOutput(String s){
		parentWindow.writeToOutput(s);
	}

	@Override
	public void update(String updateIdentifier, String info) {
		//TODO: Complex Update Processing
	}

}
