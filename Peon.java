public class Peon extends BipedalAbstract {

	public Peon(int x, int y) {
		super(x, y, 100, 10, 200, 5, GetImage.get("/Images/blinkyL.png"),
		GetImage.get("/Images/blinkyR.png"), 50);
		// Change to Image.
		setScaleX(75);
		setScaleY(75);	
		setType("Peon");
	}
	
	
	
	public static void main(String[] args) {

	}

	@Override
	public void attack() {
	}

	@Override
	public void heal() {
	}



	@Override
	public void move() {
		
	}

}
