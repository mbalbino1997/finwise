package org.finwise.java.finwise.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.finwise.java.finwise.model.CardResponseDTO;
import org.finwise.java.finwise.model.ProfileResponseDto;
import org.finwise.java.finwise.model.TransactionResponseDTO;
import org.finwise.java.finwise.model.CardPersonal;
import org.finwise.java.finwise.model.ProfileUser;
import org.finwise.java.finwise.model.User;
import org.finwise.java.finwise.repository.ProfileUserRepository;
import org.finwise.java.finwise.repository.UserRepository;
import org.finwise.java.finwise.security.DatabaseUserDetails;
import org.finwise.java.finwise.security.JwtUtil;

@RestController
@RequestMapping("/api/profile")
public class UserProfileRestController {

    @Autowired
    private ProfileUserRepository profileRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCurrentUserProfile(
            @AuthenticationPrincipal DatabaseUserDetails userDetails) {

        Integer userId = userDetails.getId();
        ProfileUser profile = profileRepo.findByUserId(userId);
        User user = userRepo.findById(userId).orElseThrow();

        var cardsDto = profile.getCards().stream().map(card -> {
            var txDto = card.getTransactions().stream()
                .map(tx -> new TransactionResponseDTO(
                    tx.getId(), tx.getAmount(), tx.getType(), tx.getDescription(), tx.getDate()
                )).collect(Collectors.toList());

            return new CardResponseDTO(
                card.getId(),
                card.getCardNumber(),
                card.getCvv(),
                card.getExpirationDate(),
                card.getCard().getCardType(),         // nuovo campo
                card.getCard().getSpendingLimit(),    // nuovo campo
                txDto
            );
        }).collect(Collectors.toList());

        ProfileResponseDto dto = new ProfileResponseDto(
            profile.getId(), profile.getFirstName(), profile.getLastName(), profile.getBirthDate(),
            profile.getIncomeRange(), profile.getRiskTolerance(),
            user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()),
            cardsDto
        );

        String jwt = jwtUtil.generateToken(user.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("profile", dto);

        return ResponseEntity.ok(response);
    }
}
