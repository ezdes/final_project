package com.example.project.Controller;

import com.example.project.Entity.Contact;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) throws ResourceNotFoundException {
        return contactService.getContact(id);
    }

    @PutMapping("/{id}")
    public Contact updateContactById(@PathVariable Long id, @RequestBody Contact contact) throws ResourceNotFoundException {
        return contactService.updateContactById(id, contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContactById(@PathVariable Long id) {
        contactService.deleteContactById(id);
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) throws ResourceNotFoundException {
        return contactService.createContact(contact);
    }
}
