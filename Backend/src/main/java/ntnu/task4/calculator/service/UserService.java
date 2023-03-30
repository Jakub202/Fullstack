package ntnu.task4.calculator.service;

import lombok.RequiredArgsConstructor;
import ntnu.task4.calculator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public long getUserIdByName(String name){
        return userRepository.getUserIdByName(name);
    }
}
