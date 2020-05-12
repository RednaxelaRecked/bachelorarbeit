package com.test;

import javax.servlet.annotation.WebServlet;

import com.test.services.BookService;
import com.test.services.BookServiceImpl;
import com.test.services.Format;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import io.swagger.client.ApiException;
import io.swagger.client.model.Book;

import java.io.IOException;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //final WindowLayout layout = new WindowLayout();
        //setContent(layout);
        CssLayout cssLayout = new CssLayout();
        LoginForm component = new LoginForm();
        component.addLoginListener(e -> {
            boolean isAuthenticated = authenticate(e);
            if (isAuthenticated) {
                WindowLayout layout = new WindowLayout();
                setContent(layout);
            } else {
                ErrorMessage errorMessage = new ErrorMessage() {
                    @Override
                    public ErrorLevel getErrorLevel() {
                        return ErrorLevel.ERROR;
                    }

                    @Override
                    public String getFormattedHtmlMessage() {
                        return "Invalid Credentials";
                    }
                };
                component.setComponentError(errorMessage);
            }
        });

        cssLayout.addComponent(component);
        component.setStyleName("login");
        cssLayout.setSizeFull();
        setContent(cssLayout);


    }

    private boolean authenticate(LoginForm.LoginEvent event) {
        if ("Admin".equals(event.getLoginParameter("username"))
                && "admin".equals(event.getLoginParameter("password"))) {
            return true;
        }
        return false;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
