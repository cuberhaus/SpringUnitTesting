package edu.uoc.epcsd.productcatalog;

import edu.uoc.epcsd.productcatalog.domain.Product;
import edu.uoc.epcsd.productcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.productcatalog.domain.repository.ProductRepository;
import edu.uoc.epcsd.productcatalog.domain.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

//@ContextConfiguration(classes={ProductCatalogApplication.class})
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ProductCatalogServiceUnitTest {
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;


    @Test
    public void testFindProductById_ValidId_ReturnsProduct() {
        // Arrange
        Long productId = 1L;
        Product expectedProduct = Product.builder().id(productId).build();
        Long actualProductId = 120L;
        when(productRepository.findProductById(actualProductId)).thenReturn(Optional.of(expectedProduct));
        Product product = productService.findProductById(actualProductId).get();

        Assertions.assertThat(product).usingRecursiveComparison().isEqualTo(expectedProduct);

    }

    // Test que comprova que si cridem findProductById amb un id inexistent, obtenim l'excepció pertinent
    @Test
    public void testFindProductById_Invalid_Excepcio() {
        Long notRealId = 120L;
        when(productRepository.findProductById(notRealId)).thenReturn(null);
        Optional<Product> product = productService.findProductById(notRealId);
        Assertions.assertThat(product).isNull();
    }

}
