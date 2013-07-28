package de.staehler.spring.lazybeans.service.impl;

import de.staehler.spring.lazybeans.service.ServiceOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceOneImpl implements ServiceOne {

    private static final Logger LOG  = LoggerFactory.getLogger(ServiceOneImpl.class);

    public ServiceOneImpl() {
        LOG.info("Created ServiceOneImpl");
    }

    public String greetMe(String name) {
        return "Hello " + name;
    }
}
