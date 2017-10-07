package adoper.model.service;

import adopet.model.criteria.UsuarioCriteria;
import adopet.model.entity.Usuario;
import adopet.model.service.UsuarioService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.applet.AppletIllegalArgumentException;

public class UsuarioServiceTest {

    private Usuario entity = null;

    @Before
    public void setUp() {
        entity = new Usuario();
        entity.setEmail("UsuarioTeste");
        entity.setSenha("SenhaTeste");
    }

    @Test
    public void createUsuarioCorrectTest() throws Exception {
        UsuarioService service = new UsuarioService();
        Long idResposta = service.create(entity);
        service.delete(idResposta);
        Assert.assertTrue(idResposta > 0L);

    }

    @Test
    public void createUsuarioIncorrectTest() throws Exception {
        UsuarioService service = new UsuarioService();
        entity.setSenha(null);

        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("email", entity.getEmail());
        fields.put("senha", entity.getSenha());

        Assert.assertTrue(!service.validate(fields).isEmpty());

    }

    @Test
    public void readByIdTest() throws Exception {
        UsuarioService service = new UsuarioService();
        Long idResposta = service.create(entity);

        Usuario entityTest = service.readById(idResposta);

        service.delete(idResposta);

        Assert.assertTrue(entityTest != null);

    }

    @Test
    public void readByCriteriaTest() throws Exception {
        UsuarioService service = new UsuarioService();
        Long idResposta = service.create(entity);

        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(UsuarioCriteria.EMAIL_EQ, entity.getEmail());
        criteria.put(UsuarioCriteria.SENHA_EQ, entity.getSenha());

        List<Usuario> entityList = service.readByCriteria(criteria, null, null);

        service.delete(idResposta);

        Assert.assertTrue(!entityList.isEmpty());

    }

}
