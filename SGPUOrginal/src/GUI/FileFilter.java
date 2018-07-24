package GUI;

import java.io.File;
public class FileFilter extends javax.swing.filechooser.FileFilter{

	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		String name=file.getName();
		String extension=Extension.getfileextension(name);
		if (extension==null) {
			return false;
		}
		if (extension.equals("sgpu")) {
			return true;
		}
		return false;
	
	}

	@Override
	public String getDescription() {
		return "SGPU File (*.sgpu)";
	}

}
