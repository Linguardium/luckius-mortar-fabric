package net.luckius.luckius_mortar.datagen.recipe;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.luckius.luckius_mortar.LuckiusMortar;
import net.luckius.luckius_mortar.item.ModItems;
import net.luckius.luckius_mortar.recipe.mortar.MortarRecipe;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static net.luckius.luckius_mortar.item.ModItems.*;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerMortarRecipe(exporter, Items.DIAMOND, DIAMOND_MORTAR);
        offerMortarRecipe(exporter, ItemTags.PLANKS, WOODEN_MORTAR);
        offerMortarRecipe(exporter, ConventionalItemTags.STONES, STONE_MORTAR);
        offerMortarRecipe(exporter, Items.IRON_INGOT, IRON_MORTAR);
        offerMortarTypeRecipe(exporter, Items.BONE, Items.BONE_MEAL, 3, 1);
        offerMortarTypeRecipe(exporter, Items.GRAVEL, Items.FLINT, 1, 1);
    }

    private void offerMortarRecipe(RecipeExporter exporter, ItemConvertible bowlIngredient, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
                .pattern("  /")
                .pattern("DFD")
                .pattern(" D ")
                .input('/', Items.STICK)
                .input('D',bowlIngredient)
                .input('F',Items.FLINT)
                .criterion("has_item", conditionsFromItem(bowlIngredient))
                .offerTo(exporter, getRecipeName(output));
    }
    private void offerMortarRecipe(RecipeExporter exporter, TagKey<Item> bowlIngredient, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output)
                .pattern("  /")
                .pattern("DFD")
                .pattern(" D ")
                .input('/', Items.STICK)
                .input('D',bowlIngredient)
                .input('F',Items.FLINT)
                .criterion("has_item", conditionsFromTag(bowlIngredient))
                .offerTo(exporter, getRecipeName(output));
    }
    private void offerMortarTypeRecipe(RecipeExporter exporter, ItemConvertible input, ItemConvertible output, int count, int damage) {
        exporter.accept(
                new Identifier(LuckiusMortar.MOD_ID,"mortar_"+getRecipeName(output)),
                new MortarRecipe(
                        Ingredient.ofItems(input),
                        output.asItem().getDefaultStack().copyWithCount(count),
                        damage
                ),
                        null
        );
    }

}
