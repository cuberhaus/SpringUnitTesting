package edu.uoc.epcsd.productcatalog;

import com.tngtech.archunit.core.importer.ImportOption; import com.tngtech.archunit.junit.AnalyzeClasses; import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import org.apache.commons.lang3.arch.Processor;
import org.springframework.stereotype.Service;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*; import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages="edu.uoc.epcsd.productcatalog", importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class ProductCatalogArchitectureTest {
    @ArchTest
    static final ArchRule ServiceWithAnnotation = classes()
                .that().resideInAnyPackage("..domain.service..")
                .and().areAnnotatedWith(Service.class)
                .should().haveSimpleNameEndingWith("ServiceImpl");

    @ArchTest
    static final ArchRule HexagonalArchitectureRespected = onionArchitecture()
            .domainModels("..domain..")
            .domainServices("..domain.service..")
            .applicationServices("..application..")
            .adapter("persistence", "..infrastructure.repository..")
            .adapter("rest", "..application.rest..");
}
