package it.arsinfo.pago.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import it.arsinfo.pago.ui.armatore.ArmatoreView;
import it.arsinfo.pago.ui.consumo.ConsumoView;
import it.arsinfo.pago.ui.modello.ModelloView;
import it.arsinfo.pago.ui.terms.PolicyView;
import it.arsinfo.pago.ui.terms.TermsView;
import it.arsinfo.pago.ui.utenza.UtenzaView;
import it.arsinfo.pago.ui.user.PagoUserView;

@CssImport("./styles/shared-styles.css")
@PWA(name = "Pago, Sistema di Ricarica", shortName = "Pago")
public class MainLayout extends AppLayout implements BeforeEnterObserver {

    private boolean first=true;

    private final RouterLink homeLink = new RouterLink("Ricarica",HomeView.class);
    private final RouterLink termsLink = new RouterLink("Termini e Condizioni di Vendita", TermsView.class);
    private final RouterLink privacyLink = new RouterLink("Privacy Policy", PolicyView.class);

    private final RouterLink consumiLink = new RouterLink("Consumi", ConsumoView.class);
    private final RouterLink utenzeLink = new RouterLink("Utenze", UtenzaView.class);
    private final RouterLink modelliLink = new RouterLink("Modelli", ModelloView.class);
    private final RouterLink armatoriLink = new RouterLink("Armatori", ArmatoreView.class);
    private final RouterLink usersLink = new RouterLink("Users", PagoUserView.class);

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        VerticalLayout menu = new VerticalLayout();
        if (first) {
            createHeader();
            menu.add(homeLink);

            menu.add(consumiLink);
            menu.add(utenzeLink);
            menu.add(modelliLink);
            menu.add(armatoriLink);
            menu.add(usersLink);

            menu.add(termsLink);
            menu.add(privacyLink);

            homeLink.setHighlightCondition(HighlightConditions.sameLocation());

            consumiLink.setHighlightCondition(HighlightConditions.sameLocation());
            utenzeLink.setHighlightCondition(HighlightConditions.sameLocation());
            modelliLink.setHighlightCondition(HighlightConditions.sameLocation());
            armatoriLink.setHighlightCondition(HighlightConditions.sameLocation());
            usersLink.setHighlightCondition(HighlightConditions.sameLocation());

            termsLink.setHighlightCondition(HighlightConditions.sameLocation());
            privacyLink.setHighlightCondition(HighlightConditions.sameLocation());

            addToDrawer(menu);
            first=false;
        }

    }

    private void createHeader() {
        H1 logo = new H1("Pago App");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("50%");
        header.addClassName("header");

        Div div = new Div();
        div.setText("Benvenuto");

        // Spring maps the 'logout' url so we should ignore it
        // simple link to the logout endpoint provided by Spring Security
        Element logoutLink = ElementFactory.createAnchor("logout", "Logout");
        div.getElement().appendChild(logoutLink);
        header.add(div);
        addToNavbar(header);

    }

}