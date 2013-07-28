package de.staehler.spring.lazybeans.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:/META-INF/spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {
}
