package com.aeroPark.ApplicationCodingChallenge.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("success")
public class SuccessView extends VerticalLayout {

    public SuccessView() {

        // Create title and button
        H1 successfulRegisterTitle = new H1("Successful registration");
        Button registrationButton = new Button("Return to registration", e -> navigateToRegistration());

        add(successfulRegisterTitle, registrationButton);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

    }


    private void navigateToRegistration() {
        UI.getCurrent().navigate("registration");
    }

}
