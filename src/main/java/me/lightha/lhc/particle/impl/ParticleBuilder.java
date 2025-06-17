package me.lightha.lhc.particle.impl;

import me.lightha.lhc.api.IParticles;
import me.lightha.lhc.particle.IParticleBuilder;
import me.lightha.lhc.particle.shape.Shape;
import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class ParticleBuilder implements IParticleBuilder<ParticleBuilder>, IParticles {
    private Settings<ParticleSettings> particleSettings;
    private Shape shape;
    private Location location;
    private List<Player> viewers;
    private long duration;

    @Override
    public ParticleBuilder location(Location location) {
        this.location = location;
        return this;
    }

    @Override
    public ParticleBuilder shape(Shape shape) {
        this.shape = shape;
        return this;
    }

    @Override
    public ParticleBuilder viewers(List<Player> viewers) {
        this.viewers = viewers;
        return this;
    }

    @Override
    public ParticleBuilder duration(long duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public ParticleBuilder settings(ParticleSettings particleSettings) {
        this.particleSettings = particleSettings;
        return this;
    }

    @Override
    public void build(UUID uuid) {
        particleManager.start(uuid, particleSettings, shape, location, viewers);
        particleManager.tick(uuid, duration);
    }
}

