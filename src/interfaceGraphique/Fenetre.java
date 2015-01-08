package interfaceGraphique;
import javax.swing.JFrame;

public class Fenetre extends JFrame{

	public Fenetre(String string){
		
		this.setTitle(string);
		this.setSize(1000,500);
		this.setLocationRelativeTo(null);
		
		this.setContentPane(new Panneau());
		this.setVisible(true);
		
		
	}
}