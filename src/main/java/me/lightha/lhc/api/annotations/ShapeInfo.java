package me.lightha.lhc.api.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ShapeInfo {

    String name();

    String description();
}
