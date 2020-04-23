package otomasyon.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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

import otomasyon.DAO.YazarDAO;
import otomasyon.model.Yazar;
import otomasyon.service.YazarService;

public class YazarPage extends JFrame implements ActionListener {
	
	YazarDAO yazarDAO = new YazarDAO();
	YazarService yazarService=new YazarService(yazarDAO);
	
	Menu menu=new Menu();
	JScrollPane scrollp;
	JLabel lblkatList,lblfiltre,lblfiltrealan,lblduzenle,lbldurum,lblduzenlealan,lblyazAd,lblyazAd2,lblyazId,lblperAd;
	JTextField txtyazAd,txtyazAd2,txtyazId;
	JButton btnfiltre,btnekle,btnsil,btnguncelle,btntemizle,btncik;
    JTable j;
    ImageIcon btntemizleres,btncikres;
	
	public YazarPage() {
		setTitle("Yazar İşlemleri");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		setLayout(null);
		setContentPane(new JLabel(new ImageIcon("src/otomasyon/app/bg2.jpg")));
		menu.yazar.setBackground(Color.DARK_GRAY);
		menu.yazar.setOpaque(true);
		menu.yazar.setForeground(Color.white);
		menu.mb.setSelected(menu.yazar);
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
		lblkatList=new JLabel("  Yazar Listesi");
		lblkatList.setBounds(280, 50, 250, 40);
		lblkatList.setBackground(Color.white);
		lblkatList.setForeground(Color.DARK_GRAY);
		lblkatList.setOpaque(true);
		lblkatList.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblfiltre=new JLabel("  Yazar Filtrele");
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
		lblyazAd=new JLabel("Yazar Adı : ");
		lblyazAd.setBounds(700, 120, 150, 40);
		lblyazAd.setForeground(Color.white);
		lblyazAd.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		txtyazAd=new JTextField();
		txtyazAd.setBounds(850,121,200,40);
		txtyazAd.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		txtyazAd.setHorizontalAlignment(txtyazAd.CENTER);
		txtyazAd.setBorder(null);
		btnfiltre=new JButton("Filtrele");
		btnfiltre.setBounds(950, 180, 100, 40);
		btnfiltre.setBorder(null);
		btnfiltre.setBackground(Color.white);
		btnfiltre.setForeground(Color.DARK_GRAY);
		btnfiltre.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnfiltre.addActionListener(this);
		lblyazId=new JLabel("Yazar Id : ");
		lblyazId.setBounds(700, 365, 150, 40);
		lblyazId.setForeground(Color.white);
		lblyazId.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lblduzenle=new JLabel("  Yazar Düzenle");
		lblduzenle.setBounds(680, 290, 400, 40);
		lblduzenle.setBackground(Color.white);
		lblduzenle.setForeground(Color.DARK_GRAY);
		lblduzenle.setOpaque(true);
		lblduzenle.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		lbldurum=new JLabel();
		lbldurum.setBounds(880, 290, 200, 40);
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
		txtyazId=new JTextField();
		txtyazId.setBounds(850,366,200,40);
		int yazarId= yazarService.getMaxId()+1;
		txtyazId.setText(Integer.toString(yazarId));
		txtyazId.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		txtyazId.setHorizontalAlignment(txtyazId.CENTER);
		txtyazId.setBorder(null);
		txtyazId.setEnabled(false);
		lblyazAd2=new JLabel("Yazar Adı : ");
		lblyazAd2.setBounds(700, 435, 150, 40);
		lblyazAd2.setForeground(Color.white);
		lblyazAd2.setFont(new Font("Courier",Font.TYPE1_FONT,20));
		txtyazAd2=new JTextField();
		txtyazAd2.setBounds(850,436,200,40);
		txtyazAd2.setFont(new Font("Courier",Font.TYPE1_FONT,13));
		txtyazAd2.setHorizontalAlignment(txtyazAd.CENTER);
		txtyazAd2.setBorder(null);
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
		List<Yazar> yazar = yazarService.getAll();
		scrollp=yazarTable(yazar);
		scrollp.setBounds(280, 91, 250, 223);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(lblperAd);
		add(btncik);
		add(lblkatList);
		add(txtyazAd);
		add(lblyazAd);
		add(txtyazId);
		add(lblyazId);
		add(txtyazAd2);
		add(lblyazAd2);
		add(lblfiltre);
		add(lblfiltrealan);
		add(lblduzenle);
		add(lblduzenlealan);
		add(btnfiltre);
		add(btnekle);
		add(btnsil);
		add(btnguncelle);
		add(scrollp);
		add(btntemizle);
		add(lbldurum);
	}
	public static void main(String[]args) {
		YazarPage yazarPage=new YazarPage();
		 if(LoginPage.kontrol==true)
			yazarPage.setVisible(true);
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
			int yazarId= yazarService.getMaxId()+1;
			txtyazId.setText(Integer.toString(yazarId));
			txtyazAd2.setText("");
			btnekle.setEnabled(true);
			btnguncelle.setEnabled(false);
			btnsil.setEnabled(false);
			btntemizle.setOpaque(false);
			btntemizle.setIcon(null);
			btntemizle.setBounds(1050,290,30,30);
		}
		if(e.getSource()==btnfiltre) {
			String yazAd = txtyazAd.getText();
			List<Yazar> yazar = yazarService.findYazarByName(yazAd);
			remove(scrollp);
			scrollp=yazarTable(yazar);
			scrollp.setBounds(280, 91, 250, 223);
			add(scrollp);
		}
		if(e.getSource()==btnekle) {
			boolean kontrol = control();
			Yazar yazar = new Yazar();
			yazar.setYazarId(Integer.parseInt(txtyazId.getText()));
			yazar.setYazarAd(txtyazAd2.getText());
			if(kontrol==true) {
				yazar = yazarService.save(yazar);
				List<Yazar> yazar2 = yazarService.getAll();
				remove(scrollp);
				scrollp=yazarTable(yazar2);
				scrollp.setBounds(280, 91, 250, 223);
				add(scrollp);
				int yazarId= yazarService.getMaxId()+1;
				txtyazId.setText(Integer.toString(yazarId));
				txtyazAd2.setText("");
			}
		}
		if(e.getSource()==btnsil) {
			boolean kontrol = control();
			int katId = Integer.parseInt(txtyazId.getText());
			if(kontrol==true) {
				yazarService.delete(katId);
				List<Yazar> yazar = yazarService.getAll();
				remove(scrollp);
				scrollp=yazarTable(yazar);
				scrollp.setBounds(280, 91, 250, 223);
				add(scrollp);
				int yazarId= yazarService.getMaxId()+1;
				txtyazId.setText(Integer.toString(yazarId));
				txtyazAd2.setText("");
				btnekle.setEnabled(true);
				btnguncelle.setEnabled(false);
				btnsil.setEnabled(false);
				btntemizle.setOpaque(false);
				btntemizle.setIcon(null);
				btntemizle.setBounds(1050,290,30,30);
			}
		}
		if(e.getSource()==btnguncelle) {
			boolean kontrol = control();
			int katId = Integer.parseInt(txtyazId.getText());
			String katAd = txtyazAd2.getText();
			if(kontrol==true) {
				yazarService.update(katId, katAd);
				List<Yazar> yazar = yazarService.getAll();
				remove(scrollp);
				scrollp=yazarTable(yazar);
				scrollp.setBounds(280, 91, 250, 223);
				add(scrollp);
				int yazarId= yazarService.getMaxId()+1;
				txtyazId.setText(Integer.toString(yazarId));
				txtyazAd2.setText("");
				btnekle.setEnabled(true);
				btnguncelle.setEnabled(false);
				btnsil.setEnabled(false);
				btntemizle.setOpaque(false);
				btntemizle.setIcon(null);
				btntemizle.setBounds(1050,290,30,30);
			}
		}
		
	}
	
	public JScrollPane yazarTable(List<Yazar> yazar) {
		
		String veri[][]=new String[yazar.size()][];
		
		for (int i =0; i < yazar.size(); i++) {  
		        	 
		                veri[i]=new String[]{  
		               
		                		String.valueOf(yazar.get(i).getYazarId()),  
		                		String.valueOf(yazar.get(i).getYazarAd()),    
  
		                };
		                
		 }
		
		 j = new JTable();
		 DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
	     rendar.setHorizontalAlignment(JLabel.CENTER);
		 j.setRowHeight(20);
		 TableModel tabloModeli=new DefaultTableModel(veri,new String[] {"Id", "Yazar Adı"});
		 j.setModel(tabloModeli);
		 getSelectedData();
		 for(int i=0;i<j.getColumnCount();i++)
			 j.getColumnModel().getColumn(i).setCellRenderer(rendar);
	     j.getColumnModel().setColumnMargin(20);
	     TableColumnModel columnModel = j.getColumnModel();
	     columnModel.getColumn(0).setPreferredWidth(50);
	     columnModel.getColumn(1).setPreferredWidth(200);
	     JScrollPane sp = new JScrollPane(j);
	     sp.setBounds(280, 91,250,j.getColumnCount()*20+23);
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
						String yazId = j.getValueAt(row, 0).toString();
						String yazAd = j.getValueAt(row, 1).toString();
						txtyazId.setText(yazId);
						txtyazAd2.setText(yazAd);
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
		String yazAd = txtyazAd2.getText(); 
		if(yazAd.equals("")) {
			JOptionPane.showMessageDialog(null,"Lütfen alanları boş bırakmayınız.");
		}
		else {
		        kontrol = true;
		    }
		return kontrol;
	}
	}