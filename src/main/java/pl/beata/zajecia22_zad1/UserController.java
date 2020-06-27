package pl.beata.zajecia22_zad1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @RequestMapping("/addUserFromForm")
    public String addUserFromForm(@RequestParam(name = "imie") String name, @RequestParam(name = "nazwisko", defaultValue = "xxx") String surname, @RequestParam(name = "wiek", defaultValue = "-1") Integer age) {
        if (name.equals("")) {
            return "redirect:/err.html";
        } else {
            User newUser = new User(name, surname, age);
            userRepository.add(newUser);
            return "redirect:/success.html";
        }
    }


    @RequestMapping("/add")
    public String addUser(@RequestParam(name = "imie") String name, @RequestParam(name = "nazwisko") String surname, @RequestParam(name = "wiek") Integer age) {
        if (name.equals("")) {
            return "err.html";
        } else {
            User newUser = new User(name, surname, age);
            userRepository.add(newUser);
            return "success.html";
        }
    }

    @ResponseBody
    @RequestMapping("/users")
    public String getUsers() {
        List<User> usersList = userRepository.getUsers();
        String result = "";
        for (User user : usersList) {
            result += user.getName() + " " + user.getSurname() + " wiek: " + user.getAge() + "<br/>";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/filterUsers")
    public String filterUsers(@RequestParam(name = "imie") String name, @RequestParam(name = "nazwisko", defaultValue = "xxx", required = false) String surname, @RequestParam(name = "wiek", defaultValue = "-1", required = false) Integer age) {
        List<User> usersList = getUsers(name, surname, age);

        String result = "";
        for (User user : usersList) {
            result += user.getName() + " " + user.getSurname() + " wiek: " + user.getAge() + "<br/>";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteUsers")
    public String deleteUsers(@RequestParam(name = "imie") String name, @RequestParam(name = "nazwisko", defaultValue = "xxx", required = false) String surname, @RequestParam(name = "wiek", defaultValue = "-1", required = false) Integer age) {
        List<User> usersToBeRemoved = getUsers(name, surname, age);
        userRepository.getUsers().removeAll(usersToBeRemoved);

        String result = "";
        for (User user : userRepository.getUsers()) {
            result += user.getName() + " " + user.getSurname() + " wiek: " + user.getAge() + "<br/>";
        }
        return result;
    }

    private List<User> getUsers(@RequestParam(name = "imie") String name, @RequestParam(name = "nazwisko", defaultValue = "xxx", required = false) String surname, @RequestParam(name = "wiek", defaultValue = "-1", required = false) Integer age) {
        List<User> usersList = userRepository.getUsers();

        if (!name.equals("")) {
            usersList = UserUtils.filterUsers(usersList, user -> user.getName().equalsIgnoreCase(name));
        }

        if (!surname.equals("xxx")) {
            usersList = UserUtils.filterUsers(usersList, user -> user.getSurname().equalsIgnoreCase(surname));
        }

        if (!age.equals(-1)) {
            usersList = UserUtils.filterUsers(usersList, user -> user.getAge() == age);
        }
        return usersList;
    }

}
