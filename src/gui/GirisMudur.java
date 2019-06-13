package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GirisMudur {


	JFrame frame;
	private JTextField textTC;
	private JPasswordField pswKasiyer;
	Baglanti bag = new Baglanti();

	public JTextField getTextTC() {
		return textTC;
	}


	public void setTextTC(JTextField textTC) {
		this.textTC = textTC;
	}


	public JPasswordField getPswKasiyer() {
		return pswKasiyer;
	}


	public void setPswKasiyer(JPasswordField pswKasiyer) {
		this.pswKasiyer = pswKasiyer;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisMudur window = new GirisMudur();
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
	public GirisMudur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		//String sorgu = "Select * from calisanlar where tc='" + getTextTC().getText() + "'and parola='" + getPswKasiyer().getText().toString()+"'" ;
		
		
		
		frame = new JFrame("ORAKLAR MARKET");
		frame.getContentPane().setBackground(Color.RED);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(-8, 0, 1650,750);
		frame.getContentPane().setLayout(null);
		//frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
		JLabel lblOraklarMarket = new JLabel("ORAKLAR MARKET");
		lblOraklarMarket.setHorizontalAlignment(SwingConstants.CENTER);
		lblOraklarMarket.setForeground(Color.WHITE);
		lblOraklarMarket.setFont(new Font("Arial Black", Font.BOLD, 50));
		lblOraklarMarket.setBounds(400, 122, 640, 70);
		frame.getContentPane().add(lblOraklarMarket);
		
		JLabel lblNewLabel = new JLabel("MUDUR TC      : ");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblNewLabel.setBounds(410, 297, 268, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblKasiyerSifre = new JLabel("MUDUR \u015E\u0130FRE : ");
		lblKasiyerSifre.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblKasiyerSifre.setBounds(410, 380, 268, 38);
		frame.getContentPane().add(lblKasiyerSifre);
		
		textTC = new JTextField();
		textTC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		textTC.setFont(new Font("Arial Black", Font.PLAIN, 25));
		textTC.setBounds(716, 297, 268, 38);
		frame.getContentPane().add(textTC);
		textTC.setColumns(10);
		
		pswKasiyer = new JPasswordField();
		pswKasiyer.setFont(new Font("Arial Black", Font.PLAIN, 25));
		pswKasiyer.setBounds(716, 380, 268, 38);
		frame.getContentPane().add(pswKasiyer);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Giris.class.getResource("/image/loading.gif")));
		label.setBounds(410, 203, 640, 640);
		//label.setVisible(false);
		frame.getContentPane().add(label);
	
		
		JButton btnGiriYap = new JButton("G�R�� YAP");
		btnGiriYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	label.setVisible(true);
				String kullaniciTc = textTC.getText();
				String kullaniciSifre = pswKasiyer.getText();
				
				
				String sorgu = "Select * from genel_mudur where tc='" + getTextTC().getText() + "'and parola='" + getPswKasiyer().getText().toString()+"'" ;
				System.out.println("sorgu: "+ sorgu);
				ResultSet rs = bag.yap(sorgu);
	
				try {
					while (rs.next()) {
						if (kullaniciTc.equals(rs.getString("tc")) && kullaniciSifre.equals(rs.getString("parola"))) {
							
							//	frame.setVisible(false);
									new SatisEkrani();
					
								}

					}
				} catch (SQLException e1) {

					e1.printStackTrace();
					System.out.println("hata");
					JOptionPane.showMessageDialog(null, "hata");

				}
				
				
				
			}
		});
		btnGiriYap.setBackground(new Color(30, 144, 255));
		btnGiriYap.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnGiriYap.setBounds(848, 494, 192, 38);
		frame.getContentPane().add(btnGiriYap);
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
