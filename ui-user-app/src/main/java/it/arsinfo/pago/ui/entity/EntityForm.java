package it.arsinfo.pago.ui.entity;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import it.arsinfo.pago.entity.Pago;

public abstract class EntityForm<T extends Pago>  extends FormLayout {
    private final Binder<T> binder;
    private T entity;

   private final Button save = new Button("Save");
   private final Button delete = new Button("Delete");
   private final Button close = new Button("Indietro");


    public EntityForm(Binder<T> binder) {
        this.binder=binder;
        save.addClickListener(event -> {
            if (validate()) {
                fireEvent(new EntityForm.SaveEvent(this));
            }

        });
        delete.addClickListener(event -> fireEvent(new EntityForm.DeleteEvent(this)));
        close.addClickListener(event -> fireEvent(new EntityForm.CloseEvent(this)));

    }

    public HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    public boolean validate() {
        try {
            binder.writeBean(entity);
            return true;
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void layout() {

    }

    public void setEntity(T entity) {
        this.entity = entity;
        binder.readBean(entity);
    }

    public T getEntity() {
        return entity;
    }
    public Button getSave() {
        return save;
    }

    public Button getDelete() {
        return delete;
    }

    public Button getClose() {
        return close;
    }

    public Binder<T> getBinder() {
        return binder;
    }
    public <K extends ComponentEvent<?>> Registration addListener(Class<K> eventType,
                                                                  ComponentEventListener<K> listener) {
        return getEventBus().addListener(eventType, listener);
    }

    public void setReadOnly(boolean readonly) {
        getSave().setEnabled(!readonly);
        getDelete().setEnabled(!readonly);
    }

    public boolean isNew() {
        return entity.getId() == null;
    }

    public static class SaveEvent extends ComponentEvent<EntityForm> {
        protected SaveEvent(EntityForm source) {
            super(source, false);
        }
    }

    public static class DeleteEvent extends ComponentEvent<EntityForm> {
        protected DeleteEvent(EntityForm source) {
            super(source, false);
        }
    }

    public static class CloseEvent extends ComponentEvent<EntityForm> {
        protected CloseEvent(EntityForm source) {
            super(source, false);
        }
    }



}
