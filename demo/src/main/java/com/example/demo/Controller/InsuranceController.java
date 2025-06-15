package com.example.demo.Controller;

import com.example.demo.Model.InsuranceModel;
import com.example.demo.Service.InsuranceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class InsuranceController {
    private Map<Long, Boolean> buttonStatus = new HashMap<>(); // สถานะของปุ่มแต่ละปุ่ม
    private List<InsuranceModel> cartItems = new ArrayList<>();

    @Autowired
    private InsuranceService insuranceService;

    private boolean isButtonDisabled = false;
    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("nameShop", "ประกันทุกย่างก้าว");
        return "home";
    }

    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("nameShop", "ประกันทุกย่างก้าว");
        return "home";
    }
    @GetMapping("/shop")
    public String showShop(Model model) {

        List<InsuranceModel> products = insuranceService.getAllProducts();
        for (InsuranceModel product : products) {
            buttonStatus.putIfAbsent(product.getId(), false);
        }
        model.addAttribute("nameShop", "ประกันทุกย่างก้าว");
        model.addAttribute("products", products);
        model.addAttribute("cartCount", cartItems.size());
        model.addAttribute("buttonStatus", buttonStatus);
        return "shop";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam Long productId, @RequestParam String name, @RequestParam String typeName, @RequestParam int price, @RequestParam String description, RedirectAttributes redirectAttributes, Model model) {
        cartItems.add(new InsuranceModel(productId, name, typeName, price, description,null));
        buttonStatus.put(productId, true);
        model.addAttribute("nameShop", "ประกันทุกย่างก้าว");
        redirectAttributes.addFlashAttribute("cartCount", cartItems.size());
        model.addAttribute("buttonStatus", buttonStatus);
        return "redirect:/shop";
    }

    @GetMapping("/summary")
    public String showSummary(Model model) {
        int cartCount = cartItems.size();
        int totalPrice = insuranceService.sumCartItems(cartItems);
        model.addAttribute("nameShop", "ประกันทุกย่างก้าว");
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartCount", cartCount);
        model.addAttribute("totalPrice", totalPrice);
        return "summary";
    }

    @PostMapping("/removeItem")
    public String removeItem(@RequestParam("productId") Long productId, Model model, RedirectAttributes redirectAttributes) {
        insuranceService.removeItemFromCart(productId, cartItems);
        int totalPrice = insuranceService.sumCartItems(cartItems);
        buttonStatus.put(productId, false);
        model.addAttribute("nameShop", "ประกันทุกย่างก้าว");
        redirectAttributes.addFlashAttribute("cartItems", cartItems);
        model.addAttribute("cartCount", cartItems.size());
        model.addAttribute("totalPrice", totalPrice);
        return "redirect:/summary";
    }

    @PostMapping("/purchaseHistory")
    public String purchaseHistory(@RequestParam("productId") List<Long> productIds, @RequestParam("totalPrice") Integer totalPrice, Model model){
        insuranceService.savePurchaseHistory(productIds, totalPrice);
        model.addAttribute("nameShop", "ประกันทุกย่างก้าว");
        model.addAttribute("message", "ชำระเงินสำเร็จ! กลับสู่หน้าแรก");
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "summary";
    }

    @GetMapping("/success")
    public String success(Model model){
        cartItems = new ArrayList<>();
        List<InsuranceModel> products = insuranceService.getAllProducts();
        for (InsuranceModel product : products) {
            buttonStatus.put(product.getId(), false);
        }
        return "home";
    }
}


