package otomasyon.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import otomasyon.DAO.PersonelDAO;
import otomasyon.model.Personel;
import otomasyon.service.PersonelService;

public class LoginPage extends JFrame implements ActionListener{
	
	private JTextField txtTckn;
	private JPasswordField txtSifre;
	private JLabel lbldurum,lblgirisyap,lbltckn,lblsifre;
	private JButton btngiris;
	private JButton btnunut;
	static boolean kontrol=false;
	static String adSoyad;
	
	public LoginPage() {
		setTitle("Halk Kütüphanesi");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		setContentPane(new JLabel(new ImageIcon("src/otomasyon/app/bg2.jpg")));
		JPanel p=new JPanel();
	    p.setLayout(null);
	    p.setBounds(500,170,400,340);
	    p.setOpaque(false);
	    Border border = BorderFactory.createLineBorder(Color.white, 2);
		p.setBorder(border);
		lblgirisyap=new JLabel("Giriş Yap");
		lblgirisyap.setBounds(0,0, 400, 60);
		lblgirisyap.setBackground(Color.white);
		lblgirisyap.setForeground(Color.DARK_GRAY);
		lblgirisyap.setOpaque(true);
		lblgirisyap.setHorizontalAlignment(lblgirisyap.CENTER);
		lblgirisyap.setFont(new Font("Courier",Font.TYPE1_FONT,36));
		lbltckn=new JLabel("TCKN :");
		lbltckn.setBounds(20,100,150, 40);
		lbltckn.setForeground(Color.white);
		lbltckn.setFont(new Font("Courier",Font.TYPE1_FONT,24));
		lblsifre=new JLabel("ŞİFRE :");
		lblsifre.setBounds(20,180,150, 40);
		lblsifre.setForeground(Color.white);
		lblsifre.setFont(new Font("Courier",Font.TYPE1_FONT,24));
		txtTckn=new JTextField();
		txtTckn.setBounds(133, 101, 230, 40);
		txtTckn.setOpaque(true);
		txtTckn.setBackground(Color.white);
		txtTckn.setHorizontalAlignment(txtTckn.CENTER);
		txtTckn.setForeground(Color.DARK_GRAY);
		txtTckn.setFont(new Font("Courier",Font.TYPE1_FONT,16));
		txtTckn.setBorder(null);
		txtSifre=new JPasswordField();
		txtSifre.setBounds(133, 181, 230, 40);
		txtSifre.setOpaque(true);
		txtSifre.setBackground(Color.white);
		txtSifre.setHorizontalAlignment(txtSifre.CENTER);
		txtSifre.setForeground(Color.DARK_GRAY);
		txtSifre.setFont(new Font("Courier",Font.TYPE1_FONT,16));
		txtSifre.setBorder(null);
		btngiris=new JButton("Giriş");
		btngiris.setBounds(133, 260, 100, 40);
		btngiris.setBackground(Color.white);
		btngiris.setForeground(Color.DARK_GRAY);
		btngiris.setBorder(null);
		btngiris.setFont(new Font("Courier",Font.TYPE1_FONT,14));
		btnunut=new JButton("Şifremi Unuttum");
		btnunut.setBounds(244, 260, 120, 40);
		btnunut.setBackground(Color.white);
		btnunut.setForeground(Color.DARK_GRAY);
		btnunut.setBorder(null);
		btnunut.setFont(new Font("Courier",Font.TYPE1_FONT,13));
		lbldurum=new JLabel();
		lbldurum.setBounds(633, 540, 230, 40);
		lbldurum.setFont(new Font("Courier",Font.TYPE1_FONT,12));
		lbldurum.setHorizontalAlignment(lbldurum.CENTER);
		btngiris.addActionListener(this);
		btnunut.addActionListener(this);
		p.add(txtTckn);
		p.add(txtSifre);
		p.add(btngiris);
		p.add(btnunut);
		add(lbldurum);
		p.add(lblgirisyap);
		p.add(lbltckn);
		p.add(lblsifre);
		add(p);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[]args) {
		LoginPage loginpage=new LoginPage();
		loginpage.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		PersonelDAO personelDAO=new PersonelDAO();
		PersonelService personelService=new PersonelService(personelDAO);
		
		if(e.getSource()==btngiris) {
			
			String tckn = txtTckn.getText();
			String sifre = txtSifre.getText();
				
			List<Personel> personeller = personelService.getAll();
			
			if(tckn.equals("") || sifre.equals("")) {
				
				lbldurum.setText("Lütfen boş alan bırakmayanız");
				lbldurum.setOpaque(true);
				lbldurum.setBackground(Color.red);
				lbldurum.setForeground(Color.white);
				
			}
			
			else {
				
				for(Personel p:personeller) {
					
					if(p.getPerTckn().equals(tckn) && p.getPerSifre().equals(sifre)) {
						setVisible(false);
						kontrol=true;
						adSoyad=p.getPerAdSyd();
						HomePage hp=new HomePage();
						hp.setVisible(true);

					}
					else {
						lbldurum.setText("Böyle bir kullanıcı bulunamadı.");
						lbldurum.setOpaque(true);
						lbldurum.setBackground(Color.red);
						lbldurum.setForeground(Color.white);
					}
				
				}
			}
			
		}
		else {
			
			 String perTckn = JOptionPane.showInputDialog("T.C Kimlik numaranız nedir");
			 Personel personel = personelService.findPersonelByTckn(perTckn);
			 JOptionPane.showMessageDialog(null, "Şifre : "+personel.getPerSifre());
		
		}
		
		
	}
}
