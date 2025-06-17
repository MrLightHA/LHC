package me.lightha.lhc.particle;

import me.lightha.lhc.api.IParticles;
import me.lightha.lhc.api.exception.ParticleRenderException;
import me.lightha.lhc.particle.shape.Shape;
import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class ParticleTickTask extends BukkitRunnable implements IParticles {
    private final UUID uuid;
    private final Shape shape;
    private final Settings<ParticleSettings> settings;
    private final Location location;
    private final List<Player> viewers;
    private final long duration;
    private long active = 0L;

    public ParticleTickTask(UUID uuid, Shape shape, Settings<ParticleSettings> settings, Location location, List<Player> viewers, long duration) {
        this.uuid = uuid;
        this.shape = shape;
        this.settings = settings;
        this.duration = duration;
        this.viewers = viewers;
        this.location = location;
    }

    @Override
    public void run() {
        if (active >= duration || !particleManager.getConfiguredParticleMap().containsKey(uuid)) {
            cancel();
            return;
        }

        try {
            shape.run(settings, location, viewers);
        } catch (Exception ex) {
            throw new ParticleRenderException("Невозможно выполнить рендер для локации " + location, ex);
        }

        active += 5;
    }
}

