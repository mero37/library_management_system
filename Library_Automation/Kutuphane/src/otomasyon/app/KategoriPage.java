package otomasyon.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import otomasyon.DAO.KategoriDAO;
import otomasyon.model.Kategori;
import otomasyon.service.KategoriService;

public class KategoriPage extends JFrame implements ActionListener  {
	
	KategoriDAO kategoriDAO=new KategoriDAO();
	KategoriService kategoriService=new KategoriService(kategoriDAO);
	
	Menu menu=new Menu();
	JScrollPane scrollp;
	JLabel lblkatList,lblfiltre,lblfiltrealan,lblduzenle,lbldurum,lblduzenlealan,lblkatAd,lblkatAd2,lblkatId,lblperAd;
	JTextField txtkatAd,txtkatAd2,txtkatId;
	JButton btnfiltre,btnekle,btnsil,btnguncelle,btntemizle,btncik;
	ImageIcon btntemizleres,btncikres;
    JTable j;
	
	public KategoriPage() {
		setTitle("Kategori İşlemleri");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		setLayout(null);
		setContentPane(new JLabel(new ImageIcon("src/otomasyon/app/bg2.jpg")));
		menu.kategori.setBackground(Color.DARK_GRAY);
		menu.kategori.setOpaque(true);
		menu.kategori.setForeground(Color.white);
		menu.mb.setSelected(menu.kategori);
		add(menu.mb);
		setJMenuBar(menu.mb);
		menu.anasayfa.addActionListener(this);
		menu.kategori.addActionListener(this);
		menu.personel.addActionListener(this);
		menu.yazar.addActionListener(this);
		menu.kitap.addActionListener(this);
		menu.uye.addActionListener(this);
		menu.uyeKitap.addActionListener(this);
		lblperAd=new JLabel(LoginPage.adSoyad);
		lblperAd.setBounds(1217,3,120,30);
		lblperAd.setBackground(Color.white);
		lblperAd.setForeground(Color.white);
		lblperAd.setOpaque(false);
		lblperAd.setFont(new Font("Courier",Font.TYPE1_FONT,14));
		lblperAd.setHorizontalAlignment(JLabel.CENTER);
		btncikres=new ImageIcon("src/otomasyon/app/exit.png");
		btncik=new JButton();
		btncik.setBounds(1328, 0, 30, 30);
		btncik.setOpaque(false);
		btncik.setContentAreaFilled(false);
		btncik.setBorderPainted(false);
		btncik.setIcon(btncikres);
		btncik.setBorder(null);
		btncik.addActionListener(this);
		lblkatList=new JLabel(" Kategori Listesi");
		lblkatList.setBounds(280, 50, 250, 40);
		lblkatList.setBackground(Color.white);
		lblkatList.setForeground(Color.DARK_GRAY);
		lblkatList.setOpaque(true);
		lblkatList.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblfiltre=new JLabel(" Kategori Filtrele");
		lblfiltre.setBounds(680, 50, 400, 40);
		lblfiltre.setBackground(Color.white);
		lblfiltre.setForeground(Color.DARK_GRAY);
		lblfiltre.setOpaque(true);
		lblfiltre.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblfiltrealan=new JLabel();
		lblfiltrealan.setBounds(680, 90, 400, 150);
		Border border = BorderFactory.createLineBorder(Color.white, 2);
		lblfiltrealan.setBorder(border);
		lblfiltrealan.setForeground(Color.DARK_GRAY);
		lblfiltrealan.setOpaque(false);
		lblkatAd=new JLabel("Kategori Adı : ");
		lblkatAd.setBounds(700, 120, 150, 40);
		lblkatAd.setForeground(Color.white);
		lblkatAd.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		txtkatAd=new JTextField();
		txtkatAd.setBounds(850,121,200,40);
		txtkatAd.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		txtkatAd.setHorizontalAlignment(txtkatAd.CENTER);
		txtkatAd.setBorder(null);
		btnfiltre=new JButton("Filtrele");
		btnfiltre.setBounds(950, 180, 100, 40);
		btnfiltre.setBorder(null);
		btnfiltre.setBackground(Color.white);
		btnfiltre.setForeground(Color.DARK_GRAY);
		btnfiltre.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnfiltre.addActionListener(this);
		lblkatId=new JLabel("Kategori Id : ");
		lblkatId.setBounds(700, 365, 150, 40);
		lblkatId.setForeground(Color.white);
		lblkatId.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblduzenle=new JLabel(" Kategori Düzenle");
		lblduzenle.setBounds(680, 290, 400, 40);
		lblduzenle.setBackground(Color.white);
		lblduzenle.setForeground(Color.DARK_GRAY);
		lblduzenle.setOpaque(true);
		lblduzenle.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lbldurum = new JLabel();
		lbldurum.setBounds(880,290,200,40);
		lbldurum.setBackground(Color.white);
		lbldurum.setForeground(Color.red);
		lbldurum.setOpaque(true);
		lbldurum.setHorizontalAlignment(lbldurum.CENTER);
		lbldurum.setFont(new Font("Courier",Font.TYPE1_FONT,13));
		lblduzenlealan=new JLabel();
		lblduzenlealan.setBounds(680, 330, 400, 240);
		lblduzenlealan.setBorder(border);
		lblduzenlealan.setForeground(Color.DARK_GRAY);
		lblduzenlealan.setOpaque(false);
		btntemizleres=new ImageIcon("src/otomasyon/app/clean.png");
		btntemizle=new JButton();
		btntemizle.setBackground(Color.white);
		btntemizle.setOpaque(true);
		btntemizle.setIcon(null);
		btntemizle.setOpaque(false);
		btntemizle.addActionListener(this);
		txtkatId=new JTextField();
		int katId= kategoriService.getMaxId()+1;
		txtkatId.setText(Integer.toString(katId));
		txtkatId.setBounds(850,366,200,40);
		txtkatId.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		txtkatId.setHorizontalAlignment(txtkatId.CENTER);
		txtkatId.setBorder(null);	
		txtkatId.setEnabled(false);
		lblkatAd2=new JLabel("Kategori Adı : ");
		lblkatAd2.setBounds(700, 435, 150, 40);
		lblkatAd2.setForeground(Color.white);
		lblkatAd2.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		txtkatAd2=new JTextField();
		txtkatAd2.setBounds(850,436,200,40);
		txtkatAd2.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		txtkatAd2.setHorizontalAlignment(txtkatAd.CENTER);
		txtkatAd2.setBorder(null);
		btnekle=new JButton("Ekle");
		btnekle.setBounds(700, 506, 100, 40);
		btnekle.setBorder(null);
		btnekle.setBackground(Color.white);
		btnekle.setForeground(Color.DARK_GRAY);
		btnekle.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnekle.addActionListener(this);
		btnsil=new JButton("Sil");
		btnsil.setBounds(825, 506, 100, 40);
		btnsil.setBorder(null);
		btnsil.setBackground(Color.white);
		btnsil.setForeground(Color.DARK_GRAY);
		btnsil.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnsil.addActionListener(this);
		btnsil.setEnabled(false);
		btnguncelle=new JButton("Güncelle");
		btnguncelle.setBounds(950, 506, 100, 40);
		btnguncelle.setBorder(null);
		btnguncelle.setBackground(Color.white);
		btnguncelle.setForeground(Color.DARK_GRAY);
		btnguncelle.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnguncelle.addActionListener(this);
		btnguncelle.setEnabled(false);
		List<Kategori> kategori = kategoriService.getAll();
		scrollp=kategoriTable(kategori);
		scrollp.setBounds(280, 91, 250, 543);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(lblperAd);
		add(btncik);
		add(lblkatList);
		add(txtkatAd);
		add(lblkatAd);
		add(txtkatId);
		add(lblkatId);
		add(txtkatAd2);
		add(lblkatAd2);
		add(lblfiltre);
		add(lblfiltrealan);
		add(lblduzenle);
		add(lblduzenlealan);
		add(btnfiltre);
		add(lbldurum);
		add(btnekle);
		add(btnsil);
		add(btnguncelle);
		add(scrollp);
		add(btntemizle);
		
	}
	public static void main(String[]args) {
		
		KategoriPage kategoriPage=new KategoriPage();
		if(LoginPage.kontrol==true)
			kategoriPage.setVisible(true);
		else {
			LoginPage loginpage=new LoginPage();
			loginpage.setVisible(true);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==menu.anasayfa) {
			setVisible(false);
			HomePage homePage=new HomePage();
			homePage.setVisible(true);
		}
		if(e.getSource()==menu.kategori){
			setVisible(false);
			KategoriPage kategoriPage=new KategoriPage();
			kategoriPage.setVisible(true);
		}
		if(e.getSource()==menu.personel) {
			setVisible(false);
			PersonelPage personelPage=new PersonelPage();
			personelPage.setVisible(true);
		}
		if(e.getSource()==menu.yazar) {
			setVisible(false);
			YazarPage yazarPage=new YazarPage();
			yazarPage.setVisible(true);
		}
		if(e.getSource()==menu.kitap) {
			setVisible(false);
			KitapPage kitapPage=new KitapPage();
			kitapPage.setVisible(true);
		}
		if(e.getSource()==menu.uye) {
			setVisible(false);
			UyePage uyePage=new UyePage();
			uyePage.setVisible(true);
		}
		if(e.getSource()==menu.uyeKitap) {
			setVisible(false);
			UyeKitapPage uyeKitappage=new UyeKitapPage();
			uyeKitappage.setVisible(true);
		}
		if(e.getSource()==btncik) {
			setVisible(false);
	 		LoginPage loginpage=new LoginPage();
	 		loginpage.setVisible(true);
		}
		if(e.getSource()==btntemizle) {
			int katId= kategoriService.getMaxId()+1;
			txtkatId.setText(Integer.toString(katId));
			txtkatAd2.setText("");
			btnekle.setEnabled(true);
			btnguncelle.setEnabled(false);
			btnsil.setEnabled(false);
			btntemizle.setOpaque(false);
			btntemizle.setBounds(1050,290,30,30);
		}
		if(e.getSource()==btnfiltre) {
			String katAd = txtkatAd.getText();
			List<Kategori> kategori = kategoriService.findByKategoriName(katAd);
			remove(scrollp);
			scrollp=kategoriTable(kategori);
			scrollp.setBounds(280, 91, 250, 543);
			add(scrollp);
		}
		if(e.getSource()==btnekle) {
			boolean kontrol = control();
			Kategori kategori = new Kategori();
			kategori.setKategoriId(Integer.parseInt(txtkatId.getText()));
			kategori.setKategoriAd(txtkatAd2.getText());
			if(kontrol==true) {
				kategori = kategoriService.save(kategori);
				List<Kategori> kategori2 = kategoriService.getAll();
				remove(scrollp);
				scrollp=kategoriTable(kategori2);
				scrollp.setBounds(280, 91, 250, 543);
				add(scrollp);
				int katId= kategoriService.getMaxId()+1;
				txtkatId.setText(Integer.toString(katId));
				txtkatAd2.setText("");
			}
		}
		if(e.getSource()==btnsil) {
			boolean kontrol = control();
			int katId = Integer.parseInt(txtkatId.getText());
			if(kontrol==true) {
				kategoriService.delete(katId);
				List<Kategori> kategori = kategoriService.getAll();
				remove(scrollp);
				scrollp=kategoriTable(kategori);
				scrollp.setBounds(280, 91, 250, 543);
				add(scrollp);
				katId= kategoriService.getMaxId()+1;
				txtkatId.setText(Integer.toString(katId));
				txtkatAd2.setText("");
				btnekle.setEnabled(true);
				btnguncelle.setEnabled(false);
				btnsil.setEnabled(false);
				btntemizle.setOpaque(false);
				btntemizle.setBounds(1050,290,30,30);
			}
		}
		if(e.getSource()==btnguncelle) {
			boolean kontrol = control();
			int katId = Integer.parseInt(txtkatId.getText());
			String katAd = txtkatAd2.getText();
			if(kontrol == true) {
				kategoriService.update(katId, katAd);
				List<Kategori> kategori = kategoriService.getAll();
				remove(scrollp);
				scrollp=kategoriTable(kategori);
				scrollp.setBounds(280, 91, 250, 543);
				add(scrollp);
				katId= kategoriService.getMaxId()+1;
				txtkatId.setText(Integer.toString(katId));
				txtkatAd2.setText("");
				btnekle.setEnabled(true);
				btnguncelle.setEnabled(false);
				btnsil.setEnabled(false);
				btntemizle.setOpaque(false);
				btntemizle.setBounds(1050,290,30,30);
			}
		}
		
	}
	
	public JScrollPane kategoriTable(List<Kategori> kategori) {
		
		String veri[][]=new String[kategori.size()][];
		
		for (int i =0; i < kategori.size(); i++) {  
		        	 
		                veri[i]=new String[]{  
		               
		                		String.valueOf(kategori.get(i).getKategoriId()),  
		                		String.valueOf(kategori.get(i).getKategoriAd()),    
  
		                };
		                
		 }
		
		 j = new JTable();
		 DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
	     rendar.setHorizontalAlignment(JLabel.CENTER);
		 j.setRowHeight(20);
		 TableModel tabloModeli=new DefaultTableModel(veri,new String[] {"Id", "Kategori Adı"});
		 j.setModel(tabloModeli);
		 getSelectedData();
		 for(int i=0;i<j.getColumnCount();i++)
			 j.getColumnModel().getColumn(i).setCellRenderer(rendar);
	     j.getColumnModel().setColumnMargin(20);
	     TableColumnModel columnModel = j.getColumnModel();
	     columnModel.getColumn(0).setPreferredWidth(50);
	     columnModel.getColumn(1).setPreferredWidth(200);
	     JScrollPane sp = new JScrollPane(j);
	     j.setOpaque(false);
		 return sp;
	
	}
	
	public void getSelectedData() {
		j.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!(j.getSelectionModel().isSelectionEmpty( ))){
				//retrieving the selected row index
					int row = j.getSelectedRow();

				//if a single row is selected from the table, take each cell values into the controls
					if (j.getRowSelectionAllowed()) {
						String katId = j.getValueAt(row, 0).toString();
						String katAd = j.getValueAt(row, 1).toString();
						txtkatId.setText(katId);
						txtkatAd2.setText(katAd);
						txtkatId.setEnabled(false);
						btnekle.setEnabled(false);
						btnsil.setEnabled(true);
						btnguncelle.setEnabled(true);
						btntemizle.setOpaque(true);
						btntemizle.setBounds(1048,332,30,30);
						btntemizle.setIcon(btntemizleres);
					}
				}	
			}
		});
	}
	public boolean control() {
		boolean kontrol=false;
		String katAd = txtkatAd2.getText(); 
		if(katAd.equals("")) {
			JOptionPane.showMessageDialog(null, "Lütfen alanları boş bırakmayınız.");
			kontrol = false;
		}
		else {
		        kontrol = true;
		    }
		return kontrol;
	}
}

