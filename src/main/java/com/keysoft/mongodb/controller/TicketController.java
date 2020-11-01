package com.keysoft.mongodb.controller;

import com.keysoft.mongodb.model.Ticket;
import com.keysoft.mongodb.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/tza/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Ticket> findById(@PathVariable("id") String id) {
        return ticketRepository.findById(id);
    }

    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public List<Ticket> findByStatus(@PathVariable("status") String status) {
        return ticketRepository.findByStatus(status);
    }

    @RequestMapping(value = "/appId/{id}", method = RequestMethod.GET)
    public List<Ticket> findByAppId(@PathVariable("id") String appId) {
        return ticketRepository.findByAppId(appId);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long countAllTickets() {
        Stream<Ticket> stream = ticketRepository.findAllByCustomQueryAndStream("Open");
        Long count = stream.count();
        stream.close();
        return count;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Ticket addNewApplication(@RequestBody Ticket ticket){
        return ticketRepository.save(ticket);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Ticket updateApplication(@PathVariable("id") String id, @RequestBody Ticket ticket){
        ticket.setId(id);
        return ticketRepository.save(ticket);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTicket(@PathVariable("id") String id) {
        ticketRepository.deleteById(id);
    }
}
