package com.dudko.example.model;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Model of question from user")
public record Question(String question) {
}
