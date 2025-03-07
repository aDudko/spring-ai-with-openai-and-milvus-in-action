package com.dudko.example.service;

import com.dudko.example.model.Answer;
import com.dudko.example.model.Question;

public interface AIService {

    Answer getAnswer(Question question);

}
