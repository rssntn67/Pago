package it.arsinfo.pago.ui.entity;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import it.arsinfo.pago.entity.Pago;
import it.arsinfo.pago.service.api.PagoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class EntityView<T extends Pago, K extends EntityForm<T>, S extends PagoService<T>> extends EntityGridView<T> {
    private static final Logger log = LoggerFactory.getLogger(EntityView.class);
    private final S service;
    private K form;
    private Grid<T> grid;

    public EntityView(S service) {
        super(service);
        this.service=service;
    }

    public void init(Grid<T> grid, K form) {
        super.init(grid);
        this.grid=grid;
        this.form=form;
        form.addClassName("form");
        form.addListener(K.SaveEvent.class, e -> {
            try {
                log.info("save: {}", form.getEntity());
                save(form.getEntity());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        form.addListener(K.DeleteEvent.class, e -> {
            try {
                log.info("delete: {}", form.getEntity());
                delete(form.getEntity());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        form.addListener(K.CloseEvent.class, e -> closeEditor());

    }

    public Div getContent(Component...components) {
        Div content = new Div(components);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    public void save(T entity) throws Exception {
        service.save(entity);
        closeEditor();
    }

    public void delete(T entity) throws Exception {
        service.delete(entity);
        closeEditor();
    }

    public void edit(T entity) {
        if (entity == null) {
            closeEditor();
        } else {
            form.setEntity(entity);
            form.layout();
            form.setVisible(true);
            grid.setVisible(false);
            addClassName("editing");
        }
    }

    public void closeEditor() {
        form.setEntity(null);
        form.setVisible(false);
        removeClassName("editing");
        grid.setVisible(true);
        updateList();
    }

    public void configureGrid(String...columns) {
        super.configureGrid(columns);
        grid.asSingleSelect().addValueChangeListener(event ->
                edit(event.getValue()));
    }

    public Button getAddButton() {
        Button addButton = new Button("Add");
        addButton.addClickListener(click -> add());
        return addButton;
    }

    void add() {
        grid.asSingleSelect().clear();
        edit(service.add());
    }
   }
