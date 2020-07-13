package TamilovKulanbek.FinalProject.Controllers;

import TamilovKulanbek.FinalProject.Entities.Category;
import TamilovKulanbek.FinalProject.Entities.Item;
import TamilovKulanbek.FinalProject.Services.CategoryService;
import TamilovKulanbek.FinalProject.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ItemService itemService;

    @Autowired
    public CategoryController(CategoryService categoryService, ItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @PostMapping
    public Category save(@RequestBody Category category){
        return categoryService.save(category);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Category> getAll(){
       return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public List<Item> getAllItemsByCategoryId(@PathVariable("id")Long categoryId){
       return itemService.itemsByCategoryId(categoryId);
    }

    @GetMapping("/id/{id}")
    public Category getById(@PathVariable("id")Long id){
        return categoryService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")Long id){
        categoryService.deleteById(id);
    }
}
