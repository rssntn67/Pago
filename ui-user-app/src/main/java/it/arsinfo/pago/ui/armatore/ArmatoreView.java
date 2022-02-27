package it.arsinfo.pago.ui.armatore;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.PagoEntity;
import it.arsinfo.pago.service.api.ArmatoreService;
import it.arsinfo.pago.ui.MainLayout;
import it.arsinfo.pago.ui.entity.EntityView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value="pago/armatore", layout = MainLayout.class)
@PageTitle("Armatori | Pago App")
public class ArmatoreView extends EntityView<Armatore> {

    public ArmatoreView(@Autowired ArmatoreService service) {
        super(service);
    }

    @PostConstruct
    public void init() {
        super.init(new Grid<>(Armatore.class), new ArmatoreForm(new BeanValidationBinder<>(Armatore.class)));
        configureGrid("imbarcazione", "nome", "cognome");
        getGrid().addColumn(new NumberRenderer<>(Armatore::getCreditoResiduo, PagoEntity.getEuroCurrency())).setHeader("Credito Residuo");
        getForm().addListener(ArmatoreForm.SaveEvent.class, e -> {
            try {
                save(e.getEntity());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        getForm().addListener(ArmatoreForm.DeleteEvent.class, e -> {
            try {
                delete(e.getEntity());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        getForm().addListener(ArmatoreForm.CloseEvent.class, e -> closeEditor());
        HorizontalLayout toolbar = getToolBar();
        toolbar.add(getAddButton());
        add(toolbar,getContent(getGrid(),getForm()));
        updateList();
        closeEditor();

    }
}
