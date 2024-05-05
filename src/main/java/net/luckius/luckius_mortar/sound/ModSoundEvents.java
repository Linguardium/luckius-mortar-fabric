package net.luckius.luckius_mortar.sound;

import net.minecraft.sound.SoundEvent;

import static net.luckius.luckius_mortar.registration.Util.registerSoundEvent;

public class ModSoundEvents {
    public static SoundEvent MORTAR_SOUND_CLICK =  registerSoundEvent("mortar_click_craft");

    public static void init() {
        // empty method used to class load.
    }


}
