package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;

public class ListelemeY�netici {
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] kolonlar = { "TC", "�sim","Parola", "K�dem", "Adres", "Telefon", "Mail ","�ube ID" };
	Object[] satirlar = new Object[8];

	Baglanti bag = new Baglanti();

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListelemeY�netici window = new ListelemeY�netici();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListelemeY�netici() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Y�netici");
		frame.getContentPane().setBackground(Color.RED);
		frame.setBounds(50, 100, 1250, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 45, 1205, 405);
		frame.getContentPane().add(scrollPane_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(Color.BLUE);

		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setModel(modelim);
		modelim.setColumnIdentifiers(kolonlar);


				String sorgu = "select * from yonetici ";
				ResultSet rs = bag.yap(sorgu);
				try {
					while (rs.next()) {
						satirlar[0] = rs.getString("tc");
						satirlar[1] = rs.getString("isim");
						satirlar[2] = rs.getString("parola");
						satirlar[3] = rs.getString("k�dem");
						satirlar[4] = rs.getString("adres");
						satirlar[5] = rs.getString("telefon");
						satirlar[6] = rs.getString("mail");
						satirlar[7] = rs.getString("subeId");
						modelim.addRow(satirlar);
						
				

					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

				table.setModel(modelim);
				
				JLabel baslikYonetici = new JLabel("Y\u00D6NET\u0130C\u0130LER");
				baslikYonetici.setForeground(Color.WHITE);
				baslikYonetici.setFont(new Font("Tahoma", Font.BOLD, 22));
				baslikYonetici.setBounds(535, 11, 164, 23);
				frame.getContentPane().add(baslikYonetici);
				

	}
}
