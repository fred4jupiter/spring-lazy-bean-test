package de.staehler.spring.lazybeans.service;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = {"classpath:/META-INF/spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class MockingOneDependencyWithSpringockitoTest {

    private static final Logger LOG = LoggerFactory.getLogger(MockingOneDependencyWithSpringockitoTest.class);

    @ReplaceWithMock
    @Autowired
    private ServiceTwo serviceTwo;

    @Autowired
    private ServiceWithDependencies serviceWithDependencies;

    @Test
    public void combineGreeting() {
        when(serviceTwo.greetMe(anyString())).thenReturn("Welcome Stranger!");

        String greeting = serviceWithDependencies.combineGreetings("Michael");
        LOG.debug(greeting);
        assertThat(greeting, CoreMatchers.equalTo("Hello Michael Welcome Stranger!"));
    }
}
