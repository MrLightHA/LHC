package me.lightha.lhc.particle.shape.impl;

import me.lightha.lhc.api.annotations.ShapeInfo;
import me.lightha.lhc.particle.shape.Shape;
import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

@ShapeInfo(name = "sphere", description = "Создает сферу из партиклов")
public class SphereShape extends Shape {

    @Override
    public void run(Settings<ParticleSettings> particleSettings, Location location, List<Player> viewers) {
        if (particleSettings instanceof ParticleSettings settings) {
            int count = settings.count();
            double radius = settings.radius();

            for (int i = 0; i < count; i++) {
                double phi = Math.acos(1 - 2 * (i / (double) count));
                double theta = Math.sqrt(count * Math.PI) * phi;

                double x = radius * Math.sin(phi) * Math.cos(theta);
                double y = radius * Math.sin(phi) * Math.sin(theta);
                double z = radius * Math.cos(phi);

                Location particleLoc = location.clone().add(x, y, z);
                particleUtils.sendParticle(particleSettings, particleLoc, viewers, 0, 0, 0);
            }
        }
    }

}
