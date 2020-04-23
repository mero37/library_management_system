package otomasyon.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

import otomasyon.DAO.KitapDAO;
import otomasyon.DAO.UyeDAO;
import otomasyon.DAO.UyeKitapDAO;
import otomasyon.model.Kategori;
import otomasyon.model.Kitap;
import otomasyon.model.Uye;
import otomasyon.model.UyeKitap;
import otomasyon.service.KitapService;
import otomasyon.service.UyeKitapService;
import otomasyon.service.UyeService;

public class UyeKitapPage extends JFrame implements ActionListener{
	
	UyeKitapDAO uyeKitapDAO=new UyeKitapDAO();
	UyeKitapService uyeKitapService=new UyeKitapService(uyeKitapDAO);
	
	KitapDAO kitapDAO=new KitapDAO();
	KitapService kitapService=new KitapService(kitapDAO);
	
	UyeDAO uyeDAO=new UyeDAO();
	UyeService uyeService=new UyeService(uyeDAO);
	
	Menu menu=new Menu();
	JLabel lblkitapList,lblbugunkitapList,lblgecmiskitapList,lblkitapVarMi,lblalan,lblalan1,lblalan2,lblalan3,lblkitapAd,lblkitapAd2,lbluyeTckn,lblrezTckn,lbluyeTcsi,lblkitapAdi,lblkitapAd3,lblrezTcsi,lblperAd;
	JTextField txtkitapAd;
	JTable j;
	JComboBox<String> combokitap,combokitap2,combotckn,combotckn2;
	JButton btnGetir,btnVer,btnAl,btnVer2,btnAl2,btntemizle,btncik;
	JScrollPane scrollp1,scrollp2;
	JPanel p1,p2,p3;
	DefaultListCellRenderer listRenderer;
	ImageIcon btntemizleres,btncikres;
	
	public UyeKitapPage() {
		
		setTitle("Üye Kitap İşlemleri");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		setLayout(null);
		setContentPane(new JLabel(new ImageIcon("src/otomasyon/app/bg2.jpg")));
		menu.uyeKitap.setBackground(Color.DARK_GRAY);
		menu.uyeKitap.setOpaque(true);
		menu.uyeKitap.setForeground(Color.white);
		menu.mb.setSelected(menu.uyeKitap);
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
		lblkitapList= new JLabel(" Verilmiş Kitaplar");
		lblkitapList.setBounds(300, 50,890, 40);
		lblkitapList.setBackground(Color.white);
		lblkitapList.setForeground(Color.DARK_GRAY);
		lblkitapList.setOpaque(true);
		lblkitapList.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblgecmiskitapList= new JLabel(" Teslim Tarihi Geçenler");
		lblgecmiskitapList.setBounds(300, 276, 890, 40);
		lblgecmiskitapList.setBackground(Color.white);
		lblgecmiskitapList.setForeground(Color.DARK_GRAY);
		lblgecmiskitapList.setOpaque(true);
		lblgecmiskitapList.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		List<UyeKitap>  uyeKitap = uyeKitapService.findUyeKitapOutOfDate();
		scrollp2 = uyeKitapTable(uyeKitap);
		scrollp2.setBounds(300,317,890,63);
		add(lblgecmiskitapList);
		add(scrollp2);
		uyeKitap = uyeKitapService.getAll();
		scrollp1 = uyeKitapTable(uyeKitap);
		scrollp1.setBounds(300,91,890,163);
		lblkitapVarMi=new JLabel(" Kitap Mevcut Mu?");
		lblkitapVarMi.setBounds(300, 420,400, 40);
		lblkitapVarMi.setBackground(Color.white);
		lblkitapVarMi.setForeground(Color.DARK_GRAY);
		lblkitapVarMi.setOpaque(true);
		lblkitapVarMi.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblalan=new JLabel();
		lblalan.setBounds(300, 460, 400, 140);
		Border border = BorderFactory.createLineBorder(Color.white, 2);
		lblalan.setBorder(border);
		lblalan.setOpaque(false);
		lblkitapAd=new JLabel("Kitap Adı : ");
		lblkitapAd.setBounds(320, 480, 150, 40);
		lblkitapAd.setForeground(Color.white);
		lblkitapAd.setFont(new Font("Courier",Font.TYPE1_FONT,16));
		combokitap=new JComboBox();
		combokitap.setBounds(420,480,250,40);
		combokitap.setFont(new Font("Courier",Font.TYPE1_FONT,16));
		combokitap.setBorder(null);
		listRenderer = new DefaultListCellRenderer();
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		combokitap.setRenderer(listRenderer);
		List<Kitap> kitap = kitapService.getAll();
		for(Kitap k:kitap)
			combokitap.addItem(k.getKitapAd());
		combokitap.setBorder(null);
		btnGetir=new JButton("Getir");
		btnGetir.setBounds(570, 540, 100, 40);
		btnGetir.setBorder(null);
		btnGetir.setBackground(Color.white);
		btnGetir.setForeground(Color.DARK_GRAY);
		btnGetir.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnGetir.addActionListener(this);
		lblalan1=new JLabel();
		lblalan1.setBounds(780, 410, 405, 260);
		lblalan1.setBorder(border);
		lblalan1.setOpaque(false);
		btntemizleres=new ImageIcon("src/otomasyon/app/clean.png");
		btntemizle=new JButton();
		btntemizle.setBackground(Color.white);
		btntemizle.setOpaque(true);
		btntemizle.setIcon(null);
		btntemizle.setOpaque(false);
		btntemizle.addActionListener(this);
		lblkitapAd2=new JLabel("Kitap Adı : ");
		lblkitapAd2.setBounds(800, 430, 150, 40);
		lblkitapAd2.setForeground(Color.white);
		lblkitapAd2.setFont(new Font("Courier",Font.TYPE1_FONT,16));
		combokitap2=new JComboBox();
		combokitap2.setBounds(900,430,250,40);
		combokitap2.setFont(new Font("Courier",Font.TYPE1_FONT,16));
		combokitap2.setBorder(null);
		combokitap2.setEnabled(false);
		combokitap2.setRenderer(listRenderer);
		for(Kitap k:kitap)
			combokitap2.addItem(k.getKitapAd());
		combokitap2.setBorder(null);
		lbluyeTckn=new JLabel("Uye Tckn : ");
		lbluyeTckn.setBounds(800, 490, 150, 40);
		lbluyeTckn.setForeground(Color.white);
		lbluyeTckn.setFont(new Font("Courier",Font.TYPE1_FONT,16));
		combotckn=new JComboBox();
		combotckn.setBounds(900,490,250,40);
		combotckn.setFont(new Font("Courier",Font.TYPE1_FONT,16));
		combotckn.setBorder(null);
		combotckn.setRenderer(listRenderer);
		List<Uye> uye = uyeService.getAll();
		for(Uye u:uye)
			combotckn.addItem(u.getUyeTckn());
		combotckn.setBorder(null);
		combotckn.setEnabled(false);
		lblrezTckn=new JLabel("Rez Tckn : ");
		lblrezTckn.setBounds(800, 550, 150, 40);
		lblrezTckn.setForeground(Color.white);
		lblrezTckn.setFont(new Font("Courier",Font.TYPE1_FONT,16));
		combotckn2=new JComboBox();
		combotckn2.setBounds(900,550,250,40);
		combotckn2.setFont(new Font("Courier",Font.TYPE1_FONT,16));
		combotckn2.setBorder(null);
		combotckn2.setRenderer(listRenderer);
		combotckn2.setEnabled(false);
		combotckn2.addItem("");
		for(Uye u:uye)
			combotckn2.addItem(u.getUyeTckn());
		combotckn2.setBorder(null);
		btnVer=new JButton("Ver");
		btnVer.setBounds(930, 610, 100, 40);
		btnVer.setBorder(null);
		btnVer.setBackground(Color.white);
		btnVer.setForeground(Color.DARK_GRAY);
		btnVer.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnVer.setEnabled(false);
		btnVer.addActionListener(this);
		btnAl=new JButton("Al");
		btnAl.setBounds(1050, 610, 100, 40);
		btnAl.setEnabled(false);
		btnAl.setBorder(null);
		btnAl.setBackground(Color.white);
		btnAl.setForeground(Color.DARK_GRAY);
		btnAl.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnAl.addActionListener(this);	
		add(lblperAd);
		add(btncik);
		add(lblalan1);
		add(btntemizle);
		add(lblkitapAd2);
		add(combokitap2);
		add(lbluyeTckn);
		add(combotckn);
		add(lblrezTckn);
		add(combotckn2);
		add(btnAl);
		add(btnVer);
		add(lblkitapAd);
		add(combokitap);
		add(lblkitapList);
		add(lblkitapVarMi);
		add(lblalan);
		add(btnGetir);
		add(scrollp1);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public static void main(String[]args) {
		UyeKitapPage uyeKitappage=new UyeKitapPage();
		if(LoginPage.kontrol==true)
			uyeKitappage.setVisible(true);
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
		if(e.getSource()==btnGetir) {
			
			btnGetir.setEnabled(false);
			btntemizle.setOpaque(true);
			btntemizle.setBounds(1153,412,30,30);
			btntemizle.setIcon(btntemizleres);
			combokitap.setEnabled(false);
			String kitapAd = (String) combokitap.getSelectedItem();
			List<UyeKitap> uyeKitap = uyeKitapService.getOne(kitapAd);
			
			if(uyeKitap.size()!=0) {
				for(UyeKitap uk:uyeKitap) {
					if(uk.getKitapDurum().equals("Verildi")) {
						if(uk.getUyeRezTckn()!=null) {
							combokitap2.setSelectedItem(uk.getKitapAd());
							combokitap2.setEnabled(false);
							combotckn.setSelectedItem(uk.getUyeTckn());
							combotckn.setEnabled(false);
							combotckn2.setSelectedItem(uk.getUyeRezTckn());
							combotckn2.setEnabled(false);
							btnAl.setEnabled(true);
						
						}
						else {
							combokitap2.setSelectedItem(uk.getKitapAd());
							combokitap2.setEnabled(false);
							combotckn.setSelectedItem(uk.getUyeTckn());
							combotckn.setEnabled(false);
							combotckn2.setEnabled(true);
							combotckn2.removeItem(uk.getUyeTckn());
							btnAl.setEnabled(true);
							btnVer.setEnabled(true);
						}
					}
				else {
					combokitap2.setSelectedItem(combokitap.getSelectedItem());
					combokitap2.setEnabled(false);
					combotckn.setEnabled(true);
					combotckn2.setEnabled(false);
					btnAl.setEnabled(false);
					btnVer.setEnabled(true);
				}
			  }
		   }
		   else {
				combokitap2.setSelectedItem(combokitap.getSelectedItem());
				combokitap2.setEnabled(false);
				combotckn.setEnabled(true);
				combotckn2.setEnabled(false);
				btnAl.setEnabled(false);
				btnVer.setEnabled(true); 
		   }	
		}	
		if(e.getSource()==btntemizle) {
			combokitap.setEnabled(true);
			combokitap2.setEnabled(false);
			combotckn.setEnabled(false);
			combotckn2.setEnabled(false);
			combotckn2.removeAllItems();
			combotckn2.addItem("");
			List<Uye> uye = uyeService.getAll();
			for(Uye u:uye)
				combotckn2.addItem(u.getUyeTckn());
			btnAl.setEnabled(false);
			btnVer.setEnabled(false);
			btnGetir.setEnabled(true);
			btntemizle.setOpaque(false);
			btntemizle.setIcon(null);
			btntemizle.setBounds(1500,412,30,30);
			
		}
		if(e.getSource()==btnVer) {
			
			UyeKitap uyeKitap = new UyeKitap();
			int uyeKitapId = uyeKitapService.getMaxId()+1;
			int kitapId = kitapService.getById((String)combokitap2.getSelectedItem());
			uyeKitap.setUyeKitapId(uyeKitapId);
			uyeKitap.setKitapId(kitapId);
			uyeKitap.setUyeTckn((String)combotckn.getSelectedItem());
			LocalDate now = LocalDate.now();
			LocalDate tomorrow = now.plusDays(15);
			uyeKitap.setAlisTrh(java.sql.Date.valueOf(now));
			uyeKitap.setVerisTrh(java.sql.Date.valueOf(tomorrow));
			uyeKitap.setKitapDurum("Verildi");
			uyeKitap.setUyeRezTckn(null);
			String rezTc = (String)combotckn2.getSelectedItem();
			if(rezTc.equals("")) {
				uyeKitapService.save(uyeKitap);
			}
			else {
				int uyeKitapId2 = uyeKitapService.getUyeKitapId((String)combotckn.getSelectedItem(),(String)combokitap2.getSelectedItem());
				uyeKitapService.update(uyeKitapId2,"Verildi",(String)combotckn2.getSelectedItem());
			}
			remove(scrollp1);
			List<UyeKitap> uyeKitap2 = uyeKitapService.getAll();
			scrollp1 = uyeKitapTable(uyeKitap2);
			scrollp1.setBounds(300,91,890,163);
			add(scrollp1);
			combokitap.setEnabled(true);
			combokitap2.setEnabled(false);
			combotckn.setEnabled(false);
			combotckn2.setEnabled(false);
			combotckn2.removeAllItems();
			combotckn2.addItem("");
			List<Uye> uye = uyeService.getAll();
			for(Uye u:uye)
				combotckn2.addItem(u.getUyeTckn());
			btnAl.setEnabled(false);
			btnVer.setEnabled(false);
			btnGetir.setEnabled(true);
			btntemizle.setOpaque(false);
			btntemizle.setIcon(null);
			btntemizle.setBounds(1500,412,30,30);
			
		}
		if(e.getSource()==btnAl) {
			
			int uyeKitapId2 = uyeKitapService.getUyeKitapId((String)combotckn.getSelectedItem(),(String)combokitap2.getSelectedItem());
			uyeKitapService.update(uyeKitapId2,"Alındı",(String)combotckn2.getSelectedItem());
			String rezTc = (String)combotckn2.getSelectedItem();
			

			if(rezTc.equals("")) {
				System.out.print("Boş");
				uyeKitapService.update(uyeKitapId2,"Alındı",null);
			}
			else {
				System.out.print("Dolu");
				uyeKitapService.update(uyeKitapId2,"Alındı",(String)combotckn2.getSelectedItem());
				UyeKitap uyeKitap = new UyeKitap();
				int uyeKitapId = uyeKitapService.getMaxId()+1;
				int kitapId = kitapService.getById((String)combokitap2.getSelectedItem());
				uyeKitap.setUyeKitapId(uyeKitapId);
				uyeKitap.setKitapId(kitapId);
				uyeKitap.setUyeTckn((String)combotckn2.getSelectedItem());
				LocalDate now = LocalDate.now();
				LocalDate tomorrow = now.plusDays(15);
				uyeKitap.setAlisTrh(java.sql.Date.valueOf(now));
				uyeKitap.setVerisTrh(java.sql.Date.valueOf(tomorrow));
				uyeKitap.setKitapDurum("Verildi");
				uyeKitap.setUyeRezTckn(null);
				uyeKitapService.save(uyeKitap);
			}
			remove(scrollp1);
			List<UyeKitap> uyeKitap2 = uyeKitapService.getAll();
			scrollp1 = uyeKitapTable(uyeKitap2);
			scrollp1.setBounds(300,91,890,163);
			add(scrollp1);
			combokitap.setEnabled(true);
			combokitap2.setEnabled(false);
			combotckn.setEnabled(false);
			combotckn2.setEnabled(false);
			combotckn2.removeAllItems();
			combotckn2.addItem("");
			List<Uye> uye = uyeService.getAll();
			for(Uye u:uye)
				combotckn2.addItem(u.getUyeTckn());
			btnAl.setEnabled(false);
			btnVer.setEnabled(false);
			btnGetir.setEnabled(true);
			btntemizle.setOpaque(false);
			btntemizle.setIcon(null);
			btntemizle.setBounds(1500,412,30,30);
		}
	}	
	public JScrollPane uyeKitapTable(List<UyeKitap> uyekitap) {
		
		String veri[][]=new String[uyekitap.size()][];
		
		for (int i =0; i < uyekitap.size(); i++) {  
		        	 
		                veri[i]=new String[]{  
		               
		                		String.valueOf(uyekitap.get(i).getUyeKitapId()),  
		                		String.valueOf(uyekitap.get(i).getUyeTckn()),  
		                		String.valueOf(uyekitap.get(i).getUyeAd()),
		                		String.valueOf(uyekitap.get(i).getKitapAd()),
		                		String.valueOf(uyekitap.get(i).getAlisTrh()),
		                		String.valueOf(uyekitap.get(i).getVerisTrh()),
		                		String.valueOf(uyekitap.get(i).getKitapDurum()),
		                		String.valueOf(uyekitap.get(i).getUyeRezTckn())
		                		
		                };
		                
		 }
		
		 j = new JTable();
		 DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
	     rendar.setHorizontalAlignment(JLabel.CENTER);
	     j.setRowHeight(20);
		 TableModel tabloModeli=new DefaultTableModel(veri,new String[] {"ID", "TCKN","Üye Ad","Kitap Ad","Alış Tarihi","Veriş Tarihi","Kitap Durum","Rez TCKN"});
		 j.setModel(tabloModeli);
		 for(int i=0;i<j.getColumnCount();i++)
			 j.getColumnModel().getColumn(i).setCellRenderer(rendar);
	     j.getColumnModel().setColumnMargin(20);
	     TableColumnModel columnModel = j.getColumnModel();
	     	columnModel.getColumn(0).setPreferredWidth(40);
	     columnModel.getColumn(1).setPreferredWidth(125);
	     columnModel.getColumn(2).setPreferredWidth(160);
	     columnModel.getColumn(3).setPreferredWidth(160);
	     columnModel.getColumn(4).setPreferredWidth(100);
	     columnModel.getColumn(5).setPreferredWidth(100);
	     columnModel.getColumn(6).setPreferredWidth(80);
	     columnModel.getColumn(7).setPreferredWidth(125);
	     JScrollPane sp = new JScrollPane(j);
		 return sp;
	
	}
}
