package me.lightha.lhc.particle.shape.impl;

import me.lightha.lhc.api.annotations.ShapeInfo;
import me.lightha.lhc.particle.shape.Shape;
import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

@ShapeInfo(name = "cube", description = "Создаёт куб из партиклов")
public final class CubeShape extends Shape {

    @Override
    public void run(Settings<ParticleSettings> particleSettings, Location origin, List<Player> viewers) {
        if (particleSettings instanceof ParticleSettings settings) {
            final double radius = settings.radius();
            final double half = radius / 2.0;
            final Location[] corner = new Location[8];
            for (int i = 0; i < 8; i++) {
                double dx = ((i & 1) == 0 ? -half : half);
                double dy = ((i & 2) == 0 ? -half : half);
                double dz = ((i & 4) == 0 ? -half : half);
                corner[i] = origin.clone().add(dx, settings.posY() + dy, dz);
            }

            for (int i = 0; i < 8; i++) {
                for (int axis = 0; axis < 3; axis++) {
                    int j = i ^ (1 << axis);
                    if (i < j) drawLine(corner[i], corner[j], Math.max(0.2, radius / 20.0), settings, viewers);
                }
            }
        }
    }

    private void drawLine(Location start, Location stop, double step, ParticleSettings ps, List<Player> viewers) {
        final Vector dir = stop.toVector().subtract(start.toVector());
        final int points = (int) Math.ceil(dir.length() / step);
        if (points == 0) return;

        final Vector delta = dir.multiply(1.0 / points);
        Location point = start.clone();

        for (int i = 0; i <= points; i++) {
            particleUtils.sendParticle(ps, point, viewers, 0, 0, 0);
            point.add(delta);
        }
    }
}
