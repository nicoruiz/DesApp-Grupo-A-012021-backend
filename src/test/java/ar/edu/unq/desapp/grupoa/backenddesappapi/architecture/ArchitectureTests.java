package ar.edu.unq.desapp.grupoa.backenddesappapi.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.equivalentTo;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.are;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import javax.persistence.Entity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.stream.Collectors;

public class ArchitectureTests {

    private JavaClasses classes;

    @BeforeEach
    public void init() {
        ImportOption ignoreTests = (Location location) -> !location.contains("/test/");
        classes = new ClassFileImporter().withImportOption(ignoreTests)
                .importPackages("ar.edu.unq.desapp.grupoa.backenddesappapi");
    }

    @Test
    public void testAccessFromLayers_checkAllClasses() {
        LayeredArchitecture arch = layeredArchitecture()
                .layer("Security").definedBy("..security..")
                .layer("Controller").definedBy("..controllers..")
                .layer("Service").definedBy("..services..")
                .layer("Persistence").definedBy("..persistence..")
                .layer("Configuration").definedBy("..config..")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Security", "Configuration")
                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service", "Security");

        arch.check(classes);
    }

    @Test
    public void testModelHasEntityAnnotation_checkAllClasses() {
        ArchRule rule = classes().that().resideInAPackage("..model..").and().resideOutsideOfPackages("..enums..", "..exceptions..")
                .should().beAnnotatedWith(Entity.class);

        rule.check(classes);
    }

    @Test
    public void testControllersNameEndingWithController_checkAllClasses() {
        ArchRule rule = classes().that().resideInAPackage("..controllers..")
                .should().haveSimpleNameEndingWith("Controller");

        rule.check(classes);
    }

    @Test
    public void testServicesNameEndingWithService_checkAllClasses() {
        ArchRule rule = classes().that().resideInAPackage("..services..")
                .should().haveSimpleNameEndingWith("Service");

        rule.check(classes);
    }

    @Test
    public void testPersistenceNameEndingWithRepository_checkAllClasses() {
        ArchRule rule = classes().that().resideInAPackage("..persistence..").and().resideOutsideOfPackage("..specifications..")
                .should().haveSimpleNameEndingWith("Repository");

        rule.check(classes);
    }

    @Test
    public void testDtoNameEndingWithDto_checkAllClasses() {
        ArchRule rule = classes().that().resideInAPackage("..dtos..")
                .should().haveSimpleNameEndingWith("Dto");

        rule.check(classes);
    }

}
