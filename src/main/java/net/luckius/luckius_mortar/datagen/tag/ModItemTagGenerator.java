package net.luckius.luckius_mortar.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.luckius.luckius_mortar.item.ModItemTags;
import net.luckius.luckius_mortar.item.ModItems;
import net.luckius.luckius_mortar.item.custom.MortarItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public ModItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        FabricTagBuilder tagBuilder = getOrCreateTagBuilder(ModItemTags.MORTARS).setReplace(false);
        // add all MortarItem entries to the mortar tag
        Registries.ITEM.forEach(item-> {
            if (item instanceof MortarItem) {
                tagBuilder.add(item);
            }
        });
    }
}

