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
 * Specifies machine code to be associated with a native method.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(CodeSet.class)
public @interface Code {
    /**
     * Machine code for the method implementation
     * encoded as a hex string, possibly with spaces.
     * Typically, the code should end with a return instruction.
     */
    String value();

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
