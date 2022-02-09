package InheritanceGame;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Pacman extends BipedalAbstract {

	protected Image superRightImage = GetImage.get("/Images/bluePacR.png"); 
	protected Image superLeftImage = GetImage.get("/Images/bluePacL.png");
	protected Image notSuperRightImage = GetImage.get("/Images/PacmanSprite.png"); 
	protected Image notSuperLeftImage = GetImage.get("/Images/PacmanSpriteL.png");

	protected Boolean isStrong;

	public Pacman(int x, int y) {
		super(x, y, 100, 10, 200, 5, GetImage.get("/Images/PacmanSpriteL.png"),
				GetImage.get("/Images/PacmanSprite.png"), 50);
		// Change to Image.
		setScaleX(170);
		setScaleY(170);
		setVelocity(20);
		setType("Pacman");
		isStrong = false;
	}

	public Image getSRI() {
		return superRightImage;
	}

	public Image getSLI() {
		return superLeftImage;
	}

	public Image getNSRI() {
		return notSuperRightImage;
	}

	public Image getNSLI() {
		return notSuperLeftImage;
	}
	
	public void setIsStrong(Boolean s) {
		isStrong = s;
	}

	public Boolean getIsStrong() {
		return isStrong;
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
