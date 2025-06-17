package me.lightha.lhc;

import lombok.Getter;
import me.lightha.lhc.particle.ParticleManager;
import me.lightha.lhc.particle.impl.ParticleBuilder;
import me.lightha.lhc.particle.impl.ParticleManagerImpl;
import me.lightha.lhc.particle.shape.impl.CircleShape;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
public final class LHC extends JavaPlugin {
    public static LHC instance;
    private ParticleManager particleManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
       instance = this;
       this.particleManager = new ParticleManagerImpl();
       Location location = new Location(Bukkit.getWorld("world"), 0,150,0);
       List<Player> players = new ArrayList<>(Arrays.asList(Bukkit.getPlayer("LightHA")));
       new ParticleBuilder()
               .viewers(players)
               .settings(new ParticleSettings().type(Particle.REDSTONE).posY(0).radius(5).speed(0).rgb("255;255;255"))
               .location(location)
               .shape(new CircleShape())
               .duration(600)
               .build(UUID.randomUUID());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
