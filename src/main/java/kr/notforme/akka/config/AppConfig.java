package kr.notforme.akka.config;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
@ComponentScan(basePackages = { "kr.notforme.akka" })
public class AppConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringExtension springExtension;

    @Bean
    public ActorSystem actorSystem() {

        ActorSystem system = ActorSystem
                .create("AkkaTaskProcessing", akkaConfiguration());
        springExtension.initialize(applicationContext);
        return system;
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }
}
