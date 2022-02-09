package InheritanceGame;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

public class GetImage {
    public static Image get(String Path) {
        try {
            InputStream stream = GetImage.class.getResourceAsStream(Path);
            return new ImageIcon(ImageIO.read(stream)).getImage();
        }catch (IOException error) {
            return new ImageIcon().getImage();
        }
    }
}
