package InheritanceGame;

public class Carrot extends UnitAbstract{
    public Carrot(int x, int y) {
        super(x, y, 100, 10, 200, 5, GetImage.get("/Images/Carrot.png"),
				GetImage.get("/Images/Carrot.png"));
		// Change to Image.
		setScaleX(170);
		setScaleY(170);
		setType("Carrot");
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
