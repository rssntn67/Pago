package it.arsinfo.pago.ui.armatore;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import it.arsinfo.pago.EuroConverter;
import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.ui.entity.EntityForm;

import java.util.EnumSet;

public class ArmatoreForm extends EntityForm<Armatore> {

    public ArmatoreForm(Binder<Armatore> binder) {
        super (binder);

        TextField nome = new TextField("Nome");
        TextField cognome = new TextField("Cognome");
        TextField imbarcazione = new TextField("Imbarcazione");
        TextField creditoResiduo = new TextField("Credito");
        creditoResiduo.setReadOnly(true);

        TextField indirizzo = new TextField("Indirizzo");
        TextField citta = new TextField("Citta");
        ComboBox<Armatore.Provincia> provincia = new ComboBox<>("Provincia",
                EnumSet.allOf(Armatore.Provincia.class));
        TextField cap = new TextField("Cap");

        ComboBox<Armatore.Paese> paese = new ComboBox<>("Paese",
                EnumSet.allOf(Armatore.Paese.class));

        TextField email = new TextField("Email");
        TextField telefono = new TextField("Telefono");
        TextField cellulare = new TextField("Cellulare");
        TextField codfis = new TextField("Cod. Fis.");
        TextField piva = new TextField("P.Iva");

        // Configure and style components
        provincia.setItemLabelGenerator(Armatore.Provincia::getNome);
        paese.setItemLabelGenerator(Armatore.Paese::getNome);

        binder.forField(nome).bind(Armatore::getNome,Armatore::setNome);
        binder.forField(cognome).bind(Armatore::getCognome,Armatore::setCognome);
        binder.forField(imbarcazione).asRequired().bind(Armatore::getImbarcazione,Armatore::setImbarcazione);

        binder.forField(creditoResiduo).withConverter(new EuroConverter("Errore nella conversione in Euro")).bind(Armatore::getCreditoResiduo,Armatore::setCreditoResiduo);

        binder.forField(indirizzo).bind(Armatore::getIndirizzo,Armatore::setIndirizzo);
        binder.forField(citta).bind(Armatore::getCitta,Armatore::setCitta);
        binder.forField(provincia).asRequired().bind(Armatore::getProvincia,Armatore::setProvincia);
        binder.forField(cap).bind(Armatore::getCap,Armatore::setCap);
        binder.forField(paese).asRequired().bind(Armatore::getPaese,Armatore::setPaese);

        binder.forField(email)
                .withValidator(new EmailValidator("Immettere un indizzo di mail valido"))
                .bind(Armatore::getEmail,Armatore::setEmail);
        binder.forField(telefono).bind(Armatore::getTelefono,Armatore::setTelefono);
        binder.forField(cellulare).bind(Armatore::getCellulare,Armatore::setCellulare);
        binder.forField(codfis).bind(Armatore::getCodfis,Armatore::setCodfis);
        binder.forField(piva).bind(Armatore::getPiva,Armatore::setPiva);

        creditoResiduo.setReadOnly(true);
        add(createButtonsLayout());
        add(nome, cognome,imbarcazione);
        add(creditoResiduo);

        add(indirizzo);
        add(citta, provincia, cap);
        add(paese);
        add(email,telefono,cellulare,codfis,piva);

    }

}
