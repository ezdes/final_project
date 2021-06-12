package com.example.project.Service;

import com.example.project.Entity.Contact;
import com.example.project.Entity.User;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.ContactRepository;
import com.example.project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContact(Long id) throws ResourceNotFoundException {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find contact with id ", id));
    }

    @Override
    public Contact createContact(Contact contact) throws ResourceNotFoundException {
        List<Contact> contacts = getAllContacts();
        User user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        for (Contact value : contacts) {
            if (value.getUser().getId().equals(user.getId())) {
                throw new ResourceNotFoundException();
            }
        }
        //User user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        contact.setUser(user);
        return contactRepository.save(contact);
    }

    @Override
    public Contact createContactCLR(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public Contact updateContactById(Long id, Contact contact) throws ResourceNotFoundException {
        return contactRepository.findById(id)
                .map(newContact -> {
                    newContact.setEmail(contact.getEmail());
                    newContact.setPhoneNumber(contact.getPhoneNumber());
                    newContact.setPostCode(contact.getPostCode());
                    return contactRepository.save(newContact);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find contact with id ", id));
    }
}
