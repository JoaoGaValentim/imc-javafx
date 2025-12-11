module imc.calculator {
    // Standard JavaFX requirements
    requires transitive javafx.controls; // transitive automatically includes javafx.graphics and javafx.base
    requires javafx.fxml; // Required if you use FXML

    // Optional modules you listed, keep only what you use
    requires javafx.media;
    requires javafx.web;

    // --- CRITICAL CORRECTIONS BELOW ---

    // Opens the main application package to specific modules that need reflection
    // access during runtime (like the LauncherImpl if using the old launch method).
    // The 'transitive javafx.controls' above handles javafx.graphics access for
    // normal app launch.
    opens com.github.imccalculator.app to javafx.fxml;

    // Exports your packages so other parts of your application and JavaFX runtime
    // can access classes
    exports com.github.imccalculator.app;
    exports com.github.imccalculator.app.components;
    exports com.github.imccalculator.app.models;

    // Opens components package to FXML if controllers/components are in there
    opens com.github.imccalculator.app.components to javafx.fxml, javafx.graphics;
}