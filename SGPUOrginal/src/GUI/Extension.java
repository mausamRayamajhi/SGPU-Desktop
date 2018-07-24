package GUI;

import java.net.URL;

import javax.swing.ImageIcon;

public class Extension {
	public static String getfileextension(String name) {
		int pointIndex = name.lastIndexOf(".");
		System.out.println("point index is:"+pointIndex);
		if (pointIndex == -1) {
			return null;
		}
		if (pointIndex == name.length() - 1) {
			return null;
		}

		return name.substring(pointIndex + 1, name.length());

	}

	public static ImageIcon createIcon(String path) {
		if (path == null) {
			System.err.println("Cannot load Image");
		}
		URL url = System.class.getResource(path);
		ImageIcon imageIcon = new ImageIcon(url);
		return imageIcon;
	}

}
