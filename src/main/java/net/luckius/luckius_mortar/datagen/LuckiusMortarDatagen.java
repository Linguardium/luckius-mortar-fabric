package net.luckius.luckius_mortar.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.luckius.luckius_mortar.datagen.model.ModItemModelGenerator;
import net.luckius.luckius_mortar.datagen.recipe.ModRecipeGenerator;
import net.luckius.luckius_mortar.datagen.tag.ModItemTagGenerator;

public class LuckiusMortarDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModItemTagGenerator::new);
        pack.addProvider(ModRecipeGenerator::new);
        pack.addProvider(ModItemModelGenerator::new);
    }

}
