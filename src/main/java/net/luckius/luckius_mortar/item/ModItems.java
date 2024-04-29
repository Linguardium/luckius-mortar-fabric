package net.luckius.luckius_mortar.item;

import net.luckius.luckius_mortar.item.custom.MortarItem;
import net.minecraft.item.Item;

public class ModItems {
	public static Item WOODEN_MORTAR = new MortarItem(new Item.Settings().maxCount(1).maxDamage(150));
	public static Item STONE_MORTAR = new MortarItem(new Item.Settings().maxCount(1).maxDamage(430));
	public static Item IRON_MORTAR = new MortarItem(new Item.Settings().maxCount(1).maxDamage(765));
	public static Item DIAMOND_MORTAR = new MortarItem(new Item.Settings().maxCount(1).maxDamage(1232));
}
