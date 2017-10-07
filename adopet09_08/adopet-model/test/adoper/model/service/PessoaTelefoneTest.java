package adoper.model.service;

import adopet.model.criteria.PessoaTelefoneCriteria;
import adopet.model.entity.PessoaTelefone;
import adopet.model.service.PessoaTelefoneService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.applet.AppletIllegalArgumentException;

public class PessoaTelefoneTest {

    private PessoaTelefone entity = null;

    @Before
    public void setUp() {
        entity = new PessoaTelefone();
        entity.setTelefone("231312412314214");
        entity.setCelular("231312412314214");
    }

    @Test
    public void createPessoaTelefoneCorrectTest() throws Exception {
        PessoaTelefoneService service = new PessoaTelefoneService();
        Long idResposta = service.create(entity);
        service.delete(idResposta);
        Assert.assertTrue(idResposta > 0L);

    }

    @Test
    public void createPessoaTelefoneIncorrectTest() throws Exception {
        PessoaTelefoneService service = new PessoaTelefoneService();
        entity.setTelefone(null);

        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("telefone", entity.getTelefone());
        fields.put("celular", entity.getCelular());

        Assert.assertTrue(!service.validate(fields).isEmpty());

    }

    @Test
    public void readByIdTest() throws Exception {
        PessoaTelefoneService service = new PessoaTelefoneService();
        Long idResposta = service.create(entity);

        PessoaTelefone entityTest = service.readById(idResposta);

        service.delete(idResposta);

        Assert.assertTrue(entityTest != null);

    }

    @Test
    public void readByCriteriaTest() throws Exception {
        PessoaTelefoneService service = new PessoaTelefoneService();
        Long idResposta = service.create(entity);

        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(PessoaTelefoneCriteria.TELEFONE_EQ, entity.getTelefone());
        criteria.put(PessoaTelefoneCriteria.CELULAR_EQ, entity.getCelular());

        List<PessoaTelefone> entityList = service.readByCriteria(criteria, null, null);

        service.delete(idResposta);

        Assert.assertTrue(!entityList.isEmpty());

    }

}
