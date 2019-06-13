package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListelemeSatis {
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] kolonlar = { "BARKOD", "ADI", "Satýlan Adet", "Alis Fiyati", "Satis Fiyati", "Toplam ", "Tarih" };
	Object[] satirlar = new Object[7];

	Baglanti bag = new Baglanti();

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblSatlanrnler;
	private JTextField textToplam;
	private double toplam2;
	private JTextField textYil;
	private JTextField textAy;
	private JTextField textGun;
	private JTextField textYil2;
	private JTextField textAy2;
	private JTextField textGun2;
	private JLabel lblBaslangicTarihi;
	private JLabel lblBitisTarihi;
	private JTextField textAralikToplam;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListelemeSatis window = new ListelemeSatis();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListelemeSatis() {
		
	
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Satýlan Ürünler");
		frame.getContentPane().setBackground(Color.RED);
		frame.setBounds(50, 100, 1250, 571);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		table = new JTable();
		table.setModel(modelim);
		modelim.setColumnIdentifiers(kolonlar);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 45, 1205, 404);

		frame.getContentPane().add(scrollPane);
		
		
		
		
		
		JLabel lblYil = new JLabel("YIL:");
		lblYil.setHorizontalAlignment(SwingConstants.LEFT);
		lblYil.setForeground(Color.WHITE);
		lblYil.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		lblYil.setBounds(170, 468, 40, 17);
		frame.getContentPane().add(lblYil);
		
		textYil = new JTextField();
		textYil.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		textYil.setColumns(10);
		textYil.setBounds(208, 466, 61, 20);
		frame.getContentPane().add(textYil);
		
		JLabel lblAy = new JLabel("AY:");
		lblAy.setForeground(Color.WHITE);
		lblAy.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		lblAy.setBounds(303, 468, 32, 17);
		frame.getContentPane().add(lblAy);
		
		textAy = new JTextField();
		textAy.setColumns(10);
		textAy.setBounds(345, 465, 32, 21);
		frame.getContentPane().add(textAy);
		
		JLabel lblGun = new JLabel("G\u00DCN:");
		lblGun.setForeground(Color.WHITE);
		lblGun.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		lblGun.setBounds(397, 468, 55, 16);
		frame.getContentPane().add(lblGun);
		
		textGun = new JTextField();
		textGun.setColumns(10);
		textGun.setBounds(441, 465, 40, 21);
		frame.getContentPane().add(textGun);
		
		JLabel lblYil2 = new JLabel("YIL:");
		lblYil2.setHorizontalAlignment(SwingConstants.LEFT);
		lblYil2.setForeground(Color.WHITE);
		lblYil2.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		lblYil2.setBounds(170, 503, 40, 17);
		frame.getContentPane().add(lblYil2);
		
		textYil2 = new JTextField();
		textYil2.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		textYil2.setColumns(10);
		textYil2.setBounds(208, 501, 61, 20);
		frame.getContentPane().add(textYil2);
		
		JLabel lblAy2 = new JLabel("AY:");
		lblAy2.setForeground(Color.WHITE);
		lblAy2.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		lblAy2.setBounds(303, 503, 32, 17);
		frame.getContentPane().add(lblAy2);
		
		textAy2 = new JTextField();
		textAy2.setColumns(10);
		textAy2.setBounds(345, 500, 32, 21);
		frame.getContentPane().add(textAy2);
		
		JLabel lblGun2 = new JLabel("G\u00DCN:");
		lblGun2.setForeground(Color.WHITE);
		lblGun2.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		lblGun2.setBounds(397, 503, 55, 16);
		frame.getContentPane().add(lblGun2);
		
		textGun2 = new JTextField();
		textGun2.setColumns(10);
		textGun2.setBounds(441, 500, 40, 21);
		frame.getContentPane().add(textGun2);
		
		lblBaslangicTarihi = new JLabel("BAÞLANGIÇ TARÝHÝ");
		lblBaslangicTarihi.setHorizontalAlignment(SwingConstants.LEFT);
		lblBaslangicTarihi.setForeground(Color.WHITE);
		lblBaslangicTarihi.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		lblBaslangicTarihi.setBounds(20, 468, 240, 17);
		frame.getContentPane().add(lblBaslangicTarihi);
		
		lblBitisTarihi = new JLabel("BÝTÝÞ TARÝHÝ");
		lblBitisTarihi.setHorizontalAlignment(SwingConstants.LEFT);
		lblBitisTarihi.setForeground(Color.WHITE);
		lblBitisTarihi.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		lblBitisTarihi.setBounds(20, 501, 240, 17);
		frame.getContentPane().add(lblBitisTarihi);

		
		
		
		
		
		

		table.setModel(modelim);

		lblSatlanrnler = new JLabel("SATILAN ÜRÜNLER");
		lblSatlanrnler.setForeground(Color.WHITE);
		lblSatlanrnler.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblSatlanrnler.setBounds(503, 10, 224, 24);
		frame.getContentPane().add(lblSatlanrnler);

		JLabel lblToplamFiyat = new JLabel("TOPLAM FÝYAT:");
		lblToplamFiyat.setForeground(Color.WHITE);
		lblToplamFiyat.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		lblToplamFiyat.setBounds(792, 496, 209, 25);
		frame.getContentPane().add(lblToplamFiyat);

		textToplam = new JTextField();
		textToplam.setBounds(1011, 496, 126, 25);
		frame.getContentPane().add(textToplam);
		textToplam.setColumns(10);
		
		JButton btnSorgula = new JButton("SORGULA");
		btnSorgula.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnSorgula.setBackground(Color.WHITE);
		btnSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorgula();
			}
		});
		btnSorgula.setBounds(561, 478, 116, 36);
		frame.getContentPane().add(btnSorgula);
		
		JLabel lblAralikToplamFiyat = new JLabel("ARALIK TOPLAM F\u0130YAT:");
		lblAralikToplamFiyat.setForeground(Color.WHITE);
		lblAralikToplamFiyat.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		lblAralikToplamFiyat.setBounds(792, 460, 209, 25);
		frame.getContentPane().add(lblAralikToplamFiyat);
		
		textAralikToplam = new JTextField();
		textAralikToplam.setColumns(10);
		textAralikToplam.setBounds(1011, 460, 126, 25);
		frame.getContentPane().add(textAralikToplam);
		

		String sorgu2 = "select sum(toplamFiyati) from satilanurun ";
		ResultSet rs2 = bag.yap(sorgu2);

		try {
			if (rs2.next()) {
				textToplam.setText(Double.toString(rs2.getDouble(1)));
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		textYil.setText("2015");  textYil2.setText("2050"); 
		textAy.setText("01"); textAy2.setText("01");
		textGun.setText("01"); textGun2.setText("01");
		
		frame.setVisible(true);

	}
	
	public void sorgula () {
		
	
		String tarihAraligi= "WHERE DATE(date2) BETWEEN ' " + textYil.getText() + "-" +textAy.getText()+"-"+textGun.getText()+" ' "
				+ "and ' "+ textYil2.getText() + "-" + textAy2.getText()+ "-" +textGun2.getText()+" ' ";
		
		modelim.setRowCount(0);
		
		String sorgu ="SELECT * FROM satilanurun "+ tarihAraligi;

		ResultSet rs = bag.yap(sorgu);
		System.out.println(sorgu);
		
		int count = 0;
		try {
			
			while (rs.next()) {
				satirlar[0] = rs.getString("idsatilanUrun");
				satirlar[1] = rs.getString("urunAdi");
				satirlar[2] = rs.getString("satilanAdet");
				satirlar[3] = rs.getString("alisFiyati");
				satirlar[4] = rs.getString("satisFiyati");
				satirlar[5] = rs.getString("toplamFiyati");
				satirlar[6] = rs.getString("date2");
				modelim.addRow(satirlar);
				count++;
			}
			System.out.println(count);
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		
		
		
		String sorgu2 = "select sum(toplamFiyati) from satilanurun "+ tarihAraligi;
		ResultSet rs2 = bag.yap(sorgu2);
		
		try {
			if (rs2.next()) {
				textAralikToplam.setText(Double.toString(rs2.getDouble(1)));
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}
}
