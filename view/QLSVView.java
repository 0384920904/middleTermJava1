package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Database.jdbc;
import Model.SinhVien;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Label;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuBar;
import java.awt.Dimension;

public class QLSVView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_id;
	private JTextField textField_hoVaTen;
	private JTextField textField_queQuan;
	private JTextField textField_Diem;
	ArrayList<SinhVien> dssv;
	DefaultTableModel tableModel;
	int row;
	private String i1;
	private String i2;
	private String i3;
	private String i4;
	private float i5;
	private JTextField textField_idFind;
	private JButton btn_ok;
	private JButton btn_delete;
	private JButton btn_update;
	private JButton btn_add;
	private JButton btn_cancel;
	private Label label_3;
	private JComboBox<String> comboBox_sex;
	private DefaultTableCellRenderer tableCellRenderer;
	private JScrollPane scrollPane;

	public void hienThiSinhVien() {
		tableModel.setRowCount(0);
		for (int i = 0; i < dssv.size(); i++) {
			Object[] obj = { dssv.get(i).getId(), dssv.get(i).getHoVaTen(), dssv.get(i).getQueQuan(),
					dssv.get(i).getSex(), dssv.get(i).getDiem() };
			tableModel.addRow(obj);
		}
	}

	public QLSVView() {
		this.init();
		URL url = QLSVView.class.getResource("text-icon.png");
		Image image = Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage(image);
		this.setTitle("Student Management");
		this.dssv = new ArrayList<>();
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "Location", "Sex", "Mark" });
		this.selectAll();
		table.setModel(tableModel);

		JSeparator separator = new JSeparator();
		separator.setBounds(614, 524, 396, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(40, 96, 997, 2);
		contentPane.add(separator_1);

		JButton btn_changePass = new JButton("Change password");
		btn_changePass.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLSVView.class.getResource("unlock-icon.png"))));
		btn_changePass.setFocusable(false);
		btn_changePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword();
			}
		});
		btn_changePass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_changePass.setBounds(793, 29, 211, 40);
		contentPane.add(btn_changePass);

		Label label_1 = new Label("ID");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_1.setBounds(654, 215, 90, 38);
		contentPane.add(label_1);

		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_id.setColumns(10);
		textField_id.setBounds(750, 215, 223, 38);
		contentPane.add(textField_id);

		textField_idFind = new JTextField();
		textField_idFind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_idFind.setColumns(10);
		textField_idFind.setBounds(96, 30, 126, 38);
		contentPane.add(textField_idFind);

		Label label_1_1 = new Label("ID");
		label_1_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_1_1.setBounds(45, 31, 46, 38);
		contentPane.add(label_1_1);

		JButton btn_find = new JButton("Search");
		btn_find.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLSVView.class.getResource("Search-icon.png"))));
		btn_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection c = jdbc.getConnection();

					String sql = "select * from SinhVien1 where id=?";

					PreparedStatement pst = c.prepareStatement(sql);
					pst.setString(1, textField_idFind.getText());

					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						hienThiSinhVien();
						Object obj[] = { rs.getString("id"), rs.getString("hoVaTen"), rs.getString("queQuan"),
								rs.getString("gioiTinh"), rs.getFloat("diem") };
						tableModel.addRow(obj);
					}
					jdbc.closeConnection(c);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btn_find.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_find.setFocusable(false);
		btn_find.setBounds(236, 29, 124, 40);
		contentPane.add(btn_find);

		JButton btn_reset = new JButton("Reset");
		btn_reset.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLSVView.class.getResource("Refresh-icon.png"))));
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienThiSinhVien();
				selectAll();
				textField_idFind.setText("");
				refresh();
				textField_id.setEditable(false);
				textField_hoVaTen.setEditable(false);
				textField_queQuan.setEditable(false);
				comboBox_sex.setEnabled(false);
				textField_Diem.setEditable(false);
				btn_update.setEnabled(true);
				btn_delete.setEnabled(true);
				btn_ok.setEnabled(false);
				btn_cancel.setEnabled(false);
			}
		});
		btn_reset.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_reset.setFocusable(false);
		btn_reset.setBounds(370, 29, 113, 40);
		contentPane.add(btn_reset);

		btn_add = new JButton(" Add");
		btn_add.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLSVView.class.getResource("Add-icon.png"))));
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i1 = "";
				textField_id.setEditable(true);
				textField_hoVaTen.setEditable(true);
				textField_queQuan.setEditable(true);
				textField_Diem.setEditable(true);
				comboBox_sex.setEnabled(true);
				btn_update.setEnabled(false);
				btn_delete.setEnabled(false);
				btn_ok.setEnabled(true);
				btn_cancel.setEnabled(true);
				refresh();
				table.setEnabled(false);
				hienThiSinhVien();
				selectAll();
			}
		});
		btn_add.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_add.setFocusable(false);
		btn_add.setBounds(614, 134, 106, 48);
		contentPane.add(btn_add);

		btn_cancel = new JButton("Cancel");
		btn_cancel.setEnabled(false);
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienThiSinhVien();
				selectAll();
				btn_ok.setEnabled(false);
				btn_cancel.setEnabled(false);
				textField_id.setEditable(false);
				textField_hoVaTen.setEditable(false);
				textField_queQuan.setEditable(false);
				comboBox_sex.setEnabled(false);
				textField_Diem.setEditable(false);
				btn_add.setEnabled(true);
				btn_update.setEnabled(true);
				btn_delete.setEnabled(true);
				table.setEnabled(true);
				refresh();
			}
		});
		btn_cancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_cancel.setFocusable(false);
		btn_cancel.setBounds(793, 545, 105, 38);
		contentPane.add(btn_cancel);

		label_3 = new Label("Sex");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_3.setBounds(654, 395, 90, 38);
		contentPane.add(label_3);

		comboBox_sex = new JComboBox<String>();
		comboBox_sex.setFocusable(false);
		comboBox_sex.setEnabled(false);
		comboBox_sex.addItem("");
		comboBox_sex.addItem("Male");
		comboBox_sex.addItem("Female");
		comboBox_sex.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_sex.setBounds(750, 395, 223, 38);
		contentPane.add(comboBox_sex);

		tableCellRenderer = new DefaultTableCellRenderer();
		tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableCellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(5);

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

	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 656);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				btn_cancel.setEnabled(true);
				row = table.getSelectedRow();
				textField_id.setText(tableModel.getValueAt(row, 0).toString());
				textField_hoVaTen.setText(tableModel.getValueAt(row, 1).toString());
				textField_queQuan.setText(tableModel.getValueAt(row, 2).toString());
				comboBox_sex.setSelectedItem(tableModel.getValueAt(row, 3).toString());
				textField_Diem.setText(tableModel.getValueAt(row, 4).toString());
				i1 = textField_id.getText();
				i2 = textField_hoVaTen.getText();
				i3 = textField_queQuan.getText();
				i4 = comboBox_sex.getSelectedItem().toString();
				i5 = Float.parseFloat(textField_Diem.getText());
			}
		});

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(37, 125, 537, 458);
		contentPane.add(scrollPane);

		Label label = new Label("Name");
		label.setFont(new Font("Dialog", Font.PLAIN, 18));
		label.setBounds(654, 275, 90, 38);
		contentPane.add(label);

		Label label_1 = new Label("Location");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_1.setBounds(654, 335, 90, 38);
		contentPane.add(label_1);

		Label label_2 = new Label("Mark");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_2.setBounds(654, 455, 90, 38);
		contentPane.add(label_2);

		textField_hoVaTen = new JTextField();
		textField_hoVaTen.setEditable(false);
		textField_hoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_hoVaTen.setBounds(750, 275, 223, 38);
		contentPane.add(textField_hoVaTen);
		textField_hoVaTen.setColumns(10);

		textField_queQuan = new JTextField();
		textField_queQuan.setEditable(false);
		textField_queQuan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_queQuan.setColumns(10);
		textField_queQuan.setBounds(750, 335, 223, 38);
		contentPane.add(textField_queQuan);

		textField_Diem = new JTextField();
		textField_Diem.setEditable(false);
		textField_Diem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_Diem.setColumns(10);
		textField_Diem.setBounds(750, 455, 223, 38);
		contentPane.add(textField_Diem);

		btn_ok = new JButton("OK");
		btn_ok.setEnabled(false);
		btn_ok.setFocusable(false);
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_id.getText().equals("") || textField_hoVaTen.getText().equals("")
						|| textField_queQuan.getText().equals("") || textField_Diem.getText().equals("")
						|| comboBox_sex.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Please enter all information!", "Errol",
							JOptionPane.ERROR_MESSAGE);

				} else if (!isNum() && !textField_Diem.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Invalid mark!", "Invalid", JOptionPane.ERROR_MESSAGE);
				} else {
					btn_ok.setEnabled(false);
					btn_cancel.setEnabled(false);
					delete();
					insert();
					if (i1.equals("")) {
						
						int a = table.getRowCount();
						int count = 0;
						for (int i = 0; i < a; i++) {
							String id_test = tableModel.getValueAt(i, 0).toString();
							if (textField_id.getText().equals(id_test)) {
								count++;
							}
						}
						if (count != 0) {
							JOptionPane.showMessageDialog(contentPane, "        ID already exists!", "Invalid",
									JOptionPane.ERROR_MESSAGE);
							btn_ok.setEnabled(true);
							btn_cancel.setEnabled(true);
						} else {
							hienThiSinhVien();
							selectAll();
							JOptionPane.showMessageDialog(contentPane, "                       Add successfully!",
									"Successful", JOptionPane.DEFAULT_OPTION);
							refresh();
							textField_id.setEditable(false);
							textField_hoVaTen.setEditable(false);
							textField_queQuan.setEditable(false);
							comboBox_sex.setEnabled(false);
							textField_Diem.setEditable(false);
							btn_add.setEnabled(true);
							btn_update.setEnabled(true);
							btn_delete.setEnabled(true);
							btn_add.setEnabled(true);
							table.setEnabled(true);
						}

					} else {
						hienThiSinhVien();
						selectAll();
						JOptionPane.showMessageDialog(contentPane, "                       Update successfully!",
								"Successful", JOptionPane.DEFAULT_OPTION);
						refresh();
						textField_id.setEditable(false);
						textField_hoVaTen.setEditable(false);
						textField_queQuan.setEditable(false);
						comboBox_sex.setEnabled(false);
						textField_Diem.setEditable(false);
						btn_add.setEnabled(true);
						btn_update.setEnabled(true);
						btn_delete.setEnabled(true);
						btn_add.setEnabled(true);
						table.setEnabled(true);
					}

				}
			}
		});
		btn_ok.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_ok.setBounds(682, 545, 90, 38);
		contentPane.add(btn_ok);

		btn_delete = new JButton("Delete");
		btn_delete.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLSVView.class.getResource("Delete-icon.png"))));
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( 	!textField_id.getText().equals("")) {
					int luaChon = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Delete",
							JOptionPane.YES_NO_OPTION);
					if (luaChon == JOptionPane.YES_OPTION) {
						delete();
						hienThiSinhVien();
						selectAll();
						btn_add.setEnabled(true);
						JOptionPane.showMessageDialog(contentPane, "                      Delete successfully!",
								"Successful", JOptionPane.DEFAULT_OPTION);
						refresh();
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Choose an item to delete!", "!!!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_delete.setFocusable(false);
		btn_delete.setBounds(742, 134, 120, 48);
		contentPane.add(btn_delete);

		btn_update = new JButton("Update");
		btn_update.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLSVView.class.getResource("Wrench-icon.png"))));
		btn_update.setFocusable(false);
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField_id.getText().equals("")) {
					btn_ok.setEnabled(true);
					btn_cancel.setEnabled(true);
					textField_hoVaTen.setEditable(true);
					textField_queQuan.setEditable(true);
					comboBox_sex.setEnabled(true);
					textField_Diem.setEditable(true);
					btn_delete.setEnabled(false);
					btn_add.setEnabled(false);
					table.setEnabled(false);
					hienThiSinhVien();
					selectAll();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Choose an item to update!", "!!!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_update.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_update.setBounds(884, 134, 126, 48);
		contentPane.add(btn_update);

	}

	public void refresh() {
		this.textField_id.setText("");
		this.textField_hoVaTen.setText("");
		this.textField_queQuan.setText("");
		this.comboBox_sex.setSelectedItem("");
		this.textField_Diem.setText("");
	}

	public void selectAll() {
		try {
			Connection c = jdbc.getConnection();

			String sql = "select * from SinhVien1";

			PreparedStatement pst = c.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Object obj[] = { rs.getString("id"), rs.getString("hoVaTen"), rs.getString("queQuan"),
						rs.getString("gioiTinh"), rs.getFloat("diem") };
				tableModel.addRow(obj);
			}
			;

			jdbc.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insert() {
		try {
			Connection connection = jdbc.getConnection();

			String sql = "insert into SinhVien1 values(?,?,?,?,?)";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, textField_id.getText());
			pst.setString(2, textField_hoVaTen.getText());
			pst.setString(3, textField_queQuan.getText());
			pst.setString(4, comboBox_sex.getSelectedItem().toString());
			pst.setFloat(5, Float.parseFloat(textField_Diem.getText()));

			pst.executeUpdate();

			jdbc.closeConnection(connection);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void delete() {
		try {
			Connection connection = jdbc.getConnection();

			String sql = "delete from SinhVien1 where id=? and hoVaTen=? and queQuan=? and gioiTinh=? and diem=?";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, i1);
			pst.setString(2, i2);
			pst.setString(3, i3);
			pst.setString(4, i4);
			pst.setFloat(5, i5);

			pst.executeUpdate();

			jdbc.closeConnection(connection);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	public boolean isNum() {
		try {
			Float.parseFloat(textField_Diem.getText());
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
