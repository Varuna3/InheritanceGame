package InheritanceGame;
import javax.swing.ImageIcon;

public class Pacman extends BipedalAbstract {

	public Pacman(int x, int y) {
		super(x, y, 100, 10, 200, 5, GetImage.get("/Images/PacmanSpriteL.png"),
				GetImage.get("/Images/PacmanSprite.png"), 50);
		// Change to Image.
		setScaleX(170);
		setScaleY(170);
		setVelocity(20);
		setType("Pacman");
	}
	
	
	
	public static void main(String[] args) {

	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
	}

	@Override
	public void heal() {
		// TODO Auto-generated method stub

	}



	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
