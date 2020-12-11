package com.wtl.hw5.controller;

import com.wtl.hw5.Dao.ContactJpaRepository;
import com.wtl.hw5.Data.ContactInfor;
import com.wtl.hw5.Data.Table;
import com.wtl.hw5.Service.TableAlters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AddController {

    // JPA
    @Autowired
    private ContactJpaRepository contactJpaRepository;

    // 先登录
    @RequestMapping("/add")
    public String showAdd(ContactInfor cont, Model model, HttpServletRequest request) {
        if (null == request.getSession().getAttribute("login"))
            return "redirect:/login";
        else {
            model.addAttribute("cont", cont);
            return "add";
        }
    }

    @GetMapping("/checkadd")
    public String redirectAdd() {
        return "redirect:/add";
    }


    @PostMapping("/checkadd")
    public String checkAdd(ContactInfor cont, HttpServletRequest request, Model model) {
        List<ContactInfor> list = contactJpaRepository.findByContactname(cont.getContactname());
        if (0 == list.size()) {        //   没有重复的
            contactJpaRepository.save(cont);
            return "redirect:/main";
        } else {                         //   重复
            cont.setMessage("联系人名称已存在");
            cont.setContactname("");
            return showAdd(cont, model, request);
        }
    }

    // ajax 检测电话号码 是否重复
    @ResponseBody
    @PostMapping("/checktel")
    public int checkTel(@RequestParam(name="tel")String tel, HttpServletRequest request) {
        Table table = (Table)request.getSession().getAttribute("table");
        boolean result = TableAlters.hasTel(contactJpaRepository.findAll(), tel);
        if (result)
            return 1;
        else
            return 0;
    }
}
