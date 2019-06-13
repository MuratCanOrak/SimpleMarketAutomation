package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(Giris.getSirketAdi());
		frame.getContentPane().setBackground(Color.RED);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(-8, 0, 1650, 750);
		frame.getContentPane().setLayout(null);

		JButton btnIadeListeleme = new JButton("ÝADE LÝSTELEME");
		btnIadeListeleme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ListelemeIade iade = new ListelemeIade();
			}
		});
		btnIadeListeleme.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		btnIadeListeleme.setBounds(89, 70, 319, 51);
		frame.getContentPane().add(btnIadeListeleme);

		JButton btnSatListeleme = new JButton("SATIÞ LÝSTELEME");
		btnSatListeleme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ListelemeSatis satis = new ListelemeSatis();
			}
		});
		btnSatListeleme.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		btnSatListeleme.setBounds(89, 152, 319, 51);
		frame.getContentPane().add(btnSatListeleme);

		JButton btnUrunListeleme = new JButton("ÜRÜN LÝSTELEME");
		btnUrunListeleme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ListelemeUrunler urun = new ListelemeUrunler();
			}
		});
		btnUrunListeleme.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		btnUrunListeleme.setBounds(89, 232, 319, 51);
		frame.getContentPane().add(btnUrunListeleme);

		JButton btnSatisEkrani = new JButton("SATIÞ EKRANI ");
		btnSatisEkrani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SatisEkrani sat = new SatisEkrani();
			}
		});
		btnSatisEkrani.setBackground(Color.GREEN);
		btnSatisEkrani.setForeground(Color.BLUE);
		btnSatisEkrani.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		btnSatisEkrani.setBounds(89, 528, 319, 51);
		frame.getContentPane().add(btnSatisEkrani);

		JButton btnCalisanEkle = new JButton("ÇALIÞAN EKLE");
		btnCalisanEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CalisanEkle calisanEkle = new CalisanEkle();
			}
		});
		btnCalisanEkle.setForeground(Color.GREEN);
		btnCalisanEkle.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		btnCalisanEkle.setBounds(89, 377, 319, 51);
		frame.getContentPane().add(btnCalisanEkle);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Giris.class.getResource("/image/loading.gif")));
		label.setBounds(456, 152, 640, 640);
		// label.setVisible(false);
		frame.getContentPane().add(label);

		frame.setVisible(true);

	}
}
