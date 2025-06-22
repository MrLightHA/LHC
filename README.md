*RU*

# LHC (LightHACore) - Библиотека для удобной разработки плагинов
  На данный момент разрабатывается только на **версию 1.16**
  В себе содержит генератор партиклов с формами

# Генератор партиклов
   **Использование ParticleBuilder**
  
```java
  new ParticleBuilder()
      .viewers(players)
      .settings(new ParticleSettings()
          .type(Particle.REDSTONE)
          .posY(0)
          .radius(5)
          .speed(0)
          .rgb("255;255;255"))
      .location(location)
      .shape(new CircleShape())
      .duration(600)
      .build(UUID.randomUUID());
```
viewers - те кто получат пакет о партикле (List<Player>);
settings - кастомизация партикла (Settings<ParticleSettings>);
location - локация партикла (org.bukkit.Location);
shape - форма партикла (Shape);
duration - время жизни партикла в тиках (long);

**Создание своей формы для партикла, а также ее использование**
для создания своей формы вы должны создать клас, который будет содержать в себе аннотацию @ShapeInfo и наследоваться от Shape
ниже пример создания формы которая в радиус рандомно генерирует партикл
```java
@ShapeInfo(name = "NAME", description = "DESCRIPTION")
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
```
Использовать такую форму можно также как и встроеные указав в ParticleBuilder экземпляр класа

**ParticleSettings
может содержать свои параметры**
к примеру

```java
// Установка своего значения в настройку партикла
settings.set("startRadius", 1);

// Получить значение !! Может вызвать NullPointerException
settings.get("startRadius");

// Безопасно получить значение
settings.getOrDefault("startRadius", 1);
```

Использовать экземпляры провайдера партиклов можно реализовав интерфейс IParticles


# ItemBuilder

Теперь вы можете создавать ItemStack используя ItemBuilder
пример создания предмета
new ItemBuilder(material)
                .setHeadBase64(base64)
                .name(name)
                .lore(lore)
                .flag(flags)
                .glow(glow)
                .setLeatherColor(leatherColor)
                .setPotionColor(color)
                .amount(amount)
                .setDamage(damage)
                .build();

