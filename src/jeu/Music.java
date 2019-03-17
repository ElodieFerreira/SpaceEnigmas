package jeu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;


public class Music implements Runnable {
	private String musicName;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Clip clip = AudioSystem.getClip();
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/music/"+musicName));
				clip.open(inputStream);
				clip.start();
				Thread.sleep(clip.getMicrosecondLength());
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Music(String music) {
		musicName = music;
	}

}
