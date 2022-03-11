package it.arsinfo.pago.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.converter.EuroConverter;
import it.arsinfo.pago.entity.Ricarica;
import it.arsinfo.pago.service.api.RicaricaService;
import it.arsinfo.pago.ui.entity.EntityView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

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
@Secured("ROLE_User")
public class HomeView extends EntityView<Ricarica,HomeForm,RicaricaService> {

    private final RicaricaService service;
    private final HomeForm form = new HomeForm(new BeanValidationBinder<>(Ricarica.class));
    private final Grid<Ricarica> grid = new Grid<>(Ricarica.class);
    public HomeView(@Autowired RicaricaService service) {
        super(service);
        this.service=service;
    }


    @PostConstruct
    public void init() {
        super.init(grid, form);
        configureGrid("dataPagamento");
        grid.addColumn(ricarica -> ricarica.getCommittente().getCaption()).setHeader("Committente");
        grid.addColumn(new NumberRenderer<>(Ricarica::getImporto, EuroConverter.getEuroCurrency())).setHeader("Importo");

        form.getDelete().setEnabled(false);
        form.load(service.findArmatori());

        HorizontalLayout toolbar = getToolBar();
        toolbar.add(getAddButton());
        add(toolbar,getContent(grid,form));
        closeEditor();

    }

}
