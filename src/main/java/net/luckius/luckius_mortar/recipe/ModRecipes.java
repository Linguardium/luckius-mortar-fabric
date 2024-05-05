package net.luckius.luckius_mortar.recipe;

import net.luckius.luckius_mortar.recipe.mortar.MortarRecipe;
import net.luckius.luckius_mortar.recipe.mortar.MortarRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.luckius.luckius_mortar.LuckiusMortar.makeId;

public class ModRecipes {
	private static final Identifier RECIPE_TYPE_IDENTIFIER = makeId("mortar");
	public static final RecipeType<MortarRecipe> MORTAR_RECIPE = Registry.register(Registries.RECIPE_TYPE, RECIPE_TYPE_IDENTIFIER,
		new RecipeType<MortarRecipe>() {
			public String toString() {
				return RECIPE_TYPE_IDENTIFIER.toString();
			}
		}
	);
	public static final RecipeSerializer<MortarRecipe> MORTAR_RECIPE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, RECIPE_TYPE_IDENTIFIER,new MortarRecipeSerializer());

	public static void init() {
		// empty method used to classload
	}
}
