package com.ironz.unsafe;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Alexander Efremenkov.
 * email: implimentz@gmail.com
 * twitter: iamironz
 */
public class UnsafeAndroidTest {

    @Test
    public void testInstantiation() throws Exception {
        UnsafeAndroid unsafeAndroid = new UnsafeAndroid();
        Thread thread = unsafeAndroid.allocateInstance(Thread.class);
        assertTrue(thread != null);
    }
}