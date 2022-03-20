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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Route(value="pago/admin/user", layout = MainLayout.class)
@Secured("ROLE_Admin")
@PageTitle("User | Pago App")
public class PagoUserView extends EntityView<PagoUser,PagoUserForm,PagoUserService> {

    private final PasswordEncoder passwordEncoder;

    private final Grid<PagoUser> grid= new Grid<>(PagoUser.class);

    public PagoUserView(@Autowired PagoUserService service, @Autowired PasswordEncoder passwordEncoder ) {
        super(service);
        this.passwordEncoder=passwordEncoder;
    }



    @PostConstruct
    public void init() {
        final PagoUserForm form = new PagoUserForm(new BeanValidationBinder<>(PagoUser.class), passwordEncoder);
        super.init(grid,form);
        configureGrid( "username", "role","provider","data");

        HorizontalLayout toolbar = getToolBar();
        toolbar.add(getAddButton());
        add(toolbar,getContent(grid,form));
        closeEditor();

    }
}
