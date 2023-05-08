
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Appli extends JFrame{
    public Appli(){
        //Permet de "créer" la JFrame
        super("Jeu");
        //On personnalise la JFrame pour qu'elle soit une "GridTest"
        JPanel jp = new JPanel();
    
        Grille gPlayer = new Grille();
        gPlayer.player();
        Grille gOrdi = new Grille();
        gOrdi.ia();
        gOrdi.show();


        jp.setLayout(new BorderLayout());
        jp.add(gPlayer,BorderLayout.WEST);
        jp.add(gOrdi,BorderLayout.EAST);
        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gPlayer.printPlateau();
            }
        });
        jp.add(start);
        this.setContentPane(jp);
        this.pack();
        this.setVisible(true);
    }
}