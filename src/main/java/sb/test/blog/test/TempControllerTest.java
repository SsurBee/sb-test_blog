package sb.test.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TempControllerTest {
    @GetMapping("home")
    public String tempHome(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }
}
