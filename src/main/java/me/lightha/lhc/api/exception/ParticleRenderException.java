package me.lightha.lhc.api.exception;

public class ParticleRenderException extends RuntimeException {

    public ParticleRenderException(String message) {
        super(message);
    }

    public ParticleRenderException(String message, Throwable cause) {
        super(message, cause);
    }
}

