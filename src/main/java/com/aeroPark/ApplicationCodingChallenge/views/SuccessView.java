package com.aeroPark.ApplicationCodingChallenge.views;

import com.aeroPark.ApplicationCodingChallenge.data.Customer;
import com.aeroPark.ApplicationCodingChallenge.service.CustomerService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("success")
//Show registered customers
public class SuccessView extends VerticalLayout {

    private final CustomerService customerService;

    Grid<Customer> grid = new Grid<>(Customer.class);
    TextField filterText = new TextField();

    @Autowired
    public SuccessView(CustomerService customerService) {
        this.customerService = customerService;

        addClassName("success-view");

        configureGrid();
        configureFilterText();

        //This places toolbar and then grid view beneath
        add(
                getToolBar(),
                grid
        );

        updateCustomerList();
    }


    private void updateCustomerList() {
        grid.setItems(customerService.findAllCustomers(filterText.getValue()));
    }

    private Component getToolBar() {
        Button registrationButton = new Button("Return to registration", e -> navigateToRegistration());

        HorizontalLayout toolBar = new HorizontalLayout();
        toolBar.add(registrationButton, filterText);
        toolBar.addClassName("toolbar");
        return toolBar;
    }

    private void configureFilterText() {
        filterText.setPlaceholder("Filter by text...");
        //ValueChangeMode affects calls to DB for loading entries within grid
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateCustomerList());
    }

    private void configureGrid() {
        grid.addClassName("customer-grid");
        grid.setColumns("firstName","lastName","emailAddress");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void navigateToRegistration() {
        UI.getCurrent().navigate("registration");
    }

}
