package com.dudko.example.controller;

import com.dudko.example.model.Answer;
import com.dudko.example.model.Question;
import com.dudko.example.service.AIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User REST API")
@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final AIService aiService;


    @Operation(summary = "Sent a question to LLM")
    @PostMapping("/ask/open-ai")
    public Answer askQuestion(@RequestBody Question question) {
        return aiService.getAnswer(question);
    }

}
