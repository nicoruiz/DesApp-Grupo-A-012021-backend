package ar.edu.unq.desapp.grupoa.backenddesappapi.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

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
                .layer("Messaging").definedBy("..messaging..")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Security", "Configuration", "Messaging")
                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service", "Security", "Messaging");

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
