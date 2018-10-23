package application.controllers;

import application.dao.DAO;
import application.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final DAO<Task> taskDAO;

    @Autowired(required = true)
    public HomeController(DAO<Task> taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GetMapping("/")
    public String index(Model model) {
        try {
            model.addAttribute("tasks", taskDAO.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}