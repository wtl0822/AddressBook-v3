package com.wtl.hw5.controller;

import com.wtl.hw5.Dao.ContactJpaRepository;
import com.wtl.hw5.Data.ContactInfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AlterController {

    @Autowired
    private ContactJpaRepository contactJpaRepository;

    // 访问修改联系人
    @PostMapping("/alter")
    public String showAlter(HttpServletRequest request, @ModelAttribute(name = "name") String name, Model model) {
        model.addAttribute("cont", contactJpaRepository.findByContactname(name).get(0));
        return "alter";
    }

    // 直接访问修改联系人 --- 直接弹回去
    @GetMapping("/alter")
    public String redirectAlter() {
        return "redirect:/main";
    }

    // 直接访问判断联系人修改 直接重定向回去
    @GetMapping("/checkalter")
    public String redirectCheckAlter() {
        return "redirect:/main";
    }

    // 修改联系人信息
    @PostMapping("/checkalter")
    public String checkAlter(ContactInfor infor, HttpServletRequest request) {
        contactJpaRepository.save(infor);
        return "redirect:/main";
    }

    // 删除联系人，
    @GetMapping("/del")
    public String redirectDel() {
        return "redirect:/main";
    }

    // 删除联系人
    @PostMapping("/del")
    public String DeleteContact(@ModelAttribute(value = "name") String name, HttpServletRequest request) {
        contactJpaRepository.deleteByContactname(name);
        return "redirect:/main";
    }
}
