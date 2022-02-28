package it.arsinfo.pago.ui;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import it.arsinfo.pago.EuroConverter;
import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.Ricarica;
import it.arsinfo.pago.ui.entity.EntityForm;

import java.util.List;

public class HomeForm extends EntityForm<Ricarica> {

    public HomeForm(Binder<Ricarica> binder, List<Armatore> committenti) {
        super(binder);
        TextField importo = new TextField("Importo Ricarica");

        ComboBox<Armatore> committente = new ComboBox<>("Armatore");
        committente.setItems(committenti);
        committente.setItemLabelGenerator(Armatore::getCaption);

        DateTimePicker dataPagamento=new DateTimePicker("Data Pagamento");

        binder.forField(importo).withConverter(new EuroConverter("Errore nella conversione in Euro")).bind(Ricarica::getImporto,Ricarica::setImporto);
        binder.forField(committente).bind(Ricarica::getCommittente,Ricarica::setCommittente);
        binder.forField(dataPagamento).bind(Ricarica::getDataPagamento,Ricarica::setDataPagamento);

        add(createButtonsLayout());
        add(committente);
        add(importo);
        add(dataPagamento);

        getSave().addClickListener(event -> {
            if (validate()) {
                fireEvent(new HomeForm.SaveEvent(this,getEntity()));
            }

        });
        getDelete().setEnabled(false);
        getClose().addClickListener(event -> fireEvent(new HomeForm.CloseEvent(this)));
    }

    public static abstract class FormEvent extends ComponentEvent<HomeForm> {
        private final Ricarica t;

        protected FormEvent(HomeForm source, Ricarica t) {
            super(source, false);
            this.t = t;
        }

        public Ricarica getEntity() {
            return t;
        }
    }

    public static class SaveEvent extends HomeForm.FormEvent {
        SaveEvent(HomeForm source, Ricarica t) {
            super(source, t);
        }
    }

    public static class CloseEvent extends HomeForm.FormEvent {
        CloseEvent(HomeForm source) {
            super(source,null);
        }
    }



    @Override
    public boolean validate() {
        Ricarica ricarica = getEntity();
        if (ricarica.getId() == null) {
            return super.validate();
        }
        return false;
    }

}
