package net.luckius.luckius_mortar.recipe;

import net.luckius.luckius_mortar.recipe.mortar.MortarRecipe;
import net.luckius.luckius_mortar.recipe.mortar.MortarRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.luckius.luckius_mortar.LuckiusMortar.MOD_ID;

public class ModRecipes {
	public static final RecipeType<MortarRecipe> MORTAR_RECIPE = Registry.register(Registries.RECIPE_TYPE, new Identifier(MOD_ID, "mortar"),
		new RecipeType<MortarRecipe>() {
			public String toString() {
				return MOD_ID+":mortar";
			}
		}
	);
	public static final RecipeSerializer<MortarRecipe> MORTAR_RECIPE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, "mortar"),new MortarRecipeSerializer());

	public static void init() {};
}
