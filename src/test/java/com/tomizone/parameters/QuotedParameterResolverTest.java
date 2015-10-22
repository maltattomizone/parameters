/**
 * Copyright (c) 2010 RedEngine Ltd, http://www.redengine.co.nz. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package com.tomizone.parameters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuotedParameterResolverTest {
  // prove that quotes are replace correctly ("blah '' blah", "blah") goes to "blah 'blah' blah"

    private static String txt = "blah '' blah";
    private static String param = "blah";

    @Test(expected = NullPointerException.class)
    public void nullParamTest3() {
        ParameterResolver.resolve("blah '' '' '' blah", "111", null, "333");
    }

    @Test(expected = TooManyPlaceHoldersException.class)
    public void noParamsTest() {
        ParameterResolver.resolve(txt);
    }

    @Test(expected = TooManyPlaceHoldersException.class)
    public void invalidPlaceHoldNumTest() {
        ParameterResolver.resolve("blah '' '' blah", param);
    }

    @Test(expected = TooManyArgumentsException.class)
    public void invalidArgNumTestSingleQuote() {
        ParameterResolver.resolve("blah ' blah", param);
    }

    @Test
    public void resolveCheckTest() {
        // check README
        assertEquals("User '12312312' created ticket", ParameterResolver.resolve("User '' created ticket", 12312312));

        // check task
        assertEquals("blah 'blah' blah", ParameterResolver.resolve("blah '' blah", "blah"));

        //check order and multi replace
        assertEquals("blah 'blah1' blah 'blah2'", ParameterResolver.resolve("blah '' blah ''", "blah1", "blah2"));

        //check mixed
        assertEquals("blah 'blah-q' blah-2 blah", ParameterResolver.resolve("blah '' {} blah", "blah-q", "blah-2"));

        // check Object arg
        assertEquals("blah '123' blah", ParameterResolver.resolve("blah '' blah", new QArgClass("123")));

        // check Class arg
        String msg = "321";
        assertEquals("blah 'String' blah", ParameterResolver.resolve("blah '' blah", msg.getClass()));
    }
}

class QArgClass {
    private String msg;

    public QArgClass(String s) {
        super();
        this.msg = s;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}