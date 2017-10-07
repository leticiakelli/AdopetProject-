package adoper.model.service;

import adopet.model.criteria.PessoaCriteria;
import adopet.model.entity.Foto;
import adopet.model.entity.Pessoa;
import adopet.model.entity.PessoaTelefone;
import adopet.model.entity.Usuario;
import adopet.model.service.FotoService;
import adopet.model.service.PessoaService;
import adopet.model.service.PessoaTelefoneService;
import adopet.model.service.UsuarioService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.applet.AppletIllegalArgumentException;

public class PessoaTest {

    private Pessoa entity = null;
    private static Long idUsuario;
    private static Long idPessoaTelefone;
    private static Long idFoto;

    @Before
    public void setUp() throws Exception {

        if (idFoto == null) {
            Foto foto = new Foto();
            foto.setNome("FotoTeste");

            FotoService fotoService = new FotoService();
            idFoto = fotoService.create(foto);
        }
        if (idPessoaTelefone == null) {
            PessoaTelefone pessoaTelefone = new PessoaTelefone();
            pessoaTelefone.setTelefone("231312412314214");
            pessoaTelefone.setCelular("231312412314214");

            PessoaTelefoneService pessoaTelefoneService = new PessoaTelefoneService();
            idPessoaTelefone = pessoaTelefoneService.create(pessoaTelefone);
        }
        if (idUsuario == null) {
            Usuario usuario = new Usuario();
            usuario.setEmail("UsuarioTeste");
            usuario.setSenha("SenhaTeste");

            UsuarioService usuarioService = new UsuarioService();
            idUsuario = usuarioService.create(usuario);
        }
        entity = new Pessoa();
        entity.setCpf("18886372779");
        entity.setNome("Chester");
        entity.setLogradouro("Rua Chester");
        entity.setNumero(123);
        entity.setComplemento("Perto da loja chester");
        entity.setBairro("Dos Chester");
        entity.setCidade("Cidade Chesterlandia");
        entity.setEstado("Chestina");
        entity.setFoto_id(idFoto);
        entity.setUsuario_id(idUsuario);
        entity.setPessoaTelefone_id(idPessoaTelefone);
    }

    @Test
    public void createPessoaCorrectTest() throws Exception {
        PessoaService service = new PessoaService();
        String resposta = service.createByCpf(entity);
        service.deleteByCpf(resposta);
        Assert.assertTrue(resposta != null);

    }

    @Test
    public void createPessoaIncorrectTest() throws Exception {
        PessoaService service = new PessoaService();
        entity.setCpf(null);

        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("cpf", entity.getCpf());

        Assert.assertTrue(!service.validate(fields).isEmpty());

    }

    @Test
    public void readByIdTest() throws Exception {
        PessoaService service = new PessoaService();
        String resposta = service.createByCpf(entity);

        Pessoa entityTest = service.readByCpf(resposta);

        service.deleteByCpf(resposta);

        Assert.assertTrue(entityTest != null);

    }

    @Test
    public void readByCriteriaTest() throws Exception {
        PessoaService service = new PessoaService();
        String cpfResposta = service.createByCpf(entity);

        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(PessoaCriteria.CPF_EQ, entity.getCpf());

        List<Pessoa> entityList = service.readByCriteria(criteria, null, null);

        service.deleteByCpf(cpfResposta);

        Assert.assertTrue(!entityList.isEmpty());

    }

}
