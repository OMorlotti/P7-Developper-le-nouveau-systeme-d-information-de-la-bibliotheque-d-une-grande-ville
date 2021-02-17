package xyz.morlotti.virtualbookcase.webapi.controllers;

import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import xyz.morlotti.virtualbookcase.webapi.controllers.beans.Auth;
import xyz.morlotti.virtualbookcase.webapi.models.User;
import xyz.morlotti.virtualbookcase.webapi.daos.UserDAO;
import xyz.morlotti.virtualbookcase.webapi.EmailSingleton;
import xyz.morlotti.virtualbookcase.webapi.security.jwt.JwtUtils;
import xyz.morlotti.virtualbookcase.webapi.security.services.UserDetailsImpl;

@RestController
public class AuthController
{
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(path = "/auth/login", method = RequestMethod.GET)
    public Auth login(
        @RequestParam("login") String login,
        @RequestParam("password") String password,
        HttpSession httpSession
    ) throws Exception
    {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new Auth(
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            userDetails.getAuthority(),
            jwtUtils.generateJwtToken(authentication)
        );
    }

    @RequestMapping(path = "/auth/remind-password", method = RequestMethod.GET)
    public ResponseEntity<Void> remindPassword(@RequestParam("email") String email) throws Exception
    {
        Optional<User> optional = userDAO.findByEmail(email);

        if(optional.isPresent())
        {
            User user = optional.get();

            EmailSingleton.sendMessage(
                user.getEmail(),
                user.getEmail(),
                "",
                "Mot de passe - Bibliothèque",
                "Bonjour,\nVotre mot de passe est " + user.getPassword() + ".\n" +
                "Cordialement,\n" +
                "Votre bibliothèque"
            );
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
