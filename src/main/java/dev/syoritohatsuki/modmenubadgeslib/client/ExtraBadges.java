package dev.syoritohatsuki.modmenubadgeslib.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.syoritohatsuki.modmenubadgeslib.client.dto.ExtraBadge;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.CustomValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class ExtraBadges {
    private static ExtraBadges INSTANCE;

    private static final Map<String, Set<ExtraBadge>> BADGES = new HashMap<>();
    private static final Path MINECRAFT_ROOT = FabricLoader.getInstance().getGameDir();

    private ExtraBadges() {
    }

    public static ExtraBadges getInstance() {
        if (INSTANCE == null) INSTANCE = new ExtraBadges();
        return INSTANCE;
    }

    public static void init() {
        BADGES.clear();

        FabricLoader.getInstance().getAllMods().forEach(mod -> {
            var badges = new HashSet<ExtraBadge>();
            var custom = mod.getMetadata().getCustomValue("mcb");

            if (custom != null && custom.getType() == CustomValue.CvType.ARRAY) {
                custom.getAsArray().forEach(value -> {
                    var obj = value.getAsObject();
                    badges.add(new ExtraBadge(obj.get("name").getAsString(), obj.get("outlineColor").getAsNumber().intValue(), obj.get("fillColor").getAsNumber().intValue()));
                });
            }

            if (!badges.isEmpty()) {
                BADGES.put(mod.getMetadata().getId(), badges);
            }
        });

        var extrasFile = MINECRAFT_ROOT.resolve("modmenu-extra-badges.json");
        if (!Files.exists(extrasFile)) return;

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, List<ExtraBadge>> result = mapper.readValue(extrasFile.toFile(), new TypeReference<>() {});

            result.forEach((id, list) -> BADGES.merge(id, new HashSet<>(list), (a, b) -> {
                a.addAll(b);
                return a;
            }));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load extra badges", e);
        }
    }

    public Set<ExtraBadge> getExtraBadges(String name) {
        return Set.copyOf(BADGES.getOrDefault(name, Set.of()));
    }
}
