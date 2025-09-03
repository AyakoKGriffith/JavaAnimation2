package org.oosd.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Objects;

public class MusicPlayer {

    private MediaPlayer player;

    /**
     * Load and (optionally) start playing a track. Use classpath-relative path, e.g. "/audio/bgm.mp3".
     */
    public void start(String resourcePath, boolean loop) {
        stop(); // dispose old player if any
        URL url = Objects.requireNonNull(getClass().getResource(resourcePath),
                "Music resource not found: " + resourcePath);
        Media media = new Media(url.toExternalForm());
        player = new MediaPlayer(media);
        if (loop) {
            player.setCycleCount(MediaPlayer.INDEFINITE);
        }
        player.play();
    }

    /**
     * Pause if currently playing.
     */
    public void pause() {
        if (player != null && player.getStatus() == MediaPlayer.Status.PLAYING) {
            player.pause();
        }
    }

    /**
     * Resume if currently paused.
     */
    public void resume() {
        if (player != null && player.getStatus() == MediaPlayer.Status.PAUSED) {
            player.play();
        }
    }

    /**
     * Stop and release resources.
     */
    public void stop() {
        if (player != null) {
            player.stop();
            player.dispose();
            player = null;
        }
    }

    /**
     * 0.0–1.0
     */
    public void setVolume(double volume) {
        if (player != null) {
            player.setVolume(clamp(volume, 0.0, 1.0));
        }
    }

    public boolean isPlaying() {
        return player != null && player.getStatus() == MediaPlayer.Status.PLAYING;
    }

    private static double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }
}
