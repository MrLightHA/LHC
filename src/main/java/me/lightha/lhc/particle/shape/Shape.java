package me.lightha.lhc.particle.shape;

import me.lightha.lhc.api.annotations.ShapeInfo;
import me.lightha.lhc.api.IParticles;
import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Shape implements IParticles {
    private String name;
    private String description;

    public Shape() {
        Class<? extends Shape> clazz = this.getClass();
        ShapeInfo shapeInfo = clazz.getAnnotation(ShapeInfo.class);

        if (shapeInfo == null) {
            throw new NotImplementedException("@ShapeInfo annotation not found on " + clazz.getSimpleName());
        }

        this.name = shapeInfo.name();
        this.description = shapeInfo.description();
    }

    public abstract void run(Settings<ParticleSettings> particleSettings, Location location, List<Player> viewers);
}
