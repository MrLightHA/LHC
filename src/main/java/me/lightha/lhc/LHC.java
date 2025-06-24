package me.lightha.lhc;

import lombok.Getter;
import me.lightha.lhc.gui.MenuManager;
import me.lightha.lhc.gui.impl.MenuManagerImpl;
import me.lightha.lhc.particle.ParticleManager;
import me.lightha.lhc.particle.impl.ParticleManagerImpl;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class LHC extends JavaPlugin {
    public static LHC instance;
    private ParticleManager particleManager;
    private MenuManager menuManager;

    @Override
    public void onEnable() {
       instance = this;
       this.particleManager = new ParticleManagerImpl();
       this.menuManager = new MenuManagerImpl(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
