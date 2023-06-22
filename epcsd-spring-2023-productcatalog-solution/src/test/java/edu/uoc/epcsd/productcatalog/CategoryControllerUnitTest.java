package edu.uoc.epcsd.productcatalog;

import edu.uoc.epcsd.productcatalog.application.rest.CategoryRESTController;
import edu.uoc.epcsd.productcatalog.domain.Category;
import edu.uoc.epcsd.productcatalog.domain.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes={ProductCatalogApplication2.class})
@ExtendWith(SpringExtension.class)
//@SpringBootTest
@WebMvcTest(value= CategoryRESTController.class)
public class CategoryControllerUnitTest {

    @Autowired
    private MockMvc mockmvc;

    private static final String PRODUCT1 = "Product1";
    private static final String PRODUCT2 = "Product2";
    private static final String REST_CATEGORIES_PATH = "/categories";

    @MockBean
    private CategoryService categoryService;

    @Test
    void find_products_should_return_all() throws Exception {
        Category category1 = Category.builder().name(PRODUCT1).build();
        Category category2 = Category.builder().name(PRODUCT2).build();

        List<Category> categories = Arrays.asList(category1,category2);
        Mockito.when(categoryService.findAllCategories()).thenReturn(categories);

        mockmvc.perform(
                MockMvcRequestBuilders.get(REST_CATEGORIES_PATH).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(PRODUCT1))
                .andExpect(jsonPath("$[1].name").value(PRODUCT2))
        ;

    }
}
