package it.arsinfo.pago.ui.armatore;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.converter.EuroConverter;
import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.service.api.ArmatoreService;
import it.arsinfo.pago.ui.MainLayout;
import it.arsinfo.pago.ui.entity.EntityView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value="pago/armatori", layout = MainLayout.class)
@PageTitle("Armatori | Pago App")
public class ArmatoreView extends EntityView<Armatore,ArmatoreForm,ArmatoreService> {

    private final ArmatoreForm form = new ArmatoreForm(new BeanValidationBinder<>(Armatore.class));
    private final Grid<Armatore> grid = new Grid<>(Armatore.class);

    public ArmatoreView(@Autowired ArmatoreService service) {
        super(service);
    }

    @PostConstruct
    public void init() {
        super.init(grid, form);
        configureGrid("imbarcazione", "nome", "cognome");
        grid.addColumn(new NumberRenderer<>(Armatore::getCreditoResiduo, EuroConverter.getEuroCurrency())).setHeader("Credito Residuo");
        HorizontalLayout toolbar = getToolBar();
        toolbar.add(getAddButton());
        add(toolbar,getContent(grid,form));
        closeEditor();
    }
}
