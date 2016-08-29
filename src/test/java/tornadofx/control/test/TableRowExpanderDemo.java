package tornadofx.control.test;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tornadofx.control.DatePickerTableCell;
import tornadofx.control.Fieldset;
import tornadofx.control.Form;
import tornadofx.control.TableRowExpander;

import java.time.LocalDate;

@SuppressWarnings("unchecked")
public class TableRowExpanderDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TableView<Customer> tableView = new TableView<>(FXCollections.observableArrayList(Customer.createSample()));
        tableView.setEditable(true);

        TableColumn<Customer, String> username = new TableColumn<>("Username");
        username.setCellValueFactory(param -> param.getValue().usernameProperty());

        TableColumn<Customer, LocalDate> registered = new TableColumn<>("Registered");
        registered.setCellValueFactory(param -> param.getValue().registeredProperty());
        registered.setCellFactory(DatePickerTableCell.forTableColumn());
        registered.setPrefWidth(150);

        tableView.getColumns().addAll(username, registered);

        new TableRowExpander<>(tableView, (parent, customer) -> {
            Form form = new Form();
            Fieldset fieldset = form.fieldset("Edit customer");
            fieldset.field("Username", new TextField(customer.getUsername()));
            return form;
        });
        stage.setScene(new Scene(tableView, 800, 600));
        stage.show();
    }
}