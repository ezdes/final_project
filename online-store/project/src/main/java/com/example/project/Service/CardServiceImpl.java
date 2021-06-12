package com.example.project.Service;

import com.example.project.Entity.Card;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;


    @Override
    public Card getCard(Long id) throws ResourceNotFoundException {
        return cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find card with id ", id));
    }

    @Override
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }



    @Override
    public Card updateCardById(Long id, Card card) throws ResourceNotFoundException {
        return cardRepository.findById(id)
                .map(newCard -> {
                    newCard.setBalance(card.getBalance());
                    return cardRepository.save(newCard);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find card with id ", id));
    }
}
