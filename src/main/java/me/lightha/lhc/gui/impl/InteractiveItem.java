package me.lightha.lhc.gui.impl;

import lombok.Data;
import me.lightha.lhc.api.IContainer;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

@Data
public class InteractiveItem implements IContainer {
    private List<String> actions;
    private ItemStack itemBuilder;
    private Map<String, Object> containerValues;

    public InteractiveItem(List<String> actions, ItemStack itemBuilder, Map<String, Object> metadata) {
        this.actions = actions;
        this.itemBuilder = itemBuilder;
        this.containerValues = metadata;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getContainerValue(String key, Class<T> type) {
        Object value = containerValues.get(key);
        if (value == null) return null;
        if (type.isInstance(value)) {
            return (T) value;
        }
        throw new ClassCastException("Metadata key '" + key + "' is not of type " + type.getSimpleName());
    }
}
