package com.epam.cdp.oleshchuk.cinema.controller;

import com.epam.cdp.oleshchuk.cinema.model.RequestJson;
import com.epam.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.cdp.oleshchuk.cinema.model.User;
import com.epam.cdp.oleshchuk.cinema.service.TicketService;
import com.epam.cdp.oleshchuk.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Supported url :
 *
 *
 *          GET:    http://localhost:8080/tickets/freeTickets
 *          GET:    http://localhost:8080/users/{userId}/myTickets
 *          POST:   http://localhost:8080/users/{userId}/bookTicket
 *
 *          also you could get result with 2 extension : /.pdf and /.json
 *          Example:
 *                  http://localhost:8080/tickets/freeTickets/.json
 *                  http://localhost:8080/tickets/freeTickets/.pdf
 *
 *          and also you could get result with one filter
 *                  /dateFrom/{dateFrom}/dateTo/{dateTo}
 *                  {date} - in such pattern dd-MM-YY
 *                  /title/{title}
 *                  /category/{category}
 *
 *          Example:
 *                 http://localhost:8080/tickets/freeTickets/title/mission
 *                 http://localhost:8080/users/0/myTickets/category/bar
 *          Example with extension:
 *                 http://localhost:8080/users/0/myTickets/category/bar/.json
 *                 http://localhost:8080/users/0/myTickets/category/bar/.pdf
 *
 *          There are 2 users in the system:
 *              0) max;
 *              1) dima.
 *
 *
 */

@Controller
public class TicketController {

    @Autowired
    TicketService ticketService;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/bookTicket")
    @ResponseBody
    public Map<String, Object> getJson(@PathVariable String userId, @RequestBody RequestJson requestJson) {
        Map<String, Object> response = new HashMap<String, Object>();
        List<Long> bookedTicketIds = new ArrayList<Long>();
        String message = null;
        try {

            Long longId = Long.parseLong(userId);
            User user = userService.getUserById(longId);
            List<Ticket> bookedTickets = ticketService.getBookedTickets(requestJson.getTicketId());
            if (bookedTickets.size() == 0) {
                Ticket ticket = null;
                for (Long ticketId : requestJson.getTicketId()) {
                    ticket = ticketService.getTicketById(ticketId);
                    ticketService.bookTicket(ticket, user);
                    bookedTicketIds.add(ticketId);
                }
                message = "Tickets are booked";

            } else {

                for (Ticket bookedTicket : bookedTickets) {
                    bookedTicketIds.add(bookedTicket.getId());
                }
                message = "Sorry, tickets :\r\n " + bookedTickets + " \r\nare already booked. Unchecked it.";
            }
        } catch (Exception e) {
            response.put("message", e.getMessage());
        }
        response.put("message", message);
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/myTickets/**")
    public Map<String, Object> getUserTicket(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> returnParams = new HashMap<String, Object>();
        String remainingPaths = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String endOfThePath= "myTickets";
        String enotherPath = remainingPaths.substring(remainingPaths.indexOf(endOfThePath) + endOfThePath.length());
        try {
            Long longId = Long.parseLong(userId);
            User user = userService.getUserById(longId);
            List<Ticket> myTickets = ticketService.getTicketsByUser(user);
            returnParams.put("ticketsList", myTickets);
            returnParams.put("user", user);
        } catch (Exception e) {
            returnParams.put("error", e.getMessage());
        }
        if (enotherPath.length() > 6) {
            RequestDispatcher rd = request.getRequestDispatcher(enotherPath);
            request.setAttribute("returnParams", returnParams);
            rd.forward(request, response);
        }
        return returnParams;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tickets/freeTickets/**")
    public Map<String, Object> bookTicket(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        Map<String, Object> returnParams = new HashMap<String, Object>();
        List<Ticket> availableTicket = null;
        String remainingPaths = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String enotherPath = remainingPaths.substring("/tickets/freeTickets".length());
        try {
            availableTicket = ticketService.getAvailableTicket();
            returnParams.put("ticketsList", availableTicket);
        } catch (Exception e) {
           returnParams.put("error", e.getMessage());
        }
        if (enotherPath.length() > 6) {
            RequestDispatcher rd = request.getRequestDispatcher(enotherPath);
            request.setAttribute("returnParams", returnParams);
            rd.forward(request, response);
        }
        return returnParams;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/dateFrom/{dateFrom}/dateTo/{dateTo}/**")
    public Map<String, Object> filterByDate(HttpServletRequest request, @PathVariable String dateFrom, @PathVariable String dateTo) {
        Map<String, Object> returnParams = (Map<String, Object>) request.getAttribute("returnParams");
        List<Ticket> availableTicket = (List<Ticket>) returnParams.get("ticketsList");
        try {
            availableTicket = ticketService.filterTicketsByDate(availableTicket, dateFrom, dateTo);
            returnParams.put("ticketsList", availableTicket);
        } catch (Exception e) {
            returnParams.put("error", e.getMessage());
        }
        return returnParams;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/title/{title}/**")
    public Map<String, Object> filterByTitle(HttpServletRequest request, @PathVariable String title) {
        Map<String, Object> returnParams = (Map<String, Object>) request.getAttribute("returnParams");
        List<Ticket> availableTicket = (List<Ticket>) returnParams.get("ticketsList");
        try {
            availableTicket = ticketService.filterTicketsByTitle(availableTicket, title);
            returnParams.put("ticketsList", availableTicket);
        } catch (Exception e) {
            returnParams.put("error", e.getMessage());
        }
        return returnParams;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/category/{category}/**")
    public Map<String, Object> filterByCategory(HttpServletRequest request, @PathVariable String category) {
        Map<String, Object> returnParams = (Map<String, Object>) request.getAttribute("returnParams");
        List<Ticket> availableTicket = (List<Ticket>) returnParams.get("ticketsList");
        try {
            availableTicket = ticketService.filterTicketsByCategory(availableTicket, category);
            returnParams.put("ticketsList", availableTicket);
        } catch (Exception e) {
            returnParams.put("error", e.getMessage());
        }
        return returnParams;
    }
}