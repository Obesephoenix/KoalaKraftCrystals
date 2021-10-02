package net.obesephoenix.koalakraftcrystals.crystals

import org.bukkit.entity.Player
import org.bukkit.inventory.meta.SkullMeta
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import java.lang.NoSuchFieldException
import java.lang.IllegalAccessException
import org.bukkit.NamespacedKey
import net.obesephoenix.koalakraftcrystals.KoalaKraftCrystals
import org.apache.commons.lang.WordUtils
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.craftbukkit.libs.org.codehaus.plexus.util.Base64
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.lang.reflect.Field
import java.util.*
import java.util.function.Consumer

abstract class Crystal(
    val name: String,
    val id: String,
    val textureURL: String,
    biome: String,
    effects: List<String>?,
    primaryColor: ChatColor,
    secondaryColor: ChatColor
) {
    val biome: String
    private val effects: MutableList<String>
    val primaryColor: ChatColor
    val secondaryColor: ChatColor

    abstract fun grantEffects(player: Player?): Boolean

    protected open fun addLorePrefix(lore: MutableList<String?>): MutableList<String?> {
        return lore
    }

    protected fun addLoreSuffix(lore: MutableList<String?>): MutableList<String?> {
        return lore
    }

    private fun generateDefaultLore(): List<String?> {
        var lore: MutableList<String?> = ArrayList()
        lore = addLorePrefix(lore)
        lore.add(" ")
        lore.add(ChatColor.GRAY.toString() + "Holding this gem in the " + secondaryColor + biome.lowercase(Locale.getDefault()))
        lore.add(ChatColor.GRAY.toString() + "grants the bearer the power of - ")
        lore.add(" ")
        val finalLore: MutableList<String?> = ArrayList()
        effects.forEach(Consumer { effect: String ->
            finalLore.add(
                primaryColor.toString() + "• " +
                        WordUtils.capitalize(effect.lowercase(Locale.getDefault()))
            )
        })
        lore.addAll(finalLore)
        lore.add(" ")
        lore = addLoreSuffix(lore)
        return lore
    }

    fun createItemStack(): ItemStack {
        val item = ItemStack(Material.PLAYER_HEAD)
        val meta = item.itemMeta as SkullMeta?
        val profile = GameProfile(UUID.randomUUID(), null)
        val encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", textureURL).toByteArray())
        profile.properties.put("textures", Property("textures", String(encodedData)))
        var profileField: Field? = null
        try {
            assert(meta != null)
            profileField = meta!!.javaClass.getDeclaredField("profile")
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
        if (profileField != null) {
            profileField.isAccessible = true
            try {
                profileField[meta] = profile
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        meta!!.lore = generateDefaultLore()
        meta.persistentDataContainer.set(
            NamespacedKey(KoalaKraftCrystals.instance, "uuid"), PersistentDataType.STRING, UUID.randomUUID().toString()
        )
        meta.persistentDataContainer.set(
            NamespacedKey(KoalaKraftCrystals.instance, "crystal_id"), PersistentDataType.STRING, id
        )
        meta.setDisplayName(
            primaryColor.toString() + ChatColor.BOLD + WordUtils.capitalize(
                name
            )
        )
        meta.isUnbreakable = true
        item.itemMeta = meta
        return item
    }

    fun getEffects(): List<String> {
        return effects
    }

    init {
        this.effects = ArrayList()
        this.biome = biome
        this.primaryColor = primaryColor
        this.secondaryColor = secondaryColor
        this.effects.addAll(effects!!)
        this.effects.add("Strength 2")
    }
}