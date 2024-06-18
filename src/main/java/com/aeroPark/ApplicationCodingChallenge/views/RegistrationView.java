package com.aeroPark.ApplicationCodingChallenge.views;

import com.aeroPark.ApplicationCodingChallenge.data.Customer;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;


@Route("registration")
@CssImport("./styles/styles.css")
public class RegistrationView extends FormLayout {

    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField title = new TextField("Title");
    TextField emailAddress = new TextField("Email");
    TextField addressLine1 = new TextField("Address Line 1");
    TextField addressLine2 = new TextField("Address Line 2");
    TextField city = new TextField("City");
    TextField postcode = new TextField("Post Code");
    TextField phoneNumber = new TextField("Phone Number");

    Button validateAndSave = new Button("Validate and Save");

    //Should bind the textfields to Customer class
    Binder<Customer> binder = new BeanValidationBinder<>(Customer.class);

    public RegistrationView() {
        addClassName("registration-form");
        binder.bindInstanceFields(this);

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

    //This will attempt to bind the entries within the text fields to a customer bean
    //If the entries cannot be bound to a customer object - an error message will appear
    //and the incorrect text fields will be highlighted with messages explaining the issue
    private void validateAndRegister() {
        Customer customer = new Customer();
        try {
            binder.writeBean(customer);
            Notification.show("Customer saved successfully", 3000, Notification.Position.MIDDLE);
        } catch (ValidationException e) {
            Notification.show("Some fields were entered incorrectly", 3000, Notification.Position.MIDDLE);
        }
    }
}
