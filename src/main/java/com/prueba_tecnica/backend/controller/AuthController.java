package com.prueba_tecnica.backend.controller;


import com.prueba_tecnica.backend.model.Request.TokenMetadata;
import com.prueba_tecnica.backend.model.Request.UserRequest;
import com.prueba_tecnica.backend.model.Response.Common.Response;
import com.prueba_tecnica.backend.model.Rol;
import com.prueba_tecnica.backend.model.User;
import com.prueba_tecnica.backend.service.RolService;
import com.prueba_tecnica.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final UserService userService;
    private final RolService rolService;

    @Autowired
    public AuthController(UserService userService, RolService rolService) {
        this.userService = userService;
        this.rolService = rolService;
    }

    @PostMapping
    public Response Login(@RequestBody UserRequest model) {
        Response result = new Response();

        Optional<User> user = userService.findUser(model);

        if (user.isPresent() && user.get().getPassword().equals( model.getPassword() ) ) {

            Optional<Rol> rol = rolService.getById(user.get().getRol().getId());

            TokenMetadata tokenMetadata = new TokenMetadata();
            tokenMetadata.setUser(user.get().getLogin());
            tokenMetadata.setRol_id(user.get().getRol().getId());

            if (rol.isPresent()) {
                tokenMetadata.setRol_name(rol.get().getName());
                tokenMetadata.setRead(rol.get().getRead());
                tokenMetadata.setEdit(rol.get().getEdit());
                tokenMetadata.setDelete(rol.get().getDelete());
                tokenMetadata.setActive(rol.get().getActive());
            }


            result.setMessage("ok");
            result.setCode(1);
        } else {
            result.setMessage("Invalid credentials");
            result.setCode(403);
        }

        return result;
    }
}
