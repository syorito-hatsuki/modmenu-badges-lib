package dev.syoritohatsuki.modmenubadgeslib.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExternalBadges(boolean overwrite, boolean sort, List<ExtraBadge> badges) {
    @JsonCreator
    public ExternalBadges(@JsonProperty("overwrite") Boolean overwrite, @JsonProperty("sort") Boolean sort, @JsonProperty("badges") List<ExtraBadge> badges) {
        this(overwrite != null && overwrite, sort != null && sort, badges != null ? badges : List.of());
    }
}
