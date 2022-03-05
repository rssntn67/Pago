package it.arsinfo.pago.ui.entity;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import it.arsinfo.pago.entity.Pago;
import it.arsinfo.pago.service.api.PagoService;

import java.util.List;

public abstract class EntityGridView<T extends Pago> extends VerticalLayout {
    private Grid<T> grid;

    private final PagoService<T> service;

    public EntityGridView(PagoService<T> service) {
        this.service=service;
    }

    public void init(Grid<T> grid) {
        this.grid=grid;
        addClassName("gc-view");
        setSizeFull();
    }

    public HorizontalLayout getToolBar() {
        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public Div getContent(Component...components) {
        Div content = new Div(components);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }


    public void configureGrid(String...columns) {
        grid.addClassName("grid");
        grid.setColumns(columns);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    public void setColumnCaption(String column,String header) {
        grid.addColumn(column).setHeader(header);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    public void updateList() {
        grid.setItems(filter());
    }

    public List<T> filter() {
        return service.findAll();
    }

    public Grid<T> getGrid() {
        return grid;
    }

}
