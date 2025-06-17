package me.lightha.lhc.particle.impl;

import me.lightha.lhc.particle.ParticleUtils;
import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import net.minecraft.server.v1_16_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_16_R3.ParticleParam;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_16_R3.CraftParticle;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class ParticleUtilsImpl implements ParticleUtils {

    @Override
    public void sendParticle(Settings<ParticleSettings> setting, Location location, List<Player> viewers, float offSetX, float offSetY, float offSetZ) {
        if (setting instanceof ParticleSettings particleSettings) {
            ParticleParam particleParam = particleSettings.rgb() != null
                    ? CraftParticle.toNMS(particleSettings.type(), new Particle.DustOptions(Color.fromRGB(Integer.parseInt(particleSettings.rgb().split(";")[0]), Integer.parseInt(particleSettings.rgb().split(";")[1]), Integer.parseInt(particleSettings.rgb().split(";")[2])), 1.0F))
                    : CraftParticle.toNMS(particleSettings.type());
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                    particleParam,
                    true,
                    ((float)location.getX() + offSetX),
                    ((float)location.getY() + offSetY),
                    ((float)location.getZ() + offSetZ),
                    offSetX, 0.0F,
                    offSetZ,
                    (float)particleSettings.speed(),
                    0);
            viewers.forEach(viewer -> ((CraftPlayer)viewer).getHandle().playerConnection.sendPacket(packet));
        }
    }
}
