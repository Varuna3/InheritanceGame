public class Mage implements Cloneable, Comparable<UnitAbstract> {
	private String name;
	private String lname;
	private int age;

	public Mage(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Mage(String name, String lname, int age) {
		this.name = name;
		this.age = age;
		this.lname = lname;
	}

	public Mage(Mage other) {
		// new Mage(other.getName(), other.getAge());
		this.name = other.getName();
		this.age = other.getAge();
	}

	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this == other)
			return false;
		if (!(other instanceof Mage))
			return false;

		Mage m = (Mage) other;
		if (name.equals(m.getName()) && age == m.getAge())
			return true;

		return false;
	}

	public String getName() {
		return name;
	}

	public String getLName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Mage [name=" + name + ", lname=" + lname + ", age=" + age + "]";
	}

	protected Mage clone() throws CloneNotSupportedException {
		return (Mage) super.clone(); // shallow copy. Only copies field data, & unmutable
//		return new Mage(this.name, this.age);

//		return this;
	}

	@Override
	public int compareTo(UnitAbstract other) {
		String fullName = name + lname;
		Mage m = (Mage)(Object)other;
		String otherFullName = m.getName() + m.getLName();

		int compare = fullName.compareTo(otherFullName);

		return compare;
	}

}
