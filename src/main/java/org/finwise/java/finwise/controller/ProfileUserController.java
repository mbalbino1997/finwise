package org.finwise.java.finwise.controller;

import org.finwise.java.finwise.model.ProfileUser;
import org.finwise.java.finwise.service.ProfileUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class ProfileUserController {

    private final ProfileUserService service;

    public ProfileUserController(ProfileUserService service) {
        this.service = service;
    }

    @GetMapping
    public String redirectToProfiles() {
        return "redirect:/users/profiles";
    }

    @GetMapping("/profiles")
    public String listProfiles(Model model) {
        List<ProfileUser> profiles = service.findAll();
        model.addAttribute("profiles", profiles);
        return "profiles/list";  // la view che mostra la lista di profili
    }

    @GetMapping("/profiles/details/{id}")
    public String profileDetails(@PathVariable Integer id, Model model) {
        ProfileUser profile = service.findById(id);
        if (profile == null) {
            return "redirect:/users/profiles";  // Corretto il percorso di redirect
        }
        model.addAttribute("profile", profile);
        return "profiles/show";  // pagina che mostra i dettagli del profilo
    }
}
