package adopet.model;

import adopet.model.entity.Anuncio;

import adopet.model.entity.Foto;
import adopet.model.entity.Pessoa;
import adopet.model.entity.Usuario;
import adopet.model.service.AnuncioService;
import adopet.model.service.PessoaService;
import adopet.model.service.UsuarioService;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        testePessoas();
        
        testeAnuncio();
        
        testeUsuario();
    }

    public static void testePessoas() {
        Usuario usuario = new Usuario();
        usuario.setEmail("leticia@hotmail.com");
        usuario.setSenha("111111");
        usuario.setId(1L);

    

        Foto foto = new Foto();
        foto.setId(1L);
        foto.setNome("aaa");

        

       

    }

    public static void testeAnuncio() {
        Anuncio anuncio= new Anuncio();
        anuncio.setData_hora(new Timestamp(System.currentTimeMillis()));
        anuncio.setPorte("pequeno");
        anuncio.setRaca("vira lata");
        anuncio.setCaracteristicas("seu nome é spike");
        anuncio.setSexo("M");
        anuncio.setTipo("anunciante");
        anuncio.setStatus("pendente");
        anuncio.setEspecie_id(1L);
        anuncio.setFoto_id(1L);
        anuncio.setLocal(null);
        anuncio.setRecompensa(null);
       
        AnuncioService anuncioService= new AnuncioService();
        try {
            anuncioService.create(anuncio);
            List<Anuncio> anuncioList= anuncioService.readByCriteria(null);
            for (Anuncio anuncio1 : anuncioList) {
                System.out.println(anuncio1.getCaracteristicas());
            }
            anuncioService.delete(1L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void testeUsuario(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("leticia@gmail.com");
        usuario.setSenha("121434235");
        
        UsuarioService usuarioService= new UsuarioService();
        try {
            usuarioService.create(usuario);
            List<Usuario> usuarioList= usuarioService.readByCriteria(null);
            for (Usuario usuario1 : usuarioList) {
                System.out.println(usuario1.getEmail());
            }
            usuarioService.delete(1L);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
