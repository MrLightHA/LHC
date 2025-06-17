package me.lightha.lhc.particle;


import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public interface ParticleUtils {
    /**
     * Отправит пакет с партиклом игрокам
     *
     * @param setting параметры партикла
     * @param location локация партикла
     * @param viewers игроки которые получат пакет
     * @param offSetX доп x
     * @param offSetY доп y
     * @param offSetZ доп z
     */
    void sendParticle(Settings<ParticleSettings> setting, Location location, List<Player> viewers, float offSetX, float offSetY, float offSetZ);
}
