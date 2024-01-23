package org.example.config;

import org.example.appHandler.ContactStore;
import org.example.appHandler.InitContactList;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@ComponentScan("org.example")
@Configuration
@PropertySource("classpath:application.properties")
@Profile("init")
public class InitAppConfig {

    @Bean
    public InitContactList initContactList(ContactStore contactStore) {
        return new InitContactList(contactStore);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("application.yaml"));
        configurer.setProperties(yamlPropertiesFactoryBean.getObject());
        return configurer;
    }
}
