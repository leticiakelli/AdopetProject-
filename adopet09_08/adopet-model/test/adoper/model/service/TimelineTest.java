package adoper.model.service;

import adopet.model.criteria.TimelineCriteria;
import adopet.model.entity.Anuncio;
import adopet.model.entity.Especie;
import adopet.model.entity.Foto;
import adopet.model.entity.Pessoa;
import adopet.model.entity.PessoaTelefone;
import adopet.model.entity.Timeline;
import adopet.model.entity.Usuario;
import adopet.model.service.AnuncioService;
import adopet.model.service.EspecieService;
import adopet.model.service.FotoService;
import adopet.model.service.PessoaService;
import adopet.model.service.PessoaTelefoneService;
import adopet.model.service.TimelineService;
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

 

public class TimelineTest {

    private Timeline entity = null;
    private static Long idUsuario;
    private static Long idTimelineTelefone;
    private static Long idFoto;
    
    private static String cpfPessoaAnuncio;
    private static Long idEspecieAnuncio;
    private static Long idFotoAnuncio;

    private static Long idPessoaTelefone;
    private Long idAnuncio;

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

        Anuncio anuncio = new Anuncio();
        anuncio.setId(idFotoAnuncio);
        anuncio.setData_hora(new Timestamp(System.currentTimeMillis()));
        anuncio.setPorte("PorteTeste");
        anuncio.setRaca("RacaTeste");
        anuncio.setSexo("m");
        anuncio.setRecompensa("12");
        anuncio.setCaracteristicas("CaracteristicaTeste");
        anuncio.setTipo("adocao");
        anuncio.setStatus("pendente");
        anuncio.setIdade(13);
        anuncio.setLocal("Rua Teste");
        anuncio.setFoto_id(idFotoAnuncio);
        anuncio.setEspecie_id(idEspecieAnuncio);
        anuncio.setPessoaAnuncianteCpf(cpfPessoaAnuncio);
        anuncio.setPessoaAdotanteCpf(cpfPessoaAnuncio);
        AnuncioService anuncioService = new AnuncioService();
        idAnuncio = anuncioService.create(anuncio);
        
        entity = new Timeline();
        entity.setData_hora(new Timestamp(System.currentTimeMillis()));
        entity.setTexto("Chester");
        entity.setAnuncio_id(idAnuncio);
        entity.setPessoa_cpf(cpfPessoaAnuncio);
        entity.setFoto_id(idFoto);
    }

    @Test
    public void createTimelineCorrectTest() throws Exception {
        TimelineService service = new TimelineService();
        Long resposta = service.create(entity);
        service.delete(resposta);
        Assert.assertTrue(resposta != null);

    }

    @Test
    public void createTimelineIncorrectTest() throws Exception {
        TimelineService service = new TimelineService();
        entity.setTexto(null);

        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("texto", entity.getTexto());

        Assert.assertTrue(!service.validate(fields).isEmpty());

    }

    @Test
    public void readByIdTest() throws Exception {
        TimelineService service = new TimelineService();
        Long resposta = service.create(entity);

        Timeline entityTest = service.readById(resposta);

        service.delete(resposta);

        Assert.assertTrue(entityTest != null);

    }

    @Test
    public void readByCriteriaTest() throws Exception {
        TimelineService service = new TimelineService();
        Long cpfResposta = service.create(entity);

        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(TimelineCriteria.TEXTO_EQ, entity.getTexto());

        List<Timeline> entityList = service.readByCriteria(criteria, null, null);

        service.delete(cpfResposta);

        Assert.assertTrue(!entityList.isEmpty());

    }

}
