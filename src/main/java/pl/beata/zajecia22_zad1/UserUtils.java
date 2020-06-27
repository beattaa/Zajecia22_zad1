package pl.beata.zajecia22_zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserUtils {
    static ArrayList<User> filterUsers(List<User> users, Predicate<User>expr){
        ArrayList<User> result = new ArrayList<>();
        for (User user : users) {
            if (expr.test(user)){
                result.add(user);
            }
        }
        return result;
    }

    static ArrayList<User> removeUsers(List<User> users, Predicate<User>expr){
        ArrayList<User> result = new ArrayList<>();
        for (User user : users) {
            if (expr.test(user)){
                result.add(user);
            }
        }
        return result;
    }
}
