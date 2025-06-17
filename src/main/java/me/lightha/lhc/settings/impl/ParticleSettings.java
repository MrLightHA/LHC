package me.lightha.lhc.settings.impl;

import lombok.NonNull;
import me.lightha.lhc.settings.Settings;
import org.bukkit.Particle;

public final class ParticleSettings extends Settings<ParticleSettings> {
    private static final String SPEED = "speed";
    private static final String TYPE  = "type";
    private static final String RADIUS = "radius";
    private static final String POS_Y = "posY";
    private static final String RGB = "rgb";
    private static final String COUNT = "count";

    public ParticleSettings speed(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("speed must be non‑negative");
        }
        return set(SPEED, value);
    }

    public double speed() {
        return getOrDefault(SPEED, 0.0D);
    }

    public ParticleSettings radius(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("radius must be non‑negative");
        }
        return set(RADIUS, value);
    }

    public double radius() {
        return getOrDefault(RADIUS, 0.0D);
    }

    public ParticleSettings posY(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("radius must be non‑negative");
        }
        return set(POS_Y, value);
    }

    public String rgb() {
        return get(RGB);
    }

    public ParticleSettings rgb(@NonNull String value) {
        return set(RGB, value);
    }

    public int count() {
        return get(COUNT);
    }

    public ParticleSettings count(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("count must be non‑negative");
        }
        return set(COUNT, value);
    }

    public double posY() {
        return getOrDefault(POS_Y, 0.0D);
    }

    public ParticleSettings type(@NonNull Particle particle) {
        return set(TYPE, particle);
    }

    public Particle type() {
        return get(TYPE);
    }
}
