package com.aeroPark.ApplicationCodingChallenge.views;

import com.aeroPark.ApplicationCodingChallenge.data.Customer;
import com.aeroPark.ApplicationCodingChallenge.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;


@Route("registration")
public class RegistrationView extends FormLayout {

    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    ComboBox<String> title = new ComboBox<>("Title");
    TextField emailAddress = new TextField("Email");
    TextField addressLine1 = new TextField("Address Line 1");
    TextField addressLine2 = new TextField("Address Line 2");
    TextField city = new TextField("City");
    TextField postcode = new TextField("Post Code");
    TextField phoneNumber = new TextField("Phone Number");

    Button validateAndSave = new Button("Validate and Save");

    //Should bind the textfields to Customer class
    Binder<Customer> binder = new BeanValidationBinder<>(Customer.class);

    private final CustomerService customerService;

    // Predefined list of titles
    private final List<String> titleOptions = Arrays.asList("Mr.", "Mrs.", "Miss", "Ms.", "Dr.");

    @Autowired
    public RegistrationView(CustomerService customerService) {
        this.customerService = customerService;
        addClassName("registration-form");
        binder.bindInstanceFields(this);

        //Setting up title combobox
        title.setItems(titleOptions);
        title.setPlaceholder("Select title");
        title.setAllowCustomValue(true); //users can type in their own title

        add(firstName,
                lastName,
                title,
                emailAddress,
                addressLine1,
                addressLine2,
                city,
                postcode,
                phoneNumber,
                validateAndSave);

        validateAndSave.addClickListener(event -> validateAndRegister());
    }

    private void validateAndRegister() {
        Customer customer = new Customer();
        if(isValidCustomerBean(customer)) {
            if(isValidCustomerOnServiceLayer(customer)) {
                saveCustomer(customer);
                routeToSuccessPage();
            }
        }
    }

    //This will attempt to bind the entries within the text fields to a customer bean
    //If the entries cannot be bound to a customer object - an error message will appear
    //and the incorrect text fields will be highlighted with messages explaining the issue
    //Return true if bound successfully - false otherwise.
    private boolean isValidCustomerBean(Customer customer) {
        try {
            binder.writeBean(customer);
            return true;
        } catch (ValidationException e) {
            Notification.show("Some fields were entered incorrectly", 3000, Notification.Position.MIDDLE);
            return false;
        }
    }

    //This will validate the customer on the service layer - can add more checks here if there needs to be extra uniqueness etc.
    //For now just checks whether email address is unique (case-insensitive)
    private boolean isValidCustomerOnServiceLayer(Customer customer) {
        if(!customerService.isEmailUnique(customer.getEmailAddress())) {
            Notification.show("Email address is already in use", 5000, Notification.Position.MIDDLE);
            return false;
        }
        return true;
    }

    private void saveCustomer(Customer customer) {
        customerService.saveCustomer(customer);
        Notification.show("Customer saved successfully", 1000, Notification.Position.MIDDLE);
    }

    private void routeToSuccessPage() {
        getUI().ifPresent(ui -> ui.navigate("success"));
    }

}
