package otomasyon.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
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

import com.sun.jdi.connect.Connector.StringArgument;

import otomasyon.DAO.KategoriDAO;
import otomasyon.DAO.KitapDAO;
import otomasyon.DAO.YazarDAO;
import otomasyon.model.Kategori;
import otomasyon.model.Kitap;
import otomasyon.model.Uye;
import otomasyon.model.Yazar;
import otomasyon.service.KategoriService;
import otomasyon.service.KitapService;
import otomasyon.service.YazarService;

public class KitapPage extends JFrame implements ActionListener {
	
	KitapDAO kitapDAO=new KitapDAO();
	KitapService kitapService=new KitapService(kitapDAO);
	KategoriDAO kategoriDAO=new KategoriDAO();
	KategoriService kategoriService=new KategoriService(kategoriDAO);
	YazarDAO yazarDAO=new YazarDAO();
	YazarService yazarService=new YazarService(yazarDAO);
	
	Menu menu=new Menu();
	int seconds=3;
	JScrollPane scrollp;
	JLabel lblkitapList,lblfiltre,lblfiltrealan,lblduzenle,lblduzenlealan,lbldurum,lblfiltrenecek,lblkitapId,lblkitapAd,lblkitapAd2,lblkitapSyf,lblkitapOzt,lblkatAd,lblyazAd,lbldolapNo,lblkitapRaf,lblperAd;
	JTextField txtkitapAd,txtkitapId,txtkitapAd2,txtkitapSyf,txtdolapNo,txtkitapRaf;
	JTextArea txtkitapOzt;
	JComboBox<String> combokat,comboyaz,combokat2,comboyaz2;
	JButton btnfiltre,btnekle,btnsil,btnguncelle,btntemizle,btncik;
	JRadioButton rbad,rbkat,rbyaz;
	ButtonGroup bg;
	DefaultListCellRenderer listRenderer;
    JTable j;
    ImageIcon btntemizleres,btncikres;
	
	public KitapPage() {
		setTitle("Kitap İşlemleri");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		setLayout(null);
		setContentPane(new JLabel(new ImageIcon("src/otomasyon/app/bg2.jpg")));
		menu.kitap.setBackground(Color.DARK_GRAY);
		menu.kitap.setOpaque(true);
		menu.kitap.setForeground(Color.white);
		menu.mb.setSelected(menu.kitap);
		add(menu.mb);
		setJMenuBar(menu.mb);
		menu.anasayfa.addActionListener(this);
		menu.kategori.addActionListener(this);
		menu.personel.addActionListener(this);
		menu.yazar.addActionListener(this);
		menu.kitap.addActionListener(this);
		menu.uye.addActionListener(this);
		menu.uyeKitap.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		lblkitapList=new JLabel(" Kitap Listesi");
		lblkitapList.setBounds(330, 30, 850, 40);
		lblkitapList.setBackground(Color.white);
		lblkitapList.setForeground(Color.DARK_GRAY);
		lblkitapList.setOpaque(true);
		lblkitapList.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblfiltre=new JLabel(" Kitap Filtrele");
		lblfiltre.setBounds(300, 320, 380, 40);
		lblfiltre.setBackground(Color.white);
		lblfiltre.setForeground(Color.DARK_GRAY);
		lblfiltre.setOpaque(true);
		lblfiltre.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblfiltrealan=new JLabel();
		lblfiltrealan.setBounds(300, 360, 380, 170);
		Border border = BorderFactory.createLineBorder(Color.white, 2);
		lblfiltrealan.setBorder(border);
		lblfiltrealan.setForeground(Color.DARK_GRAY);
		lblfiltrealan.setOpaque(false);
		rbad=new JRadioButton("Adına Göre");    
		rbad.setBounds(315,370,100,30);
		rbad.setForeground(Color.white);
		rbad.setOpaque(false);
		rbad.setFont(new Font("Courier",Font.TYPE1_FONT,12));
		rbad.addActionListener(this);
		rbad.setSelected(true);
		rbkat=new JRadioButton("Kategorisine Göre");    
		rbkat.setBounds(415,370,140,30);
		rbkat.setForeground(Color.white);
		rbkat.setOpaque(false);
		rbkat.setFont(new Font("Courier",Font.TYPE1_FONT,12));
		rbkat.addActionListener(this);
		rbyaz=new JRadioButton("Yazarına Göre");    
		rbyaz.setBounds(555,370,120,30);
		rbyaz.setForeground(Color.white);
		rbyaz.setOpaque(false);
		rbyaz.setFont(new Font("Courier",Font.TYPE1_FONT,12));
		rbyaz.addActionListener(this);
		bg=new ButtonGroup();
		bg.add(rbad);
		bg.add(rbkat);
		bg.add(rbyaz);
		lblfiltrenecek=new JLabel("Kitap Adı : ");
		lblfiltrenecek.setBounds(320, 410, 150, 40);
		lblfiltrenecek.setForeground(Color.white);
		lblfiltrenecek.setFont(new Font("Courier",Font.TYPE1_FONT,18));
		txtkitapAd = new JTextField();
		txtkitapAd.setBounds(420,411,235,40);
		txtkitapAd.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		txtkitapAd.setBackground(Color.white);
		txtkitapAd.setForeground(Color.DARK_GRAY);
		txtkitapAd.setHorizontalAlignment(txtkitapAd.CENTER);
		txtkitapAd.setBorder(null);
		combokat = new JComboBox<String>();
		combokat.setBounds(420,411,235,40);
		combokat.setFont(new Font("Courier",Font.TYPE1_FONT,14));
	    listRenderer = new DefaultListCellRenderer();
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
		combokat.setRenderer(listRenderer);
		combokat.addItem("Hepsi");
		List<Kategori> kategori = kategoriService.getAll();
		for(Kategori k:kategori)
			combokat.addItem(k.getKategoriAd());
		combokat.setBorder(null);
		comboyaz = new JComboBox<String>();
		comboyaz.setBounds(420,411,235,40);
		comboyaz.setFont(new Font("Courier",Font.TYPE1_FONT,13));
	    listRenderer = new DefaultListCellRenderer();
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
		comboyaz.setRenderer(listRenderer);
		comboyaz.addItem("Hepsi");
		List<Yazar> yazar = yazarService.getAll();
		for(Yazar y:yazar)
			comboyaz.addItem(y.getYazarAd());
		comboyaz.setBorder(null);
		btnfiltre=new JButton("Filtrele");
		btnfiltre.setBounds(555, 471, 100, 40);
		btnfiltre.setBorder(null);
		btnfiltre.setBackground(Color.white);
		btnfiltre.setForeground(Color.DARK_GRAY);
		btnfiltre.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnfiltre.addActionListener(this);
		lblduzenle=new JLabel(" Kitap Düzenle");
		lblduzenle.setBounds(760, 200, 440, 40);
		lblduzenle.setBackground(Color.white);
		lblduzenle.setForeground(Color.DARK_GRAY);
		lblduzenle.setOpaque(true);
		lblduzenle.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lbldurum = new JLabel();
		lbldurum.setBounds(960,200,200,40);
		lbldurum.setBackground(Color.white);
		lbldurum.setForeground(Color.red);
		lbldurum.setOpaque(true);
		lbldurum.setHorizontalAlignment(lbldurum.CENTER);
		lbldurum.setFont(new Font("Courier",Font.TYPE1_FONT,13));
		lblduzenlealan=new JLabel();
		lblduzenlealan.setBounds(760, 240, 440, 430);
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
		lblkitapId=new JLabel("Kitap Id : ");
		lblkitapId.setBounds(790, 265, 150, 40);
		lblkitapId.setForeground(Color.white);
		lblkitapId.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		txtkitapId=new JTextField();
		int kitapId= kitapService.getMaxId()+1;
		txtkitapId.setText(Integer.toString(kitapId));
		txtkitapId.setBounds(880,265,80,40);
		txtkitapId.setEnabled(false);
		txtkitapId.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		txtkitapId.setHorizontalAlignment(txtkitapId.CENTER);
		txtkitapId.setBorder(null);
		lblkitapRaf=new JLabel("Kitap Raf : ");
		lblkitapRaf.setBounds(980, 265, 150, 40);
		lblkitapRaf.setForeground(Color.white);
		lblkitapRaf.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		txtkitapRaf=new JTextField();
		txtkitapRaf.setBounds(1080,265,80,40);
		txtkitapRaf.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		txtkitapRaf.setHorizontalAlignment(txtkitapRaf.CENTER);
		txtkitapRaf.setBorder(null);
		lblkitapAd=new JLabel("Kitap Adı : ");
		lblkitapAd.setBounds(790, 330, 150, 40);
		lblkitapAd.setForeground(Color.white);
		lblkitapAd.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		txtkitapAd2=new JTextField();
		txtkitapAd2.setBounds(880,330,280,40);
		txtkitapAd2.setFont(new Font("Courier",Font.TYPE1_FONT,16));
		txtkitapAd2.setHorizontalAlignment(txtkitapRaf.CENTER);
		txtkitapAd2.setBorder(null);
		lblkatAd=new JLabel("Kategori : ");
		lblkatAd.setBounds(790, 400, 150, 40);
		lblkatAd.setForeground(Color.white);
		lblkatAd.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		combokat2 = new JComboBox<String>();
		combokat2.setBounds(880,401,280,40);
		combokat2.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		combokat2.setRenderer(listRenderer);
		for(Kategori k:kategori)
			combokat2.addItem(k.getKategoriAd());
		combokat2.setBorder(null);
		combokat2.addActionListener(this);
		lblyazAd=new JLabel("Yazar : ");
		lblyazAd.setBounds(790, 470, 150, 40);
		lblyazAd.setForeground(Color.white);
		lblyazAd.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		comboyaz2 = new JComboBox<String>();
		comboyaz2.setBounds(880,471,280,40);
		comboyaz2.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		comboyaz2.setRenderer(listRenderer);
		for(Yazar y:yazar)
			comboyaz2.addItem(y.getYazarAd());
		comboyaz2.setBorder(null);
		lblkitapSyf=new JLabel("Sayfa : ");
		lblkitapSyf.setBounds(790, 540, 150, 40);
		lblkitapSyf.setForeground(Color.white);
		lblkitapSyf.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		txtkitapSyf=new JTextField();
		txtkitapSyf.setBounds(880,540,80,40);
		txtkitapSyf.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		txtkitapSyf.setHorizontalAlignment(txtkitapSyf.CENTER);
		txtkitapSyf.setBorder(null);
		lbldolapNo=new JLabel("Dolap No : ");
		lbldolapNo.setBounds(980, 540, 150, 40);
		lbldolapNo.setForeground(Color.white);
		lbldolapNo.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		txtdolapNo=new JTextField(""+(combokat2.getSelectedIndex()+1));
		txtdolapNo.setBounds(1080,540,80,40);
		txtdolapNo.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		txtdolapNo.setHorizontalAlignment(txtdolapNo.CENTER);
		txtdolapNo.setBorder(null);
		txtdolapNo.setEnabled(false);
		btnekle=new JButton("Ekle");
		btnekle.setBounds(790, 606, 100, 40);
		btnekle.setBorder(null);
		btnekle.setBackground(Color.white);
		btnekle.setForeground(Color.DARK_GRAY);
		btnekle.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnekle.addActionListener(this);
		btnsil=new JButton("Sil");
		btnsil.setBounds(927, 606, 100, 40);
		btnsil.setBorder(null);
		btnsil.setBackground(Color.white);
		btnsil.setForeground(Color.DARK_GRAY);
		btnsil.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnsil.addActionListener(this);
		btnsil.setEnabled(false);
		btnguncelle=new JButton("Güncelle");
		btnguncelle.setBounds(1062, 606, 100, 40);
		btnguncelle.setBorder(null);
		btnguncelle.setBackground(Color.white);
		btnguncelle.setForeground(Color.DARK_GRAY);
		btnguncelle.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnguncelle.addActionListener(this);
		btnguncelle.setEnabled(false);
		List<Kitap> kitap = kitapService.getAll();
		scrollp=kitapTable(kitap);
		scrollp.setBounds(330, 71, 850, 103);
		add(lblperAd);
		add(btncik);
		add(lblfiltre);
		add(lblfiltrealan);
		add(lblfiltrenecek);
		add(rbad);
		add(rbkat);
		add(rbyaz);
		add(txtkitapAd);
		add(lblkitapList);
		add(btnfiltre);
		add(scrollp);
		add(lblduzenle);
		add(lblduzenlealan);
		add(lblkitapId);
		add(txtkitapId);
		add(lblkitapRaf);
		add(txtkitapRaf);
		add(lblkitapAd);
		add(txtkitapAd2);
		add(lblkatAd);
		add(combokat2);
		add(lblyazAd);
		add(comboyaz2);
		add(lblkitapSyf);
		add(txtkitapSyf);
		add(lbldolapNo);
		add(lbldurum);
		add(txtdolapNo);
		add(btnekle);
		add(btnsil);
		add(btnguncelle);
		add(scrollp);
		add(btntemizle);
	}
	public static void main(String[]args) {
		KitapPage kitapPage=new KitapPage();
		if(LoginPage.kontrol==true)
			kitapPage.setVisible(true);
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
		if(e.getSource()==rbad) {
			remove(combokat);
			combokat.setBounds(2500,0,0,0);
			remove(comboyaz);
			comboyaz.setBounds(2500,0,0,0);
			add(txtkitapAd);
			txtkitapAd.setBounds(420,411,235,40);
			lblfiltrenecek.setText("Kitap Adı :");
		}
		if(e.getSource()==rbkat) {
			add(combokat);
			combokat.setBounds(420,411,235,40);
			txtkitapAd.setBounds(2500,0,0,0);
			remove(txtkitapAd);
			remove(comboyaz);
			comboyaz.setBounds(2500,0,0,0);
			lblfiltrenecek.setText("Kategori : ");
		}
		if(e.getSource()==rbyaz) {
			add(comboyaz);
			remove(combokat);
			combokat.setBounds(2500,0,0,0);
			txtkitapAd.setBounds(2500,0,0,0);
			remove(txtkitapAd);
			comboyaz.setBounds(420,411,235,40);
			lblfiltrenecek.setText("Yazar :");
		}
		if(e.getSource()==btntemizle) {
			int kitapId= kitapService.getMaxId()+1;
			txtkitapId.setText(Integer.toString(kitapId));
			txtkitapId.setEnabled(false);
			txtkitapAd2.setText("");
			combokat2.setSelectedIndex(0);
			comboyaz2.setSelectedIndex(0);
			txtkitapSyf.setText("");
			txtkitapRaf.setText("");
			txtdolapNo.setText("1");
			btnekle.setEnabled(true);
			btnguncelle.setEnabled(false);
			btnsil.setEnabled(false);
			btntemizle.setOpaque(false);
			btntemizle.setIcon(null);
			btntemizle.setBounds(1168,210,30,30);
			
		}
		if(e.getSource()==btnfiltre) {
			String kitapFiltre = null;
			List<Kitap> liste = new ArrayList<Kitap>();
			if(rbad.isSelected()) {
				kitapFiltre = txtkitapAd.getText();
				liste = kitapService.findKitapByName(kitapFiltre);
			}
			if(rbkat.isSelected()) {
				kitapFiltre = (String) combokat.getSelectedItem();
				liste = kitapService.findKitapByKategori(kitapFiltre);
			}
			if(rbyaz.isSelected()) {
				kitapFiltre = (String) comboyaz.getSelectedItem();
				liste = kitapService.findKitapByYazar(kitapFiltre);
			}
			remove(scrollp);
			List<Kitap> liste2 = kitapService.getAll();
			if(kitapFiltre.equals("Hepsi"))
				scrollp=kitapTable(liste2);
			else
				scrollp=kitapTable(liste);
			scrollp.setBounds(330, 71, 850, 103);
			add(scrollp);
		}
		if(e.getSource()==combokat2) {
			txtdolapNo.setText((""+(combokat2.getSelectedIndex()+1)));
		}
		if(e.getSource()==btnekle) {
			boolean kontrol = control();
			Kitap kitap = new Kitap();
			int katId = kategoriService.KategoriById(combokat2.getSelectedItem().toString());
			int yazId = yazarService.YazarById(comboyaz2.getSelectedItem().toString());
			kitap.setKitapId(Integer.parseInt(txtkitapId.getText()));
			kitap.setKitapAd(txtkitapAd2.getText());
			kitap.setKatId(katId);
			kitap.setYazarId(yazId);
			kitap.setKitapSyf(Integer.parseInt(txtkitapSyf.getText()));
			kitap.setDolapNo(Integer.parseInt(txtdolapNo.getText()));
			kitap.setKitapRaf(Integer.parseInt(txtkitapRaf.getText()));
			if(kontrol==true) {
				kitap = kitapService.save(kitap);
				List<Kitap> kitap2 = kitapService.getAll();
				remove(scrollp);
				scrollp=kitapTable(kitap2);
				scrollp.setBounds(330, 71, 850, 103);
				add(scrollp);
				int kitapId= kitapService.getMaxId()+1;
				txtkitapId.setText(Integer.toString(kitapId));
				txtkitapId.setEnabled(false);
				txtkitapAd2.setText("");
				txtkitapRaf.setText("");
				combokat2.setSelectedIndex(0);
				comboyaz2.setSelectedIndex(0);
				txtkitapSyf.setText("");
				txtdolapNo.setText("1");
			}
		}
		if(e.getSource()==btnsil) {
			boolean kontrol = control();
			int kitapId = Integer.parseInt(txtkitapId.getText());
			if(kontrol==true) {
				kitapService.delete(kitapId);
				List<Kitap> kitap = kitapService.getAll();
				remove(scrollp);
				scrollp=kitapTable(kitap);
				scrollp.setBounds(330, 71, 850, 103);
				add(scrollp);
				btnekle.setEnabled(true);
				btnsil.setEnabled(false);
				btnguncelle.setEnabled(false);
				btntemizle.setBounds(1168,210,30,30);
				kitapId= kitapService.getMaxId()+1;
				txtkitapId.setText(Integer.toString(kitapId));
				txtkitapRaf.setText("");
				txtkitapId.setEnabled(false);
				txtkitapAd2.setText("");
				combokat2.setSelectedIndex(0);
				comboyaz2.setSelectedIndex(0);
				txtkitapSyf.setText("");
				txtdolapNo.setText("1");
			}
		}
		if(e.getSource()==btnguncelle) {
				boolean kontrol = control();
				String kitapAd = txtkitapAd2.getText();
				int kitapId = Integer.parseInt(txtkitapId.getText());
				int kitapSyf = Integer.parseInt(txtkitapSyf.getText());
				int katId = kategoriService.KategoriById(combokat2.getSelectedItem().toString());
				int yazId = yazarService.YazarById(comboyaz2.getSelectedItem().toString());
				int kitapRaf = Integer.parseInt(txtkitapRaf.getText());
				if(kontrol==true) {
					kitapService.update(kitapId,kitapAd,kitapSyf,katId,yazId,kitapRaf);
					List<Kitap> kitap = kitapService.getAll();
					remove(scrollp);
					scrollp=kitapTable(kitap);
					scrollp.setBounds(330, 71, 850, 103);
					add(scrollp);
					btnekle.setEnabled(true);
					btnsil.setEnabled(false);
					btnguncelle.setEnabled(false);
					btntemizle.setBounds(1168,210,30,30);
					kitapId= kitapService.getMaxId()+1;
					txtkitapId.setText(Integer.toString(kitapId));
					txtkitapRaf.setText("");
					txtkitapId.setEnabled(false);
					txtkitapAd2.setText("");
					combokat2.setSelectedIndex(0);
					comboyaz2.setSelectedIndex(0);
					txtkitapSyf.setText("");
					txtdolapNo.setText("1");
			}
		}
		
	}
	public JScrollPane kitapTable(List<Kitap> kitap) {
		
		String veri[][]=new String[kitap.size()][];
		
		for (int i =0; i < kitap.size(); i++) {  
		        	 
		                veri[i]=new String[]{  
		               
		                		String.valueOf(kitap.get(i).getKitapId()),  
		                		String.valueOf(kitap.get(i).getKitapAd()),  
		                		String.valueOf(kitap.get(i).getKatAd()),
		                		String.valueOf(kitap.get(i).getYazarAd()),
		                		String.valueOf(kitap.get(i).getKitapSyf()),
		                		String.valueOf(kitap.get(i).getDolapNo()),
		                		String.valueOf(kitap.get(i).getKitapRaf())
		                		
		                };
		                
		 }
		
		 j = new JTable();
		 DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
	     rendar.setHorizontalAlignment(JLabel.CENTER);
	     j.setRowHeight(20);
		 TableModel tabloModeli=new DefaultTableModel(veri,new String[] {"ID", "Kitap Adı","Kategori","Yazar","Sayfa","Dolap No","Kitap Raf"});
		 j.setModel(tabloModeli);
		 getSelectedData();
		 for(int i=0;i<j.getColumnCount();i++)
			 j.getColumnModel().getColumn(i).setCellRenderer(rendar);
	     j.getColumnModel().setColumnMargin(20);
	     TableColumnModel columnModel = j.getColumnModel();
	     	columnModel.getColumn(0).setPreferredWidth(60);
	     columnModel.getColumn(1).setPreferredWidth(200);
	     columnModel.getColumn(2).setPreferredWidth(200);
	     columnModel.getColumn(3).setPreferredWidth(200);
	     columnModel.getColumn(4).setPreferredWidth(70);
	     columnModel.getColumn(5).setPreferredWidth(60);
	     columnModel.getColumn(6).setPreferredWidth(60);
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
						String kitapId = j.getValueAt(row, 0).toString();
						String kitapAd = j.getValueAt(row, 1).toString();
						String katAd = j.getValueAt(row, 2).toString();
						String yazAd = j.getValueAt(row, 3).toString();
						String sayfa = j.getValueAt(row, 4).toString();
						String no = j.getValueAt(row, 5).toString();
						String raf = j.getValueAt(row, 6).toString();
						txtkitapId.setText(kitapId);
						txtkitapId.setEnabled(false);
						txtkitapAd2.setText(kitapAd);
						combokat2.setSelectedItem(katAd);
						comboyaz2.setSelectedItem(yazAd);
						txtkitapSyf.setText(sayfa);
						txtdolapNo.setText(no);
						txtkitapRaf.setText(raf);
						btnekle.setEnabled(false);
						btnsil.setEnabled(true);
						btnguncelle.setEnabled(true);
						btntemizle.setOpaque(true);
						btntemizle.setBounds(1168,242,30,30);
						btntemizle.setIcon(btntemizleres);
						lbldurum.setText("");
					}
				}	
			}
		});
	}
	public boolean control() {
		boolean kontrol=false;
		String kitapAd = txtkitapId.getText(); 
		if(kitapAd.equals("") || txtkitapRaf.getText().equals("") || txtkitapSyf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Lütfen alanları boş bırakmayınız.");
	        kontrol = false;
		  }
		else {
			 try {
				 int kitapRaf = Integer.parseInt(txtkitapRaf.getText());
				 int kitapSayfa = Integer.parseInt(txtkitapSyf.getText());
			    } catch (NumberFormatException nfe) {
			    	JOptionPane.showMessageDialog(null, "Lütfen Raf ve Sayfa alanlarına sayısal değer giriniz.");
			    	return kontrol=false;
			    }
			lbldurum.setText("");
	        kontrol = true;
	    }

		return kontrol;
	}
}
		


