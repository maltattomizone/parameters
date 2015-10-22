/**
 * Copyright (c) 2010 RedEngine Ltd, http://www.redengine.co.nz. All rights reserved.
 * <p/>
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 * <p/>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package com.tomizone.parameters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class ArgClass {
    private String msg;

    public ArgClass(String s) {
        super();
        this.msg = s;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}

public class ParameterResolverTest {

    // prove that braces are replaced correctly ("blah {} blah", "blah") goes to "blah blah blah"

    private static String txt = "blah {} blah";
    private static String param = "blah";

    @Test(expected = NullPointerException.class)
    public void nullMessageTest() {
        ParameterResolver.resolve(null, param);
    }

    @Test(expected = NullPointerException.class)
    public void nullParamTest() {
        ParameterResolver.resolve(txt, null);
    }

    @Test(expected = NullPointerException.class)
    public void nullParamTest3() {
        ParameterResolver.resolve("blah {} {} {} blah", "111", null, "333");
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyMessageTest() {
        ParameterResolver.resolve("", param);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyMessageWithSpacesTest() {
        ParameterResolver.resolve("  ", param);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyParamTest() {
        ParameterResolver.resolve("  ", param);
    }

    @Test(expected = TooManyPlaceHoldersException.class)
    public void noParamsTest() {
        ParameterResolver.resolve(txt);
    }

    @Test(expected = TooManyPlaceHoldersException.class)
    public void invalidPlaceHoldNumTest() {
        ParameterResolver.resolve("blah {} {} blah", param);
    }

    @Test(expected = TooManyArgumentsException.class)
    public void invalidArgNumTest() {
        ParameterResolver.resolve("blah {} {} blah", param, param, param);
    }

    @Test(expected = TooManyArgumentsException.class)
    public void invalidArgNumTest2() {
        ParameterResolver.resolve("blah } blah", param);
    }

    @Test(expected = TooManyArgumentsException.class)
    public void invalidArgNumTest3() {
        ParameterResolver.resolve("blah { blah", param);
    }

    @Test(expected = TooManyArgumentsException.class)
    public void invalidArgNumTest4() {
        ParameterResolver.resolve("blah {blah} blah", param);
    }

    @Test(expected = ThrowablesCannotBeParametersException.class)
    public void throwableArgTest() {
        ParameterResolver.resolve("blah {} blah", new Throwable("blah"));
    }

    @Test
    public void constructorTest() {
        ParameterResolver resolver = new ParameterResolver();
        assertNotNull(resolver);
    }

    @Test
    public void resolveCheckTest() {
        assertEquals("blah blah blah", ParameterResolver.resolve("blah blah blah"));

        // mixed case from README
        assertEquals("User 'user_id' created ticket 12312312", ParameterResolver.resolve("User '' created ticket {}", "user_id", 12312312));

        assertEquals("blah blah blah", ParameterResolver.resolve("blah {} blah", "blah"));
        assertEquals("blah blah1 blah blah2", ParameterResolver.resolve("blah {} blah {}", "blah1", "blah2"));
        assertEquals("blah {blah} blah", ParameterResolver.resolve("blah {{}} blah", "blah"));

        assertEquals("blah 123 blah", ParameterResolver.resolve("blah {} blah", new ArgClass("123")));

        String msg = "321";
        assertEquals("blah String blah", ParameterResolver.resolve("blah {} blah", msg.getClass()));
    }

}
