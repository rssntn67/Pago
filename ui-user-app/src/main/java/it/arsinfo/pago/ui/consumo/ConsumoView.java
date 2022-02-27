package it.arsinfo.pago.ui.consumo;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.EuroConverter;
import it.arsinfo.pago.entity.Consumo;
import it.arsinfo.pago.service.api.PagoServiceBase;
import it.arsinfo.pago.ui.MainLayout;
import it.arsinfo.pago.ui.entity.EntityGridView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value="pago/consumi", layout = MainLayout.class)
@PageTitle("Armatori | Pago App")
public class ConsumoView extends EntityGridView<Consumo> {

    public ConsumoView(@Autowired PagoServiceBase<Consumo> service) {
        super(service);
    }

    @PostConstruct
    public void init() {
        super.init(new Grid<>(Consumo.class));
        configureGrid("utenza.identificativo");
        getGrid().addColumn("fromDate").setHeader("Da");
        getGrid().addColumn("toDate").setHeader("A");
        getGrid().addColumn("consumo").setHeader("Consumo");
        getGrid().addColumn("utenza.modello.tipo.unit").setHeader("Unit");
        getGrid().addColumn(new NumberRenderer<>(Consumo::getImporto, EuroConverter.getEuroCurrency())).setHeader("Importo");

        add(
                getContent(getGrid())
        );

        updateList();
    }
}