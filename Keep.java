import java.util.ArrayList;

public class Keep extends UnitAbstract {

	private ArrayList<UnitAbstract> quartered;

	public Keep(int x, int y) {
		super(x, y, 100, 10, 200, 5, GetImage.get("/Images/pacmanhouse.png"), GetImage.get("/Images/pacmanhouse.png"));
		// Change to Image.
		setScaleX(280);
		setScaleY(280);

		quartered = new ArrayList<UnitAbstract>();
		
		setType("Keep");
	}
	
	public void setSelectedUnits(ArrayList su) {
		su.clear();
		for (UnitAbstract ua : quartered) {
			su.add(ua);
			ua.setBillet(false);
			ua.setxDest(500);
			ua.setyDest(500);
		}
	}
	
	public ArrayList getQuartered() {
		return quartered;
	}
	
	public void quarterUnit(UnitAbstract ua) {
		quartered.add(ua);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public void heal() {
		// TODO Auto-generated method stub

	}

}
