package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Set;
import TamilovKulanbek.FinalProject.Services.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sets")
public class SetController {
    private final SetService setService;

    @Autowired
    public SetController(SetService setService) {
        this.setService = setService;
    }

    @PostMapping
    public Set save (@RequestBody Set set){
        return setService.save(set);
    }

    @GetMapping
    public List<Set> getAll(){
        return setService.getAll();
    }
    @GetMapping("/{id}")
    public Set getById(@PathVariable("id")Long id){
        return setService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")Long id){
        setService.deleteById(id);
    }
}
