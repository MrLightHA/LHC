package me.lightha.lhc.particle;

import me.lightha.lhc.particle.shape.Shape;
import me.lightha.lhc.settings.impl.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 *
 * @param <B> ParticleBuilder
 */
public interface IParticleBuilder<B extends IParticleBuilder<B>> {

    /**
     * Задать локацию
     */
    B location(Location location);

    /**
     * Форма партиклов
     */
    B shape(Shape shape);

    /**
     * Игроки которые получат пакет
     */
    B viewers(List<Player> viewers);

    /**
     * Время жизни партикла в тиках
     */
    B duration(long duration);

    /**
     * Кастомизация партикла
     */
    B settings(ParticleSettings particleSettings);

    /**
     * Билдер партиклов
     */
    void build(UUID uuid);
}

