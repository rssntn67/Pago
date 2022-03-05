package it.arsinfo.pago.ui.utenza;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.Modello;
import it.arsinfo.pago.entity.Utenza;
import it.arsinfo.pago.ui.entity.EntityForm;

import java.util.List;

public class UtenzaForm extends EntityForm<Utenza> {

    private final TextField identificativo = new TextField("Identificativo");
    public UtenzaForm(Binder<Utenza> binder, List<Modello> modelli, List<Armatore> utenti) {
        super (binder);

        Checkbox active = new Checkbox("Active");

        ComboBox<Modello> modello = new ComboBox<>("Modello");
        modello.setItems(modelli);
        modello.setItemLabelGenerator(Modello::getNome);

        ComboBox<Armatore> utente = new ComboBox<>("Utenti");
        utente.setItems(utenti);
        utente.setItemLabelGenerator(Armatore::getImbarcazione);
        utente.setRequired(false);

        TextField descrizione = new TextField("Descrizione");

        binder.forField(identificativo).asRequired().bind(Utenza::getIdentificativo,Utenza::setIdentificativo);
        binder.forField(active).bind(Utenza::isActive,Utenza::setActive);
        binder.forField(modello).asRequired().bind(Utenza::getModello,Utenza::setModello);
        binder.forField(descrizione).bind(Utenza::getDescrizione,Utenza::setDescrizione);
        binder.forField(utente).bind(Utenza::getUtente,Utenza::setUtente);

        add(createButtonsLayout());
        add(identificativo,modello,active);
        add(utente);
        add(descrizione);
        
        getSave().addClickListener(event -> {
            if (validate()) {
                fireEvent(new SaveEvent(this,getEntity()));
            }

        });
        getDelete().addClickListener(event -> {
            if (validate()) {
                fireEvent(new DeleteEvent(this,getEntity()));
            }

        });
        getClose().addClickListener(event -> fireEvent(new CloseEvent(this)));
    }

    @Override
    public void layout() {
        identificativo.setReadOnly(!isNew());
    }

    public static abstract class FormEvent extends ComponentEvent<UtenzaForm> {
        private final Utenza t;

        protected FormEvent(UtenzaForm source, Utenza t) {
            super(source, false);
            this.t = t;
        }

        public Utenza getEntity() {
            return t;
        }
    }

    public static class SaveEvent extends FormEvent {
        SaveEvent(UtenzaForm source, Utenza t) {
            super(source, t);
        }
    }

    public static class DeleteEvent extends FormEvent {
        DeleteEvent(UtenzaForm source, Utenza t) {
            super(source, t);
        }

    }

    public static class CloseEvent extends FormEvent {
        CloseEvent(UtenzaForm source) {
            super(source,null);
        }
    }

}
