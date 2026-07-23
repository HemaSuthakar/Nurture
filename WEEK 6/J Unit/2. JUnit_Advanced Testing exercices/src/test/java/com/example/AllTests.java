package com.example;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Exercise 2: Test Suites and Categories
 * Groups several related test classes together so they can all be run
 * in one go, e.g. from the IDE or via `mvn test -Dtest=AllTests`.
 *
 * Requires the junit-platform-suite dependency (see pom.xml).
 */
@Suite
@SelectClasses({
        EvenCheckerTest.class,
        ExceptionThrowerTest.class,
        PerformanceTesterTest.class,
        OrderedTests.class
})
public class AllTests {
    // No body needed - annotations do all the work.
}
