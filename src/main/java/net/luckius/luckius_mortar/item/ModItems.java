package net.luckius.luckius_mortar.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.luckius.luckius_mortar.item.custom.MortarItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static net.luckius.luckius_mortar.registration.Util.registerItem;

public class ModItems {

	public static Item WOODEN_MORTAR = registerMortarItem("wooden_mortar",150);
	public static Item STONE_MORTAR = registerMortarItem("stone_mortar",430);
	public static Item IRON_MORTAR = registerMortarItem("iron_mortar",765);
	public static Item DIAMOND_MORTAR = registerMortarItem("diamond_mortar",1232);

	public static void init() {
		registerItemGroupEvent();
	}

	private static void registerItemGroupEvent() {
		// Add items to creative tab using the datagenned tag
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			Registries.ITEM.getEntryList(ModItemTags.MORTARS)
					.map(ModItems::getDefaultStackListFromEntryList)
					.filter(list -> ! list.isEmpty())
					.ifPresent(list -> content.addAfter(Items.FLINT_AND_STEEL, list));
		});
	}

	public static Item registerMortarItem(String itemId, int durability) {
		return registerItem(itemId, new MortarItem(new Item.Settings().maxCount(1).maxDamage(durability)));
	}

	@Nullable
	private static ItemStack getDefaultStackFromEntry(RegistryEntry<Item> entry) {
		Item item = entry.value();
		if (item == null) return null;

		ItemStack stack = item.getDefaultStack();
		return stack.isEmpty() ? null : stack;
	}

	private static List<ItemStack> getDefaultStackListFromEntryList(RegistryEntryList<Item> entryList) {
		return entryList.stream()
				.map(ModItems::getDefaultStackFromEntry)
				.filter(Objects::nonNull)
				.toList();
	}
}
