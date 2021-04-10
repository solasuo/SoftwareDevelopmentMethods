package ot.Bloodecode;

import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class BloodecodeApplication implements CommandLineRunner {    
    
    public static void main(String[] args) {
        SpringApplication.run(BloodecodeApplication.class);
    }
    
    @Autowired
    TextUI textUi;
    
    @Override
    public void run(String... args) throws Exception {
        Scanner reader = new Scanner(System.in);
        System.out.println("Running");
        textUi.start(reader);
    }          

}
