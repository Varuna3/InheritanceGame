package InheritanceGame;

public class Archer extends UnitAbstract {

	public Archer(int x, int y) {
		super(x, y, 100, 10, 200, 5, GetImage.get("/Images/inkyL.png"),
				GetImage.get("/Images/inkyR.png"));
		// Change to Image.
		setScaleX(100);
		setScaleY(100);
		setType("Archer");
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
