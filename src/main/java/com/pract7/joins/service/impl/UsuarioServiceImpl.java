package com.pract7.joins.service.impl;

import com.pract7.joins.model.UsuarioTable;
import com.pract7.joins.repository.UsuarioRepository;
import com.pract7.joins.service.UsuarioService;
import com.pract7.joins.service.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDTO> getUsuarios() {
        return StreamSupport.stream(usuarioRepository.findAll().spliterator(),false)
                .map(obj -> new UsuarioDTO(
                        obj.getId(),
                        obj.getUserName(),
                        obj.getUserPwd(),
                        obj.getIdPsic()))
                .toList();
    }

    @Override
    public List<UsuarioDTO> getUsuariosConPsicologo() {

        String query=
                """
                SELECT U.ID, U.USER_NAME, U.USER_PWD, U.ID_PSIC
                FROM USUARIOS U, PSICOLOGOS P
                WHERE U.ID_PSIC=P.ID;
                """;

        List<UsuarioDTO> usuariosList = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new UsuarioDTO(
                                rs.getLong("ID"),
                                rs.getString("USER_NAME"),
                                rs.getString("USER_PWD"),
                                rs.getLong("ID_PSIC"))
        );

        return usuariosList;
    }

    @Override
    public List<UsuarioDTO> getUsuariosConPsicologoById(Long idPsic){

        String query= "SELECT ID, USER_NAME, USER_PWD, ID_PSIC FROM USUARIOS WHERE ID_PSIC=" + idPsic + ";";

        List<UsuarioDTO> usuariosList = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new UsuarioDTO(
                                rs.getLong("ID"),
                                rs.getString("USER_NAME"),
                                rs.getString("USER_PWD"),
                                rs.getLong("ID_PSIC"))
        );

        return usuariosList;
    }

    @Override
    public List<UsuarioDTO> getUsuariosSinPsicologo() {

        String query=
                """
                SELECT ID, USER_NAME, USER_PWD, ID_PSIC
                FROM USUARIOS
                WHERE ID_PSIC=0;
                """;

        List<UsuarioDTO> usuariosList = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new UsuarioDTO(
                                rs.getLong("ID"),
                                rs.getString("USER_NAME"),
                                rs.getString("USER_PWD"),
                                rs.getLong("ID_PSIC"))
        );

        return usuariosList;
    }

    @Override
    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioTable updateUsuario(Long id, UsuarioTable usuario){
        if (usuarioRepository.existsById(id)){
            return usuarioRepository.save(usuario);
        }else{
            return null;
        }
    }

    @Override
    public UsuarioTable insertUsuario(UsuarioTable usuarioTable){
        UsuarioTable userTable= new UsuarioTable();
        // Como es un POST, no pasamos el ID (es un Long @Id, se autoincrementa solo)
        userTable.setUserName(usuarioTable.getUserName());
        userTable.setUserPwd(usuarioTable.getUserPwd());
        userTable.setIdPsic(usuarioTable.getIdPsic());
        UsuarioTable newUser = usuarioRepository.save(userTable);
        return newUser;
    }
}
