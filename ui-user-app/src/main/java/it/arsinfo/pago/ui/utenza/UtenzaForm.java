package it.arsinfo.pago.ui.utenza;

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
    private final ComboBox<Modello> modello = new ComboBox<>("Modello");
    private final ComboBox<Armatore> utente = new ComboBox<>("Utenti");
    public UtenzaForm(Binder<Utenza> binder) {
        super (binder);

        Checkbox active = new Checkbox("Active");

        modello.setItemLabelGenerator(Modello::getNome);

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
    }

    public void load( List<Modello> modelli, List<Armatore> utenti) {
        modello.setItems(modelli);
        utente.setItems(utenti);
    }

    @Override
    public void layout() {
        identificativo.setReadOnly(!isNew());
    }

}
