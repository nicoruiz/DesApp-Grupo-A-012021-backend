package ar.edu.unq.desapp.grupoa.backenddesappapi.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import org.junit.jupiter.api.Test;

public class ArchitectureTests {

    @Test
    public void testAccessFromLayers_checkAllClasses() {
        JavaClasses classes = new ClassFileImporter()
                .importPackages("ar.edu.unq.desapp.grupoa.backenddesappapi");
        
        LayeredArchitecture arch = layeredArchitecture()
                .layer("Security").definedBy("..security..")
                .layer("Controller").definedBy("..controllers..")
                .layer("Service").definedBy("..services..")
                .layer("Persistence").definedBy("..persistence..")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Security")
                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service", "Security");
        arch.check(classes);
    }

}
