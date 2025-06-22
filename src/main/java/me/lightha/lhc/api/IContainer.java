package me.lightha.lhc.api;

public interface IContainer {
    <T> T getContainerValue(String key, Class<T> type);
}
