package jeu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class LayerItem extends JLabel{
    public LayerItem(){
        this.addMouseMotionListener((new MouseAdapter(){
            public void mouseDragged(MouseEvent evt){
                lblMouseDragged(evt);
            }
        }));
    }

    public void lblMouseDragged(MouseEvent evt){
        System.out.println("Here");
    }
}
