package de.staehler.spring.lazybeans.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceTwoImpl implements ServiceTwo {

    private static final Logger LOG  = LoggerFactory.getLogger(ServiceTwoImpl.class);

    public ServiceTwoImpl() {
        LOG.info("Created ServiceTwoImpl");
    }

    @Override
    public String greetMe(String name) {
        return "Hi " + name;
    }
}
