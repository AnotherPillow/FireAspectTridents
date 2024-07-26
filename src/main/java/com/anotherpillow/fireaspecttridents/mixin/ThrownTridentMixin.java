package com.anotherpillow.fireaspecttridents.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownTrident.class)
public class ThrownTridentMixin {
    @Shadow
    private ItemStack tridentItem;
    @Inject(
            method="onHitEntity",
            at = @At("HEAD")
    )
    private void onHitEntityInjection(EntityHitResult result, CallbackInfo ci) {
        Entity entity = result.getEntity();
        int lvl = this.tridentItem.getEnchantmentLevel(Enchantments.FIRE_ASPECT);
        if (lvl > 0) entity.setSecondsOnFire(lvl * 4);
    }
}
