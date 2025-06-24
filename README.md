*RU*

# LHC (LightHACore) - Библиотека для удобной разработки плагинов
  На данный момент разрабатывается только на **версию 1.16**
  В себе содержит

- ParticleBuilder
- ItemBuilder
- Menu system

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
```java
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
                
```

# Menu - Создание своих меню

Пример создания меню

```java
public class TestGui extends MenuParent {

    public TestGui() {
        super("Тест меню", 
                Map.of(0, "X"), // K - слот; V - ключ предмета
                Map.of("X", new InteractiveItem(new ItemBuilder(Material.STONE).build()))); // K - ключ предмета; V InteractiveItem
    }

    @Override
    public void create(Object... objects) {
        // Создание меню
        createMenu(menu -> { 
            menu.removeOnClose(true);
            menu.onClick(e -> e.setCancelled(true));
            
            // Вы также можете передавать свои данные в меню, пример
            menu.setData("page", 1);
        });
        fill(objects);
    }

    @Override
    public void fill(Object... objects) {
        getInventoryItemSlotsMap().forEach((slot, itemKey) -> {
            InteractiveItem actionItem = getItemMap().get(itemKey);
            if (actionItem == null) return;
            ItemStack item = actionItem.getItemBuilder();
            if (item == null) item = new ItemStack(Material.AIR);
            MenuItem menuItem = menuManager.createItem(item, click -> {
                // Действие при клике по предмету
                click.getWhoClicked().sendMessage("Тест клик");
            });

            getMenu().setItem(slot, menuItem);
        });
    }
}
```

**Pagination**

Вы можете добавить аннотацию @Paginated и получить доступ к интерфейсу Pagination
использовать его можно через getPagination()

**FakeInventory**

Также для работы с инвентарями, неважно созданы они с помощью MenuParent, вы можете реализовать интерфейс IFakeInventory
и изменять данные инвентаря через пакеты, пример
```fakeInv.replaceTitle(player, "Фейк заголовок");```

