package com.example.devcopassignment;

import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class IncomeValidationController {

    @PostMapping("/validate")
    public ResponseEntity<String> validateRegularAmount(@Valid @RequestBody RegularAmount regularAmount, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(String.join(",", errors));
        } else {
            // perform payment processing logic
            return ResponseEntity.ok("Payment processed successfully.");
        }
    }
}
