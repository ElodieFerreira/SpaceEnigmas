package jeu;
import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUI implements ActionListener
{
    private Jeu jeu;
    private JFrame fenetre;
    private JPanel panel;
    private JPanel panelImage;
    private ArrayList<JLabel> labelArray;
    private ArrayList<Object> objetsDansLaZone;
    private ArrayList<String> naturesObjetsDansLaZone;
    private JLabel label1;
    private JLabel label3;
    private JLabel label2;
    private JPanel panelTexte;
    private JPanel panelCarte;
    private JTextField entree;
    private JButton boutonNord;
    private JTextArea texte;
    private JLabel image;
    private JButton boutonSud;
    private JButton boutonOuest;
    private JButton boutonEst;
    private JMenuBar menuBar;
    private JMenu mnJoueur;
    private JMenuItem Sauvegarde;
    private JMenuItem Inventaire;
    private JMenuItem mntmAmis;
    private JMenuItem test;
    private JMenu mnAide;
    private JMenuItem Interface;
    private JMenuItem Planete;
    

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
    	System.out.println(nomImage+"coucou");
    	URL imageURL = this.getClass().getClassLoader().getResource("images/" + nomImage);
    	ImageIcon img = new ImageIcon(imageURL);
    	Image imageFormatImage = img.getImage();
    	imageFormatImage.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
    	ImageIcon newIcon = new ImageIcon(imageFormatImage);
    	System.out.println(imageURL);
	   	if( imageURL != null ) {
        	image.setIcon(newIcon);
        	panelImage.add(image);
        }
   }
    public void afficheImagePauseTest(String nomImage) {
    	System.out.println(nomImage);
    	URL imageURL = this.getClass().getClassLoader().getResource("images/" + nomImage);
    	BufferedImage img = null;
    	try {
			img = ImageIO.read(imageURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Image imageResize = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
    	System.out.println(imageURL);
	   	if( imageURL != null ) {
        	image.setIcon(new ImageIcon(imageResize));
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

    private void creerGUI() {
        fenetre = new JFrame("SpaceEnigmas");
//      personnage.setBounds(350, 150, 150, 150);
        panel = new JPanel();
        panelTexte = new JPanel();
        panelTexte.setBounds(0, 525, 880, 128);
        panelTexte.setLayout(new BorderLayout());
        panelCarte = new JPanel();
        panelCarte.setBounds(0, 25, 880, 499);
        entree = new JTextField(34);
        texte = new JTextArea();
        new JButton("Est");
        new JButton("Ouest");
        texte.setEditable(false);
        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));
        panelTexte.add(listScroller,BorderLayout.CENTER);
        panelTexte.add(entree,BorderLayout.SOUTH);
        panel.setLayout(null);
        panel.add(panelCarte);
        
        boutonOuest = new JButton("Ouest");
        boutonOuest.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		jeu.allerEn("OUEST");
        	}
        });
        boutonNord = new JButton("Nord");
        boutonNord.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jeu.allerEn("NORD");
        	}
        });
        panelCarte.setLayout(new BorderLayout(0, 0));
        panelCarte.add(boutonNord, BorderLayout.NORTH);
        panelCarte.add(boutonOuest, BorderLayout.WEST);
        
        boutonSud = new JButton("Sud");
        boutonSud.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		jeu.allerEn("SUD");
        	}
        });
        panelImage = new JPanel();
        image = new JLabel();
        image.setBounds(0, 0, 760, 449);
        image.setBackground(new Color(153, 0, 204));
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelImage.setLayout(null);
        label1 = new JLabel();
        label1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		interractionObjet(label1, 0);
        	}
        });
        
        label1.setBounds(50, 241, 180, 180);
        label1.setBackground(new Color(204, 204, 0));
        panelImage.add(label1);
        panelImage.add(image);
        panelCarte.add(panelImage);
        
        label2 = new JLabel();
        label2.setBackground(new Color(204, 204, 0));
        label2.setBounds(316, 241, 180, 180);
        label2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		interractionObjet(label2, 1);
        	}
        });
        panelImage.add(label2);
        
        label3 = new JLabel();
        label3.setBackground(new Color(204, 204, 0));
        label3.setBounds(568, 241, 180, 180);
        label3.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		interractionObjet(label3, 2);
        	}
        });
        panelImage.add(label3);
        
        labelArray = new ArrayList<JLabel>();
        labelArray.add(label1);
        labelArray.add(label2);
        labelArray.add(label3);
        
        boutonEst = new JButton("EST");
        boutonEst.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		jeu.allerEn("EST");
        	}
        });
        panelCarte.add(boutonEst, BorderLayout.EAST);
        panelCarte.add(boutonSud, BorderLayout.SOUTH);
        
        panel.add(panelTexte);	
        fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        
        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 880, 26);
        panel.add(menuBar);
        
        mnJoueur = new JMenu("Joueur");
        menuBar.add(mnJoueur);
        
        Sauvegarde = new JMenuItem("Sauvegarde");
        mnJoueur.add(Sauvegarde);
        
        Inventaire = new JMenuItem("Inventaire");
        mnJoueur.add(Inventaire);
        
        mntmAmis = new JMenuItem("Amis");
        mntmAmis.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		System.out.println("ola");
        		System.out.println(jeu.getPartie().getSalleDeRepos()+"voici ma salle de repos");
        		jeu.allerEn(jeu.getPartie().getSalleDeRepos());
        	}
        });
        mnJoueur.add(mntmAmis);      
        
        mnAide = new JMenu("Aide");
        mnJoueur.add(mnAide);
        
        test = new JMenuItem("Test");
        mnAide.add(test);
        
        Interface = new JMenuItem("Interface");
        mnAide.add(Interface);
        
        Planete = new JMenuItem("Carte");
        mnAide.add(Planete);
        test.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Desktop desk = Desktop.getDesktop();
        		try {
					desk.open(new File("src/images/edolie.gif"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
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
	public void afficherElementZone(ArrayList<Mouton> animauxDansLazone,ArrayList<Personnage> personnageDansLaZone) {
		// TODO Auto-generated method stub
		for(JLabel label : labelArray) {
			label.setVisible(false);
		}
        objetsDansLaZone = new ArrayList<Object>();
        naturesObjetsDansLaZone = new ArrayList<String>();
		int cpt = 0;
		for(Mouton mouton : animauxDansLazone) {
			URL moutonURL = this.getClass().getClassLoader().getResource("images/"+mouton.getImage());
		   	System.out.println(mouton);
		   	if( mouton != null ) {
		   		objetsDansLaZone.add((Object)mouton);
		   		naturesObjetsDansLaZone.add("mouton");
	        	labelArray.get(cpt).setIcon( new ImageIcon(moutonURL));
	        	labelArray.get(cpt).setVisible(true);
	        }
		   	cpt++;
		}
		for(Personnage personnage : personnageDansLaZone) {
			System.out.println("ola");
		   	URL personnageURL = this.getClass().getClassLoader().getResource("images/"+personnage.getImage());
		   	System.out.println(personnage.getImage());
			System.out.println(personnageURL);
		   	if( personnageURL != null ) {
		   		System.out.println(personnage);
		   		objetsDansLaZone.add((Object)personnage);
		   		naturesObjetsDansLaZone.add("personnage");
	        	labelArray.get(cpt).setIcon( new ImageIcon(personnageURL));
	        	labelArray.get(cpt).setVisible(true);
	        }
		   	cpt++;
		}
	}
	public void interractionObjet(JLabel label, int index) {
		if(jeu.getPartie().getJoueur()!=null) {
			if(naturesObjetsDansLaZone.get(index)=="mouton") {
				System.out.println(jeu.getPartie().getJoueur().inventaire.size());
				jeu.captureDeMouton((Mouton)objetsDansLaZone.get(index));
				System.out.println(jeu.getPartie().getJoueur().inventaire.size());
				label.setVisible(false);
			} else {
				System.out.println(jeu.getPartie().getJoueur().friends.size());
				jeu.interractionPersonnage((Personnage)objetsDansLaZone.get(index));
				System.out.println(jeu.getPartie().getJoueur().friends.size());
			}
		} else {
			afficher("Je n'ai pas ton pr�nom jeune inconnu ! Donne le moi avant de commencer la partie!");
		}
	}
}