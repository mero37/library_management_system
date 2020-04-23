package otomasyon.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import otomasyon.DAO.KitapDAO;
import otomasyon.DAO.PersonelDAO;
import otomasyon.DAO.UyeDAO;
import otomasyon.service.KitapService;
import otomasyon.service.PersonelService;
import otomasyon.service.UyeService;

public class HomePage extends JFrame implements ActionListener {

	PersonelDAO personelDAO=new PersonelDAO();
	PersonelService personelService=new PersonelService(personelDAO);

	KitapDAO kitapDAO=new KitapDAO();
	KitapService kitapService=new KitapService(kitapDAO);

	UyeDAO uyeDAO=new UyeDAO();
	UyeService uyeService=new UyeService(uyeDAO);
	
	Menu menu=new Menu();
	JTextPane txtonsoz;
	JLabel lblonsoz,lblkitap,lbluye,lblper,lblkitapsayi,lbluyesayi,lblpersayi,lblperAd;
	JButton btncik;
	ImageIcon btncikres;
	
	public HomePage() {
		setTitle("Anasayfa");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		setLayout(null);
		setContentPane(new JLabel(new ImageIcon("src/otomasyon/app/bg2.jpg")));
		menu.anasayfa.setBackground(Color.DARK_GRAY);
		menu.anasayfa.setOpaque(true);
		menu.anasayfa.setForeground(Color.white);
		menu.mb.setSelected(menu.anasayfa);
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
		txtonsoz=new JTextPane();
		txtonsoz.setText("Nefret ettiği bir işi olup da başarıya ulaşmış bir insan tanıyor musunuz, ben tanımıyorum. Başarının sırlarından birisi de yaptığınız işle, sevdiğiniz şeyler arasında sıkı bir ilişki kurmaktır.");
		txtonsoz.setBounds(300,120,1000,200);
		txtonsoz.setEnabled(false);	
		txtonsoz.setOpaque(false);
		txtonsoz.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		txtonsoz.setForeground(Color.white);
		lblonsoz=new JLabel("Anthony Robbins");
		lblonsoz.setBounds(1100,110,150,200);
		lblonsoz.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		lblonsoz.setForeground(Color.white);
		lblkitap=new JLabel("KİTAP SAYISI");
		lblkitap.setBounds(330,320,250,30);
		lblkitap.setFont(new Font("Courier",Font.TYPE1_FONT,30));
		lblkitap.setForeground(Color.white);
		lblkitap.setHorizontalAlignment(lblkitap.CENTER);
		lbluye=new JLabel("ÜYE SAYISI");
		lbluye.setBounds(630,320,250,30);
		lbluye.setFont(new Font("Courier",Font.TYPE1_FONT,30));
		lbluye.setForeground(Color.white);
		lbluye.setHorizontalAlignment(lbluye.CENTER);
		lblper=new JLabel("PERSONEL SAYISI");
		lblper.setBounds(930,320,280,30);
		lblper.setFont(new Font("Courier",Font.TYPE1_FONT,30));
		lblper.setForeground(Color.white);
		lblper.setHorizontalAlignment(lblper.CENTER);
		int kitapSayisi= kitapService.countKitap();
		lblkitapsayi=new JLabel(Integer.toString(kitapSayisi));
		lblkitapsayi.setBounds(330,390,250,100);
		lblkitapsayi.setFont(new Font("Courier",Font.TYPE1_FONT,120));
		lblkitapsayi.setForeground(Color.white);
		lblkitapsayi.setHorizontalAlignment(lblkitapsayi.CENTER);
		int uyeSayisi= uyeService.countUye();
		lbluyesayi=new JLabel(Integer.toString(uyeSayisi));
		lbluyesayi.setBounds(630,390,250,100);
		lbluyesayi.setFont(new Font("Courier",Font.TYPE1_FONT,120));
		lbluyesayi.setForeground(Color.white);
		lbluyesayi.setHorizontalAlignment(lbluyesayi.CENTER);
		int perSayisi= personelService.countPersonel();
		lblpersayi=new JLabel(Integer.toString(perSayisi));
		lblpersayi.setBounds(930,390,280,100);
		lblpersayi.setFont(new Font("Courier",Font.TYPE1_FONT,120));
		lblpersayi.setForeground(Color.white);
		lblpersayi.setHorizontalAlignment(lblpersayi.CENTER);
		add(lblperAd);
		add(btncik);
		add(txtonsoz);
		add(lblonsoz);
		add(lblkitapsayi);
		add(lbluyesayi);
		add(lblpersayi);
		add(lblkitap);
		add(lbluye);
		add(lblper);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[]args) {
		HomePage homepage=new HomePage();
		if(LoginPage.kontrol==true)
			homepage.setVisible(true);
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
		
	}
	
}
