package it.arsinfo.pago.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.PostConstruct;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */

@Route(value="", layout = MainLayout.class)
@PageTitle("Home | ADP Portale")
public class HomeView extends VerticalLayout  {

    private final Div content = new Div();


    @PostConstruct
    public void init() {
        content.setText("Pago App");
        add(content);
    }

}
