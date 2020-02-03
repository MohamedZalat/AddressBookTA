package part2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AddressBookApplication {

    private static final Logger log = LoggerFactory.getLogger(AddressBookApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(BuddyInfoRepository repository) {
//        return (args) -> {
//            // save a few buddyInfos
//            repository.save(new BuddyInfo("Jack", 123));
//            repository.save(new BuddyInfo("Chloe", 4));
//            repository.save(new BuddyInfo("Kim", 123));
//            repository.save(new BuddyInfo("David", 456));
//            repository.save(new BuddyInfo("Michelle", 789));
//
//            // fetch all buddyInfos
//            log.info("BuddyInfos found with findAll():");
//            log.info("-------------------------------");
//            for (BuddyInfo buddyInfo : repository.findAll()) {
//                log.info(buddyInfo.toString());
//            }
//            log.info("");
//
//            // fetch an individual buddyInfo by ID
//            BuddyInfo buddyInfo = repository.findById(1L);
//            log.info("part2.BuddyInfo found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(buddyInfo.toString());
//            log.info("");
//
//            // fetch buddyInfos by last name
//            log.info("part2.BuddyInfo found with findByName('Bauer'):");
//            log.info("--------------------------------------------");
//            repository.findByName("Bauer").forEach(bauer -> {
//                log.info(bauer.toString());
//            });
//
//            log.info("part2.BuddyInfo found with findByPhoneNumber(123):");
//            log.info("--------------------------------------------");
//            for (part2.BuddyInfo bauer : repository.findByPhoneNumber(123)) {
//                log.info(bauer.toString());
//            }
//            log.info("");
//        };
//    }

}