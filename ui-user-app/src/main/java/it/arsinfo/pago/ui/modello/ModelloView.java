package it.arsinfo.pago.ui.modello;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.converter.EuroConverter;
import it.arsinfo.pago.entity.Modello;
import it.arsinfo.pago.service.api.ModelloService;
import it.arsinfo.pago.ui.MainLayout;
import it.arsinfo.pago.ui.entity.EntityView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import javax.annotation.PostConstruct;

@Route(value="pago/modelli", layout = MainLayout.class)
@Secured("ROLE_User")
@PageTitle("Modelli | Pago App")
public class ModelloView extends EntityView<Modello,ModelloForm,ModelloService> {

    private final ModelloForm form =new ModelloForm(new BeanValidationBinder<>(Modello.class));
    private final Grid<Modello> grid = new Grid<>(Modello.class);
    public ModelloView(@Autowired ModelloService service) {
        super(service);
    }

    @PostConstruct
    public void init() {
        super.init(grid, form);
        configureGrid( "nome", "tipo.unit");
        grid.addColumn(new NumberRenderer<>(Modello::getCostoUnitario, EuroConverter.getEuroCurrency())).setHeader("Costo");
        HorizontalLayout toolbar = getToolBar();
        toolbar.add(getAddButton());
        add(toolbar,getContent(grid,form));
        closeEditor();
    }
}
