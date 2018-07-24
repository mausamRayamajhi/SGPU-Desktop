package GUI;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DatabaseConnection.ConnectorWithDatabase;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewEntry extends JPanel {
	public static JTable table1;
	DefaultTableModel model1 ;
	private JPopupMenu popmenu;
	/**
	 * Create the panel.
	 */
	public NewEntry() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		popmenu = new JPopupMenu();
		JMenuItem deleterow = new JMenuItem("Delete Row");
		popmenu.add(deleterow);

		
		table1 = new JTable();
		Object[]  column = { "SN", "Name","Address", "Contact", "Selecetd Iteam","Price","Country","Tax","Vat","Vat/Tax Rate","Advance","Order Date","Delivary Date","Delivary Address", "Status","Gender" };
		model1 = new DefaultTableModel();
		model1.setColumnIdentifiers(column);
		table1.setModel(model1);
		scrollPane.setViewportView(table1);
		
		table1.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int row = table1.rowAtPoint(e.getPoint());
				table1.getSelectionModel().setSelectionInterval(row, row);

				if (e.getButton() == MouseEvent.BUTTON3) {
					popmenu.show(table1, e.getX(), e.getY());
				}

			}

		});
		deleterow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				int row = table1.getSelectedRow();
			
				model1.removeRow(row);

			}
		});

	}
	

	public JTable getTable1() {
		return table1;
	}
	public void setTable1(JTable table1) {
		this.table1 = table1;
	}
	public DefaultTableModel getModel1() {
		return model1;
	}
	public void setModel1(DefaultTableModel model1) {
		this.model1 = model1;
	}

}

		//orderDate = ((JTextField) dateChooserField1.getDateEditor().getUiComponent()).getText();
		//delivaryDate = ((JTextField) dateChooserField2.getDateEditor().getUiComponent()).getText();
