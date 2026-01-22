package dev.syoritohatsuki.modmenubadgeslib.client;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;

public final class ModMenuBadgesLibClient implements ClientModInitializer {
    static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing ModMenu Badges Lib");
        ExtraBadges.init();
    }
}
