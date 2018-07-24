package GUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Calculator extends JFrame {
	private double num, ans;
	private int calculation;
	private JTextField inputResult;
	private JButton input1;
	private JButton input2;
	private JButton input3;
	private JButton input4;
	private JButton input5;
	private JButton input6;
	private JButton input7;
	private JButton input8;
	private JButton input9;
	private JButton input0;
	private JButton inputPoint;

	private JButton inputCE;
	private JButton inputC;
	private JButton inputBackSpace;
	private JButton inputPlus;
	private JButton inputMinus;
	private JButton inputDiv;
	private JButton inputMul;
	private JButton inputSub;
	private JButton inputEqualTo;
	private JLabel currentopertation;

	public Calculator(MainController2 parent) {
		super("Calculator");
		setVisible(true);
		setResizable(false);
		Dimension di =new Dimension(230, 370);
		setMinimumSize(di);
		setMaximumSize(di);
		
		setLocationRelativeTo(parent);
		inputResult = new JTextField(9);
		input0 = new JButton("0");
		input1 = new JButton("1");
		input2 = new JButton("2");
		input3 = new JButton("3");
		input4 = new JButton("4");
		currentopertation = new JLabel("");
		input5 = new JButton("5");
		input6 = new JButton("6");
		input7 = new JButton("7");
		input8 = new JButton("8");
		input9 = new JButton("9");
		inputPoint = new JButton(".");
		inputCE = new JButton("CE");
		inputC = new JButton("C");
		inputPlus = new JButton("+");
		inputMinus = new JButton("-");
		inputMul = new JButton("*");
		inputDiv = new JButton("/");
		inputBackSpace = new JButton("<-");
		inputEqualTo = new JButton("=");
		inputResult.setPreferredSize(new Dimension(50, 50));
		inputResult.setFont(new Font("", Font.BOLD, 25));
		currentopertation.setFont(new Font("", Font.BOLD, 15));
		 inputResult.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// currentopertation.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		inputBackSpace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int length = inputResult.getText().length();
				int number = inputResult.getText().length() - 1;
				String store;
				StringBuilder back = new StringBuilder(inputResult.getText());
				back.deleteCharAt(number);
				store = back.toString();
				inputResult.setText(store);
			}
		});
		inputEqualTo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				arithermatic();
				currentopertation.setText("");

			}
		});

		input0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText(inputResult.getText() + "0");

			}
		});
		input1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText(inputResult.getText() + "1");

			}
		});
		input2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText(inputResult.getText() + "2");

			}
		});
		input3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText(inputResult.getText() + "3");

			}
		});
		input4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText(inputResult.getText() + "4");

			}
		});
		input5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText(inputResult.getText() + "5");

			}
		});
		input6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText(inputResult.getText() + "6");

			}
		});
		input7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText(inputResult.getText() + "7");

			}
		});
		input8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText(inputResult.getText() + "8");

			}
		});
		input9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText(inputResult.getText() + "9");

			}
		});
		inputPoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText(inputResult.getText() + ".");

			}
		});
		inputPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				num = Double.parseDouble(inputResult.getText());
				calculation = 1;
				inputResult.setText("");
				currentopertation.setText(num + "+");
			}
		});
		inputMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				num = Double.parseDouble(inputResult.getText());
				calculation = 2;
				inputResult.setText("");
				currentopertation.setText(num + "-");

			}
		});
		inputMul.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				num = Double.parseDouble(inputResult.getText());
				calculation = 3;
				inputResult.setText("");
				currentopertation.setText(num + "*");

			}
		});
		inputDiv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				num = Double.parseDouble(inputResult.getText());
				calculation = 4;
				inputResult.setText("");
				currentopertation.setText(num + "/");

			}
		});
		inputC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText("");
				currentopertation.setText("");

			}
		});
		inputCE.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputResult.setText("");
				currentopertation.setText("");

			}
		});

		inputResult.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent eve) {
				int key = eve.getKeyCode();
				if ((key >= eve.VK_0 && key <= eve.VK_9) || (key >= eve.VK_NUMPAD0 && key <= eve.VK_NUMPAD9)
						|| key == KeyEvent.VK_BACK_SPACE) {

					inputResult.setEditable(true);
					inputResult.setBackground(Color.WHITE);

				}

				else {

					inputResult.setEditable(false);
					inputResult.setBackground(Color.YELLOW);

				}

				switch (key) {
				case 107:
					num = Double.parseDouble(inputResult.getText());
					calculation = 1;
					inputResult.setText("");
					currentopertation.setText(num + "+");
					break;
				case 109:
					num = Double.parseDouble(inputResult.getText());
					calculation = 2;
					inputResult.setText("");
					currentopertation.setText(num + "-");
					break;
				case 106:
					num = Double.parseDouble(inputResult.getText());
					calculation = 3;
					inputResult.setText("");
					currentopertation.setText(num + "*");
					break;
				case 111:
					num = Double.parseDouble(inputResult.getText());
					calculation = 4;
					inputResult.setText("");
					currentopertation.setText(num + "/");
					break;
				case 112:
					num = Double.parseDouble(inputResult.getText());
					calculation = 4;
					inputResult.setText("");
					currentopertation.setText(num + "");
					break;
				case 110:
					inputResult.setText(inputResult.getText() + ".");

					break;
				case 10:
					arithermatic();
					currentopertation.setText("");
					break;
				default:
					break;
				}

			}

		});

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		Dimension dim = inputEqualTo.getPreferredSize();
		dim.width = 90;
		inputEqualTo.setPreferredSize(dim);

		gc.weightx = 1.0;
		gc.weighty = 0.50;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		// gc.insets = new Insets(50,170,100,0);
		gc.insets = new Insets(10, 150, 0, 0);
		add(currentopertation, gc);

		// next
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(50, 10, 0, 0);
		add(inputResult, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 20, 0, 0);
		add(inputCE, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 70, 0, 0);
		add(inputBackSpace, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 120, 0, 0);
		add(inputC, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 170, 0, 0);
		add(inputPlus, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 20, 0, 0);
		add(input7, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 70, 0, 0);
		add(input8, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 120, 0, 0);
		add(input9, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 170, 0, 0);
		add(inputMinus, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 20, 0, 0);
		add(input4, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 70, 0, 0);
		add(input5, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 120, 0, 0);
		add(input6, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 170, 0, 0);
		add(inputMul, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 20, 0, 0);
		add(input1, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 70, 0, 0);
		add(input2, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 120, 0, 0);
		add(input3, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 170, 0, 0);
		add(inputDiv, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 20, 0, 0);
		add(input0, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 70, 0, 0);
		add(inputPoint, gc);

		// next
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 120, 0, 0);
		add(inputEqualTo, gc);

	}

	public void arithermatic() {
		switch (calculation) {
		case 1:
			ans = num + Double.parseDouble(inputResult.getText());
			inputResult.setText(Double.toString(ans));
			break;
		case 2:
			ans = num - Double.parseDouble(inputResult.getText());
			inputResult.setText(Double.toString(ans));
			break;
		case 3:
			ans = num * Double.parseDouble(inputResult.getText());
			inputResult.setText(Double.toString(ans));
			break;
		case 4:
			ans = num / Double.parseDouble(inputResult.getText());
			inputResult.setText(Double.toString(ans));
			break;
		default:
			break;
		}
	}
}
