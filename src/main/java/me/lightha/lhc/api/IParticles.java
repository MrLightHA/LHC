package me.lightha.lhc.api;

import me.lightha.lhc.LHC;
import me.lightha.lhc.particle.ParticleManager;
import me.lightha.lhc.particle.ParticleUtils;
import me.lightha.lhc.particle.impl.ParticleUtilsImpl;

public interface IParticles {
    ParticleUtils particleUtils = new ParticleUtilsImpl();
    ParticleManager particleManager = LHC.instance.getParticleManager();

}
