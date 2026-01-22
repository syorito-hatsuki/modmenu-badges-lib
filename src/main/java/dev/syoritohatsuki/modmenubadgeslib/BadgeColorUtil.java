package dev.syoritohatsuki.modmenubadgeslib;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class BadgeColorUtil {
    private static final int DEFAULT_LABEL_COLOR = -3487030;

    private static final Map<String, Integer> OUTLINE_CACHE = new ConcurrentHashMap<>();
    private static final Map<String, Integer> FILL_CACHE = new ConcurrentHashMap<>();

    public static int getOutlineColor(String name) {
        return OUTLINE_CACHE.computeIfAbsent(name, BadgeColorUtil::generateOutlineColor);
    }

    public static int getFillColor(String name) {
        return FILL_CACHE.computeIfAbsent(name, BadgeColorUtil::computeFill);
    }

    private static int computeFill(String name) {
        return BadgeColorUtil.generateFillColor(getOutlineColor(name), 0.8f);
    }

    public static int generateOutlineColor(String name) {
        int hash = name.hashCode();

        int r = (hash >> 16) & 0xFF;
        int g = (hash >> 8) & 0xFF;
        int b = hash & 0xFF;

        r = (r + 128) / 2;
        g = (g + 128) / 2;
        b = (b + 128) / 2;

        return (0xFF << 24) | (r << 16) | (g << 8) | b;
    }

    public static int generateFillColor(int outlineColor, float factor) {
        int a = (outlineColor >> 24) & 0xFF;
        int r = (outlineColor >> 16) & 0xFF;
        int g = (outlineColor >> 8) & 0xFF;
        int b = outlineColor & 0xFF;

        r = (int)(r * factor);
        g = (int)(g * factor);
        b = (int)(b * factor);

        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static int getDefaultLabelColor() {
        return DEFAULT_LABEL_COLOR;
    }
}
