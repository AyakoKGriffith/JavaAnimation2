package org.oosd.audio;

import org.oosd.audio.SoundEffects.Effect;

/**
 * Facade to keep audio usage super simple across the app.
 */
public class AudioFacade {

//    private static final AudioFacade INSTANCE = new AudioFacade();
//
//    public static AudioFacade get() {
//        return INSTANCE;
//    }

    private static final MusicPlayer music = new MusicPlayer();
    private static final SoundEffects sfx = new SoundEffects();

//    private AudioFacade() {
//    }

    // ===== Background music (MP3) =====
    static public void playBgm() {
        playBgm("/audio/background.mp3", true);
    }

    static public void playBgm(String resourcePath, boolean loop) {
        music.start(resourcePath, loop);
    }

    static public void pauseBgm() {
        music.pause();
    }

    static public void resumeBgm() {
        music.resume();
    }

    static public void stopBgm() {
        music.stop();
    }

    static public void setBgmVolume(double volume) {
        music.setVolume(volume);
    }

    static public boolean isBgmPlaying() {
        return music.isPlaying();
    }

    // ===== Sound effects (WAV/AIFF) =====
    static public void playEatFood() {
        sfx.play(Effect.EAT_FOOD);
    }

    static public void playGameFinish() {
        sfx.play(Effect.GAME_FINISH);
    }

    static public void playChangeDir() {
        sfx.play(Effect.CHANGE_DIR);
    }

    static public void playNewFood() {
        sfx.play(Effect.NEW_FOOD);
    }

    static public void setSfxVolume(double volume) {
        sfx.setVolume(volume);
    }
}
