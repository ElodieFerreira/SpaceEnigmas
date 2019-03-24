package jeu;
import java.awt.*;
import javax.swing.*;

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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.border.LineBorder;

public class GUI implements ActionListener,Serializable 
{
	private static final long serialVersionUID = -1091520350373841679L;
	private Jeu jeu;
    private JFrame fenetre;
    private JPanel panel,panelImage,panelTexte,panelCarte,characterWorldMiniature;
    private ArrayList<JLabel> labelArray;
    private ArrayList<Object> objetsDansLaZone;
    private ArrayList<String> naturesObjetsDansLaZone;
    private JLabel label1,label2,label3,image,saveMessage;
    private JLabel world,character,dyspros;
    private JTextField entree;
    private JTextArea texte;
	private JButton boutonNord,boutonSud,boutonOuest,boutonEst;
    private JMenu mnJoueur,mnAide;
    private JMenuBar menuBar;
    private JMenuItem Sauvegarde,Inventaire,mntmAmis,test,Interface,Planete,suppression;

    private MouseListener lbl1,lbl2,lbl3,teleporteur;
    
    /**
     * Panel contenant 8 JLabel de l'inventaire
     */
    private JPanel inventaire;
    //Jlabel correspondant à l'inventaire
    private JLabel label_1,label_2,label_3,label_4,label_5,label_6,label_7,label_8;
    private ArrayList<JLabel> inventaireSurZone,lifePoints;
    

    public GUI(Jeu j) {
        jeu = j;
        creerGUI();
    }
    public void addNameFrame(String nom) {
    	fenetre.setTitle("SpaceEnigmas "+nom);
    }
    public void afficher(String s) {
    	texte.setText("\n");
    	texte.append(s+"\n");
        texte.setCaretPosition(texte.getDocument().getLength());
    }
    public void afficher(String s, Personnage perso) {
    	texte.append("\n");
    	texte.setText(perso.getNom()+"\n");
    	Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				afficherMiniature(jeu.getPartie().getZoneCourante().getNomImage(), perso.getImage());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				afficherMiniature(jeu.getPartie().getZoneCourante().getNomImage(), jeu.getPartie().getGuideDuJeu().getImage());
			}
		});
    	t.start();
    	texte.append(s+"\n");
        texte.setCaretPosition(texte.getDocument().getLength());
    }
    
    public void afficher() {
        afficher("\n");
    }
    public void afficheImage(String nomImage) {
    	URL imageURL = this.getClass().getClassLoader().getResource("images/" + nomImage);
    	ImageIcon img = new ImageIcon(imageURL);
    	Image imageFormatImage = img.getImage();
    	imageFormatImage.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
    	ImageIcon newIcon = new ImageIcon(imageFormatImage);
	   	if( imageURL != null ) {
        	image.setIcon(newIcon);
        	panelImage.add(image);
        }
   }
    public void afficheImageMiniatureWorld(String nomImage, JLabel jlabel) {

    	URL imageURL = this.getClass().getClassLoader().getResource("images/" + nomImage);
    	BufferedImage img = null;
    	try {
			img = ImageIO.read(imageURL);
		} catch (ArrayIndexOutOfBoundsException | IOException e) {
			// TODO Auto-generated catch block
			imageURL = this.getClass().getClassLoader().getResource("images/zone1.gif");
			try {
				img = ImageIO.read(imageURL);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
    	Image imageResize = img.getScaledInstance(jlabel.getWidth(), jlabel.getHeight(), Image.SCALE_SMOOTH);
   	   	if( imageURL != null ) {
	   		jlabel.setIcon(new ImageIcon(imageResize));
	   		
        }
   }
 
    public JButton texteVerticaleBouton(JButton bouton, String text) {
    	String textBoutton = new String("<HTML>");
    	for(int i=0;i<text.length();i++) {
    		textBoutton += text.charAt(i);	
    		textBoutton += "<br>";
    	}
    	textBoutton += "</HTML>";
    	bouton.setText(textBoutton);
    	return bouton;
    }
    public void afficherBoutonSortie(HashMap<String,Zone> sorties) {
    	if(sorties.get("NORD")!=null) {
    		boutonNord.setText(sorties.get("NORD").getNom());
    		boutonNord.setVisible(true);
    	} else {
    		boutonNord.setVisible(false);
    	}
    	if(sorties.get("SUD")!=null) {
    		boutonSud.setText(sorties.get("SUD").getNom());
    		boutonSud.setVisible(true);
    	} else {
    		boutonSud.setVisible(false);
    	}
    	if(sorties.get("EST")!=null) {
    		boutonEst = texteVerticaleBouton(boutonEst, sorties.get("EST").getNom());
    		boutonEst.setVisible(true);
    	} else {
    		boutonEst.setVisible(false);
    	}
    	if(sorties.get("OUEST")!=null) {
    		boutonOuest = texteVerticaleBouton(boutonOuest, sorties.get("OUEST").getNom());
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
        panel = new JPanel();
        panelCarte = new JPanel();
        panelCarte.setBounds(0, 25, 985, 499);
        new JButton("Est");
        new JButton("Ouest");
        panel.setLayout(null);
        panel.add(panelCarte);
        panelCarte.setLayout(new BorderLayout(0, 0));
        
        

        panelImage = new JPanel();
        panelImage.setBackground(Color.DARK_GRAY);
        panelImage.setLayout(null);
        label1 = new JLabel();
        lbl1= new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		interractionObjet(label1, 0);
        	}
        };
        label1.addMouseListener(lbl1);
        lifePoints=new ArrayList<JLabel>();
        JLabel lifePoint1 = new JLabel("");
//        lifePoint1.setIcon(new ImageIcon(GUI.class.getResource("/images/lifepoints.png")));
        lifePoint1.setBounds(49, 228, 97, 23);
        lifePoints.add(lifePoint1);
        boutonNord = new JButton("Nord");
        boutonNord.setIcon(new ImageIcon(GUI.class.getResource("/images/button2.png")));
        boutonNord.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 10));
        boutonNord.setVerticalTextPosition(SwingConstants.CENTER);
        boutonNord.setHorizontalTextPosition(SwingConstants.CENTER);
        boutonNord.setBounds(0, 0, 985, 23);
        panelImage.add(boutonNord);
        boutonSud = new JButton("Sud");
        boutonSud.setIcon(new ImageIcon(GUI.class.getResource("/images/button2.png")));
        boutonSud.setBounds(0, 476, 985, 23);
        boutonSud.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 10));
        boutonSud.setVerticalTextPosition(SwingConstants.CENTER);
        boutonSud.setHorizontalTextPosition(SwingConstants.CENTER);
        panelImage.add(boutonSud);
        boutonEst = new JButton("EST");
        boutonEst.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 10));
        boutonEst.setBounds(955, 0, 35, 499);
        boutonEst.setVerticalTextPosition(SwingConstants.CENTER);
        boutonEst.setHorizontalTextPosition(SwingConstants.CENTER);
        boutonEst.setIcon(new ImageIcon(GUI.class.getResource("/images/button.png")));
        panelImage.add(boutonEst);
        boutonOuest = new JButton("Ouest");
        boutonOuest.setForeground(new Color(0, 0, 0));
        boutonOuest.setBackground(SystemColor.activeCaption);
        boutonOuest.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 10));
        boutonOuest.setIcon(new ImageIcon(GUI.class.getResource("/images/button.png")));
        boutonOuest.setVerticalTextPosition(SwingConstants.CENTER);
        boutonOuest.setHorizontalTextPosition(SwingConstants.CENTER);
        boutonOuest.setBounds(0, 0, 35, 499);
        panelImage.add(boutonOuest);
        JLabel lifePoint2 = new JLabel("");
        lifePoint2.setBounds(350, 228, 163, 23);
        lifePoints.add(lifePoint2);
        panelImage.add(lifePoint2);
        JLabel lifePoint3 = new JLabel("");
        lifePoint3.setBackground(Color.GREEN);
        lifePoint3.setBounds(580, 228, 160, 23);
        lifePoints.add(lifePoint3);
        panelImage.add(lifePoint3);
       
        for(JLabel label : lifePoints) {
        	URL lifePointURL = this.getClass().getClassLoader().getResource("images/lifepoints.png");
        	label.setIcon(new ImageIcon(lifePointURL));
        	label.setVisible(false);
        }
        panelImage.add(lifePoint1);
        
        inventaire = new JPanel();
        inventaire.setForeground(Color.WHITE);
        inventaire.setBounds(29, 332, 334, 156);
        panelImage.add(inventaire);
        inventaire.setLayout(null);
        inventaire.setBorder(new LineBorder(new Color(0, 0, 0)));
        inventaire.setBackground(SystemColor.control);
        
        label_1 = new JLabel("");
        label_1.setForeground(Color.DARK_GRAY);
        label_1.setBackground(Color.BLACK);
        label_1.setOpaque(true);
        label_1.setBounds(10, 5, 65, 65);
        inventaire.add(label_1);
        
        label_2 = new JLabel("");
        label_2.setBackground(Color.BLACK);
        label_2.setOpaque(true);
        label_2.setBounds(85, 5, 65, 65);
        inventaire.add(label_2);
        
        label_3 = new JLabel("");
        label_3.setOpaque(true);
        label_3.setBackground(Color.BLACK);
        label_3.setBounds(160, 5, 65, 65);
        inventaire.add(label_3);
        
        label_4 = new JLabel("");
        label_4.setBackground(Color.BLACK);
        label_4.setOpaque(true);
        label_4.setBounds(235, 5, 65, 65);
        inventaire.add(label_4);
        
        label_5 = new JLabel("");
        label_5.setBackground(Color.BLACK);
        label_5.setOpaque(true);
        label_5.setBounds(10, 81, 65, 65);
        inventaire.add(label_5);
        
        label_6 = new JLabel("");
        label_6.setBackground(Color.BLACK);
        label_6.setOpaque(true);
        label_6.setBounds(85, 81, 65, 65);
        inventaire.add(label_6);
        
        label_7 = new JLabel("");
        label_7.setBackground(Color.BLACK);
        label_7.setOpaque(true);
        label_7.setBounds(160, 81, 65, 65);
        inventaire.add(label_7);
        
        label_8 = new JLabel("");
        label_8.setBackground(Color.BLACK);
        label_8.setOpaque(true);
        label_8.setBounds(235, 81, 65, 65);
        inventaire.add(label_8);
        
        JButton btnX = new JButton("");
        btnX.setToolTipText("exit");
        btnX.setIcon(new ImageIcon(GUI.class.getResource("/images/close.png")));
        btnX.setForeground(Color.BLACK);
        btnX.setBounds(310, 0, 25, 25);
        btnX.addMouseListener( new MouseAdapter() {
	       	@Override
	       	public void mouseClicked(MouseEvent arg0) {
	       		inventaire.setVisible(false);
	       	}
		});
        inventaire.add(btnX);
        inventaire.setVisible(false);
        panelImage.add(inventaire);
        
        dyspros = new JLabel();
        dyspros.setBackground(new Color(204, 204, 0));
        dyspros.setBounds(523, 30, 180, 180);
        panelImage.add(dyspros);
        
        label1.setBounds(50, 241, 180, 180);
        label1.setBackground(new Color(204, 204, 0));
        panelImage.add(label1);
        panelCarte.add(panelImage);
        
        label2 = new JLabel();
        label2.setBackground(new Color(204, 204, 0));
        label2.setBounds(316, 241, 180, 180);
        lbl2 = new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		interractionObjet(label2, 1);
        	}
        };
        label2.addMouseListener(lbl2);
        panelImage.add(label2);
        
        label3 = new JLabel();
        label3.setBackground(new Color(204, 204, 0));
        label3.setBounds(568, 241, 180, 180);
        lbl3 = new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		interractionObjet(label3, 2);
        	}
        };
        label3.addMouseListener(lbl3);
        panelImage.add(label3);
        teleporteur = new MouseAdapter() {
           	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		jeu.teleporterJoueur();
        	}
        };
        label_1.addMouseListener(teleporteur);       
        labelArray = new ArrayList<JLabel>();
        labelArray.add(label1);
        labelArray.add(label2);
        labelArray.add(label3);
        fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        
        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 984, 26);
        panel.add(menuBar);
        
        mnJoueur = new JMenu("Joueur");
        menuBar.add(mnJoueur);
        
        Sauvegarde = new JMenuItem("Sauvegarde");
        mnJoueur.add(Sauvegarde);
    	saveMessage = new JLabel("Votre sauvegarde a bien été effectué");
    	saveMessage.setHorizontalAlignment(SwingConstants.CENTER);
    	saveMessage.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 11));
    	saveMessage.setForeground(new Color(255, 255, 255));
        saveMessage.setBounds(369, 89, 220, 87);
        saveMessage.setBackground(SystemColor.inactiveCaption);
        saveMessage.setOpaque(true);
        saveMessage.setVisible(false);
        panelImage.add(saveMessage);
        Inventaire = new JMenuItem("Inventaire");
        Inventaire.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		inventaire.setVisible(true);
        		afficherInventaire(jeu.getPartie().getJoueur().inventaire);
        	}
        });
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
        
        suppression = new JMenuItem("Suppression");
        suppression.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jeu.SupprimerPartie();
        	}
        });
        
        test = new JMenuItem("Restart");
        mnJoueur.add(test);
        test.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	 jeu.lancerDebutJeu();
        	}
        });
        mnJoueur.add(suppression);
        
        mnAide = new JMenu("Aide");
        mnJoueur.add(mnAide);
        
        Interface = new JMenuItem("Interface");
        Interface.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Desktop desk = Desktop.getDesktop();
        		try {
					desk.open(new File("src/data/interface.pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        mnAide.add(Interface);      
        Planete = new JMenuItem("Carte");
        mnAide.add(Planete);     
        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.15);
        splitPane.setBounds(0, 523, 985, 190);
        panel.add(splitPane);
        panelTexte = new JPanel();
        splitPane.setRightComponent(panelTexte);
        panelTexte.setLayout(new BorderLayout());
        texte = new JTextArea();
        texte.setFont(new Font("Monospaced", Font.PLAIN, 21));
        texte.setEditable(false);
        JScrollPane listScroller = new JScrollPane(texte);
        panelTexte.add(listScroller, BorderLayout.NORTH);
        listScroller.setPreferredSize(new Dimension(0, 200));
        listScroller.setMinimumSize(new Dimension(0, 200));
        
        characterWorldMiniature = new JPanel();
        splitPane.setLeftComponent(characterWorldMiniature);
        characterWorldMiniature.setLayout(null);
        
        character = new JLabel("");
        character.setBounds(10, 57, 127, 131);
        characterWorldMiniature.add(character);
        
        world = new JLabel("");
        world.setBounds(0, 0, 147, 188);
        characterWorldMiniature.add(world);
        entree = new JTextField(34);
        entree.setToolTipText("Veuillez écrire ici");
        entree.setBounds(0, 714, 985, 36);
        panel.add(entree);
        entree.addActionListener(this);
        entree.requestFocus();
        inventaireSurZone = new ArrayList<JLabel>();
        inventaireSurZone.add(label_1);
        inventaireSurZone.add(label_2);
        inventaireSurZone.add(label_3);
        inventaireSurZone.add(label_4);
        inventaireSurZone.add(label_5);
        inventaireSurZone.add(label_6);
        inventaireSurZone.add(label_7);
        inventaireSurZone.add(label_8);
        image = new JLabel();
        image.setBounds(0, 0, 985, 499);
        image.setBackground(new Color(153, 0, 204));
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelImage.add(image);  
        texte.setWrapStyleWord(true);
        texte.setLineWrap(true);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setBounds(500,10,1000,785);
        fenetre.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        executerCommande();
    }

    private void executerCommande() {
    	jeu.incrementerCommande();
        String commandeLue = entree.getText();
        entree.setText("");
        if(jeu.getPartie().getJoueur()==null) {
        	 jeu.creationJoueur(commandeLue);
        } else {
        	// Ici on test si on est dans une enigme qui a besoin des commandes
        	if(jeu.getPartie().queteEnCoursPartie() instanceof Pendu  || jeu.getPartie().queteEnCoursPartie() instanceof EnigmeTextuel) {	
        		Queteur queteur = null;
        		// On cherche si il y a un quêteur dans la zone
        		for(Object obj : objetsDansLaZone) {
        			if(obj instanceof Queteur) {
            			if(((Queteur) obj).quete()==jeu.getPartie().queteEnCoursPartie()) {
            				queteur = (Queteur) obj;
            			}
        			}
        		}
        		if (queteur!=null) {
        			jeu.envoyerReponseEnigme(commandeLue, queteur);
        		}
        	} 
        }
        
    }
    public void addAllActionListener() {
		// TODO Auto-generated method stub
        boutonEst.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		jeu.incrementerCommande();
        		jeu.allerEn("EST");
        	}
        });
        boutonNord.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jeu.incrementerCommande();
        		jeu.allerEn("NORD");
        	}
        });
        boutonSud.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		jeu.incrementerCommande();
        		jeu.allerEn("SUD");
        	}
        });
        boutonOuest.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		jeu.incrementerCommande();
        		jeu.allerEn("OUEST");
        	}
        });
        Sauvegarde.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jeu.Sauvegarde();
        		Thread t = new Thread( new Runnable() {
        			public void run() {
        				saveMessage.setVisible(true);
        				try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				saveMessage.setVisible(false);
        			}
        		});
        		t.start();
        		
        	}
        });
	}
	public void stopFenetre() {
    	fenetre.setVisible(false);
    }
	public void afficherElementZone(ArrayList<Mouton> animauxDansLazone,ArrayList<Personnage> personnageDansLaZone) {
		// TODO Auto-generated method stub
		for(JLabel label : labelArray) {
			label.setVisible(false);
		}
		for(JLabel label : lifePoints) {
			label.setVisible(false);
		}
        objetsDansLaZone = new ArrayList<Object>();
        naturesObjetsDansLaZone = new ArrayList<String>();
		int cpt = 0;
		for(Mouton mouton : animauxDansLazone) {
			System.out.println("je suis pas un mouton");
			URL moutonURL = this.getClass().getClassLoader().getResource("images/"+mouton.getImage());
		   	if( moutonURL != null ) {
		   		objetsDansLaZone.add((Object)mouton);
		   		naturesObjetsDansLaZone.add("mouton");
	        	labelArray.get(cpt).setIcon( new ImageIcon(moutonURL));
	        	labelArray.get(cpt).setVisible(true);
	          	cpt++;
	        }
		}
		for(Personnage personnage : personnageDansLaZone) {
		   	URL personnageURL = this.getClass().getClassLoader().getResource("images/"+personnage.getImage());
//		   	System.out.println("moi je suis un perso");
//		   	System.out.println(personnageURL);
		   	if( personnageURL != null ) {
		   		objetsDansLaZone.add((Object)personnage);
		   		naturesObjetsDansLaZone.add("personnage");
	        	labelArray.get(cpt).setIcon( new ImageIcon(personnageURL)); 	
	        	labelArray.get(cpt).setVisible(true);
	        	if(personnage instanceof PersonnageActifs) {
	        		PersonnageActifs personnageActifs = (PersonnageActifs) personnage;
	        		System.out.println("j'ai pv:"+personnageActifs.getPointDeVie());
	        		lifePoints.get(cpt).setSize(((PersonnageActifs) personnage).getPointDeVie()*10, 23);
	        		lifePoints.get(cpt).setVisible(true);
	        	}
	        }
		   	cpt++;
		}
	}
	public void interractionObjet(JLabel label, int index) {
		jeu.incrementerCommande();
		if(jeu.getPartie().getJoueur()!=null) {
			if(naturesObjetsDansLaZone.get(index)=="mouton") {
				jeu.captureDeMouton((Mouton)objetsDansLaZone.get(index));
				label.setVisible(false);
			} else {
				jeu.interractionPersonnage((Personnage)objetsDansLaZone.get(index));
			}
		} else {
			afficher("Je n'ai pas ton prénom jeune inconnu ! Donne le moi avant de commencer la partie!\n");
		}
	}
    public JTextField getEntree() {
		return entree;
	}
	public void afficherMiniature(String nomImage, String imageQueteur) {
		// TODO Auto-generated method stub
		afficheImageMiniatureWorld(nomImage,world);
		afficheImageMiniatureWorld(imageQueteur, character);
		world.setVisible(true);
	}
	public void displayDyspros(String image2, boolean isDisplayed) {
		afficheImageMiniatureWorld(image2, dyspros);
		dyspros.setVisible(isDisplayed);
	}
	public void addActionListenerCombat() {
		label1.removeMouseListener(lbl1);
		lbl1= new MouseAdapter() {
	       	@Override
	       	public void mouseClicked(MouseEvent arg0) {
	       		interractionCombat(0);
	       	}
		};
		label1.addMouseListener(lbl1);
		label2.removeMouseListener(lbl2);
		lbl2= new MouseAdapter() {
	       	@Override
	       	public void mouseClicked(MouseEvent arg0) {
	       		interractionCombat(1);
	       	}
		};
		label2.addMouseListener(lbl2);
		label3.removeMouseListener(lbl2);
		lbl3= new MouseAdapter() {
	       	@Override
	       	public void mouseClicked(MouseEvent arg0) {
	       		interractionCombat(2);
	       	}
		};
		label3.addMouseListener(lbl3);
	}      
	public void interractionCombat(int index) {
		jeu.tourDuComabat((PersonnageActifs) objetsDansLaZone.get(index));
	}
	public void afficherInventaire(ArrayList<Objets> inventaire ) {
		for(JLabel label : inventaireSurZone) {
			label.setIcon(null);
		}
		for(Objets obj : inventaire) {
			int index = inventaire.indexOf(obj);
			afficheImageMiniatureWorld(obj.getNomImage(), inventaireSurZone.get(index));
			inventaireSurZone.get(index).setToolTipText(obj.getDescription());
			
			inventaireSurZone.get(index).setVisible(true);
		}
	}
	public void afficherCredit() {
		URL imageURL = this.getClass().getClassLoader().getResource("images/crédit.gif");
    	ImageIcon img = new ImageIcon(imageURL);
    	Image imageFormatImage = img.getImage();
    	imageFormatImage.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
    	ImageIcon newIcon = new ImageIcon(imageFormatImage);
	   	if( imageURL != null ) {
	   		JLabel credit = new JLabel(newIcon);
	   		credit.setBounds(100,10,600,100);
        	panelImage.add(credit);
        }
		
	}
}
