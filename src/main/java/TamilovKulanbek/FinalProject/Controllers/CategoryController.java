package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Category;
import TamilovKulanbek.FinalProject.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category save(@RequestBody Category category){
        return categoryService.save(category);
    }
    @GetMapping
    public List<Category> getAll(){
       return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable("id")Long id){
        return categoryService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")Long id){
        categoryService.deleteById(id);
    }
}
