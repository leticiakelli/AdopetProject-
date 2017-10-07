package adoper.model.service;

import adopet.model.criteria.FotoCriteria;
import adopet.model.entity.Foto;
import adopet.model.service.FotoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.applet.AppletIllegalArgumentException;

public class FotoTest {

    private Foto entity = null;

    @Before
    public void setUp() {
        entity = new Foto();
        entity.setNome("FotoTeste");
    }

    @Test
    public void createFotoCorrectTest() throws Exception {
            FotoService service = new FotoService();
            Long idResposta = service.create(entity);
        service.delete(idResposta);
        Assert.assertTrue(idResposta > 0L);

    }

    @Test
    public void createFotoIncorrectTest() throws Exception {
        FotoService service = new FotoService();
        entity.setNome(null);

        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("nome", entity.getNome());

        Assert.assertTrue(!service.validate(fields).isEmpty());

    }

    @Test
    public void readByIdTest() throws Exception {
        FotoService service = new FotoService();
        Long idResposta = service.create(entity);

        Foto entityTest = service.readById(idResposta);

        service.delete(idResposta);

        Assert.assertTrue(entityTest != null);

    }

    @Test
    public void readByCriteriaTest() throws Exception {
        FotoService service = new FotoService();
        Long idResposta = service.create(entity);

        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(FotoCriteria.NOME_EQ, entity.getNome());

        List<Foto> entityList = service.readByCriteria(criteria, null, null);

        service.delete(idResposta);

        Assert.assertTrue(!entityList.isEmpty());

    }

}
