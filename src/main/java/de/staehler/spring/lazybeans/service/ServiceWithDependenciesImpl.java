package de.staehler.spring.lazybeans.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithDependenciesImpl implements ServiceWithDependencies {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceWithDependenciesImpl.class);

    @Autowired
    private ServiceOne serviceOne;

    @Autowired
    private ServiceTwo serviceTwo;

    public ServiceWithDependenciesImpl() {
        LOG.info("Created ServiceWithDependenciesImpl");
    }

    public String combineGreetings(String name) {
        return serviceOne.greetMe(name) + " " + serviceTwo.greetMe(name);
    }

}
