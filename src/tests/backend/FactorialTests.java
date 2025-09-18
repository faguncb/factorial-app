// Simple test class without JUnit (pure Java). Run as java FactorialTests
// Assumes the factorial classes are in the classpath.

public class FactorialTests {
    public static void main(String[] args) {
        testProcedural();
        testOOP();
        testFunctional();
        System.out.println("All tests completed.");
    }

    private static void testProcedural() {
        System.out.println("Testing Procedural:");
        assertEquals(ProceduralFactorial.compute(0), 1);
        assertEquals(ProceduralFactorial.compute(1), 1);
        assertEquals(ProceduralFactorial.compute(5), 120);
        assertEquals(ProceduralFactorial.compute(10), 362