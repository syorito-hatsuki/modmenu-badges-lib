package dev.syoritohatsuki.modmenubadgeslib.client;

import net.fabricmc.api.ClientModInitializer;

public final class ModMenuBadgesLibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ExtraBadges.init();
    }
}
