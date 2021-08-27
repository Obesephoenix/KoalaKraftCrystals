package net.obesephoenix.koalakraftcrystals.crystals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.libs.org.codehaus.plexus.util.Base64;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import javax.naming.Name;

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

    protected List<String> addLorePrefix(List<String> lore) {
        return lore;
    }

    protected List<String> addLoreSuffix(List<String> lore) {
        return lore;
    }

    private List<String> generateDefaultLore() {
        List<String> lore = new ArrayList<String>();
        lore = this.addLorePrefix(lore);

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Holding this gem grants the bearer");
        lore.add(ChatColor.GRAY + "the power of - ");
        lore.add(" ");

        List<String> finalLore = new ArrayList<>();
        effects.forEach((effect, level) -> {
            finalLore.add(ChatColor.AQUA + "• " + WordUtils.capitalize(effect.getName().toLowerCase()) + " " + level);
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
        meta.getPersistentDataContainer().set(new NamespacedKey(KoalaKraftCrystals.instance, "uuid")
                , PersistentDataType.STRING, UUID.randomUUID().toString());
        meta.setDisplayName(this.getName());

        item.setItemMeta(meta);
        return item;
    }

    public String getName() {return name;}
    public String getID() {return id;}
    public String getTextureURL() {return textureURL;}
    public Map<PotionEffectType, Integer> getEffects() {return effects;}
}
