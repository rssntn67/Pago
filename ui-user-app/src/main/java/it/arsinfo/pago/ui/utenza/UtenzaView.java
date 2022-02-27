package it.arsinfo.pago.ui.utenza;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.entity.Utenza;
import it.arsinfo.pago.service.api.UtenzaService;
import it.arsinfo.pago.ui.MainLayout;
import it.arsinfo.pago.ui.entity.EntityView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value="pago/utenze", layout = MainLayout.class)
@PageTitle("Utenze | Pago App")
public class UtenzaView extends EntityView<Utenza> {

    final private UtenzaService service;
    public UtenzaView(@Autowired UtenzaService service) {
        super(service);
        this.service=service;
    }

    @PostConstruct
    public void init() {
        super.init(new Grid<>(Utenza.class), new UtenzaForm(new BeanValidationBinder<>(Utenza.class),service.findModelli(),service.findUtenza()));
        configureGrid( "identificativo", "modello.tipo.unit","active");
        getGrid().addColumn(utenza -> {
            if (utenza.getUtente() == null) return "no";
            else return utenza.getUtente().getImbarcazione();
        }).setHeader("Utente");
        getForm().addListener(UtenzaForm.SaveEvent.class, e -> {
            try {
                save(e.getEntity());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        getForm().addListener(UtenzaForm.DeleteEvent.class, e -> {
            try {
                delete(e.getEntity());
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
