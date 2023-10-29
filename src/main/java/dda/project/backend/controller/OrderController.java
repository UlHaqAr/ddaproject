/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.controller;

import dda.project.backend.entity.AddressModel;
import dda.project.backend.entity.CustomerModel;
import dda.project.backend.entity.ItemModel;
import dda.project.backend.entity.OrderDetailModel;
import dda.project.backend.entity.OrderModel;
import dda.project.backend.entity.OrderToItemsModel;
import dda.project.backend.entity.PaymentModel;
import dda.project.backend.entity.UserModel;
import dda.project.backend.service.AddressService;
import dda.project.backend.service.CustomerService;
import dda.project.backend.service.ItemService;
import dda.project.backend.service.OrderDetailService;
import dda.project.backend.service.OrderService;
import dda.project.backend.service.OrderToItemsService;
import dda.project.backend.service.PaymentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final AddressService addressService;
    private final ItemService itemService;
    private final OrderDetailService orderDetailService;
    private final OrderToItemsService orderToItemsService;
    private final PaymentService paymentService;

    public OrderController(OrderService orderService, CustomerService customerService,
                           AddressService addressService, ItemService itemService,
                           OrderDetailService orderDetailService,
                           OrderToItemsService orderToItemsService, PaymentService paymentService)
    {
        this.orderService = orderService;
        this.customerService = customerService;
        this.addressService = addressService;
        this.itemService = itemService;
        this.orderDetailService = orderDetailService;
        this.orderToItemsService = orderToItemsService;
        this.paymentService = paymentService;
    }

    // 1. CUSTOMER
    // add customer, get customer by id, get customer by name
    @PostMapping("/customer")
    public void addCustomer(@RequestBody CustomerModel customer)
    {
        this.customerService.saveCustomer(customer);
    }

    @GetMapping("/customer/id/{custid}")
    public CustomerModel getCustomerById(@PathVariable("custid") Long custid)
    {
        return this.customerService.getCustomer(custid);
    }

    @GetMapping("/customer/name/{custname}")
    public CustomerModel getCustomerByName(@PathVariable("custname") String custname)
    {
        return this.customerService.getCustomer(custname);
    }

    @GetMapping("/customer")
    public List<CustomerModel> getAllCustomerByName()
    {
        return this.customerService.getAllCustomer();
    }


    // 2. ADDRESS
    // add address, get address by id
    @PostMapping("/address")
    public void addAddress(@RequestBody AddressModel addressModel)
    {
        this.addressService.saveAddress(addressModel);
    }

    @GetMapping("/address/{addid}")
    public AddressModel getAddress(@PathVariable("addid") Integer addid)
    {
        return this.addressService.getAddress(addid);
    }

    @GetMapping("/address")
    public List<AddressModel> getAllAddress()
    {
        return this.addressService.getAllAddress();
    }

    // 3. USER
    @PostMapping("/user/register")
    public void registerUser(@RequestBody UserModel userModel)
    {
        this.customerService.registerUser(userModel);
    }

    @GetMapping("/user/{userid}")
    public UserModel getUserById(@PathVariable("userid") Long userid)
    {
        return this.customerService.getUserById(userid);
    }

    @PostMapping("/user/login")
    public boolean loginForUser(@RequestBody UserModel userModel)
    {
        return this.customerService.loginAndGetUserDetails(userModel);
    }

    // 4. ITEM
    @GetMapping("/item/{itemid}")
    public ItemModel getItem(@PathVariable("itemid") Long itemid)
    {
        return this.itemService.getItemForId(itemid);
    }

    @GetMapping("/item")
    public List<ItemModel> getAllItem()
    {
        return this.itemService.getAllItem();
    }

    @PostMapping("/item")
    public void addItem(@RequestBody ItemModel itemModel)
    {
        this.itemService.addItem(itemModel);
    }

    // 5. ORDERDETAIL
    @GetMapping("/detail/{orderdetailid}")
    public OrderDetailModel getOrderDetail(@PathVariable("orderdetailid") Long orderdetailid)
    {
        return this.orderDetailService.getOrderDetailForId(orderdetailid);
    }

    @PostMapping("/detail")
    public void addOrderDetail(@RequestBody OrderDetailModel orderDetailModel)
    {
        this.orderDetailService.addOrderDetail(orderDetailModel);
    }

    // 6. ORDERTOITEMS
    @GetMapping("/map/{ordertoitemid}")
    OrderToItemsModel getEntryForId(@PathVariable("ordertoitemid") Long ordertoitemid)
    {
        return this.orderToItemsService.getEntryForId(ordertoitemid);
    }

    @GetMapping("/map/detail/{orderDetailId}")
    List<OrderToItemsModel> getEntriesForOrderDetailId(@PathVariable("orderDetailId") Long orderDetailId)
    {
        return this.orderToItemsService.getEntriesForOrderDetailId(orderDetailId);
    }

    @GetMapping("/map/item/{itemId}")
    List<OrderToItemsModel> getEntriesForItemId(@PathVariable("itemId") Long itemId)
    {
        return this.orderToItemsService.getEntriesForItemId(itemId);
    }

    @PostMapping("/map")
    void addEntry(@RequestBody OrderToItemsModel orderToItemsModel)
    {
        this.orderToItemsService.addEntry(orderToItemsModel);
    }

    // 7. PAYMENT
    @GetMapping("/pay/{payId}")
    PaymentModel getPaymentForId(@PathVariable("payId") Long payId)
    {
        return this.paymentService.getPaymentForId(payId);
    }

    @GetMapping("/pay")
    List<PaymentModel> getAllPayment()
    {
        return this.paymentService.getAllPayment();
    }

    @GetMapping("/pay/order/{orderId}")
    PaymentModel getPaymentForOrderId(@PathVariable("orderId") Long orderId)
    {
        return this.paymentService.getPaymentForOrderId(orderId);
    }

    @PostMapping("/pay")
    void addPayment(@RequestBody PaymentModel paymentModel)
    {
        this.paymentService.addPayment(paymentModel);
    }

    @PostMapping("/pay/{payId}")
    void updatePayment(@PathVariable("payId") Long payId, @RequestBody PaymentModel newModel)
    {
        // amount and pay status can be updated for a payment
        this.paymentService.updatePayment(payId, newModel);
    }

    // 8. ORDER
    @GetMapping("/orders/user/{userid}")
    public OrderModel getOrdersForUser(@PathVariable("userid") Long userid)
    {
        return this.orderService.getOrderForUser(userid);
    }

    @GetMapping("/orders")
    public List<OrderModel> getAllOrders()
    {
        return this.orderService.getAllOrders();
    }

    @GetMapping("/orders/{orderId}")
    OrderModel getOrderById(@PathVariable("orderId") Long orderId)
    {
        return this.orderService.getOrderById(orderId);
    }

    @PostMapping("/orders")
    void addOrder(@RequestBody OrderModel orderModel)
    {
        this.orderService.addOrder(orderModel);
    }

}

