package net.luckius.luckius_mortar.registration;

import net.luckius.luckius_mortar.sound.ModSoundEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static net.luckius.luckius_mortar.LuckiusMortar.makeId;

public class Util {

    public static Item registerItem(String itemId, Item item) {
        return Registry.register(Registries.ITEM, makeId(itemId), item);
    }

    public static SoundEvent registerSoundEvent(String soundId) {
        Identifier id = makeId(soundId);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
