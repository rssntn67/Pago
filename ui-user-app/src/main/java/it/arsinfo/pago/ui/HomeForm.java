package it.arsinfo.pago.ui;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import it.arsinfo.pago.converter.EuroConverter;
import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.Ricarica;
import it.arsinfo.pago.ui.entity.EntityForm;

import java.util.List;

public class HomeForm extends EntityForm<Ricarica> {

    private final ComboBox<Armatore> committente = new ComboBox<>("Armatore");
    private final TextField importo = new TextField("Importo Ricarica");

    public HomeForm(Binder<Ricarica> binder) {
        super(binder);


        committente.setItemLabelGenerator(Armatore::getCaption);

        DateTimePicker dataPagamento=new DateTimePicker("Data Pagamento");
        dataPagamento.setReadOnly(true);

        binder.forField(importo).withConverter(new EuroConverter("Errore nella conversione in Euro")).bind(Ricarica::getImporto,Ricarica::setImporto);
        binder.forField(committente).bind(Ricarica::getCommittente,Ricarica::setCommittente);
        binder.forField(dataPagamento).bind(Ricarica::getDataPagamento,Ricarica::setDataPagamento);

        add(createButtonsLayout());
        add(committente);
        add(importo);
        add(dataPagamento);
    }

    public void load(List<Armatore> committenti) {
        committente.setItems(committenti);
    }
   @Override
   public void layout() {
        if (isNew()) {
            getSave().setEnabled(true);
            importo.setReadOnly(false);
            committente.setReadOnly(false);
        } else {
            getSave().setEnabled(false);
            importo.setReadOnly(true);
            committente.setReadOnly(true);
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
