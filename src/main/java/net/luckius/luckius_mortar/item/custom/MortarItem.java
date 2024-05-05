package net.luckius.luckius_mortar.item.custom;

import net.luckius.luckius_mortar.recipe.ModRecipes;
import net.luckius.luckius_mortar.recipe.mortar.MortarRecipe;
import net.luckius.luckius_mortar.sound.ModSoundEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ClickType;
import net.minecraft.world.World;

public class MortarItem extends Item {
	public MortarItem(Settings settings) {
		super(settings);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	public boolean tryCraftItem(World world, PlayerEntity player, ItemStack mortarStack, ItemStack inputStack) {
		SimpleInventory inventory = new SimpleInventory(inputStack);
		final int maxDamage = mortarStack.isDamageable() ? mortarStack.getMaxDamage() - mortarStack.getDamage() : Integer.MAX_VALUE;
		final boolean mortarCanBeDamaged = mortarStack.isDamageable();
		MortarRecipe recipe = world.getRecipeManager()
				.getAllMatches(ModRecipes.MORTAR_RECIPE, inventory, world)
				.stream()
				.map(RecipeEntry::value)
				.filter(mortarRecipe->!mortarCanBeDamaged || (!mortarRecipe.canDamage() || mortarRecipe.getDamage() <= maxDamage))
				.findFirst()
				.orElse(null);

		if (recipe == null) return false; // Check if a recipe was found
		ItemStack resultStack = recipe.craft(inventory, world.getRegistryManager());

		if (resultStack.isEmpty()) return false;

		if (!world.isClient()) {
			if (!player.isCreative() && recipe.canDamage() && mortarCanBeDamaged) {
				mortarStack.damage(recipe.getDamage(), world.getRandom(), player instanceof ServerPlayerEntity serverPlayer ? serverPlayer : null, ()->{});
			}
			inputStack.decrement(1);
			player.getInventory().offerOrDrop(inputStack.getRecipeRemainder());
			player.getInventory().offerOrDrop(resultStack);
		}

		world.playSound(player, player.getBlockPos(), ModSoundEvents.MORTAR_SOUND_CLICK, SoundCategory.PLAYERS);
		return true;
	}

	@Override
	public boolean onStackClicked(ItemStack mortarStack, Slot inputSlot, ClickType clickType, PlayerEntity player) {
		// clicking a stack with the mortar
		if (clickType.equals(ClickType.RIGHT) && inputSlot.hasStack()) return tryCraftItem(player.getWorld(), player, mortarStack, inputSlot.getStack());
		return super.onStackClicked(mortarStack, inputSlot, clickType, player);
	}

	@Override
	public boolean onClicked(ItemStack mortarStack, ItemStack inputStack, Slot thisSlot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
		// clicking the mortar with a stack
		if (clickType.equals(ClickType.RIGHT) && !inputStack.isEmpty()) return tryCraftItem(player.getWorld(), player, mortarStack, inputStack);
		return super.onClicked(mortarStack, inputStack, thisSlot, clickType, player, cursorStackReference);
	}
}
