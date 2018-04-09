package app;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;

@SpringBootApplication
@ComponentScan(basePackages = {"controllers","services", "services.launcher"})
@EnableMongoRepositories(basePackages = {"services"})
@EnableAutoConfiguration
@EntityScan(basePackages = {"entity"})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}