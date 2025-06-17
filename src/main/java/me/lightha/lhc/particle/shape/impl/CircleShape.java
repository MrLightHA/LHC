package me.lightha.lhc.particle.shape.impl;

import me.lightha.lhc.api.annotations.ShapeInfo;
import me.lightha.lhc.particle.shape.Shape;
import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

@ShapeInfo(name = "circle", description = "Создает круг из партиклов")
public class CircleShape extends Shape {

    @Override
    public void run(Settings<ParticleSettings> particleSettings, Location location, List<Player> viewers) {
        if (particleSettings instanceof ParticleSettings settings) {
            double visualRadius = settings.radius();
            float offsetY = (float) settings.posY();

            for (double i = 0.0D; i <= Math.PI * 2; i += 0.15D) {
                double offsetX = Math.cos(i) * visualRadius;
                double offsetZ = Math.sin(i) * visualRadius;
                Location particleLoc = location.clone().add(offsetX, offsetY, offsetZ);

                float dirX = (float) (offsetX * 0.25);
                float dirZ = (float) (offsetZ * 0.25);
                particleUtils.sendParticle(particleSettings, particleLoc, viewers, dirX, 0, dirZ);
            }
        }
    }
}
