package com.abdz.trialspbgit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdz.trialspbgit.dao.MeterDAO;
import com.abdz.trialspbgit.dao.PowerDAO;
import com.abdz.trialspbgit.dao.ProductDAO;
import com.abdz.trialspbgit.dao.RequestDAO;
import com.abdz.trialspbgit.dao.UserDAO;
import com.abdz.trialspbgit.enitity.Meter;
import com.abdz.trialspbgit.enitity.Power;
import com.abdz.trialspbgit.enitity.Product;
import com.abdz.trialspbgit.enitity.Request;
import com.abdz.trialspbgit.enitity.User;
import com.abdz.trialspbgit.form.PowerForm;
import com.abdz.trialspbgit.form.ProductForm;
import com.abdz.trialspbgit.form.RegisterForm;
import com.abdz.trialspbgit.form.RequestForm;
import com.abdz.trialspbgit.form.SmartMeterForm;

@Service
public class PowerService {
    
    public UserDAO userDAO;
    public ProductDAO productDAO;
    public RequestDAO requestDAO;
    public MeterDAO meterDAO;
    public PowerDAO powerDAO;

    @Autowired
    public PowerService(UserDAO userDAO, ProductDAO productDAO, RequestDAO requestDAO, MeterDAO meterDAO,
            PowerDAO powerDAO) {
        this.userDAO = userDAO;
        this.productDAO = productDAO;
        this.requestDAO = requestDAO;
        this.meterDAO = meterDAO;
        this.powerDAO = powerDAO;
    } 

    // public List<Product> validateProducts(List<Product> products)
    // {
    //     for(Product product: products)
    //     {

    //     }
    // }

    public List<Product> getAllProducts()
    {
        return productDAO.findAll();
    }

    public HashMap<Product, User> getUserAndProduct(List<Product> products)
    {
        HashMap<Product, User> mp = new HashMap<Product, User>();
        for(Product product : products)
        {
            mp.put(product,getUser(product.getUid()));
        }
        return mp;
    }

    
    public List<Request> getAllRecvRequests(int id)
    {
        return requestDAO.findAllRecievedRequest(id);
    }

    public List<Request> getAllSentRequests(int id)
    {
        return requestDAO.findAllSentRequest(id);
    }

    public HashMap<Request, User> getUserAndRequest(List<Request> requests)
    {
        
        HashMap<Request, User> mp = new HashMap<Request, User>();
        for(Request request : requests)
        {
            mp.put(request,getUser(request.getBuyerid()));
        }
        return mp;
    }

    public HashMap<Request, User> getUserAndProposal(List<Request> requests)
    {
        
        HashMap<Request,User> mp = new HashMap<Request, User>();
        for(Request request : requests)
        {
            mp.put(request, getUser(request.getSellerid()));
        }
        return mp;
    }

    public User getUser(int id)
    {
        return userDAO.findById(id);
    }

    public Meter getMeter(int id)
    {
        return meterDAO.findById(id);
    }

    public Product getProduct(int id)
    {
        return productDAO.findById(id);
    }


    public void createUser(User user)
    {
        userDAO.save(user);
    }

    public void createProduct(Product product)
    {
        productDAO.save(product);
    }

    public void createRequest(Request request)
    {
        requestDAO.save(request);
    }

    public int getPowerMode(int uid,String mode)
    {
        User user = getUser(uid);
        List<Power> powers = powerDAO.findByIdAndMode(user.getMid(), mode);
        int powerMode = 0;
        for(Power p : powers)
        {
            powerMode+=p.getQuantity();
        }
        return powerMode;
    }

    public int getAvailablePower(int uid)
    {
        
        int generated = getPowerMode(uid, "generated");
        int sold = getPowerMode(uid, "sold");
        int consumed = getPowerMode(uid, "consumed");
        int purchased = getPowerMode(uid, "purchased");

        return (generated + purchased) - (sold + consumed);

    }

    public int getPowerOnSale(int uid)
    {

        List<Product> products = productDAO.findByUser(uid);
        int sale = 0;
        for(Product p : products){
            sale += p.getQuantity();
        }
        return sale;

    }

    public Request createRequestFromForm(RequestForm requestForm, int uid)
    {
        Request request;
        try{
            Product product = getProduct(requestForm.getPid());
            request = new Request(uid, product.getUid(), requestForm.getPid(), "pending", requestForm.getRate(), requestForm.getQuantity(), requestForm.getMessage());
            requestDAO.save(request);
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
        return request;
    }

    public Product createProductFromForm(ProductForm productForm, int uid)
    {
        Product product;
        if(productForm.getQuantity() > (getAvailablePower(uid) - getPowerOnSale(uid))){
            return null;
        }
        try{
            product = new Product(uid, productForm.getRate(), productForm.getQuantity(), null);
            productDAO.save(product);
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
        return product;
    }

    public User createUserFromForm(RegisterForm registerForm)
    {
        User user;
        
        try{
            user = new User(registerForm.getMid(),registerForm.getWalletaccntno(),registerForm.getUsername(),registerForm.getGmail(),registerForm.getDescription());
            userDAO.save(user);
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
        return user;
    }

    public Request acceptRequest(int requestid, int uid)
    {
        User user = getUser(uid);
        Request request = requestDAO.findById(requestid);
        request.setStatus("accepted");
        requestDAO.update(request);
        Product product = getProduct(request.getPid());
        product.setQuantity(product.getQuantity() - request.getQuantity());
        productDAO.update(product);
        Power power = new Power(user.getMid(), "sold", request.getQuantity());
        powerDAO.save(power);
        return request;
    }

    public Request rejectRequest(int requestid)
    {
        Request request = requestDAO.findById(requestid);
        request.setStatus("rejected");
        requestDAO.update(request);
        return request;
    }

    public Request paymentCompleted(int requestid, int uid)
    {
        User user = getUser(uid);
        Request request = requestDAO.findById(requestid);
        request.setStatus("paid");
        requestDAO.update(request);
        Power power = new Power(user.getMid(), "purchased", request.getQuantity());
        powerDAO.save(power);
        return request;
    }

    

    

}
