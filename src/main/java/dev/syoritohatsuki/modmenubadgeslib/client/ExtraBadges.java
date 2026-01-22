package dev.syoritohatsuki.modmenubadgeslib.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terraformersmc.modmenu.util.mod.Mod;
import dev.syoritohatsuki.modmenubadgeslib.client.dto.ExternalBadges;
import dev.syoritohatsuki.modmenubadgeslib.client.dto.ExtraBadge;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.CustomValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public final class ExtraBadges {
    private static ExtraBadges INSTANCE;

    private static final Map<String, Set<ExtraBadge>> INTERNAL = new HashMap<>();
    private static final Map<String, ExternalBadges> EXTERNAL = new HashMap<>();
    private static final Path MINECRAFT_ROOT = FabricLoader.getInstance().getGameDir();

    private ExtraBadges() {
    }

    public static ExtraBadges getInstance() {
        if (INSTANCE == null) INSTANCE = new ExtraBadges();
        return INSTANCE;
    }

    public static void init() {
        ModMenuBadgesLibClient.LOGGER.info("Initializing Badges Collecting");

        INTERNAL.clear();
        EXTERNAL.clear();

        ModMenuBadgesLibClient.LOGGER.info("Cleared INTERNAL and EXTERNAL");

        loadInternal();
        loadExternal();

        ModMenuBadgesLibClient.LOGGER.info("Badges Collecting complete");
    }

    private static void loadInternal() {
        ModMenuBadgesLibClient.LOGGER.info("Loading INTERNAL badges from Mods");
        FabricLoader.getInstance().getAllMods().forEach(mod -> {
            var badges = new HashSet<ExtraBadge>();
            var custom = mod.getMetadata().getCustomValue("mcb");

            if (custom == null || custom.getType() != CustomValue.CvType.ARRAY) return;

            var customAsArray = custom.getAsArray();
            ModMenuBadgesLibClient.LOGGER.info("Detected mcb component in {} with {} elements", mod.getMetadata().getId(), customAsArray.size());

            customAsArray.forEach(value -> {
                var obj = value.getAsObject();
                var name = obj.get("name").getAsString();
                ModMenuBadgesLibClient.LOGGER.info("\t- {}", name);

                badges.add(new ExtraBadge(name, obj.containsKey("outlineColor") ? obj.get("outlineColor").getAsNumber().intValue() : null, obj.containsKey("fillColor") ? obj.get("fillColor").getAsNumber().intValue() : null, obj.containsKey("labelColor") ? obj.get("labelColor").getAsNumber().intValue() : null, false));
            });

            if (!badges.isEmpty()) INTERNAL.put(mod.getMetadata().getId(), badges);
        });
    }

    private static void loadExternal() {
        Path extrasFile = MINECRAFT_ROOT.resolve("modmenu-extra-badges.json");
        if (!Files.exists(extrasFile)) return;

        ModMenuBadgesLibClient.LOGGER.info("Detected external badges file");
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, ExternalBadges> parsed = mapper.readValue(extrasFile.toFile(), new TypeReference<>() {
            });
            parsed.forEach((key, value) -> {
                ModMenuBadgesLibClient.LOGGER.info("\t- {}", key);
                value.badges().forEach(badge -> ModMenuBadgesLibClient.LOGGER.info("\t\t- {}\t|\tDelete: {}", badge.name(), "(" + badge.delete() + ")"));
            });
            EXTERNAL.putAll(parsed);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load modmenu-extra-badges.json");
        }
    }

    public Set<ExtraBadge> getExtraBadges(String modId, Set<Mod.Badge> originalBadges) {
        Map<String, ExtraBadge> resolved = new HashMap<>();

        ExternalBadges external = EXTERNAL.get(modId);

        if (external == null || !external.overwrite()) {
            for (Mod.Badge badge : originalBadges) {
                ExtraBadge extra = ExtraBadge.from(badge);
                resolved.put(extra.name(), extra);
            }

            for (ExtraBadge badge : INTERNAL.getOrDefault(modId, Set.of())) {
                resolved.put(badge.name(), badge);
            }
        }

        if (external != null) {
            for (ExtraBadge badge : external.badges()) {
                if (badge.delete()) {
                    resolved.remove(badge.name());
                } else {
                    resolved.put(badge.name(), badge);
                }
            }
        }

        if (external != null && external.sort()) {
            return resolved.values().stream().sorted(Comparator.comparing(ExtraBadge::name, String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toCollection(LinkedHashSet::new));
        }

        return Set.copyOf(resolved.values());
    }
}
