package com.java.myrotiuk;

import com.java.myrotiuk.helper.LoadDataHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//java -Dserver.port=9090 -jar target/practice-spring-boot-microservice-0.0.1-SNAPSHOT.jar run on particular port
@SpringBootApplication
@RequiredArgsConstructor
public class PracticeSpringBootMicroserviceApplication implements CommandLineRunner {

    private final LoadDataHelper loadDataHelper;

    public static void main(String[] args) {
        SpringApplication.run(PracticeSpringBootMicroserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loadDataHelper.createData();
    }

}
