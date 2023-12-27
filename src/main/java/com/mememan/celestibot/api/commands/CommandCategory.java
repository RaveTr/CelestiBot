package com.mememan.celestibot.api.commands;

import java.awt.*;

public enum CommandCategory implements ICommandCategory {
    DEFAULT("default"),
    MODERATION("moderation"),
    UTILITY("utility"),
    FUN("fun"),
    ECONOMY("economy"),
    LEVELLING("levelling"),
    MISC("misc");

    private final String categoryName;
    private final String categoryDescription;
    private final Color categoryColor;

    CommandCategory(String categoryName, String categoryDescription, Color categoryColor) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryColor = categoryColor;
    }

    CommandCategory(String categoryName, String categoryDescription) {
        this(categoryName, categoryDescription, new Color(125, 60, 0));
    }

    CommandCategory(String categoryName, Color categoryColor) {
        this(categoryName, "placeholder", categoryColor);
    }

    CommandCategory(String categoryName) {
        this(categoryName, "placeholder", new Color(125, 12, 0));
    }

    @Override
    public String getName() {
        return categoryName;
    }

    @Override
    public String getDescription() {
        return categoryDescription;
    }

    @Override
    public Color getColor() {
        return categoryColor;
    }
}
