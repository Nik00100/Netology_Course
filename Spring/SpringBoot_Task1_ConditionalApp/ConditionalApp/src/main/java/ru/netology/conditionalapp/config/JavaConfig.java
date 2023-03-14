package ru.netology.conditionalapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.conditionalapp.systemProfile.DevProfile;
import ru.netology.conditionalapp.systemProfile.ProductionProfile;
import ru.netology.conditionalapp.systemProfile.SystemProfile;


@Configuration
public class JavaConfig {

    @Bean(name = "devProfile")
    @ConditionalOnProperty(prefix = "netology", name = "profile.dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean(name = "prodProfile")
    @ConditionalOnProperty(prefix = "netology", name = "profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
