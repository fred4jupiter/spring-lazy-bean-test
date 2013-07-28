package de.staehler.spring.lazybeans.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertNotNull;

public class UsingServiceOneOnlyTest extends AbstractIntegrationTest {

    @Autowired
    private ServiceOne serviceOne;

    @Test
    public void injectAndCheck() {
        assertNotNull(serviceOne);
    }

}
