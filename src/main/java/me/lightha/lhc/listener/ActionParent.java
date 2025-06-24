package me.lightha.lhc.listener;

import java.util.ArrayList;
import java.util.List;

public abstract class ActionParent {
    private final String name;
    private final ActionParent.ActionField actionField;
    private List<String> aliases = new ArrayList<>();

    protected ActionParent(String name, ActionField actionField) {
        this.name = name;
        this.actionField = actionField;
    }

    public abstract void runCommand(Object... objects);

    public enum ActionField {
        PRIVATE,
        PUBLIC
    }
}
