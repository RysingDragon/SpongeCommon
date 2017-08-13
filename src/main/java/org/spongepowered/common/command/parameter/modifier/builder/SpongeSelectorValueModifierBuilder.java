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
package org.spongepowered.common.command.parameter.modifier.builder;

import com.google.common.base.Preconditions;
import org.spongepowered.api.command.parameter.managed.ValueParameterModifier;
import org.spongepowered.api.command.parameter.managed.standard.VariableValueParameterModifiers;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.common.command.parameter.modifier.SelectorModifier;

import java.util.HashSet;
import java.util.Set;

public class SpongeSelectorValueModifierBuilder implements VariableValueParameterModifiers.SelectorValueModifierBuilder {

    private final Set<Class<? extends Entity>> entityTypes = new HashSet<>();
    private boolean expectOne = false;
    private boolean strict = false;

    @Override
    public VariableValueParameterModifiers.SelectorValueModifierBuilder entityType(Class<? extends Entity> entityType) {
        this.entityTypes.add(entityType);
        return this;
    }

    @Override
    public VariableValueParameterModifiers.SelectorValueModifierBuilder setExpectOne(boolean expectOne) {
        this.expectOne = expectOne;
        return this;
    }

    @Override
    public VariableValueParameterModifiers.SelectorValueModifierBuilder setStrict(boolean strict) {
        this.strict = strict;
        return this;
    }

    @Override
    public VariableValueParameterModifiers.SelectorValueModifierBuilder from(ValueParameterModifier value) {
        Preconditions.checkArgument(value instanceof SelectorModifier, "value must be a SelectorModifier");
        SelectorModifier sm = ((SelectorModifier) value);
        this.entityTypes.clear();
        this.entityTypes.addAll(sm.getEntityClasses());
        this.expectOne = sm.isOnlyOne();
        this.strict = sm.isStrict();
        return this;
    }

    @Override
    public VariableValueParameterModifiers.SelectorValueModifierBuilder reset() {
        this.entityTypes.clear();
        this.strict = false;
        this.expectOne = false;
        return this;
    }

    @Override
    public ValueParameterModifier build() {
        Preconditions.checkState(!this.entityTypes.isEmpty(), "There must be at least one Entity Type");
        return new SelectorModifier(this.entityTypes, this.expectOne, this.strict);
    }

}
