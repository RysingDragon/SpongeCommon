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
package org.spongepowered.common.interfaces;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;

import java.util.Collection;

public interface IMixinWorldSettings {

    int getDimensionId();

    boolean getIsMod();

    void setDimensionType(DimensionType type);

    void setGeneratorSettings(DataContainer generatorSettings);

    void setGeneratorModifiers(Collection<WorldGeneratorModifier> modifiers);

    void setEnabled(boolean isWorldEnabled);

    void setLoadOnStartup(boolean loadOnStartup);

    void setKeepSpawnLoaded(boolean keepSpawnLoaded);

    void setDimensionId(int id);

    void setIsMod(boolean isMod);

    void setPVPEnabled(boolean enabled);
}
