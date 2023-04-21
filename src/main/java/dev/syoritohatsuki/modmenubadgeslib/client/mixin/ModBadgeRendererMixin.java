package dev.syoritohatsuki.modmenubadgeslib.client.mixin;

import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.ModBadgeRenderer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModBadgeRenderer.class)
public abstract class ModBadgeRendererMixin {

    @Shadow
    protected Mod mod;

    @Shadow
    public abstract void drawBadge(MatrixStack matrices, OrderedText text, int outlineColor, int fillColor, int mouseX, int mouseY);

    @Inject(method = "draw", at = @At("TAIL"))
    public void drawCustomBadges(MatrixStack matrices, int mouseX, int mouseY, CallbackInfo ci) {
        try {
            FabricLoader.getInstance().getModContainer(mod.getId()).orElse(null)
                    .getMetadata().getCustomValue("mcb").getAsArray().forEach(customValue -> {
                        var obj = customValue.getAsObject();
                        var name = obj.get("name").getAsString();
                        var outline = obj.get("outlineColor").getAsNumber().intValue();
                        var fill = obj.get("fillColor").getAsNumber().intValue();
                        drawBadge(matrices, new LiteralText(name).asOrderedText(), outline, fill, mouseX, mouseY);
                    });
        } catch (Exception ignored) {
        }
    }
}