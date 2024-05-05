package net.luckius.luckius_mortar;

import net.fabricmc.api.ModInitializer;
import net.luckius.luckius_mortar.item.ModItems;
import net.luckius.luckius_mortar.recipe.ModRecipes;
import net.luckius.luckius_mortar.sound.ModSoundEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuckiusMortar implements ModInitializer {
	public static final String MOD_ID = "luckius_mortar";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world from Luckius' Mortar!");
		ModSoundEvents.init();
		ModRecipes.init();
		ModItems.init();
	}
	public static Identifier makeId(String path) {
		return new Identifier(MOD_ID, path);
	}
}