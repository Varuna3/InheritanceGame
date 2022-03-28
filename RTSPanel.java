import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

public class RTSPanel extends JPanel {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int boundx = (int)screenSize.getWidth();
	private int boundy = (int)screenSize.getWidth();

	private Image bg;
	private javax.swing.Timer timer;
	private javax.swing.Timer moveTimer;
	private List<UnitAbstract> units = new ArrayList<UnitAbstract>();
	private List<UnitAbstract> selectedUnits = new ArrayList<UnitAbstract>();
	private List<Pacman> pacmen = new ArrayList<Pacman>();
	private List<Carrot> carrots = new ArrayList<Carrot>();
	// public static Queue<UnitAbstract> selectedQueue = new LinkedList<>();

	private JButton knightBtn, peonBtn, archerBtn, pacmanBtn, exitBtn, startBtn, pauseBtn, resetBtn;
	private JButton removeKBtn, removePBtn, removeABtn, removePMBtn;
	private JMenuBar mb;
	private JPanel northPnl;
	Carrot badFruit = new Carrot(200, 200);
	Keep house1 = new Keep(200, 200);

	// private Carrot badFruit = new Carrot(200, 200);

	private int siberiaX = 100000000, siberiaY = 1000000000;

	private Rectangle SelectedRect;

	private Rectangle pacRect;

	private Boolean deadly = false;

	private int velocityX;
	private int velocityY;

	private Carrot carrot;

	private int selectionRectx;
	private int selectionRecty;
	private int selectionRectBRX;
	private int selectionRectBRY;
	private int selectionRectWidth;
	private int selectionRectHeight;

	public RTSPanel(JMenuBar mb) {
		bg = GetImage.get("/Images/RTSBG.png");
		this.mb = mb;
		timer = new javax.swing.Timer(50, new MoveListener());
		timer.start();

		moveTimer = new javax.swing.Timer(5000, new MoveListener());
		moveTimer.start();

		carrot = new Carrot(600, 600);
		// alfred = new Peon(400, 500);
		// lance = new Knight(800, 100);
		// units.add(alfred);
		// units.add(lance);
		loadGUIObjects();
		loadMenu();

		
		carrots.add(badFruit);
		// g.drawImage();
		// loadUnits();

		addMouseListener(new MouseListener());
		addMouseMotionListener(new MouseMoveListener());
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(bg, 0, 0, boundx, boundy, null);
//		g.drawImage(carrot.getSelectedImage(), carrot.getxLoc(), carrot.getyLoc(), carrot.getScaleX(), carrot.getScaleY(), null);
		g.drawImage(house1.getSelectedImage(), house1.getxLoc(), house1.getyLoc(), house1.getScaleX(), house1.getScaleY(), null);

		repaint();
//		g.drawImage(badFruit.getSelectedImage(), badFruit.getxLoc(), badFruit.getyLoc(), badFruit.getScaleX(), badFruit.getScaleY(), null);
		for (UnitAbstract ua : units) {
			if(!ua.isBillet()) {
				
			g.drawImage(ua.getSelectedImage(), ua.getxLoc(), ua.getyLoc(), ua.getScaleX(), ua.getScaleY(), null);
			repaint();
			if(ua.getType() != "Pacman") {
				g.setColor(Color.RED);
				double healthBarWidth = 75;
				g.fillRoundRect(ua.getxLoc() + 10, ua.getyLoc() - 10, (int)healthBarWidth, 10, 10, 10);
				g.setColor(Color.CYAN);
				double percentHealth = ((double)ua.getCurrentHp() / (double)ua.getHp());
				healthBarWidth = healthBarWidth * percentHealth;
				// ua.setCurrentHp(50);
				g.fillRoundRect(ua.getxLoc() + 10, ua.getyLoc() - 10, (int)healthBarWidth, 10, 10, 10);

			}
			}


			g.setColor(Color.WHITE);
			if (ua.isSelected()) {
				g.drawRect(ua.getxLoc(), ua.getyLoc(), ua.getScaleX(), ua.getScaleY());
			}
			if (SelectedRect != null)
				g.drawRect((int) SelectedRect.getX(), (int) SelectedRect.getY(), (int) SelectedRect.getWidth(),
						(int) SelectedRect.getHeight());
			// g.draw(SelectedRect);
			
			// **************************** New-Logic **********************
			addPacmen();
			pacIsDeadly();
			wanderAround();
			ShouldTakeDamage();
			// ******************************** End-New-Logic **********************
			

			repaint();
		}

		// g.drawImage(alfred.getSelectedImage(), alfred.getxLoc(), alfred.getyLoc(),
		// alfred.getScaleX(), alfred.getScaleY(), null);
		// g.drawImage(lance.getSelectedImage(), lance.getxLoc(), lance.getyLoc(),
		// lance.getScaleX(), lance.getScaleY(), null);
	}

	private void addPacmen() {
		for (UnitAbstract ua : units) {
			if(ua.getType() == "Pacman") {
				pacmen.add((Pacman)ua);
			}
		}
	}

	private void wanderAround() {
		for (UnitAbstract ua : units) {
			if(ua.getType() == "Archer") {
				// if(ua.getxDest() == ua.getxLoc() && ua.getyDest() == ua.getyLoc()) {
					ua.setxDest(rand.nextInt(boundx));
					ua.setyDest(rand.nextInt(boundy));
				// }
			}
		}

		for (UnitAbstract ua : units) {
			if(ua.getType() == "Peon") {
				// if(ua.getxDest() == ua.getxLoc() && ua.getyDest() == ua.getyLoc()) {
					ua.setxDest(rand.nextInt(boundx));
					ua.setyDest(rand.nextInt(boundy));
					System.out.println(ua.getVelocity());
				// }
			}
		}

		for (UnitAbstract ua : units) {
			if(ua.getType() == "Knight") {
				// if(ua.getxDest() == ua.getxLoc() && ua.getyDest() == ua.getyLoc()) {
					ua.setxDest(rand.nextInt(boundx));
					ua.setyDest(rand.nextInt(boundy));
				// }
			}
		}
	}

	// private void selectionSort() {

	// 	System.out.println("********************************SELECTION SORT********************************");
	// 	for (UnitAbstract ua : units) {
	// 		System.out.println(ua);
	// 	}
	// 	SortClass sc = new SortClass();

	// 	UnitAbstract[] temp = units.toArray(new UnitAbstract[0]);
	// 	sc.selectionSort(temp);
	// 	units = Arrays.asList(temp);
	// 	System.out.println("********************************SORTED********************************");
	// 	// for (UnitAbstract ua : units) {
	// 	// System.out.println(ua);
	// 	// }
	// 	units.forEach(u -> System.out.println(u));
	// }

	// private void bubbleSort() {
	// 	System.out.println("********************************BUBBLE SORT********************************");
	// 	for (UnitAbstract ua : units) {
	// 		System.out.println(ua);
	// 	}
	// 	SortClass sc = new SortClass();

	// 	UnitAbstract[] temp = units.toArray(new UnitAbstract[0]);
	// 	sc.bubbleSort(temp);
	// 	units = Arrays.asList(temp);
	// 	System.out.println("********************************SORTED********************************");
	// 	// for (UnitAbstract ua : units) {
	// 	// System.out.println(ua);
	// 	// }
	// 	units.forEach(u -> System.out.println(u));
	// }

	// private void insertionSort() {
	// 	System.out.println("********************************INSERTION SORT********************************");
	// 	for (UnitAbstract ua : units) {
	// 		System.out.println(ua);
	// 	}
	// 	SortClass sc = new SortClass();

	// 	UnitAbstract[] temp = units.toArray(new UnitAbstract[0]);
	// 	sc.insertionSort(temp);
	// 	units = Arrays.asList(temp);
	// 	System.out.println("********************************SORTED********************************");
	// 	// for (UnitAbstract ua : units) {
	// 	// System.out.println(ua);
	// 	// }
	// 	units.forEach(u -> System.out.println(u));
	// }

	private void loadMenu() {
		JMenu fileMnu = new JMenu("file");
		JMenu unitMnu = new JMenu("units");
		JMenu sortMnu = new JMenu("sort");

		mb.add(fileMnu);
		mb.add(unitMnu);
		mb.add(sortMnu);

		fileMnu.setForeground(Color.white);
		unitMnu.setForeground(Color.white);
		sortMnu.setForeground(Color.PINK);

		JMenuItem exitMi = new JMenuItem("Exit");
		JMenuItem startMi = new JMenuItem("Start");
		JMenuItem pauseMi = new JMenuItem("Pause");
		JMenuItem resetMi = new JMenuItem("Reset");

		JMenuItem peonMi = new JMenuItem("Peon");
		JMenuItem knightMi = new JMenuItem("Knight");
		JMenuItem archerMi = new JMenuItem("Archer");
		JMenuItem pacmanMi = new JMenuItem("Pacman");

		JMenuItem rpeonMi = new JMenuItem("Remove Peons");
		JMenuItem rknightMi = new JMenuItem("Remove Knights");
		JMenuItem rarcherMi = new JMenuItem("Remove Archers");
		JMenuItem rpacmanMi = new JMenuItem("Remove Pacmen");

		JMenuItem selectionMi = new JMenuItem("Selection Sort");
		JMenuItem bubbleMi = new JMenuItem("Bubble Sort");
		JMenuItem insertionMi = new JMenuItem("Insertion Sort");

		fileMnu.add(exitMi);
		fileMnu.add(startMi);
		fileMnu.add(pauseMi);
		fileMnu.add(resetMi);

		unitMnu.add(peonMi);
		unitMnu.add(knightMi);
		unitMnu.add(archerMi);
		unitMnu.add(pacmanMi);
		unitMnu.add(rpeonMi);
		unitMnu.add(rknightMi);
		unitMnu.add(rarcherMi);
		unitMnu.add(rpacmanMi);

		sortMnu.add(selectionMi);
		sortMnu.add(bubbleMi);
		sortMnu.add(insertionMi);

		// make mi for startMi, pauseMi,

		exitMi.addActionListener(event -> System.exit(0));
		startMi.addActionListener(event -> timer.start());
		pauseMi.addActionListener(event -> timer.stop());
		resetMi.addActionListener(event -> {
			units.clear();
			repaint();
		});

		peonMi.addActionListener(new PeonBtnAction());
		knightMi.addActionListener(new KnightBtnAction());
		archerMi.addActionListener(new ArcherBtnAction());
		pacmanMi.addActionListener(new PacmanBtnAction());

		rpeonMi.addActionListener(event -> removePeons());
		rknightMi.addActionListener(event -> removeKnights());
		rarcherMi.addActionListener(event -> removeArchers());
		rpacmanMi.addActionListener(event -> removePacmen());

		exitMi.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		startMi.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		pauseMi.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		resetMi.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));

		peonMi.setAccelerator(KeyStroke.getKeyStroke("P"));
		knightMi.setAccelerator(KeyStroke.getKeyStroke("K"));
		archerMi.setAccelerator(KeyStroke.getKeyStroke("A"));
		pacmanMi.setAccelerator(KeyStroke.getKeyStroke("M"));

		rpeonMi.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
		rknightMi.setAccelerator(KeyStroke.getKeyStroke("ctrl K"));
		rarcherMi.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
		rpacmanMi.setAccelerator(KeyStroke.getKeyStroke("ctrl M"));

		// selectionMi.addActionListener(event -> selectionSort());
		// bubbleMi.addActionListener(event -> bubbleSort());
		// insertionMi.addActionListener(event -> insertionSort());

	}

	private void loadGUIObjects() {
		this.setLayout(new BorderLayout());

		// southPnl = new JPanel();
		// southPnl.setLayout(new BoxLayout(southPnl, BoxLayout.Y_AXIS));

		// northPnl = new JPanel();
		northPnl = new JPanel() {

			public void paintComponent(Graphics g) {
				g.drawImage(Toolkit.getDefaultToolkit().getImage("Images/RTSBG.png"), 0, 0, 1980, 200, this);
			}
		};

		this.add(northPnl, BorderLayout.NORTH);
		// northPnl.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));

		Border raisedBevel = BorderFactory.createRaisedBevelBorder();

		northPnl.setBorder(raisedBevel);

		// instantiate objects
		knightBtn = new JButton("Knight");
		peonBtn = new JButton("Peon");
		archerBtn = new JButton("Archer");
		pacmanBtn = new JButton("PacMan");
		exitBtn = new JButton("Exit");
		startBtn = new JButton("Start");
		pauseBtn = new JButton("Pause");
		resetBtn = new JButton("Reset");
		removeKBtn = new JButton("Remove Knights");
		removePBtn = new JButton("Remove Peons");
		removeABtn = new JButton("Remove Archers");
		removePMBtn = new JButton("Remove Pacmen");

		// // add file chooser
		// JButton fileBtn = new JButton("Select File");
		// northPnl.add(fileBtn);
		// fileBtn.addActionListener(new FileAction());

		// // add combo box
		// String[] cbModel = { "Knight", "Peon", "Archer", "Pacman" };
		// JComboBox unitsCB = new JComboBox(cbModel);
		// northPnl.add(unitsCB);
		// unitsCB.addActionListener(event -> {
		// 	int index = unitsCB.getSelectedIndex();
		// 	if (index == 0) {
		// 		System.out.println("Knight Selected");

		// 	} else if (index == 1) {
		// 		System.out.println("Peon Selected");

		// 	} else if (index == 2) {
		// 		System.out.println("Archer Selected");

		// 	} else if (index == 3) {
		// 		System.out.println("Pacman Selected");

		// 	}
		// });

		// add to panel
		northPnl.add(Box.createRigidArea(new Dimension(200, 0)));

		northPnl.add(knightBtn);
		northPnl.add(peonBtn);
		northPnl.add(archerBtn);
		northPnl.add(pacmanBtn);

		northPnl.add(Box.createRigidArea(new Dimension(30, 0)));

		northPnl.add(exitBtn);
		northPnl.add(startBtn);
		northPnl.add(pauseBtn);
		northPnl.add(resetBtn);

		northPnl.add(Box.createRigidArea(new Dimension(30, 0)));

		northPnl.add(removeKBtn);
		northPnl.add(removePBtn);
		northPnl.add(removeABtn);
		northPnl.add(removePMBtn);

		exitBtn.setPreferredSize(new Dimension(80, 25));
		startBtn.setPreferredSize(new Dimension(80, 25));
		pauseBtn.setPreferredSize(new Dimension(80, 25));
		resetBtn.setPreferredSize(new Dimension(80, 25));

		// add listeners
		knightBtn.addActionListener(new KnightBtnAction());
		peonBtn.addActionListener(new PeonBtnAction());
		archerBtn.addActionListener(new ArcherBtnAction());
		pacmanBtn.addActionListener(new PacmanBtnAction());
		exitBtn.addActionListener(event -> System.exit(0));

		// startBtn.addActionListener(event -> timer.start());
		startBtn.addActionListener((event) -> {
			timer.start();
			// System.out.println(event.getActionCommand());
		});

		pauseBtn.addActionListener(event -> timer.stop());
		resetBtn.addActionListener(event -> {
			units.clear();
			repaint();
		});

		removeKBtn.addActionListener(event -> removeKnights());
		removePBtn.addActionListener(event -> removePeons());
		removeABtn.addActionListener(event -> removeArchers());
		removePMBtn.addActionListener(event -> removePacmen());

		knightBtn.setMnemonic('K');
		peonBtn.setMnemonic('P');
		archerBtn.setMnemonic('A');
		pacmanBtn.setMnemonic('M');

		exitBtn.setMnemonic('X');
		startBtn.setMnemonic('S');
		pauseBtn.setMnemonic('U');
		resetBtn.setMnemonic('R');

		// knightBtn.setText("Knight");

		// knightBtn, peonBtn, archerBtn, pacmanBtn
		// repaint();
	}

	private void removeKnights() {

		units.removeIf(unit -> {
			if (unit instanceof Knight)
				return true;
			else
				return false;
		});
	}

	private void removePeons() {
		units.removeIf(unit -> {
			if (unit instanceof Peon)
				return true;
			else
				return false;
		});
	}

	private void removeArchers() {
		units.removeIf(unit -> {
			if (unit instanceof Archer)
				return true;
			else
				return false;
		});
	}

	private void removePacmen() {
		units.removeIf(unit -> {
			if (unit instanceof Pacman)
				return true;
			else
				return false;
		});
	}

	private void loadKnight() {
		// String value = JOptionPane.showInputDialog(this, "How many units do you
		// want?");
		// int num = Integer.parseInt(value);
		// System.out.println(value);

		for (int i = 0; i < 1; i++) {
			Random rand = new Random();
			int x = rand.nextInt(1200) + 50;
			int y = rand.nextInt(600) + 50;
			int h = rand.nextInt(2); // 0 = up, 1 = down
			int b = rand.nextInt(2); // 0 = left, 1 = right
			Knight k = new Knight(x, y);
			k.setDirectionH(h);
			k.setDirectionV(b);
			units.add(k);
			// System.out.println(k.toString());
		}
	}

	private void loadPeon() {
		// String value = JOptionPane.showInputDialog(this, "How many units do you
		// want?");
		// int num = Integer.parseInt(value);
		// System.out.println(value);

		for (int i = 0; i < 1; i++) {
			Random rand = new Random();
			int x = rand.nextInt(1200) + 50;
			int y = rand.nextInt(600) + 50;
			int h = rand.nextInt(2); // 0 = up, 1 = down
			int b = rand.nextInt(2); // 0 = left, 1 = right
			Peon k = new Peon(x, y);
			k.setDirectionH(h);
			k.setDirectionV(b);
			units.add(k);
			// System.out.println(k.toString());
		}
	}

	private void loadArcher() {
		// String value = JOptionPane.showInputDialog(this, "How many units do you
		// want?");
		// int num = Integer.parseInt(value);
		// System.out.println(value);

		for (int i = 0; i < 1; i++) {
			Random rand = new Random();
			int x = rand.nextInt(1200) + 50;
			int y = rand.nextInt(600) + 50;
			int h = rand.nextInt(2); // 0 = up, 1 = down
			int b = rand.nextInt(2); // 0 = left, 1 = right
			Archer k = new Archer(x, y);
			k.setDirectionH(h);
			k.setDirectionV(b);
			units.add(k);
			// System.out.println(k.toString());
		}
	}

	private void loadPacman() {
		// String value = JOptionPane.showInputDialog(this, "How many units do you
		// want?");
		// int num = Integer.parseInt(value);
		// System.out.println(value);

		for (int i = 0; i < 1; i++) {
			Random rand = new Random();
			int x = rand.nextInt(1200) + 50;
			int y = rand.nextInt(600) + 50;
			int h = rand.nextInt(2); // 0 = up, 1 = down
			int b = rand.nextInt(2); // 0 = left, 1 = right
			Pacman k = new Pacman(x, y);
			k.setDirectionH(h);
			k.setDirectionV(b);
			units.add(k);
			// System.out.println(k.toString());
		}
	}

	// private void loadUnits() {
	//
	//// for (int i = 0; i < 10; i++) {
	//// Random rand = new Random();
	//// int x = rand.nextInt(1820) + 50;
	//// int y = rand.nextInt(980) + 50;
	//// int h = rand.nextInt(2); // 0 = up, 1 = down
	//// int b = rand.nextInt(2); // 0 = left, 1 = right
	//// Knight k = new Knight(x,y);
	//// k.setDirectionH(h);
	//// k.setDirectionV(b);
	//// units.add(k);
	//// System.out.println(k.toString());
	//// }
	//
	// for (int i = 0; i < 10; i++) {
	// Random rand = new Random();
	// int x = rand.nextInt(1820) + 50;
	// int y = rand.nextInt(980) + 50;
	// int h = rand.nextInt(2); // 0 = up, 1 = down
	// int b = rand.nextInt(2); // 0 = left, 1 = right
	// Peon k = new Peon(x,y);
	// k.setDirectionH(h);
	// k.setDirectionV(b);
	// units.add(k);
	// System.out.println(k.toString());
	// }
	//
	// for (int i = 0; i < 10; i++) {
	// Random rand = new Random();
	// int x = rand.nextInt(1820) + 50;
	// int y = rand.nextInt(980) + 50;
	// int h = rand.nextInt(2); // 0 = up, 1 = down
	// int b = rand.nextInt(2); // 0 = left, 1 = right
	// Archer k = new Archer(x,y);
	// k.setDirectionH(h);
	// k.setDirectionV(b);
	// units.add(k);
	// System.out.println(k.toString());
	// }
	//
	// for (int i = 0; i < 1; i++) {
	// Random rand = new Random();
	// int x = rand.nextInt(1820) + 50;
	// int y = rand.nextInt(980) + 50;
	// int h = rand.nextInt(2); // 0 = up, 1 = down
	// int b = rand.nextInt(2); // 0 = left, 1 = right
	// Pacman k = new Pacman(x,y);
	// k.setDirectionH(h);
	// k.setDirectionV(b);
	// units.add(k);
	// System.out.println(k.toString());
	// }
	//
	// }
	Random rand = new Random();

	private void ShouldTakeDamage() {
		for (UnitAbstract ua : units) {
			if(ua.getType() == "Pacman") {
				pacRect = new Rectangle(ua.getxLoc(), ua.getyLoc(), ua.getScaleX(), ua.getScaleY());
			} else if(ua.getType() == "Archer") {
				if(pacRect.contains(ua.getxLoc(), ua.getyLoc())) {
					if(deadly == true) {
					
						if(ua.getCurrentHp() - 50 > 0) {
							// ua.takeDamage(10);
							ua.setxLoc(rand.nextInt(1100));
							ua.setyLoc(rand.nextInt(500));
							ua.takeDamage(50);
						} else {
							ua.setxLoc(siberiaX);
							ua.setyLoc(siberiaY);
						}
					
					} else {
					hurtPacman();
					}
				}
			}
			 else if(ua.getType() == "Knight") {
				if(pacRect.contains(ua.getxLoc(), ua.getyLoc())) {
					if(deadly == true) {
					
						if(ua.getCurrentHp() - 50 > 0) {
							// ua.takeDamage(10);
							ua.setxLoc(rand.nextInt(1100));
							ua.setyLoc(rand.nextInt(500));
							ua.takeDamage(50);
						} else {
							ua.setxLoc(siberiaX);
							ua.setyLoc(siberiaY);
						}
					
					} else {
					hurtPacman();
					}
				}
			} else if(ua.getType() == "Peon") {
				if(pacRect.contains(ua.getxLoc(), ua.getyLoc())) {
					if(deadly == true) {
					
						if(ua.getCurrentHp() - 50 > 0) {
							// ua.takeDamage(10);
							ua.setxLoc(rand.nextInt(1100));
							ua.setyLoc(rand.nextInt(500));
							ua.takeDamage(50);
						} else {
							ua.setxLoc(siberiaX);
							ua.setyLoc(siberiaY);
						}
					
					} else {
					hurtPacman();
					}
				}
			}
		}
	}

	private void pacIsDeadly() {
		for (Pacman p : pacmen) {
			if(p.getIsStrong()) {
				deadly = true;
			} else {
				deadly = false;
			}
		}
	}

	private void hurtPacman() {
		for (Pacman p : pacmen) {
			p.setxLoc(siberiaX);
			p.setyLoc(siberiaY);
		}
	}

	

	private class MoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			for (UnitAbstract ua : units) {
				if ((ua.getxDest() != null) && (ua.getyDest() != null)) {
					if (ua.getxDest() != ua.getxLoc() && ua.getyDest() != ua.getyLoc()) {
						// ua.setVelocity(10);
						
						int xDist, yDist;
	
						xDist = Math.abs(ua.getxLoc() - ua.getxDest());
						yDist = Math.abs(ua.getyLoc() - ua.getyDest());
	
						double xRatio = xDist / ((double) xDist + yDist);
	
						velocityX = (int) Math.round(ua.getVelocity() * xRatio);
						velocityY = ua.getVelocity() - velocityX;
	
						if (ua.getyDest() < ua.getyLoc() && ua.getxDest() < ua.getxLoc()) { // Above and left
							if(ua.getType() == "Pacman") {
								for (Pacman p : pacmen) {
									if(p.getIsStrong() == true) {
										p.setSelectedImage(p.getSLI());
										p.setVelocity(50);
									} else {
										p.setSelectedImage(p.getNSLI());
										p.setVelocity(20);
									}
								}
							} else {
								ua.setSelectedImage(ua.getlImage());
							}
							ua.move(-velocityX, -velocityY);
						}
						else if (ua.getyDest() < ua.getyLoc() && ua.getxDest() > ua.getxLoc()) { // Above and right
							if(ua.getType() == "Pacman") {
								for (Pacman p : pacmen) {
									if(p.getIsStrong() == true) {
										p.setSelectedImage(p.getSRI());
										p.setVelocity(50);
									} else {
										p.setSelectedImage(p.getNSRI());
										p.setVelocity(20);
									}
								}
							} else {
								ua.setSelectedImage(ua.getrImage());
							}
							ua.move(+velocityX, -velocityY);
						}
						else if (ua.getyDest() > ua.getyLoc() && ua.getxDest() > ua.getxLoc()) { // Down and right
							if(ua.getType() == "Pacman") {
								for (Pacman p : pacmen) {
									if(p.getIsStrong() == true) {
										p.setSelectedImage(p.getSRI());
										p.setVelocity(50);
									} else {
										p.setSelectedImage(p.getNSRI());
										p.setVelocity(20);
									}
								}
							} else {
								ua.setSelectedImage(ua.getrImage());
							}
							ua.move(+velocityX, +velocityY);
						}
						else if (ua.getyDest() > ua.getyLoc() && ua.getxDest() < ua.getxLoc()) { // Down and left
							if(ua.getType() == "Pacman") {
								for (Pacman p : pacmen) {
									if(p.getIsStrong() == true) {
										p.setSelectedImage(p.getSLI());
										p.setVelocity(50);
									} else {
										p.setSelectedImage(p.getNSLI());
										p.setVelocity(20);
									}
								}
							} else {
								ua.setSelectedImage(ua.getlImage());
							}
							ua.move(-velocityX, +velocityY);
						}
						if (xDist < velocityX) {
							ua.setxLoc(ua.getxDest());
						}
	
						if (yDist < velocityY) {
							ua.setyLoc(ua.getyDest());
						}

						if (ua.getxLoc() == ua.getxDest() && ua.getyLoc() == ua.getyDest()) {
							ua.setVelocity(ua.getVelocity());
							ua.setxDest(null);
							ua.setyDest(null);
							break;
						}

						if(ua.getRect().intersects(badFruit.getRect())){
							if(ua.getType() == "Pacman") {
								ua.setxLoc(siberiaX);
								ua.setyLoc(siberiaY);
							}

						}
						
						if(ua.getRect().intersects(house1.getRect())) {
							ua.setBillet(true);
							house1.quarterUnit(ua);
						}

						
						// repaint();
						// ua.move();
					}

				}

				repaint();
			}

			// System.out.println("You are in timer =)");
			// alfred.move();
			// lance.move();
			// repaint();
		}

	}

	// ******************** MOUSE ADAPTER ****************
	private class MouseMoveListener extends MouseMotionAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {
			selectionRectBRX = e.getX();
			selectionRectBRY = e.getY();

			if (selectionRectWidth > 0 && selectionRectHeight > 0) {
				SelectedRect = new Rectangle(selectionRectx, selectionRecty, selectionRectWidth, selectionRectHeight);
			} else if (selectionRectWidth > 0 && selectionRectHeight < 0) {
				selectionRectHeight = selectionRecty - selectionRectBRY;
				SelectedRect = new Rectangle(selectionRectx, e.getY(), selectionRectWidth, selectionRectHeight);
			} else if (selectionRectWidth < 0 && selectionRectHeight > 0) {
				selectionRectWidth = selectionRectx - e.getX();
				SelectedRect = new Rectangle(e.getX(), selectionRecty, selectionRectWidth, selectionRectHeight);
			} else if (selectionRectWidth < 0 && selectionRectHeight < 0) {
				selectionRectWidth = selectionRectx - e.getX();
				selectionRectHeight = selectionRecty - e.getY();
				SelectedRect = new Rectangle(e.getX(), e.getY(), selectionRectWidth, selectionRectHeight);
			}

			selectionRectWidth = selectionRectBRX - selectionRectx;
			selectionRectHeight = selectionRectBRY - selectionRecty;
			// SelectedRect = new Rectangle(selectionRectx, selectionRecty,
			// selectionRectWidth, selectionRectHeight);
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// System.out.println("Mouse moving");
		}
	}

	private class MouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if(e.getButton() == 1) {
				selectionRectx = e.getX();
				selectionRecty = e.getY();
			}
			// selectionRectx = e.getX();
			// selectionRecty = e.getY();
			// Rectangle selectionRect = new Rectangle(selectionRectx, selectionRecty, 200,
			// 200);
			// repaint();

		}

		public void mouseReleased(MouseEvent e) {
			for (UnitAbstract ua : units) {
				if (SelectedRect.contains(ua.getRect())) {
					ua.setIsSelected(true);
					selectedUnits.add(ua);
					// System.out.println("IVE BEEN SELECTED!!!" + ua);
				}
			}
			SelectedRect = null;

			
		}

		public void mouseClicked(MouseEvent e) {
			// System.out.println("Mouse clicked");
			// System.out.println(e.getX() + " " + e.getY());
			// System.out.println(e.getButton());

			if (e.getButton() == 1) {
				
				if (house1.containsPoint(e.getX(), e.getY())) {
					System.out.println("I love nerds");
					System.out.println(house1.getQuartered().toString());
				}
				
				for (UnitAbstract ua : units) {
					if (ua.containsPoint(e.getX(), e.getY())) {
						// System.out.println(ua.getId());
						// ua.setVelocity(0);
						// selectedUnit = ua;
						ua.setIsSelected(true);
						selectedUnits.add(ua);
						// System.out.println(selectedUnit.toString());
					}

				}

			} else if (e.getButton() == 3) {
				if(selectedUnits.equals(house1)) {
					System.out.println("Moes is selected");
//					house1.setSelectedUnits(selectedUnits);
				}
				
				
				// initialxDest = e.getX();
				// initialyDest = e.getY();
				if (!(selectedUnits.isEmpty())) {
					for (UnitAbstract ua : selectedUnits) {
						if(ua.isSelected()){
							ua.setxDest(e.getX());
							ua.setyDest(e.getY());

						}
						ua.setIsSelected(false);
						// ua = null;
						
					} 
					for (UnitAbstract ua : selectedUnits) {
						selectedUnits.remove(ua);
					}

	
	
					

				}
			 } 
			 else if(e.getButton() == 2) {
				 for (Pacman p : pacmen) {
					 if(p.getIsStrong() == true) {
						 p.setIsStrong(false);
					 } else if(p.getIsStrong() == false){
					 p.setIsStrong(true);
					 }
				 }
				 repaint();
			 }
		}
	}

	// ***************************** PRIVATE INNER CLASS
	// ***************************************
	private class KnightBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			loadKnight();
			// System.out.println("Knight button pressed");

		}

	} // ******************************* end KnightBtnAction class
		// *********************************************

	// ***************************** PRIVATE INNER CLASS
	// ***************************************
	private class PeonBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			loadPeon();
			// System.out.println("Peon button pressed");
		}

	} // ******************************* end KnightBtnAction class
		// *********************************************

	// ***************************** PRIVATE INNER CLASS
	// ***************************************
	private class ArcherBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			loadArcher();
			System.out.println(event.getID());
			// System.out.println("Archer button pressed");
		}

	} // ******************************* end KnightBtnAction class
		// *********************************************

	// ***************************** PRIVATE INNER CLASS
	// ***************************************
	private class PacmanBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			loadPacman();
			// System.out.println("Pacman button pressed");
		}

	} // ******************************* end KnightBtnAction class
		// *********************************************

} // ******************************** END MAIN CLASS
	// **********************************************
