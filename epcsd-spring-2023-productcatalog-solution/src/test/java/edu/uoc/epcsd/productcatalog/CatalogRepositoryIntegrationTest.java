package edu.uoc.epcsd.productcatalog;

import edu.uoc.epcsd.productcatalog.domain.Category;
import edu.uoc.epcsd.productcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa.CategoryEntity;
import edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa.CategoryRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
//@Import(CategoryRepositoryImpl.class) // Alternative to ComponentScan
@ComponentScan
public class CatalogRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void findCategoryByIDcanBeFound() {
        String categoryName = "Product";
        Category category = Category.builder().name(categoryName).description("Product description").build();
        CategoryEntity categoryEntity = CategoryEntity.fromDomain(category);
        entityManager.persistAndFlush(categoryEntity);

        Category fromDB = categoryRepository.findCategoryById(categoryEntity.getId()).get();
        assertThat(fromDB.getName()).isEqualTo(category.getName());
    }
}
