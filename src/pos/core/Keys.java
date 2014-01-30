package pos.core;

import java.util.ArrayList;

import pos.lib.Reference;

public class Keys {
	
	public ArrayList<String> brands;
	public ArrayList<String> colors;
	public ArrayList<String> sizes;
	public ArrayList<String> types;
	public ArrayList<String> genders;
	public ArrayList<String> clients;
	
	public ServerManager server;
	
	public Keys(ServerManager server){
		brands = new ArrayList<String>();
		colors = new ArrayList<String>();
		sizes = new ArrayList<String>();
		types = new ArrayList<String>();
		genders = new ArrayList<String>();
		clients = new ArrayList<String>();
		
		this.server = server;
		
		readAll();
	}
	
	public void readAll(){
		try{
			readBrands();
			readTypes();
			readColors();
			readSizes();
			readGenders();
			readClients();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean read(int identifier){
		try{
			if(identifier == Reference.BRAND){
				readBrands();
			} else if(identifier == Reference.TYPE){
				readTypes();
			} else if(identifier == Reference.COLOR){
				readColors();
			} else if(identifier == Reference.SIZE){
				readSizes();
			} else if(identifier == Reference.GENDER){
				readGenders();
			} else if(identifier == Reference.CLIENT){
				readClients();
			} else {
				return false;
			}
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void readBrands(){
		try{
			brands = server.readElements(Reference.BRAND);
		 } catch (Exception e){
			 System.out.println(e);
		 }
	}
	
	public void readTypes(){
		try{
			types = server.readElements(Reference.TYPE);
		 } catch (Exception e){
			 System.out.println(e);
		 }
	}

	public void readColors(){
		try{
			colors = server.readElements(Reference.COLOR);
		 } catch (Exception e){
			 System.out.println(e);
		 }
	}

	public void readSizes(){
		try{
			sizes = server.readElements(Reference.SIZE);
		 } catch (Exception e){
			 System.out.println(e);
		 }
	}

	public void readGenders(){
		try{
			genders = server.readElements(Reference.GENDER);
		 } catch (Exception e){
			 System.out.println(e);
		 }
	}

	public void readClients(){
		try{
			clients = server.readElements(Reference.CLIENT);
		 } catch (Exception e){
			 System.out.println(e);
		 }
	}
	
	public void writeAll(){
		try{
			writeAllBrands();
			writeAllTypes();
			writeAllColors();
			writeAllSizes();
			writeAllGenders();
			writeAllClients();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean write(int identifier){
		try{
			if(identifier == Reference.BRAND){
				writeAllBrands();
			} else if(identifier == Reference.TYPE){
				writeAllTypes();
			} else if(identifier == Reference.COLOR){
				writeAllColors();
			} else if(identifier == Reference.SIZE){
				writeAllSizes();
			} else if(identifier == Reference.GENDER){
				writeAllGenders();
			} else if(identifier == Reference.CLIENT){
				writeAllClients();
			} else {
				return false;
			}
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void writeAllBrands(){
		server.writeAllElements(Reference.BRAND, brands);
	}
	
	public void writeAllTypes(){
		server.writeAllElements(Reference.TYPE, types);
	}
	
	public void writeAllColors(){
		server.writeAllElements(Reference.COLOR, colors);
	}
	
	public void writeAllSizes(){
		server.writeAllElements(Reference.SIZE, sizes);
	}
	
	public void writeAllGenders(){	
		server.writeAllElements(Reference.GENDER, genders);
	}
	
	public void writeAllClients(){	
		server.writeAllElements(Reference.CLIENT, clients);
	}
}
