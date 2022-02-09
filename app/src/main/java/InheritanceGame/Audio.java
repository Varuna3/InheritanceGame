package InheritanceGame;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Audio {

	public static void main(String FileName) throws Exception, IOException {
		InputStream audioSrc = Audio.class.getResourceAsStream(FileName);
		InputStream bufferedIn = new BufferedInputStream(audioSrc);

		// TODO Auto-generated method stub
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);
		Clip clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
	}

}
