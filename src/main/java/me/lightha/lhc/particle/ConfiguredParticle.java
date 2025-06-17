package me.lightha.lhc.particle;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.lightha.lhc.particle.shape.Shape;
import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

@Data
@AllArgsConstructor
public class ConfiguredParticle {
    private Settings<ParticleSettings> particleSettings;
    private Shape shape;
    private Location location;
    private List<Player> viewers;
}
