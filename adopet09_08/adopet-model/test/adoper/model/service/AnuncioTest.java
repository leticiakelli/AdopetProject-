package adoper.model.service;

import adopet.model.criteria.AnuncioCriteria;
import adopet.model.entity.Anuncio;
import adopet.model.entity.Especie;
import adopet.model.entity.Foto;
import adopet.model.entity.Pessoa;
import adopet.model.entity.PessoaTelefone;
import adopet.model.entity.Usuario;
import adopet.model.service.AnuncioService;
import adopet.model.service.EspecieService;
import adopet.model.service.FotoService;
import adopet.model.service.PessoaService;
import adopet.model.service.PessoaTelefoneService;
import adopet.model.service.UsuarioService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.applet.AppletIllegalArgumentException;

public class AnuncioTest {

    private Anuncio entity = null;

    private static String cpfPessoaAnuncio;
    private static Long idEspecieAnuncio;
    private static Long idFotoAnuncio;

    private static Long idUsuario;
    private static Long idPessoaTelefone;
    private static Long idFoto;

    @Before
    public void setUp() throws Exception {
        if (idFotoAnuncio == null) {
            Foto foto = new Foto();
            foto.setNome("FotoTeste");

            FotoService fotoService = new FotoService();
            idFotoAnuncio = fotoService.create(foto);
        }
        if (idEspecieAnuncio == null) {
            Especie especie = new Especie();
            especie.setNome("Teste");

            EspecieService especieService = new EspecieService();
            idEspecieAnuncio = especieService.create(especie);
        }
        if (cpfPessoaAnuncio == null) {
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
            Pessoa pessoa = new Pessoa();
            pessoa.setCpf("18886372779");
            pessoa.setNome("Chester");
            pessoa.setLogradouro("Rua Chester");
            pessoa.setNumero(123);
            pessoa.setComplemento("Perto da loja chester");
            pessoa.setBairro("Dos Chester");
            pessoa.setCidade("Cidade Chesterlandia");
            pessoa.setEstado("Chestina");
            pessoa.setFoto_id(idFoto);
            pessoa.setUsuario_id(idUsuario);
            pessoa.setPessoaTelefone_id(idPessoaTelefone);
            PessoaService pessoaService = new PessoaService();
            cpfPessoaAnuncio = pessoaService.createByCpf(pessoa);
        }

        entity = new Anuncio();
        entity.setId(idFotoAnuncio);
        entity.setData_hora(new Timestamp(System.currentTimeMillis()));
        entity.setPorte("PorteTeste");
        entity.setRaca("RacaTeste");
        entity.setSexo("m");
        entity.setRecompensa("12");
        entity.setCaracteristicas("CaracteristicaTeste");
        entity.setTipo("adocao");
        entity.setStatus("pendente");
        entity.setIdade(13);
        entity.setLocal("Rua Teste");
        entity.setFoto_id(idFotoAnuncio);
        entity.setEspecie_id(idEspecieAnuncio);
        entity.setPessoaAnuncianteCpf(cpfPessoaAnuncio);
        entity.setPessoaAdotanteCpf(cpfPessoaAnuncio);

    }

    @Test
    public void createAnuncioCorrectTest() throws Exception {
        AnuncioService service = new AnuncioService();
        Long idResposta = service.create(entity);
        service.delete(idResposta);
        Assert.assertTrue(idResposta > 0L);

    }

    @Test
    public void createAnuncioIncorrectTest() throws Exception {
        AnuncioService service = new AnuncioService();
        entity.setTipo(null);

        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("tipo", entity.getTipo());

        Assert.assertTrue(!service.validate(fields).isEmpty());

    }

    @Test
    public void readByIdTest() throws Exception {
        AnuncioService service = new AnuncioService();
        Long idResposta = service.create(entity);

        Anuncio entityTest = service.readById(idResposta);

        service.delete(idResposta);

        Assert.assertTrue(entityTest != null);

    }

    @Test
    public void readByCriteriaTest() throws Exception {
        AnuncioService service = new AnuncioService();
        Long idResposta = service.create(entity);

        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(AnuncioCriteria.TIPO_EQ, entity.getTipo());

        List<Anuncio> entityList = service.readByCriteria(criteria, null, null);

        service.delete(idResposta);

        Assert.assertTrue(!entityList.isEmpty());

    }

}
