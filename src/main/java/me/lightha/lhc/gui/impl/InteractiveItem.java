package me.lightha.lhc.gui.impl;

import lombok.Data;
import me.lightha.lhc.api.IContainer;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class InteractiveItem implements IContainer {
    private List<String> actions;
    private final ItemStack itemBuilder;
    private Map<String, Object> containerValues = new HashMap<>();

    public InteractiveItem(ItemStack itemBuilder) {
        this.itemBuilder = itemBuilder;
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
