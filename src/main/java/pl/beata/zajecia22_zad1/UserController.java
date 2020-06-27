package pl.beata.zajecia22_zad1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

}
