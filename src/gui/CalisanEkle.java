package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.DropMode;

public class CalisanEkle {

	DefaultTableModel modelim = new DefaultTableModel();
	Object[] kolonlar = { "NO ", "TC/ID ", "AD SOYAD", "PAROLA" };
	Object[] satirlar = new Object[4];

	Baglanti bag = new Baglanti();

	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textTC;
	private JTextField textAd;
	private JFrame frame;
	private JTextField textParola;
	private JTextField textNumarasi;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalisanEkle window = new CalisanEkle();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CalisanEkle() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("ÇALIÞAN EKLEME LÝSTELEME GÜNCELLEME");
		frame.getContentPane().setBackground(Color.RED);
		frame.setBounds(50, 100, 1250, 560);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textTC = new JTextField();
		textTC.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		textTC.setBounds(102, 459, 250, 20);
		frame.getContentPane().add(textTC);
		textTC.setColumns(10);

		textAd = new JTextField();
		textAd.setColumns(10);
		textAd.setBounds(565, 458, 250, 20);
		frame.getContentPane().add(textAd);

		table = new JTable();
		table.setModel(modelim);
		modelim.setColumnIdentifiers(kolonlar);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 45, 1205, 404);

		frame.getContentPane().add(scrollPane);

		JButton btnListele = new JButton("LÝSTELE / GÜNCELLE");
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelim.setRowCount(0);

				String sorgu2 = "UPDATE calisanlar set tc='" + textTC.getText() + " ',isim='" + textAd.getText()
						+ " ',parola='" + textParola.getText() + "' where numarasi=" + textNumarasi.getText();

				bag.update(sorgu2);

				String sorgu = "SELECT * FROM calisanlar;";
				ResultSet rs = bag.yap(sorgu);

				try {
					while (rs.next()) {
						satirlar[0] = rs.getString("numarasi");
						satirlar[1] = rs.getString("tc");
						satirlar[2] = rs.getString("isim");
						satirlar[3] = rs.getString("parola");

						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

				table.setModel(modelim);

			}

		});

		btnListele.setBounds(996, 489, 164, 23);
		frame.getContentPane().add(btnListele);

		JLabel lblTC = new JLabel("TC / ID:");
		lblTC.setForeground(Color.WHITE);
		lblTC.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		lblTC.setBounds(38, 462, 54, 14);
		frame.getContentPane().add(lblTC);

		JLabel labelAd = new JLabel("ADI SOYADI: ");
		labelAd.setForeground(Color.WHITE);
		labelAd.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		labelAd.setBounds(466, 462, 89, 14);
		frame.getContentPane().add(labelAd);

		JLabel labelParola = new JLabel("PAROLA: ");
		labelParola.setForeground(Color.WHITE);
		labelParola.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		labelParola.setBounds(862, 463, 70, 14);
		frame.getContentPane().add(labelParola);

		JButton btnYeniKayit = new JButton("YENi KAYIT");
		btnYeniKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sorgu3 = "INSERT INTO calisanlar(tc,isim,parola) VALUES ('" + textTC.getText() + "','"
						+ textAd.getText() + "','" + textParola.getText() + "')";

				bag.ekle(sorgu3);

			}
		});
		btnYeniKayit.setBounds(800, 489, 164, 23);
		frame.getContentPane().add(btnYeniKayit);

		textParola = new JTextField();
		textParola.setBounds(942, 458, 250, 21);
		frame.getContentPane().add(textParola);
		textParola.setColumns(10);

		textNumarasi = new JTextField();
		textNumarasi.setBounds(112, 490, 147, 22);
		frame.getContentPane().add(textNumarasi);
		textNumarasi.setColumns(10);
		textNumarasi.setVisible(false);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textNumarasi.setText(modelim.getValueAt(table.getSelectedRow(), 0).toString());
				textTC.setText(modelim.getValueAt(table.getSelectedRow(), 1).toString());
				textAd.setText(modelim.getValueAt(table.getSelectedRow(), 2).toString());
				textParola.setText(modelim.getValueAt(table.getSelectedRow(), 3).toString());

			}
		});

		frame.setVisible(true);

	}
}
