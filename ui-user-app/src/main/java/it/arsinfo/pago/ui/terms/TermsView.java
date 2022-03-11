package it.arsinfo.pago.ui.terms;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.ui.MainLayout;
import org.springframework.security.access.annotation.Secured;

@Route(value="pago/termini-econdizioni-vendita", layout = MainLayout.class)
@Secured("ROLE_User")
@PageTitle("Termini e Condizioni Pago App")
public class TermsView extends VerticalLayout {
    public TermsView() {
        H2 header = new H2("Termini e condizioni di utilizzo");
        add(header);


    }
}
