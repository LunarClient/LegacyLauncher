/*
 * This file is part of project Orion, licensed under the MIT License (MIT).
 *
 * Copyright (c) Original contributors ("I don't care" license? See https://github.com/Mojang/LegacyLauncher/issues/1)
 * Copyright (c) 2017-2018 Mark Vainomaa <mikroskeem@mikroskeem.eu>
 * Copyright (c) Contributors
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

package net.minecraft.launchwrapper;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

/**
 * @author Mark Vainomaa
 */
public class DummyTweaker implements ITweaker {
    @Override public void acceptOptions(@NonNull List<String> args) {}
    @Override public void injectIntoClassLoader(@NonNull LaunchClassLoader classLoader) {}
    @Override @NonNull public String getLaunchTarget() { return DummyTweaker.class.getName(); }
    @Override @NonNull public String[] getLaunchArguments() { return new String[0]; }

    public static void main(String... args){
        String[] text = new String[]{
                "This is dummy tweaker class. You should specify tweak class using ",
                "'--tweakClass' argument or hook into LegacyLauncher programmatically."
        };
        System.out.println(String.join("\n", text));
    }
}
