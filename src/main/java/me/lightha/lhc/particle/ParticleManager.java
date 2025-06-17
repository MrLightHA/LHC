package me.lightha.lhc.particle;

import me.lightha.lhc.particle.shape.Shape;
import me.lightha.lhc.settings.Settings;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ParticleManager {

    /**
     * Вернет мапу с партиклами
     *
     * @return Map with uuid
     */
    Map<UUID, ConfiguredParticle> getConfiguredParticleMap();

    /**
     * Уничтожит ВСЕ партиклы
     */
    void resetAll();

    /**
     * Уничтожит партикл по его уникальному тегу
     *
     * @param uuid уникальный тег
     */
    void reset(UUID uuid);

    /**
     * Запустит партикл с указаной формой
     *
     * @param uuid уникальный тег
     * @param particleSettings параметры для партикла
     * @param shape форма партикла
     * @param location локация партикла
     * @param viewers игроки которые получат пакет
     */
    void start(UUID uuid, Settings<ParticleSettings> particleSettings, Shape shape, Location location, List<Player> viewers);

    /**
     * Генерирует форму партиклов в течение указанного времени
     *
     * @param uuid уникальный тег
     * @param duration длительность генерации в тиках
     */
    void tick(UUID uuid, long duration);

}
