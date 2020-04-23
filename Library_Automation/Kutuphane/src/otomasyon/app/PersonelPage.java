package otomasyon.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import otomasyon.DAO.PersonelDAO;
import otomasyon.model.Personel;
import otomasyon.model.Uye;
import otomasyon.service.PersonelService;

public class PersonelPage extends JFrame implements ActionListener {
	
	PersonelDAO personelDAO=new PersonelDAO();
	PersonelService personelService=new PersonelService(personelDAO);
	
	Menu menu=new Menu();
	JScrollPane scrollp;
	JLabel lblperList,lblfiltre,lblfiltrealan,lblduzenle,lbldurum,lblduzenlealan,lblperTckn,lblperAd,lblperTckn2,lblsifre,lblperAdi;
	JTextField txtperAd,txtperTckn,txtsifre;
	JComboBox<String> comboTckn;
	JButton btnfiltre,btnekle,btnsil,btnguncelle,btntemizle,btncik;
	DefaultListCellRenderer listRenderer;
    JTable j;
    ImageIcon btntemizleres,btncikres;
    
	public PersonelPage() {
		setTitle("Personel İşlemleri");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		setLayout(null);
		setContentPane(new JLabel(new ImageIcon("src/otomasyon/app/bg2.jpg")));
		menu.personel.setBackground(Color.DARK_GRAY);
		menu.personel.setOpaque(true);
		menu.personel.setForeground(Color.white);
		menu.mb.setSelected(menu.personel);
		add(menu.mb);
		setJMenuBar(menu.mb);
		menu.anasayfa.addActionListener(this);
		menu.kategori.addActionListener(this);
		menu.personel.addActionListener(this);
		menu.yazar.addActionListener(this);
		menu.kitap.addActionListener(this);
		menu.uye.addActionListener(this);
		menu.uyeKitap.addActionListener(this);
		lblperAdi=new JLabel(LoginPage.adSoyad);
		lblperAdi.setBounds(1217,3,120,30);
		lblperAdi.setBackground(Color.white);
		lblperAdi.setForeground(Color.white);
		lblperAdi.setOpaque(false);
		lblperAdi.setFont(new Font("Courier",Font.TYPE1_FONT,14));
		lblperAdi.setHorizontalAlignment(JLabel.CENTER);
		btncikres=new ImageIcon("src/otomasyon/app/exit.png");
		btncik=new JButton();
		btncik.setBounds(1328, 0, 30, 30);
		btncik.setOpaque(false);
		btncik.setContentAreaFilled(false);
		btncik.setBorderPainted(false);
		btncik.setIcon(btncikres);
		btncik.setBorder(null);
		btncik.addActionListener(this);
		lblperList=new JLabel(" Personel Listesi");
		lblperList.setBounds(280, 50, 450, 40);
		lblperList.setBackground(Color.white);
		lblperList.setForeground(Color.DARK_GRAY);
		lblperList.setOpaque(true);
		lblperList.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblfiltre=new JLabel(" Personel Filtrele");
		lblfiltre.setBounds(780, 50, 400, 40);
		lblfiltre.setBackground(Color.white);
		lblfiltre.setForeground(Color.DARK_GRAY);
		lblfiltre.setOpaque(true);
		lblfiltre.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblfiltrealan=new JLabel();
		lblfiltrealan.setBounds(780, 90, 400, 150);
		Border border = BorderFactory.createLineBorder(Color.white, 2);
		lblfiltrealan.setBorder(border);
		lblfiltrealan.setForeground(Color.DARK_GRAY);
		lblfiltrealan.setOpaque(false);
		lblperTckn=new JLabel("Personel Tckn : ");
		lblperTckn.setBounds(800, 120, 150, 40);
		lblperTckn.setForeground(Color.white);
		lblperTckn.setFont(new Font("Courier",Font.TYPE1_FONT,18));
		comboTckn = new JComboBox<String>();
		comboTckn.setBounds(950,121,200,40);
		comboTckn.setFont(new Font("Courier",Font.TYPE1_FONT,17));
	    listRenderer = new DefaultListCellRenderer();
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
		comboTckn.setRenderer(listRenderer);
		comboTckn.addItem("Hepsi");
		List<Personel> personel = personelService.getAll();
		for(Personel p:personel)
			comboTckn.addItem(p.getPerTckn());
		comboTckn.setBorder(null);
		btnfiltre=new JButton("Filtrele");
		btnfiltre.setBounds(1050, 180, 100, 40);
		btnfiltre.setBorder(null);
		btnfiltre.setBackground(Color.white);
		btnfiltre.setForeground(Color.DARK_GRAY);
		btnfiltre.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnfiltre.addActionListener(this);
		lblduzenle=new JLabel(" Personel Düzenle");
		lblduzenle.setBounds(780, 290, 400, 40);
		lblduzenle.setBackground(Color.white);
		lblduzenle.setForeground(Color.DARK_GRAY);
		lblduzenle.setOpaque(true);
		lblduzenle.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lbldurum = new JLabel();
		lbldurum.setBounds(980,290,200,40);
		lbldurum.setBackground(Color.white);
		lbldurum.setForeground(Color.red);
		lbldurum.setOpaque(true);
		lbldurum.setHorizontalAlignment(lbldurum.CENTER);
		lbldurum.setFont(new Font("Courier",Font.TYPE1_FONT,13));
		lblduzenlealan=new JLabel();
		lblduzenlealan.setBounds(780, 330, 400, 320);
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
		lblperTckn2=new JLabel("Personel Tckn : ");
		lblperTckn2.setBounds(800, 365, 150, 40);
		lblperTckn2.setForeground(Color.white);
		lblperTckn2.setFont(new Font("Courier",Font.TYPE1_FONT,18));
		txtperTckn=new JTextField();
		txtperTckn.setBounds(950,365,200,40);
		txtperTckn.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		txtperTckn.setHorizontalAlignment(txtperTckn.CENTER);
		txtperTckn.setBorder(null);
		lblperAd=new JLabel("Personel Adı : ");
		lblperAd.setBounds(800, 435, 150, 40);
		lblperAd.setForeground(Color.white);
		lblperAd.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		txtperAd=new JTextField();
		txtperAd.setBounds(950,436,200,40);
		txtperAd.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		txtperAd.setHorizontalAlignment(txtperAd.CENTER);
		txtperAd.setBorder(null);
		lblsifre=new JLabel("Personel Sifre : ");
		lblsifre.setBounds(800, 505, 150, 40);
		lblsifre.setForeground(Color.white);
		lblsifre.setFont(new Font("Courier",Font.TYPE1_FONT,18));
		txtsifre=new JTextField();
		txtsifre.setBounds(950,505,200,40);
		txtsifre.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		txtsifre.setHorizontalAlignment(txtperAd.CENTER);
		txtsifre.setBorder(null);
		btnekle=new JButton("Ekle");
		btnekle.setBounds(800, 576, 100, 40);
		btnekle.setBorder(null);
		btnekle.setBackground(Color.white);
		btnekle.setForeground(Color.DARK_GRAY);
		btnekle.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnekle.addActionListener(this);
		btnsil=new JButton("Sil");
		btnsil.setBounds(925, 576, 100, 40);
		btnsil.setBorder(null);
		btnsil.setBackground(Color.white);
		btnsil.setForeground(Color.DARK_GRAY);
		btnsil.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnsil.addActionListener(this);
		btnsil.setEnabled(false);
		btnguncelle=new JButton("Güncelle");
		btnguncelle.setBounds(1050, 576, 100, 40);
		btnguncelle.setBorder(null);
		btnguncelle.setBackground(Color.white);
		btnguncelle.setForeground(Color.DARK_GRAY);
		btnguncelle.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnguncelle.addActionListener(this);
		btnguncelle.setEnabled(false);
		scrollp=personelTable(personel);
		scrollp.setBounds(280, 91, 450, 123);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(lblperList);
		add(lblperAdi);
		add(btncik);
		add(comboTckn);
		add(lblperTckn);
		add(txtperTckn);
		add(lblsifre);
		add(txtsifre);
		add(lblperTckn2);
		add(txtperAd);
		add(lblperAd);
		add(lblfiltre);
		add(lblfiltrealan);
		add(lblduzenle);
		add(lbldurum);
		add(lblduzenlealan);
		add(btnfiltre);
		add(btnekle);
		add(btnsil);
		add(btnguncelle);
		add(scrollp);
		add(btntemizle);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[]args) {
		PersonelPage personelPage=new PersonelPage();
		  if(LoginPage.kontrol==true)
			personelPage.setVisible(true);
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
			txtperTckn.setText("");
			txtperAd.setText("");
			txtsifre.setText("");
			txtperTckn.setEnabled(true);
			btnekle.setEnabled(true);
			btnguncelle.setEnabled(false);
			btnsil.setEnabled(false);
			btntemizle.setOpaque(false);
			btntemizle.setIcon(null);
			btntemizle.setBounds(1150,290,30,30);
		}
		if(e.getSource()==btnfiltre) {
			String perTckn = (String) comboTckn.getSelectedItem();
			Personel personel = personelService.findPersonelByTckn(perTckn);
			List<Personel> liste=new ArrayList<Personel>();
			liste.add(personel);
			remove(scrollp);
			List<Personel> liste2 = personelService.getAll();
			if(perTckn.equals("Hepsi"))
				scrollp=personelTable(liste2);
			else
				scrollp=personelTable(liste);
			scrollp.setBounds(280, 91, 450, 123);
			add(scrollp);
		}
		if(e.getSource()==btnekle) {
			boolean kontrol = control();
			Personel personel = new Personel();
			personel.setPerAdSyd(txtperAd.getText());
			personel.setPerTckn(txtperTckn.getText());
			personel.setPerSifre(txtsifre.getText());
			if(kontrol==true) {
				personel = personelService.save(personel);
				List<Personel> personel2 = personelService.getAll();
				comboTckn.removeAllItems();
				comboTckn.addItem("Hepsi");
				for(Personel p:personel2)
					comboTckn.addItem(p.getPerTckn());
				remove(scrollp);
				scrollp=personelTable(personel2);
				scrollp.setBounds(280, 91, 450, 123);
				add(scrollp);
				txtperTckn.setText("");
				txtperAd.setText("");
				txtsifre.setText("");
				btnekle.setEnabled(true);
				btnsil.setEnabled(false);
				btnguncelle.setEnabled(false);
			}
		}
		if(e.getSource()==btnsil) {
			boolean kontrol = control();
			String perTckn = txtperTckn.getText();
			if(kontrol==true) {
				personelService.delete(perTckn);
				List<Personel> personel = personelService.getAll();
				comboTckn.removeAllItems();
				comboTckn.addItem("Hepsi");
				for(Personel p:personel)
					comboTckn.addItem(p.getPerTckn());
				remove(scrollp);
				scrollp=personelTable(personel);
				scrollp.setBounds(280, 91, 450, 123);
				add(scrollp);
				txtperTckn.setText("");
				txtperTckn.setEnabled(true);
				txtperAd.setText("");
				txtsifre.setText("");
				btnekle.setEnabled(true);
				btnsil.setEnabled(false);
				btnguncelle.setEnabled(false);
				btntemizle.setBounds(1150,290,30,30);
			}
		}
		if(e.getSource()==btnguncelle) {
			boolean kontrol = control();
			String perTckn = txtperTckn.getText();
			String perAd = txtperAd.getText();
			String perSifre = txtsifre.getText();
			if(kontrol==true) {
				personelService.update(perTckn, perAd,perSifre);
				List<Personel> personel = personelService.getAll();
				comboTckn.removeAllItems();
				comboTckn.addItem("Hepsi");
				for(Personel p:personel)
					comboTckn.addItem(p.getPerTckn());
				remove(scrollp);
				scrollp=personelTable(personel);
				scrollp.setBounds(280, 91, 450, 123);
				add(scrollp);
				txtperTckn.setText("");
				txtperTckn.setEnabled(true);
				txtperAd.setText("");
				txtsifre.setText("");
				btnekle.setEnabled(true);
				btnsil.setEnabled(false);
				btnguncelle.setEnabled(false);
				btntemizle.setBounds(1150,290,30,30);
			}
		}
		
	}
	
	public JScrollPane personelTable(List<Personel> personel) {
		
		String veri[][]=new String[personel.size()][];
		
		for (int i =0; i < personel.size(); i++) {  
		        	 
		                veri[i]=new String[]{  
		               
		                		String.valueOf(personel.get(i).getPerTckn()),  
		                		String.valueOf(personel.get(i).getPerAdSyd()),  
		                		String.valueOf(personel.get(i).getPerSifre())
  
		                };
		                
		 }
		
		 j = new JTable();
		 DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
	     rendar.setHorizontalAlignment(JLabel.CENTER);
		 j.setRowHeight(20);
		 TableModel tabloModeli=new DefaultTableModel(veri,new String[] {"TCKN", "Personel Adı","Personel Şifre"});
		 j.setModel(tabloModeli);
		 getSelectedData();
		 for(int i=0;i<j.getColumnCount();i++)
			 j.getColumnModel().getColumn(i).setCellRenderer(rendar);
	     j.getColumnModel().setColumnMargin(20);
	     TableColumnModel columnModel = j.getColumnModel();
	     columnModel.getColumn(0).setPreferredWidth(125);
	     columnModel.getColumn(1).setPreferredWidth(200);
	     columnModel.getColumn(2).setPreferredWidth(125);
	     JScrollPane sp = new JScrollPane(j);
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
						String perTckn = j.getValueAt(row, 0).toString();
						String perAd = j.getValueAt(row, 1).toString();
						String perSifre = j.getValueAt(row, 2).toString();
						txtperTckn.setText(perTckn);
						txtperTckn.setEnabled(false);
						txtperAd.setText(perAd);
						txtsifre.setText(perSifre);
						btnekle.setEnabled(false);
						btnsil.setEnabled(true);
						btnguncelle.setEnabled(true);
						btntemizle.setOpaque(true);
						btntemizle.setBounds(1148,332,30,30);
						btntemizle.setIcon(btntemizleres);
					}
				}	
			}
		});
	}
	public boolean control() {
		boolean kontrol=false;
		String perTckn = txtperTckn.getText(); 
		String perAd = txtperAd.getText(); 
		String perSifre = txtsifre.getText();
		 try {
			 	int perTckn2 = Integer.parseInt(txtperTckn.getText());
			 	int perSifre2 = Integer.parseInt(txtsifre.getText());
		    } catch (NumberFormatException nfe) {
		    	JOptionPane.showMessageDialog(null, "Lütfen Tckn ve Şifre alanlarına sayısal değer giriniz.");
		    	return false;
		    }
		if(perTckn.equals("") || perAd.equals("") || perSifre.equals("")) {
			JOptionPane.showMessageDialog(null, "Lütfen alanları boş bırakmayınız");
			kontrol = false;
		}
		else {
			if(perTckn.length()!=11) {
				JOptionPane.showMessageDialog(null,"Tc Kimlik No 11 haneli olmalı.");
				kontrol=false;
			}
			else if(perSifre.length()!=6) {
				JOptionPane.showMessageDialog(null,"Şifre 6 haneli olmalı.");
				kontrol=false;
			}
			else {
		        kontrol = true;
			}
		}
		return kontrol;
	}
}

