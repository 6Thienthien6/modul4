package com.cg.controller;


import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Withdraw;
import com.cg.repository.DepositRepository;
import com.cg.service.DepositService;
import com.cg.service.customer.ICustomerService;
import com.cg.service.withdraw.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private WithdrawService withdrawService;

    @GetMapping
    public ModelAndView showListCustomer() {
        ModelAndView modelAndView = new ModelAndView("customer/index");
        Iterable<Customer> customers = customerService.findAllByDeletedIsFalse();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/createcustomer");
        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView doCreate(@Validated @ModelAttribute Customer customer, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/createcustomer");
        customer.setBalance(new BigDecimal(0L));
        customer.setDeleted(false);
        customerService.save(customer);
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "Create successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView doEdit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            modelAndView.addObject("error", "Customer  does not exist!");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateCustomer(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        customer.setDeleted(false);
        customerService.save(customer);
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer was update!");
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        System.out.println(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/remove");
        if (customer.isPresent()) {
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            modelAndView.addObject("error", "Customer does not exist!");
            return modelAndView;
        }
    }

    @PostMapping("/remove")
    public ModelAndView removeCustomer(@ModelAttribute("customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView("/customer/index");
        Optional<Customer> customerOptional = customerService.findById(customer.getId());

        System.out.println(customerOptional.isPresent());
        customerOptional.get().setDeleted(true);
        customerService.save(customerOptional.get());
        Iterable<Customer> customers = customerService.findAllByDeletedIsFalse();
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("message", "Remove successfully ");
        return modelAndView;
    }

    @GetMapping("/deposit/{id}")
    public ModelAndView showFormDeposit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/deposit");
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            modelAndView.setViewName("error-404");
            return modelAndView;
        }
        modelAndView.addObject("customer", customer.get());
        modelAndView.addObject("deposit", new Deposit());
        return modelAndView;
    }

    @PostMapping("/deposit/{id}")
    public ModelAndView depositAction(@PathVariable("id") Long id, @ModelAttribute Deposit deposit) {
        ModelAndView modelAndView = new ModelAndView("customer/deposit");
        Optional<Customer> customerOptional = customerService.findById(id);

        Customer customer = customerOptional.get();
        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = deposit.getTransactionAmount();
        BigDecimal newBalance = currentBalance.add(transactionAmount);

        deposit.setCustomer(customer);
        customer.setBalance(newBalance);


        depositRepository.save(deposit);
        Customer newCustomer = customerService.save(customer);
        modelAndView.addObject("customer", newCustomer);
        modelAndView.addObject("deposit", new Deposit());
        modelAndView.addObject("message", "Deposit successfully");

        return modelAndView;
    }

    @GetMapping("/withdraw/{id}")
    public ModelAndView showFormWithdraw(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/withdraw");
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            modelAndView.setViewName("error-404");
            return modelAndView;
        }
        modelAndView.addObject("customer", customer.get());
        modelAndView.addObject("withdraw", new Withdraw());
        return modelAndView;
    }

    @PostMapping("/withdraw/{id}")
    public ModelAndView withdrawAction(@PathVariable("id") Long id, @ModelAttribute Withdraw withdraw) {
        ModelAndView modelAndView = new ModelAndView("customer/withdraw");
        Optional<Customer> customerOptional = customerService.findById(id);

        Customer customer = customerOptional.get();
        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = withdraw.getTransactionAmount();
        BigDecimal newBalance = currentBalance.subtract(transactionAmount);
        if (transactionAmount.compareTo(currentBalance) > 0) {
//            modelAndView.addObject("error", "Account balance is not enough to make a transaction!!!");
            modelAndView.addObject("error", "tiền thì ai cũng muốn nhưng mà đmm ảo tưởng ít thôi , khốn nạn !!!");
            Customer newCustomer = customerService.save(customer);
            modelAndView.addObject("customer", newCustomer);
            modelAndView.addObject("withdraw", new Withdraw());
            return modelAndView;
        } else {
            withdraw.setCustomer(customer);
            customer.setBalance(newBalance);
            withdrawService.save(withdraw);
            withdraw.setId(0L);
            Customer newCustomer = customerService.save(customer);
            modelAndView.addObject("customer", newCustomer);
            modelAndView.addObject("withdraw", new Withdraw());
            modelAndView.addObject("message", "Withdraw successfully");
            return modelAndView;
        }

    }
}
