import java.awt.Image;


public abstract class BipedalAbstract extends UnitAbstract {
	private int buildRate;

	public BipedalAbstract(int x, int y, int hp, int def, int att, int vel, Image li, Image ri, int br) {
		super(x, y, hp, def, att, vel, li, ri);

		this.buildRate = br;
	}

	public void attack() {

	}

	public int getBuildRate() {
		return buildRate;
	}

	public void setBuildRate(int buildRate) {
		this.buildRate = buildRate;
	}

	public String toString() {
		return super.toString() + "buildRate " + buildRate;
	}

}
