package application.api;

import application.dao.TaskDAO;
import application.models.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/1.0", produces = "application/json")
public class APITaskController {

    private final TaskDAO taskDAO;

    @Autowired
    public APITaskController(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @PostMapping
    public String addNews(@RequestBody String taskString, Error error) {
        String json = "";
        try {
            Task task = taskDAO.add(new ObjectMapper().readValue(taskString, Task.class));
            json = new ObjectMapper().writeValueAsString(task);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        try {
            taskDAO.removeById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
