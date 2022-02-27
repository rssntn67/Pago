package it.arsinfo.pago.ui.terms;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.ui.MainLayout;

@Route(value="privacy-policy", layout = MainLayout.class)
@PageTitle("Privacy Policy Pago App")
public class PolicyView extends VerticalLayout {

    public PolicyView() {
        H2 header = new H2("Privacy Policy");
        add(header);
    }
}
