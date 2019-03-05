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
	   	URL imageURL = this.getClass().getClassLoader().getResource("images/" + nomImage);
	   	URL mouton = this.getClass().getClassLoader().getResource("images/MoutonTest.png");
	   	System.out.println(imageURL);
	   	if( imageURL != null ) {
        	image.setIcon( new ImageIcon( imageURL ));
        	personnage.setIcon(new ImageIcon(mouton));
            fenetre.pack();   
        }
   }

    public void enable(boolean ok) {
        entree.setEditable(ok);
        if ( ! ok )
            entree.getCaret().setBlinkRate(0);
    }

    private void creerGUIVersionInitial() {
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
        panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panelTexte = new JPanel();
        panelTexte.setLayout(new BorderLayout());
        panelCarte = new JPanel();
        panelCarte.setLayout(new BorderLayout());
        panelImage = new JPanel();
        panelImage.setLayout(new CardLayout());
        panelDialogue = new JPanel();
        panelDialogue.setLayout(new BorderLayout());
        entree = new JTextField(34);
        image = new JLabel();
        texte = new JTextArea();
        boutonNord = new JButton("Nord");
        boutonSud = new JButton("Sud");
        boutonEst = new JButton("Est");
        boutonOuest = new JButton("Ouest");
        personnage = new JLabel();
        texte.setEditable(false);
        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));
        panelImage.add(image);
        panelImage.add(personnage);
        panelCarte.add(boutonNord,BorderLayout.NORTH);
        panelCarte.add(panelImage);
        panelTexte.add(panelDialogue,BorderLayout.NORTH);
        panelTexte.add(listScroller,BorderLayout.CENTER);
        panelTexte.add(entree,BorderLayout.SOUTH);
        panel.add(panelCarte);
        panel.add(panelTexte);	
        fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        entree.addActionListener(this);

        fenetre.pack();
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