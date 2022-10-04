package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

        private final CustomerService customerService = new CustomerServiceImpl();
        @GetMapping("")
        public String index(Model model) {
            List<Customer> customerList = customerService.findAll();
            model.addAttribute("customers", customerList);
            return "/index";
        }

        @GetMapping("/create")
        public String create(Model model) {
            model.addAttribute("customer", new Customer());
            return "/create";
        }

        @GetMapping("/{id}/edit")
        public String edit(@PathVariable int id, Model model) {
            model.addAttribute("customer", customerService.findById(id));
            return "/edit";
        }

        @GetMapping("/{id}/delete")
        public String delete(@PathVariable int id, Model model) {
            model.addAttribute("customer", customerService.findById(id));
            return "/delete";
        }

        @GetMapping("/{id}/view")
        public String view(@PathVariable int id, Model model) {
            model.addAttribute("customer", customerService.findById(id));
            return "/view";
        }

        @PostMapping("/save")
        public String save(Customer customer, RedirectAttributes redirect) {
            customer.setId((int) (Math.random() * 10000));
            customerService.save(customer);
            redirect.addFlashAttribute("success", "Save customer successfully!");
            return "redirect:/customer";
        }

        @PostMapping("/update")
        public String update(Customer customer) {
            customerService.update(customer.getId(), customer);
            return "redirect:/customer";
        }

        @PostMapping("/delete")
        public String delete(Customer customer, RedirectAttributes redirect) {
            customerService.remove(customer.getId());
            redirect.addFlashAttribute("success", "Removed customer successfully!");
            return "redirect:/customer";
        }
    }

