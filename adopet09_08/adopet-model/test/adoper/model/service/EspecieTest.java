package adoper.model.service;

import adopet.model.criteria.EspecieCriteria;
import adopet.model.entity.Especie;
import adopet.model.service.EspecieService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.applet.AppletIllegalArgumentException;

public class EspecieTest {

    private Especie entity = null;

    @Before
    public void setUp() {
        entity = new Especie();
        entity.setNome("Gato");
    }

    @Test
    public void createEspecieCorrectTest() throws Exception {
            EspecieService service = new EspecieService();
            Long idResposta = service.create(entity);
        service.delete(idResposta);
        Assert.assertTrue(idResposta > 0L);

    }

    @Test
    public void createEspecieIncorrectTest() throws Exception {
        EspecieService service = new EspecieService();
        entity.setNome(null);

        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("nome", entity.getNome());

        Assert.assertTrue(!service.validate(fields).isEmpty());

    }

    @Test
    public void readByIdTest() throws Exception {
        EspecieService service = new EspecieService();
        Long idResposta = service.create(entity);

        Especie entityTest = service.readById(idResposta);

        service.delete(idResposta);

        Assert.assertTrue(entityTest != null);

    }

    @Test
    public void readByCriteriaTest() throws Exception {
        EspecieService service = new EspecieService();
        Long idResposta = service.create(entity);

        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(EspecieCriteria.NOME_EQ, entity.getNome());

        List<Especie> entityList = service.readByCriteria(criteria, null, null);

        service.delete(idResposta);

        Assert.assertTrue(!entityList.isEmpty());

    }

}
