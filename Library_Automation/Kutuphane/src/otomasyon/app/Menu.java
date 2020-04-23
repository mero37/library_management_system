package otomasyon.app;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class Menu {
	static JMenuBar mb;    
	static JMenuItem anasayfa,kategori,personel,yazar,kitap,uye,uyeKitap; 
	
	public Menu(){
		mb=new JMenuBar();    
		anasayfa=new JMenuItem("                     Anasayfa");
		kategori=new JMenuItem("             Kategori İşlemleri");  
		personel=new JMenuItem("             Personel İşlemleri"); 
		yazar=new JMenuItem("                Yazar İşlemleri");    
		kitap=new JMenuItem("                 Kitap İşlemleri");
		uye=new JMenuItem("                  Uye İşlemleri");
		uyeKitap=new JMenuItem("             Üye Kitap İşlemleri");
		mb.setFont(new Font("Courier",Font.TYPE1_FONT,15));
		mb.setBackground(Color.WHITE);
		mb.setOpaque(true);
		mb.add(anasayfa);
		mb.add(kategori);
		mb.add(yazar);
		mb.add(personel);
		mb.add(uye);
		mb.add(kitap);
		mb.add(uyeKitap);
	}


}
