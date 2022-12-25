package guru.sfg.brewery.security;

import guru.sfg.brewery.domain.security.User;
import guru.sfg.brewery.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserUnlockedService {

    private final UserRepository userRepository;

    @Scheduled(fixedRate = 500000000)
    public void unlockAccounts(){

        log.debug("running unlock account Schedular");

        List<User> lockedUsers = userRepository.findAllByAccountNonLockedAndLastModifiedDateIsBefore(false,
                Timestamp.valueOf(LocalDateTime.now().minusSeconds(30)));

        if(lockedUsers.size()>0){
            log.debug("unlocking the user...");
            lockedUsers.forEach(user-> user.setAccountNonLocked(true));
            userRepository.saveAll(lockedUsers);
        }
    }
}
