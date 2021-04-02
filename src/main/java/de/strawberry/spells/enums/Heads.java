package de.strawberry.spells.enums;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import java.lang.reflect.Field;
import java.util.UUID;

public enum Heads {
    SPELL_BOOK_RED("OTNhNjljM2NhYTMxMzA0ZTk5NTIzMjhjNzJjZWUwYjU3YjJhMmJkNDZjZTljNWNiODhjMDdkMTI2NjI3N2Q2YSJ9fX0=", "81da9dfa-5ff8-4c8e-a41d-d2a82bf6e9f3"),
    SPELL_BOOK_LIGHT_BLUE("OGJlMmJhZjQwZmQ4NWViNTczZmU1YjJlNWI2Yzg4MTdjZjUwZjg4M2Q5NTc2OTQxNTgwN2FiMDcyODhhNDdjZCJ9fX0=", "bea9ae4a-9e16-4ed1-b8ac-bec02556b473"),
    SPELL_BOOK_BLUE("Y2NmNDM0MTYwMDlkMWE4MTMwYWE4NzA4Y2E3NGVlNmVlYWU3NzVhNzY5ZTdkMDI5M2U1NjhhZjY2Njk0OTY2OSJ9fX0=", "15d53408-2362-4212-96b4-d049c788d21f"),
    SPELL_BOOK_PURPLE("YWM5MmU1MTExZWE3ZDdlYjNmMDU1ODMzZTFmMzVkNjUxYzBkYTU1NjQzYzkzODNlMGJjZTZjMjM2OTZkNThiOSJ9fX0=", "ac076147-811b-4ccb-8779-973a16aac6cd"),
    SPELL_BOOK_YELLOW("ZDUzZmExYjU3ZTRmNzg0ZDE2ZTVhMmRhYTJmNzQ2YjJlY2ZlNjI0Y2NkNzRhNGQ0YWNjNmEyZTZhMDgzZjU0ZSJ9fX0=", "8676f144-122c-46bd-b085-c89f153fdeb4"),
    SPELL_BOOK_GREEN("MWMwMzBjYzM1ZmRlMDIxOGRlZDViYTVkNTU3Y2NhOWUwM2RjYmM4NGY5ZTY3MjkxMWRhNTRmZTA3YzVmZGJiYiJ9fX0=", "cab463bf-60a6-41e9-b801-715151dcefd4"),
    SPELL_BOOK_NETHER("NTZkMTMyM2IyMjA1NTc3MTAxZjZkOTE0ZDM0OGQ2YTU1OGYyNTUxODliODk4Mjk4NWRiMDdiYmNlMDNkMjAxMSJ9fX0=", "f30b3913-4ada-4fc3-ac03-3477abea2717"),
    SPELL_BOOK_LIBRARY("Yjc4ODA1ZTc1ZmEzNzkwZjNmZWZjZDgwNzIyZDMxZjY5MDk1OGY5YzAyMDEyYmZhZDJhMjBhOWI4YzQyNDQyYSJ9fX0=", "8f623ad7-6799-4e53-a6f6-25e8833cdbd1"),
    KNOWLEDGE_SPELL_BOOK("YjE2Y2M1NzU1Y2RkMjYwZjdiNGI1YzFhMWYxZjNiZDMxODUxZmMxZDk4Yjc0NDM3YjJmYjRiZDZlYjhkMiJ9fX0=", "50c4425c-a66f-4e04-871a-9cebe5b4edf2"),
    BASIC_SPELL_BOOK("ZWQ4OGE4NjQ0NWQ0MTE0YjE1MTcxYmI5ZWY4Mzc0NjA2MGNhZmZmMmNiMmFhNzUxMTU5NzQwY2ZmNDhkZjg5ZSJ9fX0=","9f4d98ed-ceb0-42eb-ac33-e5e2e7d087c7");

    private ItemStack item;
    private String prefix = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";
    private Heads(String texture, String uuid){
        item = createSkull(prefix + texture, uuid);
    }

    public ItemStack getItem(){
        return item;
    }

    private ItemStack createSkull(String url, String uuid){
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        if(url.isEmpty()) return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.fromString(uuid), null);
        profile.getProperties().put("textures", new Property("textures", url));

        try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
        head.setItemMeta(headMeta);

        return head;
    }
}
