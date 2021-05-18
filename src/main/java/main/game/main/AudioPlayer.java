package main.game.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AudioPlayer implements ActionListener {

    private Clip clip;
    private AudioInputStream input;

    private static boolean SOUND;

    public AudioPlayer(boolean sound) {
        AudioPlayer.SOUND = sound;
    }

    public AudioPlayer() {
        try {
            if(AudioPlayer.SOUND) {
                //Loading file from resources package
                File file = new File(getClass().getClassLoader().getResource("music.wav").getPath());
                input = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(input);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        try {
            if(AudioPlayer.SOUND) clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            if(AudioPlayer.SOUND) {
                clip.stop();
                clip.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        if(text.equals("Sound ON")) {
            AudioPlayer.SOUND = false;
            button.setText("Sound OFF");
        } else {
            AudioPlayer.SOUND = true;
            button.setText("Sound ON");
        }
    }
}
