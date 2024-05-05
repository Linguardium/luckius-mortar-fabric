package net.luckius.luckius_mortar.datagen.model;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

import static net.luckius.luckius_mortar.LuckiusMortar.MOD_ID;

public class ModItemModelGenerator extends FabricModelProvider {

    public ModItemModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // no blocks currently
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // get all items for our mod
        Registries.ITEM.streamEntries()
        .filter(reference->
                reference.hasKeyAndValue() &&
                reference.registryKey()
                         .getValue()
                         .getNamespace()
                         .equals(MOD_ID)
        )
        .map(RegistryEntry.Reference::value)
        .forEach(item->{
            // unless it's a block item, just give it a standard generated model
            if (!(item instanceof BlockItem)) {
                itemModelGenerator.register(item, Models.GENERATED);
            }
        });
    }
}
