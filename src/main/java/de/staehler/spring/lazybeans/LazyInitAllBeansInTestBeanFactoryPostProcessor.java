package de.staehler.spring.lazybeans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * This class is for loading all beans lazy in test environment to reduce memory usage of unused beans.
 */
public class LazyInitAllBeansInTestBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(LazyInitAllBeansInTestBeanFactoryPostProcessor.class);

    private final String packageFilter;

    public LazyInitAllBeansInTestBeanFactoryPostProcessor(String packageFilter) {
        this.packageFilter = packageFilter;
    }

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
        if (!beanClassName.startsWith(packageFilter)) {
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
