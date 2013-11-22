package pos.model;

import java.io.*;
import java.util.ArrayList;

public class Keys {
	
	public static final int BRAND = 0;
	public static final int COLOR = 1;
	public static final int SIZE = 2;
	public static final int TYPE = 3;
	public static final int GENDER = 4;
	public static final int CLIENT = 5;
	
	public String keyPath;
	
	public ArrayList<String> brands;
	public ArrayList<String> colors;
	public ArrayList<String> sizes;
	public ArrayList<String> types;
	public ArrayList<String> genders;
	public ArrayList<String> clients;
	
	public Keys(String path){
		brands = new ArrayList<String>();
		colors = new ArrayList<String>();
		sizes = new ArrayList<String>();
		types = new ArrayList<String>();
		genders = new ArrayList<String>();
		clients = new ArrayList<String>();
		
		keyPath = path + "\\keys";
		
		readAll();
	}
	
	public boolean readAll(){
		try{
			readBrands();
			readTypes();
			readColors();
			readSizes();
			readGenders();
			readClients();
		return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean read(int identifier){
		try{
			if(identifier == BRAND){
				readBrands();
			} else if(identifier == TYPE){
				readTypes();
			} else if(identifier == COLOR){
				readColors();
			} else if(identifier == SIZE){
				readSizes();
			} else if(identifier == GENDER){
				readGenders();
			} else if(identifier == CLIENT){
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
	
	public boolean readBrands(){
		BufferedReader in;
		try{
			 File file = new File(keyPath + "\\brands.csv");
			 file.createNewFile();
			 in = new BufferedReader(new FileReader(file)); 
			 String s = in.readLine();
			 while (s != null){
				String t = s;
				t = t.replace((char)34, ' ');
				t = t.trim();
				brands.add(t);
				s = in.readLine();
			 }
			 in.close();
			 return true;
		 } catch (Exception e){
			 System.out.println(e);
			 return false;
		 }
	}
	
	public boolean readTypes(){
		BufferedReader in;
		try{
			 File file = new File(keyPath + "\\types.csv");
			 file.createNewFile();
			 in = new BufferedReader(new FileReader(file)); 
			 String s = in.readLine();
			 while (s != null){
				String t = s;
				t = t.replace((char)34, ' ');
				t = t.trim();
				types.add(t);
				s = in.readLine();
			 }
			 in.close();
			 return true;
		 } catch (Exception e){
			 System.out.println(e);
			 return false;
		 }
	}

	public boolean readColors(){
		BufferedReader in;
		try{
			 File file = new File(keyPath + "\\colors.csv");
			 file.createNewFile();
			 in = new BufferedReader(new FileReader(file)); 
			 String s = in.readLine();
			 while (s != null){
				String t = s;
				t = t.replace((char)34, ' ');
				t = t.trim();
				colors.add(t);
				s = in.readLine();
			 }
			 in.close();
			 return true;
		 } catch (Exception e){
			 System.out.println(e);
			 return false;
		 }
	}

	public boolean readSizes(){
		BufferedReader in;
		try{
			File file = new File(keyPath + "\\sizes.csv");
			 file.createNewFile();
			 in = new BufferedReader(new FileReader(file)); 
			 String s = in.readLine();
			 while (s != null){
				String t = s;
				t = t.replace((char)34, ' ');
				t = t.trim();
				sizes.add(t);
				s = in.readLine();
			 }
			 in.close();
			 return true;
		 } catch (Exception e){
			 System.out.println(e);
			 return false;
		 }
	}

	public boolean readGenders(){
		BufferedReader in;
		try{
			 File file = new File(keyPath + "\\genders.csv");
			 file.createNewFile();
			 in = new BufferedReader(new FileReader(file)); 
			 String s = in.readLine();
			 while (s != null){
				String t = s;
				t = t.replace((char)34, ' ');
				t = t.trim();
				genders.add(t);
				s = in.readLine();
			 }
			 in.close();
			 return false;
		 } catch (Exception e){
			 System.out.println(e);
			 return true;
		 }
	}

	public boolean readClients(){
		BufferedReader in;
		try{
			 File file = new File(keyPath + "\\clients.csv");
			 file.createNewFile();
			 in = new BufferedReader(new FileReader(file)); 
			 String s = in.readLine();
			 while (s != null){
				String t = s;
				t = t.replace((char)34, ' ');
				t = t.trim();
				clients.add(t);
				s = in.readLine();
			 }
			 in.close();
			 return true;
		 } catch (Exception e){
			 System.out.println(e);
			 return false;
		 }
	}
	
	public boolean write(String name, int identifier){
		try{
			if(identifier == BRAND){
				writeBrand(name);
			} else if(identifier == TYPE){
				writeType(name);
			} else if(identifier == COLOR){
				writeColor(name);
			} else if(identifier == SIZE){
				writeSize(name);
			} else if(identifier == GENDER){
				writeGender(name);
			} else if(identifier == CLIENT){
				writeClient(name);
			} else {
				return false;
			}
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeBrand(String name){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\brands.csv", true));
			out.append("\"" + name + "\"");
			out.newLine();
			out.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeColor(String name){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\colors.csv", true));
			out.append("\"" + name +"\"");
			out.newLine();
			out.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeSize(String name){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\sizes.csv", true));
			out.append("\"" + name + "\"");
			out.newLine();
			out.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeType(String name){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\types.csv", true));
			out.append("\"" + name + "\"");
			out.newLine();
			out.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeGender(String name){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\genders.csv", true));
			out.append("\"" + name +"\"");
			out.newLine();
			out.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeClient(String name){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\clients.csv", true));
			out.append("\"" + name +"\"");
			out.newLine();
			out.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean rewriteAll(int identifier){
		try{
			if(identifier == BRAND){
				rewriteAllBrands();
			} else if(identifier == TYPE){
				rewriteAllTypes();
			} else if(identifier == COLOR){
				rewriteAllColors();
			} else if(identifier == SIZE){
				rewriteAllSizes();
			} else if(identifier == GENDER){
				rewriteAllGenders();
			} else if(identifier == CLIENT){
				rewriteAllClients();
			} else {
				return false;
			}
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean rewriteAllBrands(){
		try{
			 BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\brands.csv", false)); 
			 for(int i = 0; i < brands.size(); i++){
				 out.append("\"" + brands.get(i) + "\"");
				 out.newLine();
			 }
			 out.close();
			 return true;
		 } catch (Exception e){
			 System.out.println(e);
			 return false;
		 }
	}
	
	public boolean rewriteAllTypes(){
		try{
			 BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\types.csv", false)); 
			 for(int i = 0; i < types.size(); i++){
				 out.append("\"" + types.get(i) + "\"");
				 out.newLine();
			 }
			 out.close();
			 return true;
		 } catch (Exception e){
			 System.out.println(e);
			 return false;
		 }
	}
	
	public boolean rewriteAllColors(){
		try{
			 BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\colors.csv", false)); 
			 for(int i = 0; i < colors.size(); i++){
				 out.append("\"" + colors.get(i) + "\"");
				 out.newLine();
			 }
			 out.close();
			 return true;
		 } catch (Exception e){
			 System.out.println(e);
			 return false;
		 }
	}
	
	public boolean rewriteAllSizes(){
		try{
			 BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\sizes.csv", false)); 
			 for(int i = 0; i < sizes.size(); i++){
				 out.append("\"" + sizes.get(i) + "\"");
				 out.newLine();
			 }
			 out.close();
			 return true;
		 } catch (Exception e){
			 System.out.println(e);
			 return false;
		 }
	}
	
	public boolean rewriteAllGenders(){
		try{
			 BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\genders.csv", false)); 
			 for(int i = 0; i < genders.size(); i++){
				 out.append("\"" + genders.get(i) + "\"");
				 out.newLine();
			 }
			 out.close();
			 return true;
		 } catch (Exception e){
			 System.out.println(e);
			 return false;
		 }
	}
	
	public boolean rewriteAllClients(){
		try{
			 BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\clients.csv", false)); 
			 for(int i = 0; i < clients.size(); i++){
				 out.append("\"" + clients.get(i) + "\"");
				 out.newLine();
			 }
			 out.close();
			 return true;
		 } catch (Exception e){
			 System.out.println(e);
			 return false;
		 }
	}
}
