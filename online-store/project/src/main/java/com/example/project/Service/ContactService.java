package com.example.project.Service;

import com.example.project.Entity.Contact;
import com.example.project.Exception.ResourceNotFoundException;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContacts();
    Contact getContact(Long id) throws ResourceNotFoundException;
    Contact createContact(Contact contact) throws ResourceNotFoundException;
    Contact createContactCLR(Contact contact);
    void deleteContactById(Long id);
    Contact updateContactById(Long id, Contact contact) throws ResourceNotFoundException;
}
