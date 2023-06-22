package edu.uoc.epcsd.showcatalog.controllers;

import edu.uoc.epcsd.showcatalog.entities.Category;
import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.repositories.CategoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    // get categories
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories() {
        log.trace("Get All Categories");
        return categoryRepository.findAll();
    }

    // create category
    @PostMapping("/createCategory")
    @ResponseStatus(HttpStatus.OK)
    public Category createCategory(@RequestBody Category category){
        log.trace("Create Category");
        return categoryRepository.save(category);
    }

    //delete category
    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long categoryId) {

        if (categoryRepository.existsById(categoryId)){
            log.trace("Delete Category");
            categoryRepository.deleteById(categoryId);
        } else {
            log.trace("Category does not exist");
        }

    }


}
