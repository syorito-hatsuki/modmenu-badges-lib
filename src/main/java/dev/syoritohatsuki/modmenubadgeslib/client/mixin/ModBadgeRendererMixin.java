package dev.syoritohatsuki.modmenubadgeslib.client.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.ModBadgeRenderer;
import dev.syoritohatsuki.modmenubadgeslib.client.ExtraBadges;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(ModBadgeRenderer.class)
public abstract class ModBadgeRendererMixin {

    @Shadow
    protected Mod mod;

    @Shadow
    public abstract void drawBadge(DrawContext DrawContext, OrderedText text, int outlineColor, int fillColor, int mouseX, int mouseY);

    @Inject(method = "draw", at = @At(value = "INVOKE", target = "Ljava/util/Set;forEach(Ljava/util/function/Consumer;)V"), cancellable = true)
    public void drawCustomBadges(DrawContext DrawContext, int mouseX, int mouseY, CallbackInfo ci, @Local(name = "badges") Set<Mod.Badge> originalBadges) {
        ExtraBadges.getInstance()
                .getExtraBadges(mod.getId(), originalBadges)
                .forEach(extraBadge -> {
                    drawBadge(
                            DrawContext,
                            Text.translatable(extraBadge.name())
                                    .styled(style -> style.withColor(extraBadge.getLabelColorOrDefault()))
                                    .asOrderedText(),
                            extraBadge.getOutlineColorOrDefault(),
                            extraBadge.getFillColorOrDefault(),
                            mouseX,
                            mouseY
                    );
                });
        ci.cancel();
    }
}
