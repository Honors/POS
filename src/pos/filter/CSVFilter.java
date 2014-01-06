package pos.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class CSVFilter extends FileFilter {

	public final static String csv = "csv";
	@Override
	public boolean accept(File f) {
		if(f.isDirectory())
	        return true;
	    String extension = getExtension(f);
	    if(extension != null)
	        return extension.equals(csv);
	    return false;
	}

	@Override
	public String getDescription(){
		return "CSV File (*.csv)";
	}
	
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

}
