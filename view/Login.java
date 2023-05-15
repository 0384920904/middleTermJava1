package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.jdbc;

import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JPasswordField passwordField;

	public Login() {
		URL url=Login.class.getResource("Login-icon.png");
		Image image=Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage(image);
		setBackground(new Color(227, 227, 227));
		setTitle("Log in");
		JFrame frame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 294, 199);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 239, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel();
		label.setForeground(new Color(64, 128, 128));
		label.setBackground(new Color(255, 255, 255));
		label.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Login.class.getResource("Username-icon.png"))));
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		label.setBounds(20, 21, 35, 39);
		contentPane.add(label);

		JLabel label_1 = new JLabel();
		label_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Login.class.getResource("Lock-icon.png"))));
		label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_1.setBounds(16, 70, 35, 39);
		contentPane.add(label_1);

		textField_id = new JTextField();
		textField_id.setForeground(new Color(0, 0, 0));
		textField_id.setBackground(new Color(255, 255, 255));
		textField_id.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_id.setBounds(65, 25, 185, 35);
		contentPane.add(textField_id);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(65, 74, 185, 35);
		contentPane.add(passwordField);

		JButton btn_login = new JButton("Login");
		btn_login.setBackground(new Color(240, 240, 240));
		btn_login.setFocusable(false);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_id.getText();
				String password = String.valueOf(passwordField.getPassword());
				StringBuilder sb = new StringBuilder();
				try {
					Connection c = jdbc.getConnection();

					String sql = "select * from idAndPassword where id=? and password=?";

					PreparedStatement pst = c.prepareStatement(sql);
					pst.setString(1, id);
					pst.setString(2, password);

					ResultSet rs = pst.executeQuery();

					if (id.equals("")) {
						sb.append("UserID is empty\n");
					}
					if (password.equals("")) {
						sb.append("Password is empty");
					}
					if (sb.length() > 0) {
						JOptionPane.showMessageDialog(frame, sb.toString(), "Errol", JOptionPane.ERROR_MESSAGE);
						;
						return;
					}

					if (rs.next()) {
						dispose();
						new QLSVView();
					} else {
						JOptionPane.showMessageDialog(frame, "Invalid userID or password!", "Invalid",
								JOptionPane.ERROR_MESSAGE);
					}

					jdbc.closeConnection(c);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}

		});
		btn_login.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_login.setBounds(94, 123, 84, 29);
		contentPane.add(btn_login);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setResizable(false);
		this.setVisible(true);
	}
}
