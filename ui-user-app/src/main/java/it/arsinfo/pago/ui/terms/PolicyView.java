package it.arsinfo.pago.ui.terms;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.ui.MainLayout;
import org.springframework.security.access.annotation.Secured;

@Route(value="pago/privacy-policy", layout = MainLayout.class)
@Secured("ROLE_User")
@PageTitle("Privacy Policy Pago App")
public class PolicyView extends VerticalLayout {

    public PolicyView() {
        H2 header = new H2("Privacy Policy");
        add(header);
    }
}
