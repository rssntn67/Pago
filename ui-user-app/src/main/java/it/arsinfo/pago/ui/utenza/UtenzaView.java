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
import org.springframework.security.access.annotation.Secured;

import javax.annotation.PostConstruct;

@Route(value="pago/utenze", layout = MainLayout.class)
@Secured("ROLE_User")
@PageTitle("Utenze | Pago App")
public class UtenzaView extends EntityView<Utenza,UtenzaForm,UtenzaService> {

    private final UtenzaService service;
    private final UtenzaForm form= new UtenzaForm(new BeanValidationBinder<>(Utenza.class));
    private final Grid<Utenza> grid = new Grid<>(Utenza.class);
    public UtenzaView(@Autowired UtenzaService service) {
        super(service);
        this.service=service;
    }

    @PostConstruct
    public void init() {
        super.init(grid,form);
        configureGrid( "identificativo", "modello.tipo.unit","active");
        grid.addColumn(utenza -> {
            if (utenza.getUtente() == null) return "nessuna utenza";
            else return utenza.getUtente().getCaption();
        }).setHeader("Utente");
        HorizontalLayout toolbar = getToolBar();
        toolbar.add(getAddButton());
        form.load(service.findModelli(),service.findUtenza());
        add(toolbar,getContent(grid,form));
        closeEditor();
    }
}
