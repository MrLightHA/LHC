package me.lightha.lhc.particle.impl;

import me.lightha.lhc.api.IMain;
import me.lightha.lhc.api.exception.ParticleRenderException;
import me.lightha.lhc.particle.*;
import me.lightha.lhc.particle.shape.Shape;
import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class ParticleManagerImpl implements ParticleManager, IMain {
    private final Map<UUID, ConfiguredParticle> particles = new HashMap<>();

    @Override
    public Map<UUID, ConfiguredParticle> getConfiguredParticleMap() {
        return this.particles;
    }

    @Override
    public void resetAll() {
        particles.clear();
    }

    @Override
    public void reset(UUID uuid) {
       particles.remove(uuid);
    }

    @Override
    public void start(UUID uuid, Settings<ParticleSettings> particleSettings, Shape shape, Location location, List<Player> viewers) {
        particles.put(uuid, new ConfiguredParticle(particleSettings, shape, location, viewers));
    }

    @Override
    public void tick(UUID uuid, long duration) {
        final ConfiguredParticle particle = particles.get(uuid);
        if (particle == null) return;

        try {
            new ParticleTickTask(uuid, particle.getShape(), particle.getParticleSettings(), particle.getLocation(), particle.getViewers(), duration)
                    .runTaskTimer(instance, 0L, 5L);
        } catch (ParticleRenderException ex) {
            ex.printStackTrace();
        }
    }


}
