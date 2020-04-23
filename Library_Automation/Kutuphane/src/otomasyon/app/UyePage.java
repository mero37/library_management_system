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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import otomasyon.DAO.PersonelDAO;
import otomasyon.DAO.UyeDAO;
import otomasyon.model.Personel;
import otomasyon.model.Uye;
import otomasyon.service.PersonelService;
import otomasyon.service.UyeService;

public class UyePage extends JFrame implements ActionListener {
	
	UyeDAO uyeDAO=new UyeDAO();
	UyeService uyeService=new UyeService(uyeDAO);
	
	Menu menu=new Menu();
	JScrollPane scrollp,scrollline;
	JLabel lbluyeList,lblfiltre,lblfiltrealan,lblduzenle,lblduzenlealan,lbldurum,lbluyeTckn,lbluyeAd,lbluyeTckn2,lbluyeAdres,lbluyeTel,lblperAd;
	JTextField txtuyeAd,txtuyeTckn,txtuyeTel;
	JTextArea txtAdres;
	JComboBox<String> comboTckn;
	JButton btnfiltre,btnekle,btnsil,btnguncelle,btntemizle,btncik;
	DefaultListCellRenderer listRenderer;
    JTable j;
    ImageIcon btntemizleres,btncikres;
	
	public UyePage() {
		setTitle("Üye İşlemleri");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		setLayout(null);
		setContentPane(new JLabel(new ImageIcon("src/otomasyon/app/bg2.jpg")));
		menu.uye.setBackground(Color.DARK_GRAY);
		menu.uye.setOpaque(true);
		menu.uye.setForeground(Color.white);
		menu.mb.setSelected(menu.uye);
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		lbluyeList=new JLabel(" Üye Listesi");
		lbluyeList.setBounds(380, 40, 750, 40);
		lbluyeList.setBackground(Color.white);
		lbluyeList.setForeground(Color.DARK_GRAY);
		lbluyeList.setOpaque(true);
		lbluyeList.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblfiltre=new JLabel(" Üye Filtrele");
		lblfiltre.setBounds(300, 330, 380, 40);
		lblfiltre.setBackground(Color.white);
		lblfiltre.setForeground(Color.DARK_GRAY);
		lblfiltre.setOpaque(true);
		lblfiltre.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblfiltrealan=new JLabel();
		lblfiltrealan.setBounds(300, 370, 380, 150);
		Border border = BorderFactory.createLineBorder(Color.white, 2);
		lblfiltrealan.setBorder(border);
		lblfiltrealan.setForeground(Color.DARK_GRAY);
		lblfiltrealan.setOpaque(false);
		lbluyeTckn=new JLabel("Üye Tckn : ");
		lbluyeTckn.setBounds(320, 400, 150, 40);
		lbluyeTckn.setForeground(Color.white);
		lbluyeTckn.setFont(new Font("Courier",Font.TYPE1_FONT,18));
		comboTckn = new JComboBox<String>();
		comboTckn.setBounds(450,401,200,40);
		comboTckn.setFont(new Font("Courier",Font.TYPE1_FONT,17));
	    listRenderer = new DefaultListCellRenderer();
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
		comboTckn.setRenderer(listRenderer);
		comboTckn.addItem("Hepsi");
		List<Uye> uye = uyeService.getAll();
		for(Uye u:uye)
			comboTckn.addItem(u.getUyeTckn());
		comboTckn.setBorder(null);
		btnfiltre=new JButton("Filtrele");
		btnfiltre.setBounds(550, 461, 100, 40);
		btnfiltre.setBorder(null);
		btnfiltre.setBackground(Color.white);
		btnfiltre.setForeground(Color.DARK_GRAY);
		btnfiltre.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnfiltre.addActionListener(this);
		lblduzenle=new JLabel(" Üye Düzenle");
		lblduzenle.setBounds(760, 220, 440, 40);
		lblduzenle.setBackground(Color.white);
		lblduzenle.setForeground(Color.DARK_GRAY);
		lblduzenle.setOpaque(true);
		lblduzenle.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lbldurum=new JLabel();
		lbldurum.setBounds(960,220,200,40);
		lbldurum.setBackground(Color.white);
		lbldurum.setForeground(Color.red);
		lbldurum.setOpaque(true);
		lbldurum.setHorizontalAlignment(lbldurum.CENTER);
		lbldurum.setFont(new Font("Courier",Font.TYPE1_FONT,13));
		lblduzenlealan=new JLabel();
		lblduzenlealan.setBounds(760, 260, 440, 400);
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
		lbluyeTckn2=new JLabel("Üye Tckn : ");
		lbluyeTckn2.setBounds(790, 295, 150, 40);
		lbluyeTckn2.setForeground(Color.white);
		lbluyeTckn2.setFont(new Font("Courier",Font.TYPE1_FONT,18));
		txtuyeTckn=new JTextField();
		txtuyeTckn.setBounds(910,295,250,40);
		txtuyeTckn.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		txtuyeTckn.setHorizontalAlignment(txtuyeTckn.CENTER);
		txtuyeTckn.setBorder(null);
		lbluyeAd=new JLabel("Üye Adı : ");
		lbluyeAd.setBounds(790, 365, 150, 40);
		lbluyeAd.setForeground(Color.white);
		lbluyeAd.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		txtuyeAd=new JTextField();
		txtuyeAd.setBounds(910,366,250,40);
		txtuyeAd.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		txtuyeAd.setHorizontalAlignment(txtuyeAd.CENTER);
		txtuyeAd.setBorder(null);
		lbluyeAdres=new JLabel("Üye Adres : ");
		lbluyeAdres.setBounds(790, 445, 150, 40);
		lbluyeAdres.setForeground(Color.white);
		lbluyeAdres.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		txtAdres=new JTextArea();
		txtAdres.setBounds(910,426,250,80);
		txtAdres.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		border = BorderFactory.createLineBorder(Color.white,5);
		txtAdres.setBorder(border);
		txtAdres.setWrapStyleWord(true);
		txtAdres.setLineWrap(true);
		lbluyeTel=new JLabel("Üye Tel : ");
		lbluyeTel.setBounds(790, 525, 150, 40);
		lbluyeTel.setForeground(Color.white);
		lbluyeTel.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		txtuyeTel=new JTextField();
		txtuyeTel.setBounds(910,526,250,40);
		txtuyeTel.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		txtuyeTel.setBorder(null);
		txtuyeTel.setHorizontalAlignment(txtuyeTel.CENTER);
		btnekle=new JButton("Ekle");
		btnekle.setBounds(790, 596, 100, 40);
		btnekle.setBorder(null);
		btnekle.setBackground(Color.white);
		btnekle.setForeground(Color.DARK_GRAY);
		btnekle.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnekle.addActionListener(this);
		btnsil=new JButton("Sil");
		btnsil.setBounds(925, 596, 100, 40);
		btnsil.setBorder(null);
		btnsil.setBackground(Color.white);
		btnsil.setForeground(Color.DARK_GRAY);
		btnsil.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnsil.addActionListener(this);
		btnsil.setEnabled(false);
		btnguncelle=new JButton("Güncelle");
		btnguncelle.setBounds(1060, 596, 100, 40);
		btnguncelle.setBorder(null);
		btnguncelle.setBackground(Color.white);
		btnguncelle.setForeground(Color.DARK_GRAY);
		btnguncelle.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnguncelle.addActionListener(this);
		btnguncelle.setEnabled(false);
		scrollp=uyeTable(uye);
		scrollp.setBounds(380, 81, 750, 103);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(lbluyeList);
		add(lblperAd);
		add(btncik);
		add(comboTckn);
		add(lbluyeTckn);
		add(txtuyeTckn);
		add(lbluyeAdres);
		add(txtAdres);
		add(lbluyeTckn2);
		add(lbluyeTckn2);
		add(lbluyeAd);
		add(txtuyeAd);
		add(lbluyeTel);
		add(txtuyeTel);
		add(lblfiltre);
		add(lblfiltrealan);
		add(lblduzenle);
		add(lblduzenlealan);
		add(btnfiltre);
		add(btnekle);
		add(btnsil);
		add(btnguncelle);
		add(scrollp);
		add(lbldurum);
		add(btntemizle);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[]args) {
		UyePage uyePage=new UyePage();
	 	if(LoginPage.kontrol==true)
			uyePage.setVisible(true);
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
			txtuyeTckn.setText("");
			txtuyeTckn.setEnabled(true);
			txtuyeAd.setText("");
			txtAdres.setText("");
			txtuyeTel.setText("");
			btnekle.setEnabled(true);
			btnguncelle.setEnabled(false);
			btnsil.setEnabled(false);
			btntemizle.setOpaque(false);
			btntemizle.setIcon(null);
			btntemizle.setBounds(1168,230,30,30);
		}
		if(e.getSource()==btnfiltre) {
			String uyeTckn = (String) comboTckn.getSelectedItem();
			Uye uye = uyeService.findUyeByTckn(uyeTckn);
			List<Uye> liste=new ArrayList<Uye>();
			liste.add(uye);
			remove(scrollp);
			List<Uye> liste2 = uyeService.getAll();
			if(uyeTckn.equals("Hepsi"))
				scrollp=uyeTable(liste2);
			else
				scrollp=uyeTable(liste);
			scrollp.setBounds(380, 81, 750, 103);
			add(scrollp);
		}
		if(e.getSource()==btnekle) {
			boolean kontrol = control();
			Uye uye = new Uye();
			uye.setUyeAdSoyad(txtuyeAd.getText());
			uye.setUyeTckn(txtuyeTckn.getText());
			uye.setUyeAdres(txtAdres.getText());
			uye.setUyeTel(txtuyeTel.getText());
			if(kontrol==true) {
				uye = uyeService.save(uye);
				List<Uye> uye2 = uyeService.getAll();
				comboTckn.removeAllItems();
				comboTckn.addItem("Hepsi");
				for(Uye u:uye2)
					comboTckn.addItem(u.getUyeTckn());
				remove(scrollp);
				scrollp=uyeTable(uye2);
				scrollp.setBounds(380, 81, 750, 103);
				add(scrollp);
				txtuyeTckn.setText("");
				txtuyeAd.setText("");
				txtAdres.setText("");
				txtuyeTel.setText("");
			}
		}
		if(e.getSource()==btnsil) {
			boolean kontrol = control();
			String uyeTckn = txtuyeTckn.getText();
			if(kontrol==true) {
				uyeService.delete(uyeTckn);
				List<Uye> uye = uyeService.getAll();
				comboTckn.removeAllItems();
				comboTckn.addItem("Hepsi");
				for(Uye u:uye)
					comboTckn.addItem(u.getUyeTckn());
				remove(scrollp);
				scrollp=uyeTable(uye);
				scrollp.setBounds(380, 81, 750, 103);
				add(scrollp);
				txtuyeTckn.setText("");
				txtuyeTckn.setEnabled(true);
				txtuyeAd.setText("");
				txtAdres.setText("");
				txtuyeTel.setText("");
				btnekle.setEnabled(true);
				btnsil.setEnabled(false);
				btnguncelle.setEnabled(false);
				btntemizle.setBounds(1168,230,30,30);
			}
		}
		if(e.getSource()==btnguncelle) {
			boolean kontrol = control();
			String uyeTckn = txtuyeTckn.getText();
			String uyeAd = txtuyeAd.getText();
			String uyeAdres = txtAdres.getText();
			String uyeTel = txtuyeTel.getText();
			if(kontrol==true) {
				uyeService.update(uyeTckn, uyeAd,uyeAdres,uyeTel);
				List<Uye> uye = uyeService.getAll();
				comboTckn.removeAllItems();
				comboTckn.addItem("Hepsi");
				for(Uye u:uye)
					comboTckn.addItem(u.getUyeTckn());
				remove(scrollp);
				scrollp=uyeTable(uye);
				scrollp.setBounds(380, 81, 750, 103);
				add(scrollp);
				txtuyeTckn.setText("");
				txtuyeTckn.setEnabled(true);
				txtuyeAd.setText("");
				txtAdres.setText("");
				txtuyeTel.setText("");
				btnekle.setEnabled(true);
				btnsil.setEnabled(false);
				btnguncelle.setEnabled(false);
				btntemizle.setBounds(1168,230,30,30);
			}
		}
		
	}
	public JScrollPane uyeTable(List<Uye> uye) {
		
		String veri[][]=new String[uye.size()][];
		
		for (int i =0; i < uye.size(); i++) {  
		        	 
		                veri[i]=new String[]{  
		               
		                		String.valueOf(uye.get(i).getUyeTckn()),  
		                		String.valueOf(uye.get(i).getUyeAdSoyad()),  
		                		String.valueOf(uye.get(i).getUyeAdres()),
		                		String.valueOf(uye.get(i).getUyeTel())
		                		
		                };
		                
		 }
		
		 j = new JTable();
		 DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
	     rendar.setHorizontalAlignment(JLabel.CENTER);
		 j.setRowHeight(20);
		 TableModel tabloModeli=new DefaultTableModel(veri,new String[] {"TCKN", "Üye Adı","Üye Adres","Üye Tel"});
		 j.setModel(tabloModeli);
		 getSelectedData();
		 for(int i=0;i<j.getColumnCount();i++)
			 j.getColumnModel().getColumn(i).setCellRenderer(rendar);
	     j.getColumnModel().setColumnMargin(20);
	     TableColumnModel columnModel = j.getColumnModel();
	     columnModel.getColumn(0).setPreferredWidth(125);
	     columnModel.getColumn(1).setPreferredWidth(150);
	     columnModel.getColumn(2).setPreferredWidth(400);
	     columnModel.getColumn(3).setPreferredWidth(125);
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
						String uyeTckn = j.getValueAt(row, 0).toString();
						String uyeAd = j.getValueAt(row, 1).toString();
						String uyeAdres = j.getValueAt(row, 2).toString();
						String uyeTel = j.getValueAt(row, 3).toString();
						txtuyeTckn.setText(uyeTckn);
						txtuyeTckn.setEnabled(false);
						txtuyeAd.setText(uyeAd);
						txtAdres.setText(uyeAdres);
						txtuyeTel.setText(uyeTel);
						btnekle.setEnabled(false);
						btnsil.setEnabled(true);
						btnguncelle.setEnabled(true);
						btntemizle.setOpaque(true);
						btntemizle.setBounds(1168,262,30,30);
						btntemizle.setIcon(btntemizleres);
					}
				}	
			}
		});
	}
	public boolean control() {
		boolean kontrol=false;
		String uyeTckn = txtuyeTckn.getText(); 
		String uyeAd = txtuyeAd.getText(); 
		String uyeAdres = txtAdres.getText(); 
		String uyeTel = txtuyeTel.getText();

		if(uyeTckn.equals("") || uyeAd.equals("") || uyeAdres.equals("") || uyeTel.equals("")) {
			JOptionPane.showMessageDialog(null,"Lütfen alanları boş bırakmayınız");
			kontrol = false;
		}
		else {
			 
			if(uyeTckn.length()!=11) {
				JOptionPane.showMessageDialog(null,"Tc Kimlik No 11 haneli olmalı.");
				kontrol=false;
			}
			else if(uyeTel.length()!=11) {
				JOptionPane.showMessageDialog(null,"Telefon 11 haneli olmalı.");
				kontrol=false;
			}
			else {
		        kontrol = true;
			}
		    }
		return kontrol;
	}
}

	

