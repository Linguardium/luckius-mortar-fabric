package net.luckius.luckius_mortar.recipe.mortar;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.registry.Registries;

public class MortarRecipeSerializer implements RecipeSerializer<MortarRecipe> {
	// TODO: standardize result format to standard output format and use ItemStack.RECIPE_RESULT_CODEC
	MapCodec<MortarRecipe> CODEC = RecordCodecBuilder.mapCodec(instance->instance.group(
			Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(MortarRecipe::getInput),
			ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(recipe->recipe.output),
			Codec.INT.optionalFieldOf("damage",0).forGetter(MortarRecipe::getDamage)
	).apply(instance,(ingredient,result,damage)->new MortarRecipe(ingredient,result,Math.max(damage,0))));


	public static final PacketCodec<RegistryByteBuf, MortarRecipe> PACKET_CODEC = PacketCodec.ofStatic(MortarRecipeSerializer::write, MortarRecipeSerializer::read);

	@Override
	public MapCodec<MortarRecipe> codec() {
		return CODEC;
	}

	@Override
	public PacketCodec<RegistryByteBuf, MortarRecipe> packetCodec() {
		return PACKET_CODEC;
	}


	public static MortarRecipe read(RegistryByteBuf buf) {
		var input = Ingredient.PACKET_CODEC.decode(buf);
		var output = ItemStack.PACKET_CODEC.decode(buf);
		var damage = buf.readInt();

		return new MortarRecipe(input, output, damage);
	}

	public static void write(RegistryByteBuf buf, MortarRecipe recipe) {
		Ingredient.PACKET_CODEC.encode(buf, recipe.input);
		ItemStack.PACKET_CODEC.encode(buf, recipe.output);
		buf.writeInt(recipe.damage);
	}
}
