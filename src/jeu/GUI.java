package jeu;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GUI implements ActionListener
{
    private Jeu jeu;
    private JFrame fenetre;
    private JPanel panel;
    private JPanel panelImage;
    private JLabel personnage;
    private JPanel panelTexte;
    private JPanel panelCarte;
    private JTextField entree;
    private JButton boutonNord;
    private JTextArea texte;
    private JLabel image;
    private JButton boutonSud;
    private JButton boutonOuest;
    private JButton boutonEst;

    public GUI(Jeu j) {
        jeu = j;
        creerGUI();
    }
    public void addNameFrame(String nom) {
    	fenetre.setTitle("SpaceEnigmas "+nom);
    }
    public void afficher(String s) {
    	texte.append(s);
        texte.setCaretPosition(texte.getDocument().getLength());
    }
    
    public void afficher() {
        afficher("\n");
    }

    public void afficheImage(String nomImage) {
    	System.out.println(nomImage);
    	URL imageURL = this.getClass().getClassLoader().getResource("images/" + nomImage);
    	ImageIcon img = new ImageIcon(imageURL);
    	Image imageFormatImage = img.getImage();
    	imageFormatImage.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
    	ImageIcon newIcon = new ImageIcon(imageFormatImage);
    	System.out.println(imageURL);
	   	if( imageURL != null ) {
        	image.setIcon(newIcon);
        	panelImage.add(image);
        	panelImage.repaint();
        }
   }
    public void afficherBoutonSortie(HashMap<String,Zone> sorties) {
    	if(sorties.get("NORD")!=null) {
    		boutonNord.setVisible(true);
    	} else {
    		boutonNord.setVisible(false);
    	}
    	if(sorties.get("SUD")!=null) {
    		boutonSud.setVisible(true);
    	} else {
    		boutonSud.setVisible(false);
    	}
    	if(sorties.get("EST")!=null) {
    		boutonEst.setVisible(true);
    	} else {
    		boutonEst.setVisible(false);
    	}
    	if(sorties.get("OUEST")!=null) {
    		boutonOuest.setVisible(true);
    	} else {
    		boutonOuest.setVisible(false);
    	}
    }
    public void enable(boolean ok) {
        entree.setEditable(ok);
        if ( ! ok )
            entree.getCaret().setBlinkRate(0);
    }

    private void creerGUIVersion() {
        fenetre = new JFrame("Jeu");
        
        entree = new JTextField(34);

        texte = new JTextArea();
        texte.setEditable(false);
        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));

        panel = new JPanel();
        image = new JLabel();

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entree, BorderLayout.SOUTH);

        fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        entree.addActionListener(this);

        fenetre.pack();
        fenetre.setVisible(true);
        entree.requestFocus();
    }
    private void creerGUI() {
        fenetre = new JFrame("SpaceEnigmas");
//        personnage.setBounds(350, 150, 150, 150);
        panel = new JPanel();
        panelTexte = new JPanel();
        panelTexte.setBounds(0, 500, 880, 153);
        panelTexte.setLayout(new BorderLayout());
        panelCarte = new JPanel();
        panelCarte.setBounds(0, 0, 880, 500);
        panelImage = new JPanel();
        entree = new JTextField(34);
        image = new JLabel();
        image.setBounds(0, 0, 782, 475);
        image.setBackground(new Color(153, 0, 204));
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        texte = new JTextArea();
        new JButton("Est");
        new JButton("Ouest");
        texte.setEditable(false);
        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));
        panelImage.setLayout(null);
        personnage = new JLabel();
        personnage.addMouseMotionListener(new MouseMotionAdapter() {
        	@Override
        	public void mouseDragged(MouseEvent arg0) {
        		mouseDragged(arg0);
        	}
        });
        personnage.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		jeu.allerEn("NORD");
        	}
        });
        personnage.setBounds(40, 200, 180, 180);
        personnage.setBackground(new Color(204, 204, 0));
        panelImage.add(personnage);
        panelImage.add(image);
        boutonNord = new JButton("Nord");
        boutonNord.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jeu.allerEn("NORD");
        	}
        });
        panelCarte.setLayout(new BorderLayout(0, 0));
        panelCarte.add(boutonNord, BorderLayout.NORTH);
        panelCarte.add(panelImage, BorderLayout.CENTER);
        panelTexte.add(listScroller,BorderLayout.CENTER);
        panelTexte.add(entree,BorderLayout.SOUTH);
        panel.setLayout(null);
        panel.add(panelCarte);
        
        boutonSud = new JButton("Sud");
        boutonSud.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		jeu.allerEn("SUD");
        	}
        });
        panelCarte.add(boutonSud, BorderLayout.SOUTH);
        
        boutonOuest = new JButton("Ouest");
        boutonOuest.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		jeu.allerEn("OUEST");
        	}
        });
        
        panelCarte.add(boutonOuest, BorderLayout.WEST);
        
        boutonEst = new JButton("EST");
        boutonEst.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		jeu.allerEn("EST");
        	}
        });
        panelCarte.add(boutonEst, BorderLayout.EAST);
        
        panel.add(panelTexte);	
        fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        entree.addActionListener(this);

        fenetre.setBounds(500,10,900,700);
        fenetre.setVisible(true);
        entree.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {
        executerCommande();
    }

    private void executerCommande() {
        String commandeLue = entree.getText();
        entree.setText("");
        if(jeu.getPartie().getJoueur()==null) {
        	 jeu.creationJoueur(commandeLue);
        } else {
        	jeu.traiterCommande( commandeLue);
        }
        
    }
	public void afficherElementZone(ArrayList<Mouton> animauxDansLazone) {
		// TODO Auto-generated method stub
		personnage.setVisible(false);
		for(int i=0;i<animauxDansLazone.size();i++) {
		   	URL mouton = this.getClass().getClassLoader().getResource("images/"+animauxDansLazone.get(i).getImage());
		   	System.out.println(mouton);
		   	if( mouton != null ) {
	        	personnage.setIcon( new ImageIcon(mouton));
	        	personnage.setVisible(true);
	        }
		}
	}
}