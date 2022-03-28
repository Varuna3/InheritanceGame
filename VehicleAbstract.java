import java.awt.Image;

public abstract class VehicleAbstract extends UnitAbstract {
	private int repairInterval;

	public VehicleAbstract(int x, int y, int hp, int def, int att, int vel, Image li, Image ri, int rep) {
		super(x, y, hp, def, att, vel, li, ri);

		this.repairInterval = rep;
	}

	public String toString() {
		return super.toString() + "repairInterval " + repairInterval;
	}

	public int getRepairInterval() {
		return repairInterval;
	}

	public void setRepairInterval(int repairInterval) {
		this.repairInterval = repairInterval;
	}

}
