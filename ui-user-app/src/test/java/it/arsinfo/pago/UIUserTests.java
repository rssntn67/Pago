package it.arsinfo.pago;

import it.arsinfo.pago.service.api.ArmatoreService;
import it.arsinfo.pago.service.impl.ArmatoreServiceDaoImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class UIUserTests {


    @Autowired
    private ArmatoreService armatoreService;

    @BeforeEach
    public void onSetUp() {

    }

    @AfterEach
    public void onTearDown() {
    }

    @Test
    public void testConfiguration() {
        assertNotNull(armatoreService);
        assertTrue(armatoreService instanceof ArmatoreServiceDaoImpl);
    }

}