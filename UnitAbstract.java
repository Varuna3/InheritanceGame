import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public abstract class UnitAbstract implements UnitInterface, Cloneable, Comparable<Object>{
	private int id;
	protected int hp;
	protected int currentHp = 100;
	protected int defense;
	protected int attack;
	protected int velocity;
	protected Image lImage, rImage, selectedImage;
	private int directionV, directionH;
	private int scaleX, scaleY;
	
	private boolean isSelected = false;
	
	private Integer xLoc, yLoc;
	private Integer xDest, yDest;
	
	protected String type;

	private int centerX, centerY;
	
	private static int unitCount;
	private final int MAX_UNITS = 100000000;
	
	private boolean isBillet = false;

	public UnitAbstract(int x, int y, int hp, int def, int att, int vel, Image li, Image ri) {
		this.xLoc = x;
		this.yLoc = y;
		this.xDest = x;
		this.yDest = y;
		this.hp = hp;
		this.defense = def;
		this.attack = att;
		this.velocity = vel;
		this.lImage = li;
		this.rImage = ri;
		this.directionH = 0;
		this.directionV = 0;
		this.type = "nothin yet";
		// this.type = unitType;

		selectedImage = lImage;
		unitCount++;
		
		genId();
	}
	
	public boolean containsPoint(int x, int y) {
		Rectangle rect = new Rectangle(xLoc, yLoc, scaleX, scaleY);
		return rect.contains(x, y);

		
		
//		return false;
	}

	public boolean hasCollision(Rectangle pacRect) {
		Rectangle rect = new Rectangle(xLoc, yLoc, scaleX, scaleY);
		return rect.contains(pacRect);
	}
	
	public Rectangle getRect() {
		return new Rectangle(xLoc, yLoc, scaleX, scaleY);
	}
	
	@Override
	public int compareTo(Object other) {
		
		
//		String fullName = name + lname;
		UnitAbstract ua = (UnitAbstract) other;
		
//		String otherFullName = m.getName() + m.getLName();
		Integer i = id;
		Integer i2 = ua.getId();
		
		int compare = i.compareTo(i2);
		
		return compare;
	}
	
//	public boolean containsPoint(int x, int y) {
//		Rectangle rect = new Rectangle(xLoc, yLoc, scaleX, scaleY);
//		return rect.contains(x, y);
//	}
	
	private void genId() {
		Random gen = new Random();
		this.id = gen.nextInt(100000) + 1;
	}
	
	protected UnitAbstract clone() throws CloneNotSupportedException {
		return (UnitAbstract) super.clone();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		if(this == other) return true;
		if(!(other instanceof UnitAbstract)) return false;
		UnitAbstract ua = (UnitAbstract) other;
		if(this.id == ua.getId() && this.attack == ua.getAttack() && this.defense == ua.getDefense()
				&& this.hp == ua.getHp() && this.velocity == ua.getVelocity()) return true;
		
		return false;
	}

	@Override
	public String toString() {
		return "UnitAbstract [hp=" + hp + ", defense=" + defense + ", attack=" + attack + ", velocity=" + velocity
				+ ", id=" + id + "]";
	}

	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public Image getlImage() {
		return lImage;
	}

	public void setlImage(Image lImage) {
		this.lImage = lImage;
	}

	public Image getrImage() {
		return rImage;
	}

	public void setrImage(Image rImage) {
		this.rImage = rImage;
	}

	public Image getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(Image selectedImage) {
		this.selectedImage = selectedImage;
	}

	public int getDirectionV() {
		return directionV;
	}

	public void setDirectionV(int directionV) {
		this.directionV = directionV;
	}

	public int getDirectionH() {
		return directionH;
	}

	public void setDirectionH(int directionH) {
		this.directionH = directionH;
	}

	public int getScaleX() {
		return scaleX;
	}

	public void setScaleX(int scaleX) {
		this.scaleX = scaleX;
	}

	public int getScaleY() {
		return scaleY;
	}

	public void setScaleY(int scaleY) {
		this.scaleY = scaleY;
	}

	public static void setUnitCount(int unitCount) {
		UnitAbstract.unitCount = unitCount;
	}

	public static int getUnitCount() {
		return unitCount;
	}

	public int getMAX_UNITS() {
		return MAX_UNITS;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getxDest() {
		return xDest;
	}

	public void setxDest(Integer xDest) {
		this.xDest = xDest;
	}

	public Integer getyDest() {
		return yDest;
	}

	public void setyDest(Integer yDest) {
		this.yDest = yDest;
	}
	
	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int hp) {
		this.currentHp = hp;
	}

	public void takeDamage(int damage) {
		/*if(currentHp - damage < 0){
			currentHp = 0;
		} else{*/
			currentHp -= damage;
		// }
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String unitType) {
		type = unitType;
	}
	
	public boolean isBillet() {
		return isBillet;
	}
	
	public void setBillet(boolean isBillet) {
		this.isBillet = isBillet;
	}
	

//	public void move(int x, int y) {
//		
//		if(getDirectionH() == 0) { // Left Facing
//			setxLoc(getxLoc() + -getVelocity()); 
//			selectedImage = lImage;
//		}
//		else {setxLoc(getxLoc() + getVelocity());
//		selectedImage = rImage;
//		}
//		if(getDirectionV() == 0) { // Up Facing
//			setyLoc(getyLoc() + -getVelocity());
//		}
//		else 	setyLoc(getyLoc() + getVelocity()); // Down facing
//	}
	


	public void move(int xAmount, int yAmount) {
		xLoc = xLoc + xAmount;
		yLoc = yLoc + yAmount;
	}

}
