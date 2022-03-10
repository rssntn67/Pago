package it.arsinfo.pago.ui.consumo;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.converter.EuroConverter;
import it.arsinfo.pago.entity.Consumo;
import it.arsinfo.pago.service.api.PagoService;
import it.arsinfo.pago.ui.MainLayout;
import it.arsinfo.pago.ui.entity.EntityGridView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value="pago/consumi", layout = MainLayout.class)
@PageTitle("Armatori | Pago App")
public class ConsumoView extends EntityGridView<Consumo> {

    private final Grid<Consumo> grid = new Grid<>(Consumo.class);
    public ConsumoView(@Autowired PagoService<Consumo> service) {
        super(service);
    }

    @PostConstruct
    public void init() {
        super.init(grid);
        configureGrid("utenza.identificativo");
        grid.addColumn("fromDate").setHeader("Da");
        grid.addColumn("toDate").setHeader("A");
        grid.addColumn("consumo").setHeader("Consumo");
        grid.addColumn("utenza.modello.tipo.unit").setHeader("Unit");
        grid.addColumn(new NumberRenderer<>(Consumo::getImporto, EuroConverter.getEuroCurrency())).setHeader("Importo");
        grid.addColumn(consumo -> consumo.getArmatore().getCaption()).setHeader("Armatore");

        add(
                getContent(grid)
        );

        updateList();
    }
}