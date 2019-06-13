package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JLabel;
import java.awt.Font;

public class ListelemeUrunler {
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] kolonlar = { "BARKOD", "ADET", "URUN ADI", "ALIÞ FÝYATI", "SATIÞ FÝYATI" };
	Object[] satirlar = new Object[5];

	Baglanti bag = new Baglanti();
	FileOutputStream fos;
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textid;
	private JTextField textAdet;
	private JTextField textUrunAdi;
	private JTextField textAlisFiyati;
	private JTextField textSatisFiyati;
	private JLabel lblStok;
	private JLabel lblkckOlan;
	private JTextField textStok;
	private JButton btnExceleAt;
	private Workbook wb;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListelemeUrunler window = new ListelemeUrunler();
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
	public ListelemeUrunler() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("ÜRÜNLER");
		frame.getContentPane().setBackground(Color.RED);
		frame.setBounds(50, 100, 1250, 560);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textid = new JTextField();
		textid.setBounds(10, 458, 231, 20);
		frame.getContentPane().add(textid);
		textid.setColumns(10);

		textAdet = new JTextField();
		textAdet.setColumns(10);
		textAdet.setBounds(251, 458, 231, 20);
		frame.getContentPane().add(textAdet);

		textUrunAdi = new JTextField();
		textUrunAdi.setColumns(10);
		textUrunAdi.setBounds(492, 458, 231, 20);
		frame.getContentPane().add(textUrunAdi);

		textAlisFiyati = new JTextField();
		textAlisFiyati.setColumns(10);
		textAlisFiyati.setBounds(733, 458, 231, 20);
		frame.getContentPane().add(textAlisFiyati);

		textSatisFiyati = new JTextField();
		textSatisFiyati.setColumns(10);
		textSatisFiyati.setBounds(974, 458, 231, 20);
		frame.getContentPane().add(textSatisFiyati);

		table = new JTable();
		table.setModel(modelim);
		modelim.setColumnIdentifiers(kolonlar);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 45, 1205, 404);

		frame.getContentPane().add(scrollPane);

		JButton btnListele = new JButton("LÝSTELE / GÜNCELLE");
		btnListele.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelim.setRowCount(0);

				String sorgu2 = "UPDATE urunlistesi set adet=" + textAdet.getText() + ",urunAdi='"
						+ textUrunAdi.getText() + "',alisFiyati=" + textAlisFiyati.getText() + ",satisFiyati="
						+ textSatisFiyati.getText() + " where idurunListesi=" + textid.getText();
				bag.update(sorgu2);

				String sorgu = "SELECT * FROM urunlistesi;";
				ResultSet rs = bag.yap(sorgu);

				try {
					while (rs.next()) {
						satirlar[0] = rs.getString("idurunListesi");
						satirlar[1] = rs.getInt("adet");
						satirlar[2] = rs.getString("urunAdi");
						satirlar[3] = rs.getDouble("alisFiyati");
						satirlar[4] = rs.getDouble("satisFiyati");
						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

				table.setModel(modelim);

			}

		});

		btnListele.setBounds(996, 489, 209, 23);
		frame.getContentPane().add(btnListele);

		JButton btnYeniKayit = new JButton("YENi KAYIT");
		btnYeniKayit.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnYeniKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sorgu3 = "INSERT INTO urunlistesi(idurunListesi,adet,urunAdi,alisFiyati,satisFiyati) VALUES ('"
						+ textid.getText() + "','" + textAdet.getText() + "','" + textUrunAdi.getText() + "','"
						+ textAlisFiyati.getText() + "','" + textSatisFiyati.getText() + "')";

				// System.out.println("sorgu 3: "+sorgu3);
				bag.ekle(sorgu3);

			}
		});
		btnYeniKayit.setBounds(800, 489, 164, 23);
		frame.getContentPane().add(btnYeniKayit);

		lblStok = new JLabel("STOK");
		lblStok.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		lblStok.setBounds(20, 490, 58, 20);
		frame.getContentPane().add(lblStok);

		lblkckOlan = new JLabel("'DEN K\u00DC\u00C7\u00DCK OLANLARI");
		lblkckOlan.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		lblkckOlan.setBounds(121, 490, 209, 20);
		frame.getContentPane().add(lblkckOlan);

		textStok = new JTextField();
		textStok.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		textStok.setBounds(75, 490, 36, 20);
		frame.getContentPane().add(textStok);
		textStok.setColumns(10);

		JButton btnGetir = new JButton("GET\u0130R");
		btnGetir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modelim.setRowCount(0);

				String sorgu2 = "SELECT * FROM urunlistesi WHERE adet<" + textStok.getText();
				ResultSet rs = bag.yap(sorgu2);

				try {
					while (rs.next()) {
						satirlar[0] = rs.getString("idurunListesi");
						satirlar[1] = rs.getInt("adet");
						satirlar[2] = rs.getString("urunAdi");
						satirlar[3] = rs.getDouble("alisFiyati");
						satirlar[4] = rs.getDouble("satisFiyati");
						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

				table.setModel(modelim);

			}
		});
		btnGetir.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnGetir.setBounds(326, 489, 107, 23);
		frame.getContentPane().add(btnGetir);

		btnExceleAt = new JButton("EXCEL'E AKTAR");
		btnExceleAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeToExcel();

			}
		});
		btnExceleAt.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnExceleAt.setBounds(606, 489, 164, 23);
		frame.getContentPane().add(btnExceleAt);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textid.setText(modelim.getValueAt(table.getSelectedRow(), 0).toString());
				textAdet.setText(modelim.getValueAt(table.getSelectedRow(), 1).toString());
				textUrunAdi.setText(modelim.getValueAt(table.getSelectedRow(), 2).toString());
				textAlisFiyati.setText(modelim.getValueAt(table.getSelectedRow(), 3).toString());
				textSatisFiyati.setText(modelim.getValueAt(table.getSelectedRow(), 4).toString());
			}
		});
		frame.setVisible(true);
	}

	public String getCellVal(int x, int y) {
		return modelim.getValueAt(x, y).toString();
	}

	public void writeToExcel() {

		wb = new HSSFWorkbook();
		Sheet ws = wb.createSheet();

		TreeMap<String, Object[]> data = new TreeMap<>();
		data.put("0", new Object[] { modelim.getColumnName(0), modelim.getColumnName(1), modelim.getColumnName(2),
				modelim.getColumnName(3), modelim.getColumnName(4) });
		for (int i = 1; i <= modelim.getRowCount(); i++) {
			System.out.println("" + i);

			data.put("" + i, new Object[] { getCellVal(i, 0), getCellVal(i, 1), getCellVal(i, 2), getCellVal(i, 3),
					getCellVal(i, 4) });
		}

		Set<String> ids = data.keySet();
		Row row;
		int RowID = 0;

		for (String key : ids) {
			row = ws.createRow(RowID++);

			Object[] values = data.get(key);

			int cellID = 0;
			for (Object o : values) {
				Cell cell = row.createCell(cellID++);
				cell.setCellValue(o.toString());
			}
		}

		try {
			fos = new FileOutputStream(
					new File("C:\\Users\\Murat Can ORAK\\Desktop\\JavaProjelerim\\MarketOtomasyonu//workbook.xls")); // EXCELL
																														// oluþturuldu
			wb.write(fos);
			fos.close();
		} catch (FileNotFoundException e2) {

			e2.printStackTrace();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
