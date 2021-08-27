package net.obesephoenix.koalakraftcrystals.crystals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.codehaus.plexus.util.Base64;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffectType;

public abstract class Crystal {

    private final String name;
    private final String id;
    private final String textureURL;
    private final Map<PotionEffectType, Integer> effects;

    public Crystal(String name, String id, String textureURL, Map<PotionEffectType, Integer> effects) {
        this.name = name;
        this.id = id;
        this.textureURL = textureURL;
        this.effects = effects;
    }

    private List<String> addLorePrefix(List<String> lore) {
        return lore;
    }

    private List<String> addLoreSuffix(List<String> lore) {
        return lore;
    }

    private List<String> generateDefaultLore() {
        List<String> lore = new ArrayList<String>();
        lore = this.addLorePrefix(lore);

        lore.add(" ");
        lore.add("Holding this gem grants the bearer");
        lore.add("the power of - ");

        List<String> finalLore = lore;
        effects.forEach((effect, level) -> {
            finalLore.add(effect.getName() + " " + level);
        });
        lore.addAll(finalLore);

        lore.add(" ");

        lore = this.addLoreSuffix(lore);
        return lore;
    }

    public ItemStack createItemStack() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(),  null);

        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", this.textureURL).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(meta, profile);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        meta.setLore(this.generateDefaultLore());
        item.setItemMeta(meta);
        return item;
    }

    public String getName() {return name;}
    public String getID() {return id;}
    public String getTextureURL() {return textureURL;}
    public Map<PotionEffectType, Integer> getEffects() {return effects;}
}
