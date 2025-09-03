package org.oosd.audio;

import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class SoundEffects {

    public enum Effect {
        EAT_FOOD, GAME_FINISH, CHANGE_DIR, NEW_FOOD
    }

    // Map each effect to a resource path in your classpath
    private static final Map<Effect, String> PATHS = Map.of(
            Effect.EAT_FOOD, "/audio/erase-line.wav",
            Effect.GAME_FINISH, "/audio/game-finish.wav",
            Effect.CHANGE_DIR, "/audio/move-turn.wav",
            Effect.NEW_FOOD, "/audio/level-up.wav"
    );

    private final Map<Effect, AudioClip> cache = new EnumMap<>(Effect.class);
    private double volume = 1.0; // 0.0–1.0

    public void play(Effect effect) {
        AudioClip clip = cache.computeIfAbsent(effect, this::load);
        if (clip != null) {
            clip.setVolume(volume);
            clip.play();
        }
    }

    public void setVolume(double volume) {
        this.volume = Math.max(0.0, Math.min(1.0, volume));
    }

    private AudioClip load(Effect effect) {
        String path = PATHS.get(effect);
        if (path == null) return null;
        URL url = Objects.requireNonNull(getClass().getResource(path),
                "SFX resource not found: " + path);
        return new AudioClip(url.toExternalForm());
    }
}
