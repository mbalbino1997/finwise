package org.finwise.java.finwise.controller;

import org.finwise.java.finwise.model.Promotion;
import org.finwise.java.finwise.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public String listPromotions(Model model) {
        model.addAttribute("promotions", promotionService.findAll());
        return "promotions/list"; // templates/promotions/list.html
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "promotions/form";
    }

    @PostMapping("/save")
    public String savePromotion(@Valid @ModelAttribute("promotion") Promotion promotion,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "promotions/form";
        }
        promotionService.save(promotion);
        return "redirect:/promotions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Promotion promotion = promotionService.findById(id);
        if (promotion != null) {
            model.addAttribute("promotion", promotion);
            return "promotions/form";
        }
        return "redirect:/promotions";
    }

    @GetMapping("/delete/{id}")
    public String deletePromotion(@PathVariable("id") Integer id) {
        promotionService.deleteById(id);
        return "redirect:/promotions";
    }
}
