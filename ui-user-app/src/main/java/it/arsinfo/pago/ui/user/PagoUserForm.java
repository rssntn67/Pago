package it.arsinfo.pago.ui.user;


import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;
import it.arsinfo.pago.SecurityUtils;
import it.arsinfo.pago.entity.PagoUser;
import it.arsinfo.pago.entity.PagoUser.Role;
import it.arsinfo.pago.ui.entity.EntityForm;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.EnumSet;


public class PagoUserForm extends EntityForm<PagoUser> {

    private final TextField username = new TextField("username");
    private final PasswordField password = new PasswordField("password");

//    public PagoUserForm(Binder<PagoUser> binder, PasswordEncoder passwordEncoder) {
    public PagoUserForm(Binder<PagoUser> binder) {
    super(binder);
        ComboBox<PagoUser.Provider> provider = new ComboBox<>("Provider", EnumSet.allOf(PagoUser.Provider.class));
        provider.setReadOnly(true);

        getBinder().forField(username).asRequired().bind("username");
        ComboBox<Role> role = new ComboBox<>("Selezionare il ruolo", EnumSet.allOf(Role.class));
        getBinder().forField(role).asRequired().bind("role");
        getBinder().forField(provider).asRequired().bind("provider");

        Validator<String> passwordValidator = new Validator<String>() {

            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public ValidationResult apply(String value, ValueContext context) {
                if (!isNew() && value.isEmpty()) {
                    return ValidationResult.ok();
                }
                if (SecurityUtils.verify(password.getValue())) {
                    return ValidationResult.ok();
                }
                return ValidationResult.error("la password deve avere minimo 8 caratteri, contenere almeno un numero, almeno un carattere minuscolo, almeno un carattere maiuscolo e almeno nun carattere speciale");
            }
        };
        getBinder().forField(password).withValidator(passwordValidator)
                .bind(
                        bean -> "",
                        (bean, value) -> {
                            if (!value.isEmpty()) {
//                                bean.setPasswordHash(passwordEncoder.encode(value));
                                bean.setPasswordHash(value);
                            }
                        });

        getSave().addClickListener(event -> {
            if (validate()) {
                fireEvent(new PagoUserForm.SaveEvent(this, getEntity()));
            }

        });

        add(createButtonsLayout());
        add(username, role,provider);
        PasswordField confirm = new PasswordField("confirm");
        add(password, confirm);
        getDelete().addClickListener(event -> fireEvent(new DeleteEvent(this, getEntity())));
        getClose().addClickListener(event -> fireEvent(new PagoUserForm.CloseEvent(this)));

    }

    @Override
    public void layout() {
        username.setReadOnly(!isNew());
    }

    public static abstract class FormEvent extends ComponentEvent<PagoUserForm> {
        private final PagoUser t;

        protected FormEvent(PagoUserForm source, PagoUser t) {
            super(source, false);
            this.t = t;
        }

        public PagoUser getEntity() {
            return t;
        }
    }

    public static class SaveEvent extends PagoUserForm.FormEvent {
        SaveEvent(PagoUserForm source, PagoUser t) {
            super(source, t);
        }
    }

    public static class DeleteEvent extends PagoUserForm.FormEvent {
        DeleteEvent(PagoUserForm source, PagoUser t) {
            super(source, t);
        }

    }

    public static class CloseEvent extends PagoUserForm.FormEvent {
        CloseEvent(PagoUserForm source) {
            super(source,null);
        }
    }

}