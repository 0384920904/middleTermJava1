package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.jdbc;

import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField password_new;
	private JPasswordField password_confirm;

	public ChangePassword() {
		URL url=ChangePassword.class.getResource("unlock-icon.png");
		Image image=Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage(image);
		this.setTitle("Change password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label label_1 = new Label("New password");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_1.setBounds(22, 25, 116, 31);
		contentPane.add(label_1);

		JButton btn_save = new JButton("Save");
		btn_save.setFocusable(false);
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPass = String.valueOf(password_new.getPassword());
				String confirmPass = String.valueOf(password_confirm.getPassword());
				StringBuilder sb = new StringBuilder();
				if (newPass.equals("")) {
					sb.append("New password is empty \n");
				}else if (confirmPass.equals("")) {
					sb.append("Confirm new password");
				}
				if (sb.length() > 0) {
					JOptionPane.showMessageDialog(contentPane, sb.toString(), "Errol", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (newPass.equals(confirmPass)) {
					try {
						Connection connection = jdbc.getConnection();

						String sql = "update idAndPassword set password=? where id='hoang'";

						PreparedStatement pst = connection.prepareStatement(sql);
						pst.setString(1, confirmPass);

						pst.executeUpdate();

						JOptionPane.showMessageDialog(contentPane, "     Password has been successfully change!","Successfully",JOptionPane.DEFAULT_OPTION);

						jdbc.closeConnection(connection);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Password does not match!", "Errol",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_save.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_save.setBounds(77, 126, 85, 31);
		contentPane.add(btn_save);

		JButton btn_cancel = new JButton("Cancel");
		btn_cancel.setFocusable(false);
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_cancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_cancel.setBounds(186, 126, 92, 31);
		contentPane.add(btn_cancel);

		password_new = new JPasswordField();
		password_new.setFont(new Font("Tahoma", Font.PLAIN, 14));
		password_new.setBounds(165, 25, 169, 31);
		contentPane.add(password_new);

		Label label_1_1 = new Label("Confirm password");
		label_1_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_1_1.setBounds(20, 78, 143, 31);
		contentPane.add(label_1_1);

		password_confirm = new JPasswordField();
		password_confirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		password_confirm.setBounds(165, 78, 169, 31);
		contentPane.add(password_confirm);
		this.setLocationRelativeTo(null);
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
