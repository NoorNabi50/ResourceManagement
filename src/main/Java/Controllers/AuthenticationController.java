package Controllers;


import Data.UserService;
import Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String Index()
    {
        return "/Login";
    }


    @GetMapping("/Login")
    public String Login(@ModelAttribute User u, Model model) {
        if (new UserService().Login(u)) {
            model.addAttribute("message", "Login Success");
            return "/Dashboard";
        } else{
            model.addAttribute("message","Login Failed ! Incorrect Username or Password");
            return  "/Login";
        }
    }
}
