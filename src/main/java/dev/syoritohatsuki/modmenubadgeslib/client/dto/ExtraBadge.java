package dev.syoritohatsuki.modmenubadgeslib.client.dto;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.modmenu.util.mod.Mod;
import dev.syoritohatsuki.modmenubadgeslib.BadgeColorUtil;

import java.util.Optional;

public record ExtraBadge(
        String name,
        Integer outlineColor,
        Integer fillColor,
        Integer labelColor,
        boolean delete
) {
    public static final Codec<ExtraBadge> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.STRING.fieldOf("name")
                            .forGetter(ExtraBadge::name),

                    Codec.INT.optionalFieldOf("outlineColor")
                            .forGetter(b -> Optional.ofNullable(b.outlineColor())),

                    Codec.INT.optionalFieldOf("fillColor")
                            .forGetter(b -> Optional.ofNullable(b.fillColor())),

                    Codec.INT.optionalFieldOf("labelColor")
                            .forGetter(b -> Optional.ofNullable(b.labelColor())),


                    Codec.BOOL.optionalFieldOf("delete", false)
                            .forGetter(ExtraBadge::delete)
            ).apply(instance, (name, outline, fill, label, delete) ->
                    new ExtraBadge(
                            name,
                            outline.orElse(null),
                            fill.orElse(null),
                            label.orElse(null),
                            delete
                    )
            ));

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