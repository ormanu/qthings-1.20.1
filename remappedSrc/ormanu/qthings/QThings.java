package ormanu.qthings;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ormanu.qthings.enchantments.SlowingEnchant;
import ormanu.qthings.enchantments.WeakinEnchant;

public class QThings implements ModInitializer {
	public static final String MOD_ID = "qthings";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Enchantment Slowing = new SlowingEnchant();
	public static Enchantment Weakin = new WeakinEnchant();

	@Override
	public void onInitialize() {
		QTItems.registerQTItemss();
		Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID,"slowing"), Slowing);
		Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID,"weakin"), Weakin);

		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (player != null && player.getStackInHand(hand).getItem() == QTItems.QT_AXE) {
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 50, 1, false, false, false));
			}
			return ActionResult.PASS;
		});

		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (player != null && player.getStackInHand(hand).getItem() == QTItems.QT_Scythe) {
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 35, 0, false, false, false));
			}
			return ActionResult.PASS;
		});

		LOGGER.info("Hello Fabric world!");
	}
}