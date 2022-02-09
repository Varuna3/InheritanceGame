package InheritanceGame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class RTSGGUI {

	public static void main(String[] args) throws IOException, Exception {
		JFrame gui = new JFrame();
		gui.setTitle("RTS Game");
		gui.setSize(1920, 1080);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setUndecorated(true);
		
		
		
		gui.setIconImage(GetImage.get("/Images/PacmanSprite.png"));
		
		JMenuBar mb = new JMenuBar() {
		
			public void paintComponent(Graphics g) {
				g.drawImage(GetImage.get("/Images/RTSBG.png"), 0, 0, 1980, 200, this);
			}
		};
		
		gui.setJMenuBar(mb);
		
		mb.setBackground(new Color(0, 10, 170));
		mb.setBorder(null);
//		mb.setOpaque(false);
//		mb.setForeground(Color.green);
		
//		gui.setOpacity(.5f);
		
		
		
		
		

		RTSPanel panel = new RTSPanel(mb);
		Container pane = gui.getContentPane();
		pane.add(panel);

		// Audio.main("/Sounds/LOST.wav");

		gui.setVisible(true);
	}

}
