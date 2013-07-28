package de.staehler.spring.lazybeans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

public class LazyInitAllBeansInTestBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(LazyInitAllBeansInTestBeanFactoryPostProcessor.class);

    private static final String PACKAGE_FILTER_STRING = "de.staehler";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefs = beanFactory.getBeanDefinitionNames();
        for (String defName : beanDefs) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(defName);
            setLazyInitIfMatch(beanDefinition);
        }
    }

    private void setLazyInitIfMatch(BeanDefinition beanDefinition) {
        if (beanDefinition.isAbstract()) {
            return;
        }

        final String beanClassName = beanDefinition.getBeanClassName();
        if (!beanClassName.startsWith(PACKAGE_FILTER_STRING)) {
            return;
        }

        if (beanClassName.equals(this.getClass().getName())) {
            return;
        }

        beanDefinition.setLazyInit(true);
        LOG.info("setting class to lazy init: " + beanClassName);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
