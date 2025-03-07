package com.dudko.example.model;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Model of answer from LLM")
public record Answer(String answer) {
}
