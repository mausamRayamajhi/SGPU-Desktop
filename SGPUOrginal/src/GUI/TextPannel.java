package GUI;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPannel extends JPanel {
	private JTextArea textArea;

	public TextPannel() {
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		setVisible(true);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}

	public void appendText(String name) {
		textArea.append(name);
		//System.out.println(name);
	}
}
