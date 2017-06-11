package encryptionAndDecryption;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUI {

	private JMenu menu = new JMenu();

	private JTextField message = new JTextField();
	private JTextField toEmail = new JTextField();
	private JTextField fromEmail = new JTextField();

	private JLabel subtitle = new JLabel(
			"Free online encryption and decryption", JLabel.CENTER);
	private JLabel messageLabel = new JLabel("Message:", JLabel.CENTER);
	private JLabel toEmailLabel = new JLabel("Destination Email:",
			JLabel.CENTER);
	private JLabel fromEmailLabel = new JLabel("Your Email:", JLabel.CENTER);

	private JButton encryptButton = new JButton(
			new ImageIcon("EncryptIcon.png"));
	private JButton emailButton = new JButton("Email Message");
	private JButton decryptButton = new JButton(
			new ImageIcon("DecryptIcon.png"));

	private JButton[] buttonArray = { encryptButton, emailButton, decryptButton };

	public void go() {

		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel messagePanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		encryptButton.addActionListener(new EncryptButton());
		emailButton.addActionListener(new EmailButton());
		decryptButton.addActionListener(new DecryptButton());

		JLabel[] labelArray1 = { title, subtitle };

		JLabel[] labelArray2 = { toEmailLabel, fromEmailLabel, messageLabel };

		JTextField[] textFieldArray = { toEmail, fromEmail, message };

		messagePanel.setLayout(new GridLayout(12, 1));

		for (JLabel label : labelArray1) {
			messagePanel.add(label);
		}
		for (int i = 0; i < labelArray2.length; i++) {
			messagePanel.add(labelArray2[i]);
			labelArray2[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
			messagePanel.add(textFieldArray[i]);
			textFieldArray[i]
					.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		}
		subtitle.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
		title.setFont(new Font(Font.SERIF, Font.BOLD, 40));

		messagePanel.add(message);

		buttonPanel.setLayout(new GridLayout(1, 3));

		for (JButton button : buttonArray) {
			buttonPanel.add(button);
			button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			button.setOpaque(true);
			button.setBorderPainted(false);
			button.setBackground(Color.WHITE);
		}

		frame.getContentPane().add(BorderLayout.CENTER, buttonPanel);
		frame.getContentPane().add(BorderLayout.NORTH, messagePanel);

		frame.setSize(800, 600);
		frame.setVisible(true);
	}

	private JLabel title = new JLabel("Decryptonite", JLabel.CENTER);

	class EncryptButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!message.getText().equals("")) {
				message.setText(Guts.encrypt(message.getText()));
			}
		}

	}

	class DecryptButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!message.getText().equals("")) {
				message.setText(Guts.decrypt(message.getText()));
			}
		}
	}

	class EmailButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

}
