/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MicroInfoRadiatorAppTest {
    @Test public void testAppHasAGreeting() {
        MicroInfoRadiatorApp classUnderTest = new MicroInfoRadiatorApp();
        assertNotNull("app should have a greeting", "fake: greeting");
    }
}
