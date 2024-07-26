package com.anotherpillow.fireaspecttridents.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FireAspectEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(FireAspectEnchantment.class)
public abstract class FireAspectEnchantmentMixin extends Enchantment {

    private static final EnchantmentCategory FullWeaponEnchantmentCategory = EnchantmentCategory.create("fullweapon",
            item -> item instanceof SwordItem || item instanceof TridentItem);

    protected FireAspectEnchantmentMixin(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, FullWeaponEnchantmentCategory, slots);
    }

    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/Enchantment;<init>(Lnet/minecraft/world/item/enchantment/Enchantment$Rarity;Lnet/minecraft/world/item/enchantment/EnchantmentCategory;[Lnet/minecraft/world/entity/EquipmentSlot;)V"
            ),
            index = 1
    )
    private static EnchantmentCategory modifyCategory(EnchantmentCategory originalCategory) {
        return FullWeaponEnchantmentCategory;
    }
}