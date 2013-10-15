package com.epam.cdp.oleshchuk.cinema.controller;

import com.epam.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.cdp.oleshchuk.cinema.model.TicketIdsJson;
import com.epam.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import com.epam.cdp.oleshchuk.cinema.model.User;
import com.epam.cdp.oleshchuk.cinema.service.TicketService;
import com.epam.cdp.oleshchuk.cinema.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;
    private static final Logger log = Logger.getLogger(TicketController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/tickets")
    public Map<String, Object> getAllTickets(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam(required = false) String title, @RequestParam(required = false) String category,
                                             @RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo) {
        Map<String, Object> returnParams = new HashMap<String, Object>();
        List<Ticket> availableTicket = null;
        TicketsFilterParams ticketsFilterParams = new TicketsFilterParams(title, category, dateFrom, dateTo);
        try {
            availableTicket = ticketService.getAvailableTickets(ticketsFilterParams);
            returnParams.put("ticketsList", availableTicket);
        } catch (Exception e) {
            returnParams.put("error", e.getMessage());
            log.error(e.getMessage(), e);
        }
        return returnParams;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/tickets")
    public Map<String, Object> getUserTickets(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response,
                                              @RequestParam(required = false) String title, @RequestParam(required = false) String category,
                                              @RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo) {
        Map<String, Object> returnParams = new HashMap<String, Object>();
        TicketsFilterParams ticketsFilterParams = new TicketsFilterParams(title, category, dateFrom, dateTo);
        try {
            Long longId = Long.parseLong(userId);
            User user = userService.getUserById(longId);
            List<Ticket> myTickets = ticketService.getTicketsByUser(user, ticketsFilterParams);
            returnParams.put("ticketsList", myTickets);
            returnParams.put("user", user);
        } catch (Exception e) {
            returnParams.put("error", e.getMessage());
            log.error(e.getMessage(), e);
        }
        return returnParams;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/tickets/book")
    @ResponseBody
    public Map<String, Object> bookTicketsByJson(@PathVariable String userId, @RequestBody TicketIdsJson ticketIdsJson) {
        Map<String, Object> response = new HashMap<String, Object>();
        String message = null;
        try {
            Long longId = Long.parseLong(userId);
            User user = userService.getUserById(longId);
            ticketService.bookTicket(ticketIdsJson.getTicketIds(), user);
            message = "Tickets are booked";
        } catch (Exception e) {
            message = e.getMessage();
            log.error(e.getMessage(), e);
        }
        response.put("message", message);
        return response;
    }

}