package com.mylearning;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.condition.DisabledOnOs;

import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.DisabledOnJre;

import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.DisabledIf;

import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;


//Don't have to remember just for understanding and learning purpose
public class ConditionalTestExecution {

    @Test
    @EnabledIf("customCondition")
    void testEnabledIfCondition() {
        System.out.println("Test is enabled because the condition is true.");
    }

    @Test
    @DisabledIf("customCondition")
    void testDisabledIfCondition() {
        System.out.println("Test is disabled because the condition is true.");
    }

    boolean customCondition() {
        // This condition can be based on some logic. For example:
        return System.getProperty("os.name").contains("Windows");
    }


    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testOnlyOnWindows() {
        System.out.println("This test will run only on Windows.");
    }

    @Test
    @DisabledOnOs(OS.LINUX)
    void testNotOnLinux() {
        System.out.println("This test will not run on Linux.");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testOnlyOnJava8() {
        System.out.println("This test will run only on Java 8.");
    }

    @Test
    @DisabledOnJre(JRE.JAVA_17)
    void testNotOnJava17() {
        System.out.println("This test will not run on Java 17.");
    }


    @Test
    @EnabledIfSystemProperty(named = "user.country", matches = "US")
    void testEnabledIfSystemProperty() {
        System.out.println("This test is enabled when the user.country property is US.");
    }

    @Test
    @DisabledIfSystemProperty(named = "user.language", matches = "fr")
    void testDisabledIfSystemProperty() {
        System.out.println("This test is disabled when the user.language property is fr.");
    }
    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "PRODUCTION")
    void testEnabledIfEnvVariable() {
        System.out.println("This test is enabled when ENV is set to PRODUCTION.");
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "ENV", matches = "TEST")
    void testDisabledIfEnvVariable() {
        System.out.println("This test is disabled when ENV is set to TEST.");
    }
}
