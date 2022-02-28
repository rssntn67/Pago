package it.arsinfo.pago.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.EuroConverter;
import it.arsinfo.pago.entity.Ricarica;
import it.arsinfo.pago.service.api.RicaricaService;
import it.arsinfo.pago.ui.entity.EntityView;
import it.arsinfo.pago.ui.utenza.UtenzaForm;
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

    private final RicaricaService service;
    public HomeView(@Autowired RicaricaService service) {
        super(service);
        this.service=service;
    }


    @PostConstruct
    public void init() {
        super.init(new Grid<>(Ricarica.class), new HomeForm(new BeanValidationBinder<>(Ricarica.class), service.findArmatori()));
        configureGrid("dataPagamento");
        getGrid().addColumn(ricarica -> ricarica.getCommittente().getCaption()).setHeader("Committente");
        getGrid().addColumn(new NumberRenderer<>(Ricarica::getImporto, EuroConverter.getEuroCurrency())).setHeader("Importo");

        getForm().addListener(HomeForm.SaveEvent.class, e -> {
            try {
                save(e.getEntity());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        getForm().addListener(UtenzaForm.CloseEvent.class, e -> closeEditor());
        HorizontalLayout toolbar = getToolBar();
        toolbar.add(getAddButton());
        add(toolbar,getContent(getGrid(),getForm()));
        updateList();
        closeEditor();

    }

}
