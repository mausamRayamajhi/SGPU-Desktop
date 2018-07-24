package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class CustomerAllDetailsShow extends JFrame {

	private JPanel contentPane;
	public static JLabel nameField;
	public static JLabel IDField;
	public static JLabel contactField;
	public static JLabel countryField;
	public static JLabel addressField;
	public static JLabel GenderField;
	public static JLabel orderID;
	public static JComboBox comboBox;
	public static JLabel amountBeforeVatTAx;
	public static JCheckBox vatCheckbox;
	public static JLabel padUnpaidField;
	public static JLabel totalField;
	public static JLabel advancefield;
	public static JLabel delivaryAddressField;
	public static JLabel orderDateField;
	public static JLabel delivaryDateField;
	public static JLabel dueAmountField;
	public static JButton closeBtn;
	public static JLabel taxvatrateField;
	public static JCheckBox taxCheckBox;
	public static JLabel lblTax;
	public static JLabel lblVat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerAllDetailsShow frame = new CustomerAllDetailsShow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerAllDetailsShow() {

		setBounds(355, 70, 647, 553);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCustomerAllInformation = new JLabel("Customer All Information");
		lblCustomerAllInformation.setBorder(new LineBorder(Color.WHITE, 2, true));
		lblCustomerAllInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerAllInformation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCustomerAllInformation.setBounds(10, 11, 611, 42);
		contentPane.add(lblCustomerAllInformation);

		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(297, 100, 34, 14);
		contentPane.add(lblId);

		IDField = new JLabel("ID :");
		IDField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		IDField.setBounds(325, 100, 65, 14);
		contentPane.add(IDField);

		nameField = new JLabel("Name Label");
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(10, 64, 611, 35);
		contentPane.add(nameField);

		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(263, 163, 65, 14);
		contentPane.add(lblAddress);

		addressField = new JLabel("Address :");
		addressField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addressField.setBounds(324, 163, 125, 14);
		contentPane.add(addressField);

		JLabel lblContact = new JLabel("Contact :");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContact.setBounds(44, 138, 65, 14);
		contentPane.add(lblContact);

		contactField = new JLabel("contactField");
		contactField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contactField.setBounds(105, 138, 125, 14);
		contentPane.add(contactField);

		JLabel lblGender = new JLabel("Gender :");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setBounds(451, 138, 65, 14);
		contentPane.add(lblGender);

		GenderField = new JLabel("GenderField");
		GenderField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GenderField.setBounds(512, 138, 125, 14);
		contentPane.add(GenderField);

		JLabel lblCountry = new JLabel("Country :");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCountry.setBounds(260, 138, 65, 14);
		contentPane.add(lblCountry);

		countryField = new JLabel("countryField");
		countryField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		countryField.setBounds(321, 138, 136, 14);
		contentPane.add(countryField);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 192, 611, 311);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.BOLD, 11));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Oder Information");
		lblNewLabel.setBorder(new LineBorder(Color.WHITE, 2, true));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 589, 26);
		panel.add(lblNewLabel);

		JLabel lblId_1 = new JLabel("ID :");
		lblId_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId_1.setBounds(279, 41, 69, 14);
		panel.add(lblId_1);

		orderID = new JLabel("ID :");
		orderID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		orderID.setBounds(308, 41, 65, 14);
		panel.add(orderID);

		JLabel lblSelectedIteams = new JLabel("Selected Iteams :");
		lblSelectedIteams.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectedIteams.setBounds(30, 111, 129, 14);
		panel.add(lblSelectedIteams);

		comboBox = new JComboBox();
		comboBox.setBounds(141, 109, 156, 20);
		panel.add(comboBox);

		JLabel lblAmountBeforeVt = new JLabel("Amount B. V/T :");
		lblAmountBeforeVt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmountBeforeVt.setBounds(39, 139, 112, 14);
		panel.add(lblAmountBeforeVt);

		amountBeforeVatTAx = new JLabel("Before vattax");
		amountBeforeVatTAx.setFont(new Font("Tahoma", Font.PLAIN, 14));
		amountBeforeVatTAx.setBounds(142, 139, 125, 14);
		panel.add(amountBeforeVatTAx);

		lblTax = new JLabel("Tax :");
		lblTax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTax.setBounds(105, 164, 37, 14);
		panel.add(lblTax);

		lblVat = new JLabel("Vat :");
		lblVat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVat.setBounds(105, 189, 32, 14);
		panel.add(lblVat);

		taxCheckBox = new JCheckBox("");
		taxCheckBox.setBounds(140, 162, 21, 23);
		panel.add(taxCheckBox);

		vatCheckbox = new JCheckBox("");
		vatCheckbox.setBounds(140, 187, 21, 23);
		panel.add(vatCheckbox);

		JLabel lblTaxvatRate = new JLabel("Tax/Vat Rate :");
		lblTaxvatRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTaxvatRate.setBounds(47, 214, 104, 14);
		panel.add(lblTaxvatRate);

		taxvatrateField = new JLabel("taxvatr");
		taxvatrateField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taxvatrateField.setBounds(141, 214, 105, 14);
		panel.add(taxvatrateField);

		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotal.setBounds(438, 115, 48, 14);
		panel.add(lblTotal);

		totalField = new JLabel("Toal field");
		totalField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalField.setBounds(480, 115, 125, 14);
		panel.add(totalField);

		JLabel lblAdvance = new JLabel("Advance :");
		lblAdvance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdvance.setBounds(415, 138, 65, 14);
		panel.add(lblAdvance);

		advancefield = new JLabel("advancefield");
		advancefield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		advancefield.setBounds(479, 137, 125, 14);
		panel.add(advancefield);

		JLabel lblDelivaryAddress = new JLabel("Delivary Address :");
		lblDelivaryAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDelivaryAddress.setBounds(368, 161, 112, 14);
		panel.add(lblDelivaryAddress);

		delivaryAddressField = new JLabel("delivaryAddressField");
		delivaryAddressField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		delivaryAddressField.setBounds(480, 161, 125, 14);
		panel.add(delivaryAddressField);

		JLabel lblOrderDate = new JLabel("Order Date :");
		lblOrderDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrderDate.setBounds(400, 188, 82, 14);
		panel.add(lblOrderDate);

		orderDateField = new JLabel("orderDate");
		orderDateField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		orderDateField.setBounds(480, 188, 125, 14);
		panel.add(orderDateField);

		JLabel lblDelivaryDate = new JLabel("Delivary Date :");
		lblDelivaryDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDelivaryDate.setBounds(387, 213, 102, 14);
		panel.add(lblDelivaryDate);

		delivaryDateField = new JLabel("orderDate");
		delivaryDateField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		delivaryDateField.setBounds(480, 213, 125, 14);
		panel.add(delivaryDateField);

		JLabel lblNewLabel_1 = new JLabel("RS.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(266, 264, 37, 34);
		panel.add(lblNewLabel_1);

		dueAmountField = new JLabel("RS.dueAmountField");
		dueAmountField.setForeground(Color.RED);
		dueAmountField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		dueAmountField.setBounds(298, 264, 142, 34);
		panel.add(dueAmountField);

		padUnpaidField = new JLabel("Unpaid");
		padUnpaidField.setForeground(Color.RED);
		padUnpaidField.setFont(new Font("Tahoma", Font.BOLD, 26));
		padUnpaidField.setBounds(437, 50, 112, 49);
		panel.add(padUnpaidField);

		closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closeBtn.setBounds(418, 274, 89, 23);
		panel.add(closeBtn);
		
		JButton print = new JButton("Print");
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Employee List Report Print");

				MessageFormat footer = new MessageFormat("Page {0,number,integer}");

				try {
               //    boolean comp=textArea.print();

					//textArea.print(JTextArea.PrintMode.FIT_WIDTH, header, footer);

				} catch (Exception eve) {
					System.err.format("Cannot Print %s%n", eve.getMessage());
				}
			}
		});
		print.setBounds(510, 274, 89, 23);
		panel.add(print);
		
		
	}
}
