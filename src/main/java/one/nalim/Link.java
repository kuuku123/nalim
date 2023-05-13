/*
 * Copyright (C) 2022 Andrei Pangin
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package one.nalim;

import java.lang.annotation.*;

/**
 * Denotes a method for linking with the native code. 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(LinkSet.class)
public @interface Link {
    /**
     * Alternative name of the native function.
     * If not specified, Java method name is assumed.
     */
    String name() default "";

    /**
     * true, if the target method uses Java calling convention;
     * false, if the Linker should generate a prologue
     * for translating arguments according to the native ABI.
     */
    boolean naked() default false;

    /**
     * The operating system that will run the code specified in this annotation.
     * When there are multiple {@link Code} annotations , the one with matching 'os' attribute
     * will have precedence over the own without it.
     */
    Os os() default Os.UNSPECIFIED;

    /**
     * The operating system that will run the code specified in this annotation.
     * When there are multiple {@link Code} annotations , the one with matching 'os' attribute
     * will have precedence over the own without it.
     */
    Arch arch() default Arch.UNSPECIFIED;
}
