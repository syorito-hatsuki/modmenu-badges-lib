package dev.syoritohatsuki.modmenubadgeslib.client.dto;

import com.terraformersmc.modmenu.util.mod.Mod;
import dev.syoritohatsuki.modmenubadgeslib.BadgeColorUtil;

public record ExtraBadge(
        String name,
        Integer outlineColor,
        Integer fillColor,
        Integer labelColor,
        boolean delete
) {
    public static ExtraBadge from(Mod.Badge badge) {
        return new ExtraBadge(
                badge.getText().getString(),
                badge.getOutlineColor(),
                badge.getFillColor(),
                BadgeColorUtil.getDefaultLabelColor(),
                false
        );
    }

    public int getOutlineColorOrDefault() {
        return outlineColor != null
                ? outlineColor
                : BadgeColorUtil.getOutlineColor(name);
    }

    public int getFillColorOrDefault() {
        return fillColor != null
                ? fillColor
                : BadgeColorUtil.getFillColor(name);
    }

    public int getLabelColorOrDefault() {
        return labelColor != null
                ? labelColor
                : BadgeColorUtil.getDefaultLabelColor();
    }
}