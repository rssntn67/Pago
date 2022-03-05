package it.arsinfo.pago.ui.user;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.arsinfo.pago.entity.PagoUser;
import it.arsinfo.pago.service.api.PagoUserService;
import it.arsinfo.pago.ui.MainLayout;
import it.arsinfo.pago.ui.entity.EntityView;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Route(value="pago/user", layout = MainLayout.class)
@PageTitle("User | Pago App")
public class PagoUserView extends EntityView<PagoUser> {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public PagoUserView(@Autowired PagoUserService service) {
        super(service);
    }



    @PostConstruct
    public void init() {
//        super.init(new Grid<>(PagoUser.class), new PagoUserForm(new BeanValidationBinder<>(PagoUser.class),passwordEncoder));
        super.init(new Grid<>(PagoUser.class), new PagoUserForm(new BeanValidationBinder<>(PagoUser.class)));
        configureGrid( "username", "role","provider","data");

        getForm().addListener(PagoUserForm.SaveEvent.class, e -> {
            try {
                save(e.getEntity());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        getForm().addListener(PagoUserForm.DeleteEvent.class, e -> {
            try {
                delete(e.getEntity());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        getForm().addListener(PagoUserForm.CloseEvent.class, e -> closeEditor());
        HorizontalLayout toolbar = getToolBar();
        toolbar.add(getAddButton());
        add(toolbar,getContent(getGrid(),getForm()));
        updateList();
        closeEditor();

    }
}
