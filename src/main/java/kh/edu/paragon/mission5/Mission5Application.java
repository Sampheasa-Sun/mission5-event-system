package kh.edu.paragon.mission5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching; // <-- Add this import

@SpringBootApplication
@EnableCaching // <-- ADD THIS ANNOTATION
public class Mission5Application {

    public static void main(String[] args) {
        SpringApplication.run(Mission5Application.class, args);
    }

}
