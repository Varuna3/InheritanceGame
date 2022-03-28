public class Knight extends VehicleAbstract {
	public Knight(int x, int y) {
		super(x, y, 100, 10, 10, 5, GetImage.get("/Images/clydeL.png"),
				GetImage.get("/Images/clydeR.png"), 50);
		// Change to Image.
		setScaleX(100);
		setScaleY(100);
		setType("Knight");
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
