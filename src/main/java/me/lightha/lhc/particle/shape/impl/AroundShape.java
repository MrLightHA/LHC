package me.lightha.lhc.particle.shape.impl;

import me.lightha.lhc.api.annotations.ShapeInfo;
import me.lightha.lhc.particle.shape.Shape;
import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

@ShapeInfo(name = "around", description = "Создает партиклы в рандомной точке")
public class AroundShape extends Shape {

    @Override
    public void run(Settings<ParticleSettings> particleSettings, Location location, List<Player> viewers) {
        if (particleSettings instanceof ParticleSettings settings) {
            for(int i = 0; i < settings.count(); ++i) {
                double offsetX = Math.random() * 2.0D * settings.radius() - settings.radius();
                double offsetY = Math.random() * 2.0D * settings.radius() - settings.radius();
                double offsetZ = Math.random() * 2.0D * settings.radius() - settings.radius();
                Location particleLocation = new Location(location.getWorld(), location.getX() + offsetX, location.getY() + offsetY, location.getZ() + offsetZ);
                particleUtils.sendParticle(particleSettings, particleLocation, viewers, 0, 0, 0);
            }
        }
    }
}
