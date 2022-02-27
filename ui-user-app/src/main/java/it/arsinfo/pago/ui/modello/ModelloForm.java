package it.arsinfo.pago.ui.modello;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import it.arsinfo.pago.EuroConverter;
import it.arsinfo.pago.entity.Modello;
import it.arsinfo.pago.ui.entity.EntityForm;

import java.util.EnumSet;

public class ModelloForm extends EntityForm<Modello> {

    public ModelloForm(Binder<Modello> binder) {
        super (binder);

        TextField nome = new TextField("Nome");
        TextField costoUnitario = new TextField("Costo Unit");

        ComboBox<Modello.TipoConsumo> tipo = new ComboBox<>("Tipologia",
                EnumSet.allOf(Modello.TipoConsumo.class));
        TextField descrizione = new TextField("Descrizione");

        tipo.setItemLabelGenerator(Modello.TipoConsumo::getUnit);

        binder.forField(nome).bind(Modello::getNome,Modello::setNome);
        binder.forField(costoUnitario).withConverter(new EuroConverter("Errore nella conversione in Euro")).bind(Modello::getCostoUnitario,Modello::setCostoUnitario);
        binder.forField(tipo).bind(Modello::getTipo,Modello::setTipo);
        binder.forField(descrizione).bind(Modello::getDescrizione,Modello::setDescrizione);

        add(createButtonsLayout());
        add(nome);
        add(costoUnitario,tipo);
        add(descrizione);

        getSave().addClickListener(event -> {
            if (validate()) {
                fireEvent(new SaveEvent(this,getEntity()));
            }

        });
        getDelete().setEnabled(false);
        getClose().addClickListener(event -> fireEvent(new CloseEvent(this)));
    }

    public static abstract class FormEvent extends ComponentEvent<ModelloForm> {
        private final Modello t;

        protected FormEvent(ModelloForm source, Modello t) {
            super(source, false);
            this.t = t;
        }

        public Modello getEntity() {
            return t;
        }
    }

    public static class SaveEvent extends FormEvent {
        SaveEvent(ModelloForm source, Modello t) {
            super(source, t);
        }
    }

    public static class DeleteEvent extends FormEvent {
        DeleteEvent(ModelloForm source, Modello t) {
            super(source, t);
        }

    }

    public static class CloseEvent extends FormEvent {
        CloseEvent(ModelloForm source) {
            super(source,null);
        }
    }

}
