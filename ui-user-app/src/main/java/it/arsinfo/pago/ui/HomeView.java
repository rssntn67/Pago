package it.arsinfo.pago.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.entity.Ricarica;
import it.arsinfo.pago.service.api.PagoService;
import it.arsinfo.pago.ui.entity.EntityView;
import org.springframework.beans.factory.annotation.Autowired;

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
@PageTitle("Ricarica | ADP Portale")
public class HomeView extends EntityView<Ricarica> {

    private final Div content = new Div();

    public HomeView(@Autowired PagoService<Ricarica> service) {
        super(service);
    }


    @PostConstruct
    public void init() {
        content.setText("Pago App");
        add(content);
    }

}
