package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class ListelemeIade {
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] kolonlar = { "BARKOD", "ADI", "Ýade Adet", "Alis Fiyati", "Satis Fiyati", "Toplam ", "Tarih" };
	Object[] satirlar = new Object[7];

	Baglanti bag = new Baglanti();

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblSatlanrnler;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListelemeIade window = new ListelemeIade();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ListelemeIade() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Ýade Edilen Ürünler");
		frame.getContentPane().setBackground(Color.RED);
		frame.setBounds(50, 100, 1250, 500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	
		
		table = new JTable();
		table.setModel(modelim);
		modelim.setColumnIdentifiers(kolonlar);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 45, 1205, 404);

		frame.getContentPane().add(scrollPane);
		


				String sorgu = "select * from iadeurun ";
				ResultSet rs = bag.yap(sorgu);
				try {
					while (rs.next()) {
						satirlar[0] = rs.getString("idiadeurun");
						satirlar[1] = rs.getString("urunAdi");
						satirlar[2] = rs.getString("satilanAdet");
						satirlar[3] = rs.getString("alisFiyati");
						satirlar[4] = rs.getString("satisFiyati");
						satirlar[5] = rs.getString("toplamFiyati");
						satirlar[6] = rs.getString("date");
						modelim.addRow(satirlar);
						
						
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

		table.setModel(modelim);
		
		lblSatlanrnler = new JLabel("ÝADE ALINAN ÜRÜNLER");
		lblSatlanrnler.setForeground(Color.WHITE);
		lblSatlanrnler.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblSatlanrnler.setBounds(461, 10, 310, 24);
		frame.getContentPane().add(lblSatlanrnler);
		
		
		
		
		
		
		frame.setVisible(true);
		
		
		
	}

}
