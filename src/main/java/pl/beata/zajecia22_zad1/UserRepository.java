package pl.beata.zajecia22_zad1;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> usersList = new ArrayList<>();

    public UserRepository() {
        usersList.add(new User("Anna", "Nowak", 25));
        usersList.add(new User("Jan", "Kowalski", 50));
        usersList.add(new User("Joanna", "Wiśniewska", 34));
    }

    public List<User> getUsers() {
        return usersList;
    }

    public void add(User user) {
        usersList.add(user);
    }

}
