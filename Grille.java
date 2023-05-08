import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Grille extends JPanel{
    private static int largeur = 10; 
    private static int hauteur = 10; 
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//ALPHABET.charAt(5) -> E
    //Caractéristiques : les valeurs dans le jeu
    private static final int VIDE = -1 ;
    private static final int TORPILLEUR = 0 , SOUSMARIN = 1 , CONTRETORPILLEUR = 2 , CROISEUR = 3 , PORTEAVION = 4; //Chiffre = identité du bateau pas la taille
    private static final int[] NB_CASES = {2,3,3,4,5};
    private static final int VERTICAL = 1 , HORIZONTAL = 2;
    
    private int[][] plateau;//LES BATEAUX ET LES CASES VIDES
    private JButton[][] grid;
    
    public static int getNbCases(int bateau){
        return NB_CASES[bateau];
    }
    
    public Grille(){
        //Permet de "créer" le JPAnel
        super();
        
        //Création du plateau  --- /!\ PAS GRAPHIQUE
        plateau = new int[hauteur][largeur] ; 
        for(int i = 0 ; i < hauteur ; i++){//lignes
            for(int j = 0 ; j < largeur ; j++){//colonnes
                plateau[i][j] = VIDE;
            }
        }
        
        //ia();
        
        //JPanel géré par un GridLayout 
        this.setLayout(new GridLayout(hauteur+1 , largeur+1));//+1 -> titres
        this.add(new JLabel());
        for(int i = 0 ; i<largeur ; i++){
            this.add(new JLabel(""+ALPHABET.charAt(i)));
        }//Titres des col
        //RESTE : on est dans plateau, sauf première col !
        
    }
    
    public void show(){
        for(int i = 0 ; i<hauteur ; i++){
            this.add(new JLabel(""+(i+1)));//AJOUT DU TITRE
            for(int j = 0 ; j<largeur ; j++){
                this.add(new JLabel(getBoat(i,j)));
            }
        }  
    }
    
    public void player(){    
        grid = new JButton[hauteur][largeur];
        for(int i = 0 ; i<hauteur ; i++){
            this.add(new JLabel(""+(i+1)));//AJOUT DU TITRE
            for (int j = 0; j < largeur; j++) {
                grid[i][j] = new JButton();
                final int x = i;
                final int y = j;
                this.add(grid[i][j]);
                grid[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        if (button.getText().equals("X")) {
                            plateau[x][y] = 1;
                            System.out.println(""+ x + y);
                            button.setText("");
                        } else {
                            plateau[x][y] = 0;
                            System.out.println(""+ x + y);
                            button.setText("X");
                        }
                    }
                });
            }        
        }  
    }
    
    public void printPlateau(){
        for(int i = 0 ; i<hauteur ; i++){
            System.out.print("[");
            for(int j = 0 ; j<largeur ; j++){
                System.out.print(plateau[i][j]+", ");
            }
            System.out.println("]");
        }  
    }
    
    public String getBoat(int i, int j){
        String rep = "";
        if(plateau[i][j] == VIDE){
            rep = "";
        }
        else {
            if(plateau[i][j] == TORPILLEUR){
                rep = "TORP";
            }
            else {
                if(plateau[i][j] == SOUSMARIN){
                    rep = "SOUS";
                }
                else {
                    if(plateau[i][j] == CONTRETORPILLEUR){
                        rep = "CONT";
                    }
                    else {
                        if(plateau[i][j] == CROISEUR){
                            rep = "CROIS";
                        }
                        else {
                            if(plateau[i][j] == PORTEAVION){
                                rep = "PORT";
                            }
                        }
                    }
                }
            }
        }
        return rep;
    }
        
    public void ia() {
        System.out.println("IA");
        int x=0, y=0, i=0;//, j;
        for(int bateau = TORPILLEUR ; bateau <= PORTEAVION ; bateau++){ //On va placer les 5 bateaux, du TORP au PORTE_AV
            //for (i = 0; i < 4; i++) { // Boucle pour placer chaque bateau
            int axe ; 
            if((int) (Math.random() * 2) <1){
                axe = VERTICAL ; 
            }
            else{
                axe = HORIZONTAL ;
            }
            //int axe = (int) (Math.random() * 2)+1;//axe : 1 -> vertic ; axe : 2 -> horiz
        
            boolean placementOk = false;
            boolean caseDisponible = true;
            while (!placementOk) { // Tant que le placement n'est pas valide, cad une case est déjà occupée
                if(axe==HORIZONTAL){ 
                    x = (int) (Math.random() * (largeur-getNbCases(bateau)));
                    y = (int) (Math.random() * hauteur);
                }
                else{
                    x = (int) (Math.random() * largeur);
                    y = (int) (Math.random() * (hauteur-getNbCases(bateau)));
                }
                
                caseDisponible = true;
                for (int j = 0; j <= getNbCases(bateau); j++){
                    if ((axe == VERTICAL && y + j < hauteur && plateau[x][y + j] != VIDE) || (axe == HORIZONTAL && x + j < largeur && plateau[x + j][y] != VIDE)) {
                        caseDisponible = false;
                        break;
                    }
                }
                //ON PEUT PLACER LE BATEAU
                if (caseDisponible) { // Si la case est disponible, place le bateau et sort de la boucle
                    for (int j = 0; j < getNbCases(bateau); j++) {
                        if(axe==VERTICAL){
                            plateau[x][y+j] = bateau;
                        }
                        else{//CAS HORIZ
                            plateau[x+j][y] = bateau;
                        }
                    }
                    placementOk = true;
                }
            }
        }
    }
    
    public void joueur(){
       
    }
}