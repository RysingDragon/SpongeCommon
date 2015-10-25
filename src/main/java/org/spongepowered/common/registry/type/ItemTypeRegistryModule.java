/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.registry.type;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.Item;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.common.registry.AdditionalCatalogRegistryModule;
import org.spongepowered.common.registry.RegistryHelper;
import org.spongepowered.common.registry.util.RegisterCatalog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ItemTypeRegistryModule implements AdditionalCatalogRegistryModule<ItemType> {

    public static final Item NONE_ITEM = new Item().setUnlocalizedName("none").setMaxDamage(0).setMaxStackSize(1);
    public static final ItemStack NONE = (ItemStack) new net.minecraft.item.ItemStack(ItemTypeRegistryModule.NONE_ITEM);


    public static ItemTypeRegistryModule getInstance() {
        return Holder.INSTANCE;
    }

    @RegisterCatalog(ItemTypes.class)
    private final Map<String, ItemType> itemTypeMappings = new HashMap<>();

    @Override
    public Optional<ItemType> getById(String id) {
        checkNotNull(id);
        if (!id.contains(":")) {
            id = "minecraft:" + id; // assume vanilla
        }
        return Optional.ofNullable(this.itemTypeMappings.get(id));
    }

    @Override
    public Collection<ItemType> getAll() {
        return ImmutableList.copyOf(this.itemTypeMappings.values());
    }

    public void registerFromGameData(String id, ItemType itemType) {
        this.itemTypeMappings.put(id.toLowerCase(), itemType);
    }

    @Override
    public void registerAdditionalCatalog(ItemType extraCatalog) {
        this.itemTypeMappings.put(extraCatalog.getId().toLowerCase(), extraCatalog);
    }

    @Override
    public void registerDefaults() {
        setItemNone();
    }

    private void setItemNone() {
        try {
            RegistryHelper.setFinalStatic(ItemStackSnapshot.class, "NONE", NONE.createSnapshot());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private ItemTypeRegistryModule() { }

    private static final class Holder {
        private static final ItemTypeRegistryModule INSTANCE = new ItemTypeRegistryModule();
    }
}
