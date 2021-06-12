package com.example.project.Service;

import com.example.project.Entity.Card;
import com.example.project.Exception.ResourceNotFoundException;

import java.util.List;

public interface CardService {
    Card getCard(Long id) throws ResourceNotFoundException;
    Card createCard(Card card);
    Card updateCardById(Long id, Card card) throws ResourceNotFoundException;
}
