package dev.syoritohatsuki.modmenubadgeslib.client.dto;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record ExternalBadges(
        boolean overwrite,
        boolean sort,
        List<ExtraBadge> badges
) {
    public static final Codec<ExternalBadges> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.BOOL.optionalFieldOf("overwrite", false)
                            .forGetter(ExternalBadges::overwrite),
                    Codec.BOOL.optionalFieldOf("sort", false)
                            .forGetter(ExternalBadges::sort),
                    ExtraBadge.CODEC.listOf()
                            .optionalFieldOf("badges", List.of())
                            .forGetter(ExternalBadges::badges)
            ).apply(instance, ExternalBadges::new));
}
