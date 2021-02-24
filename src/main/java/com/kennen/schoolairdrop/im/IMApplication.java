package com.kennen.schoolairdrop.im;

import com.kennen.schoolairdrop.im.impl.ServerLauncherImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2

@SpringBootApplication
public class IMApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IMApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final ServerLauncherImpl launcher = new ServerLauncherImpl();
        launcher.startup();
        Runtime.getRuntime().addShutdownHook(new Thread(launcher::shutdown));
    }

//    @Bean
//    public IDGenerator createIDWorker() {
//        return new IDGenerator(0, 0);
//    }
}
