package xyz.morlotti.virtualbookcase.webapi.controllers;

import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import xyz.morlotti.virtualbookcase.webapi.EmailSingleton;
import xyz.morlotti.virtualbookcase.webapi.beans.User;
import xyz.morlotti.virtualbookcase.webapi.daos.UserDAO;

@RestController
public class AuthController
{
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ResponseEntity<Void> login(
        @RequestParam("login") String login,
        @RequestParam("password") String password,
        HttpSession httpSession
    ) throws Exception
    {
        Optional<User> optional = userDAO.findByLoginPassword(login, password);

        if(optional.isPresent())
        {
            User user = optional.get();

            httpSession.setAttribute("currentUser", user);

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ResponseEntity<Void> logout(HttpSession httpSession) throws Exception
    {
        User user = (User) httpSession.getAttribute ("currentUser");

        user.initGuest();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(path = "/remind-password", method = RequestMethod.GET)
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
                    "Cordialement,\n" + "Votre bibliothèque"
            );
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
