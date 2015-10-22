package com.tomizone.parameters;

import org.junit.Test;

import java.util.Iterator;

import static mockit.Deencapsulation.getField;
import static org.junit.Assert.*;

public class FragmentsTest {

    @Test
    public void basicConstructorTest() {
        Fragments fs = new Fragments("User '' created ticket {}");
        assertNotNull(fs);
    }

    @Test
    public void testFragments() {
        String msg = "User123 {}";
        Fragments fs = new Fragments(msg);
        String result = getField(fs, "message");
        assertEquals("User123 {}", result);
    }

    @Test
    public void testIterator() {
        String msg = "User123 {}";
        Fragments fs = new Fragments(msg);

        Iterator<Fragment> it = fs.iterator();
        assertTrue(it.hasNext());

        Fragment f = it.next();
        assertNotNull(f);
    }

    @Test
    public void basicConstructorNullTest() {
        Fragments fs = new Fragments(null);
        assertNotNull(fs);
    }
}
