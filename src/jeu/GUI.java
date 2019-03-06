package jeu;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
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

public class GUI implements ActionListener
{
    private Jeu jeu;
    private JFrame fenetre;
    private JPanel panel;
    private JPanel panelImage;
    private JLabel personnage;
    private JPanel panelTexte;
    private JPanel panelDialogue;
    private JPanel panelCarte;
    private JTextField entree;
    private JButton boutonNord;
    private JButton boutonSud;
    private JButton boutonEst;
    private JButton boutonOuest;
    private JTextArea texte;
    private JLabel image;

    public GUI(Jeu j) {
        jeu = j;
        creerGUI();
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
	   	URL mouton = this.getClass().getClassLoader().getResource("images/test.png");
	   	System.out.println(imageURL);
	   	if( imageURL != null ) {
        	image.setIcon( new ImageIcon( imageURL ));
        	personnage.setIcon(new ImageIcon(mouton));
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
        fenetre = new JFrame("Jeu");
//        personnage.setBounds(350, 150, 150, 150);
        panel = new JPanel();
        panelTexte = new JPanel();
        panelTexte.setBounds(0, 426, 782, 426);
        panelTexte.setLayout(new BorderLayout());
        panelCarte = new JPanel();
        panelCarte.setBounds(0, 0, 782, 426);
        panelImage = new JPanel();
        panelImage.setBounds(0, 25, 782, 401);
        panelDialogue = new JPanel();
        panelDialogue.setLayout(new BorderLayout());
        entree = new JTextField(34);
        image = new JLabel();
        image.setBounds(0, 0, 782, 401);
        image.setBackground(new Color(153, 0, 204));
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        texte = new JTextArea();
        boutonNord = new JButton("Nord");
        boutonNord.setBounds(0, 0, 782, 25);
        boutonNord.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jeu.allerEn("NORD");
        	}
        });
        boutonSud = new JButton("Sud");
        boutonEst = new JButton("Est");
        boutonOuest = new JButton("Ouest");
        texte.setEditable(false);
        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));
        panelImage.setLayout(null);
        personnage = new JLabel();
        personnage.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		jeu.allerEn("NORD");
        	}
        });
        personnage.setBounds(0, 0, 50, 180);
        personnage.setBackground(new Color(204, 204, 0));
        panelImage.add(personnage);
        panelImage.add(image);
        panelCarte.setLayout(null);
        panelCarte.add(boutonNord);
        panelCarte.add(panelImage);
        panelTexte.add(panelDialogue,BorderLayout.NORTH);
        panelTexte.add(listScroller,BorderLayout.CENTER);
        panelTexte.add(entree,BorderLayout.SOUTH);
        panel.setLayout(null);
        panel.add(panelCarte);
        
        Panel panel_1 = new Panel();
        panel_1.setBounds(0, 0, 56, 150);
        panelCarte.add(panel_1);
        panel.add(panelTexte);	
        fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        entree.addActionListener(this);

        fenetre.setBounds(500,10,800,900);
        fenetre.setVisible(true);
        entree.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {
        executerCommande();
    }

    private void executerCommande() {
        String commandeLue = entree.getText();
        entree.setText("");
        jeu.traiterCommande( commandeLue);
    }
}