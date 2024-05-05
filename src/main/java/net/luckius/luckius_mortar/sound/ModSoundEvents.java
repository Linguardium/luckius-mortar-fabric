package net.luckius.luckius_mortar.sound;

import net.luckius.luckius_mortar.LuckiusMortar;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static net.luckius.luckius_mortar.registration.Util.registerSoundEvent;

public class ModSoundEvents {
    public static SoundEvent MORTAR_SOUND_CLICK =  registerSoundEvent("mortar_click_craft");

    public static void init() {
        // empty method used to class load.
    }


}
