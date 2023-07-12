module com.example.customprintsequencer {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.lorenzomar4.customprintsequencer to javafx.fxml;
    exports com.lorenzomar4.customprintsequencer;
    exports com.lorenzomar4.customprintsequencer.model;
    exports com.lorenzomar4.customprintsequencer.model.exception;
    exports com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies;
}