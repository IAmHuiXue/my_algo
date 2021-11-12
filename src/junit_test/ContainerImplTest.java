package junit_test;

import org.junit.jupiter.api.*;

import java.time.Duration;

/**
 * This test class can be considered as a playground,
 * so feel free to modify it as needed. E.g.:
 * - add your own custom tests
 * - delete existing tests
 * - modify test content or expected output
 * <p>
 * The results of these tests do not affect your final score
 * (unless the project fails to build).
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BasicTests {

    private Container container;

    @BeforeEach // run this method before each test case
    public void setUp() {
        container = new ContainerImpl();
    }

    /**
     * Add 1, 2, 5, 4 -> [1, 2, 4, 5]
     * Median of [1, 2, 4, 5] is 2
     * Delete 1 -> [2, 4, 5]
     * Median of [2, 4, 5] is 4
     */
    @Test // tells JUnit that the method can be run as a test case
    @Order(1)
    void test_basic1() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            container.add(1);
            container.add(2);
            container.add(5);
            container.add(4);
            Assertions.assertEquals(2, container.getMedian());
            Assertions.assertTrue(container.delete(1));
            Assertions.assertEquals(4, container.getMedian());
        });
    }

    /**
     * Add 5, 3, 5 -> [3, 5, 5]
     * Median of [3, 5, 5] is 5
     * Delete 5, 5, 5 -> [3]
     * Median of [3] is 3
     * Delete [2, 3] -> []
     * Median of [] does not exist
     * Add 1, 1, 2, 2, 2 -> [1, 1, 2, 2, 2]
     * Median of [1, 1, 2, 2, 2] is 2
     * Delete 2 -> [1, 1, 2, 2]
     * Median of [1, 1, 2, 2] is 1
     * Delete 1 -> [1, 2, 2]
     * Median of [1, 2, 2] is 2
     */
    @Test
    @Order(2)
    void test_basic2() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            container.add(5);
            container.add(3);
            container.add(5);
            Assertions.assertEquals(5, container.getMedian());
            Assertions.assertTrue(container.delete(5));
            Assertions.assertTrue(container.delete(5));
            Assertions.assertFalse(container.delete(5));
            Assertions.assertEquals(3, container.getMedian());
            Assertions.assertFalse(container.delete(2));
            Assertions.assertTrue(container.delete(3));
            Assertions.assertThrows(
                    RuntimeException.class,
                    () -> container.getMedian()
            );
            container.add(1);
            container.add(1);
            container.add(2);
            container.add(2);
            container.add(2);
            Assertions.assertEquals(2, container.getMedian());
            Assertions.assertTrue(container.delete(2));
            Assertions.assertEquals(1, container.getMedian());
            Assertions.assertTrue(container.delete(1));
            Assertions.assertEquals(2, container.getMedian());
        });
    }

    /**
     * Delete 4 -> []
     * Median of [] does not exist
     * Add 10, 9, 8, ..., 1 -> [1, 2, 3, ..., 10]
     * Median of [1, 2, ..., 10] is 5
     * Delete 4, 5, 6 -> [1, 2, 3, 7, 8, 9, 10]
     * Median of [1, 2, 3, 7, 8, 9, 10] is 7
     */
    @Test
    @Order(3)
    void test_basic3() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Assertions.assertFalse(container.delete(4));
            Assertions.assertThrows(
                    RuntimeException.class,
                    () -> container.getMedian()
            );
            for (int i = 10; i >= 1; --i) {
                container.add(i);
            }
            Assertions.assertEquals(5, container.getMedian());
            for (int i = 4; i <= 6; ++i) {
                Assertions.assertTrue(container.delete(i));
            }
            Assertions.assertEquals(7, container.getMedian());
        });
    }
}