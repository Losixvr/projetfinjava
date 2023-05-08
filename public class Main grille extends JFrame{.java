public class Main grille extends JFrame{
    public grille(){
        //Permet de "créer" la JFrame
        super("GridLayout : Test");
        //On personnalise la JFrame pour qu'elle soit une "GridTest"
        JPanel jp = new JPanel();
        int nbL = 10 ; int nbC = 10 ;
        jp.setLayout(new GridLayout(nbL,nbC, 5,5));
        for(int i = 0 ; i<nbL*nbC ; i++){
            JPanel jpf = new JPanel();
            jpf.setBackground(Color.PINK);
            JButton jb = new JButton();
            jb.setPreferredSize(new Dimension(15,15));
            jpf.add(jb);
            jp.add(jpf);
        }
        this.setContentPane(jp);
        this.pack();
        this.setVisible(true);
    }
    
}
