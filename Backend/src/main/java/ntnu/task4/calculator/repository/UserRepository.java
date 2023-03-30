package ntnu.task4.calculator.repository;

import ntnu.task4.calculator.model.User;

public interface UserRepository {

    long getUserIdByName(String name);

}
