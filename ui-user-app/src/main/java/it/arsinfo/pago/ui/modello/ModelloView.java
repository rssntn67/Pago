package it.arsinfo.pago.ui.modello;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.EuroConverter;
import it.arsinfo.pago.entity.Modello;
import it.arsinfo.pago.service.api.ModelloService;
import it.arsinfo.pago.ui.MainLayout;
import it.arsinfo.pago.ui.entity.EntityView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value="pago/modelli", layout = MainLayout.class)
@PageTitle("Modelli | Pago App")
public class ModelloView extends EntityView<Modello> {

    public ModelloView(@Autowired ModelloService service) {
        super(service);
    }

    @PostConstruct
    public void init() {
        super.init(new Grid<>(Modello.class), new ModelloForm(new BeanValidationBinder<>(Modello.class)));
        configureGrid( "nome", "tipo.unit");
        getGrid().addColumn(new NumberRenderer<>(Modello::getCostoUnitario, EuroConverter.getEuroCurrency())).setHeader("Costo");
        getForm().addListener(ModelloForm.SaveEvent.class, e -> {
            try {
                save(e.getEntity());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        getForm().addListener(ModelloForm.DeleteEvent.class, e -> {
            try {
                delete(e.getEntity());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        getForm().addListener(ModelloForm.CloseEvent.class, e -> closeEditor());
        HorizontalLayout toolbar = getToolBar();
        toolbar.add(getAddButton());
        add(toolbar,getContent(getGrid(),getForm()));
        updateList();
        closeEditor();

    }
}
