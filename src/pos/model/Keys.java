package pos.model;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import pos.dialog.DialogSingleTextInput;

public class Keys {
	
	public String keyPath;
	
	public ArrayList<String> brands, brandCodes;
	public ArrayList<String> colors, colorCodes;
	public ArrayList<String> sizes, sizeCodes;
	public ArrayList<String> types, typeCodes;
	public ArrayList<String> genders, genderCodes;
	
	public Keys(String path){
		brands = new ArrayList<String>();
		brandCodes = new ArrayList<String>();
		colors = new ArrayList<String>();
		colorCodes = new ArrayList<String>();
		sizes = new ArrayList<String>();
		sizeCodes = new ArrayList<String>();
		types = new ArrayList<String>();
		typeCodes = new ArrayList<String>();
		genders = new ArrayList<String>();
		genderCodes = new ArrayList<String>();
		
		keyPath = path + "\\keys";
		
		BufferedReader in;
		try{
			 in = new BufferedReader(new FileReader(keyPath + "\\brands.csv")); 
			 String s = in.readLine();
			 while (s != null){
				StringTokenizer z = new StringTokenizer(s, ",");
				String t = "";
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				brands.add(t);
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				brandCodes.add(t);
				s = in.readLine();
			 }
			 in.close();
		 } catch (Exception e){
			 System.out.println(e);
		 }
		try{
			 in = new BufferedReader(new FileReader(keyPath + "\\colors.csv")); 
			 String s = in.readLine();
			 while (s != null){
				StringTokenizer z = new StringTokenizer(s, ",");
				String t = "";
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				colors.add(t);
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				colorCodes.add(t);
				s = in.readLine();
			 }
			 in.close();
		 } catch (Exception e){
			 System.out.println(e);
		 }
		try{
			 in = new BufferedReader(new FileReader(keyPath + "\\sizes.csv")); 
			 String s = in.readLine();
			 while (s != null){
				StringTokenizer z = new StringTokenizer(s, ",");
				String t = "";
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				sizes.add(t);
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				sizeCodes.add(t);
				s = in.readLine();
			 }
			 in.close();
		 } catch (Exception e){
			 System.out.println(e);
		 }
		try{
			 in = new BufferedReader(new FileReader(keyPath + "\\types.csv")); 
			 String s = in.readLine();
			 while (s != null){
				StringTokenizer z = new StringTokenizer(s, ",");
				String t = "";
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				types.add(t);
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				typeCodes.add(t);
				s = in.readLine();
			 }
			 in.close();
		 } catch (Exception e){
			 System.out.println(e);
		 }
		try{
			 in = new BufferedReader(new FileReader(keyPath + "\\genders.csv")); 
			 String s = in.readLine();
			 while (s != null){
				StringTokenizer z = new StringTokenizer(s, ",");
				String t = "";
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				genders.add(t);
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				genderCodes.add(t);
				s = in.readLine();
			 }
			 in.close();
		 } catch (Exception e){
			 System.out.println(e);
		 }
		
	}
	
	public String getBrand(String code){
		System.out.println(code);
		int i = brandCodes.indexOf(code);
		if (i < 0){
			DialogSingleTextInput in = new DialogSingleTextInput(new JFrame(), "Define Brand...", "UPC segment \"" + code + "\" is not a defined Brand\nThe Brand \"" + code + "\" is", "", brands);
			String newBrand = in.getValidatedInput();
			if(newBrand != null){
				writeBrand(newBrand, code);
				brands.add(newBrand);
				brandCodes.add(code);
				i = brandCodes.indexOf(code);
			}else{
				return "";
			}
		}
		return brands.get(i);
	}
	
	public String getColor(String code){
		System.out.println(code);
		int i = colorCodes.indexOf(code);
		if (i < 0){
			DialogSingleTextInput in = new DialogSingleTextInput(new JFrame(), "Define Color...", "UPC segment \"" + code + "\" is not a defined Color\nThe Color \"" + code + "\" is", "", colors);
			String newColor = in.getValidatedInput();
			if(newColor != null){
				writeColor(newColor, code);
				colors.add(newColor);
				colorCodes.add(code);
				i = colorCodes.indexOf(code);
			}else{
				return "";
			}
		}
		return colors.get(i);
	}
	
	public String getSize(String code){
		System.out.println(code);
		int i = sizeCodes.indexOf(code);
		if (i < 0){
			DialogSingleTextInput in = new DialogSingleTextInput(new JFrame(), "Define Size...", "UPC segment \"" + code + "\" is not a defined Size\nThe Size \"" + code + "\" is", "", sizes);
			String newSize = in.getValidatedInput();
			if(newSize != null){
				writeSize(newSize, code);
				sizes.add(newSize);
				sizeCodes.add(code);
				i = sizeCodes.indexOf(code);
			}else{
				return "";
			}
		}
		return sizes.get(i);
	}
	
	public String getType(String code){
		System.out.println(code);
		int i = typeCodes.indexOf(code);
		if (i < 0){
			DialogSingleTextInput in = new DialogSingleTextInput(new JFrame(), "Define Type...", "UPC segment \"" + code + "\" is not a defined Type\nThe Type \"" + code + "\" is", "", types);
			String newType = in.getValidatedInput();
			if(newType != null){
				writeType(newType, code);
				types.add(newType);
				typeCodes.add(code);
				i = typeCodes.indexOf(code);
			}else{
				return "";
			}
		}
		return types.get(i);
	}
	
	public String getGender(String code){
		System.out.println(code);
		int i = genderCodes.indexOf(code);
		if (i < 0){
			DialogSingleTextInput in = new DialogSingleTextInput(new JFrame(), "Define Gender...", "UPC segment \"" + code + "\" is not a defined Gender\nThe Gender \"" + code + "\" is", "", genders);
			String newGender = in.getValidatedInput();
			if(newGender != null){
				writeGender(newGender, code);
				genders.add(newGender);
				genderCodes.add(code);
				i = genderCodes.indexOf(code);
			}else{
				return "";
			}
		}
		return genders.get(i);
	}
	
	public boolean writeBrand(String name, String code){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\brands.csv", true));
			out.append("\"" + name + "\",\"" + code + "\"");
			out.newLine();
			out.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeColor(String name, String code){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\colors.csv", true));
			out.append("\"" + name + "\",\"" + code + "\"");
			out.newLine();
			out.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeSize(String name, String code){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\sizes.csv", true));
			out.append("\"" + name + "\",\"" + code + "\"");
			out.newLine();
			out.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeType(String name, String code){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\types.csv", true));
			out.append("\"" + name + "\",\"" + code + "\"");
			out.newLine();
			out.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeGender(String name, String code){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(keyPath + "\\genders.csv", true));
			out.append("\"" + name + "\",\"" + code + "\"");
			out.newLine();
			out.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
