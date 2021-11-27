
package com.alkemy.disney.controller;

import com.alkemy.disney.entity.RegistroRequest;
import com.alkemy.disney.service.RegistroServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kharon
 */
@RestController
@RequestMapping(path = "auth/register")
@AllArgsConstructor
public class RegistroController {

    private final RegistroServicio registroServicio;

    @PostMapping
    public String register(@RequestBody RegistroRequest request) {
        return registroServicio.registro(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registroServicio.confirmarToken(token);
    }
}
