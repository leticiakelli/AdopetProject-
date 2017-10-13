package adopet.model.service;

import adopet.model.ConnectionManager;
import adopet.model.base.service.BaseUsuarioService;
import adopet.model.criteria.UsuarioCriteria;
import adopet.model.dao.UsuarioDAO;
import adopet.model.entity.Usuario;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioService implements BaseUsuarioService {

    @Override
    public Usuario login(String email, String senha) throws Exception {
        UsuarioService service = new UsuarioService();
        HashMap<Long, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.EMAIL_EQ, email);
        criteria.put(UsuarioCriteria.SENHA_EQ, senha);
        List<Usuario> usuarioList = service.readByCriteria(criteria, null, null);
        Usuario usuarioLogado = null;
        if (usuarioList.size() == 1) {
            Usuario usuario = usuarioList.get(0);
            if (usuario.getEmail().equals(email)) {
                usuarioLogado = usuario;
            }
        }
        return usuarioLogado;
    }

    @Override
    public Long create(Usuario entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long id = null;
        try {
            UsuarioDAO dao = new UsuarioDAO();
            id = dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return id;
    }

    @Override
    public Usuario readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.readById(conn, id);
            conn.commit();
            conn.close();
            return usuario;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public List<Usuario> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            List<Usuario> usuarioList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
            return usuarioList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void update(Usuario entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.update(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }
    @Override
     public void updateBySenha(Usuario entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.updateBySenha(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Map<String, String> validate(Map<String, Object> fields) throws Exception {
        Map<String, String> errors = new HashMap<>();

        String email = (String) fields.get("email");
        if (email == null || email.isEmpty()) {
            errors.put("email", "Campo obrigatório!");
        } else {
            Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            if (!matcher.find()) {
                errors.put("email", "Formato inválido!");
            } else {
                Map<Long, Object> criteria = new HashMap<>();
                criteria.put(UsuarioCriteria.EMAIL_EQ, email);
                Long id = (Long) fields.get("id");
                if(id!=null && id > 0){
                    criteria.put(UsuarioCriteria.ID_NE, id);
                }
                if (readByCriteria(criteria, null, null).size() > 0) {
                    errors.put("email", "Já se encontra em uso!");
                }
            }
        }

        String senha = (String) fields.get("senha");
        if (senha == null || senha.isEmpty()) {
            errors.put("senha", "Campo obrigatório!");
        } else {
            int length = senha.length();
            if (length < 6 || length > 20) {
                errors.put("senha", "Tamanho inválido!");
            } else {
                String confirmacao = (String) fields.get("confirmacao");
                if (!senha.equals(confirmacao)) {
                    errors.put("senha", "Não confere!");
                }
            }
        }

        return errors;
    }

}
