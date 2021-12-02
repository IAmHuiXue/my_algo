package util.junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.*;

/*
JUnit, the gold standard for testing in Java. If you want to ask your candidate to write JUnit-style tests during the interview,
please format your Java code like so:
 */

/*

Methods & Descirption

void assertEquals(int expected, int actual)
- check that two primitives/objects are equal

void assertTrue(boolean condition)
- check that a condition is true

void assertFalse(boolean condition)

void assertNotNull(Object obj)

void assertNull(Object obj)

void assertSame(Object a, Object b)
- check if the two object references point to the SAME object 比地址

...

 */

public class JUnit {
    // this should scan JUnit class and run all the methods that annotates @Test
    // when at interview instead of IDE, we should invoke the method below to run tests
    public static void main(String[] args) {
        JUnitCore.main("JUnit");
    }

    @Test
    public void testNoop() {
        Assert.assertTrue(true);
    }

    @Test
    public void testEqual() {
        String a = new String("hello");
        String b = new String("hello");
        Assert.assertEquals(a, b); // correct
    }

    @Test
    public void testSame() {
        String a = new String("hello");
        String b = new String("hello");
        Assert.assertSame(a, b); // wrong
    }


    @Test
    public void testMike() {
        int number = 190;
        Assert.assertEquals(A.foo(number), number + 3);
    }
}


class A {
    public static int foo(int num) {
        return num + 3;
    }
}
