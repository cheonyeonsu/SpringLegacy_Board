package com.springex.controller;
import com.springex.dto.TodoDTO;
import com.springex.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("/list")
    public void list(Model model) {
        log.info("todo list...............");

        model.addAttribute("dtoList",todoService.getAll()); //dtoList를 todoService의 getAll에 담아서 전달.
    }

    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register~~~~~");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes ) {
        log.info("POST todo register~~~~~");

        //에러가 났을 경우만 거침.
        if(bindingResult.hasErrors()) {
            log.info("has errors!");
            //addFlashAttribute -> 1회용으로 담아서 넘김
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/todo/register";
        }

        log.info(todoDTO);
        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping({"/read","/modify"}) //RequestParam-> tno의 값 인식하렴
    public void read(@RequestParam(name ="tno") Long tno, Model model) {

        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto",todoDTO);

    }

    @PostMapping("/remove")
    public String remove(@RequestParam(name ="tno") Long tno,
                         RedirectAttributes redirectAttributes) {

        log.info("-------------remove------------");
        log.info("tno: " + tno);
        todoService.remove(tno);

        return "redirect:/todo/list";
    }
    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("has errors~~~~~~~~~~~~~~");
            redirectAttributes.addFlashAttribute("errors",
                    bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);

        return "redirect:/todo/list";
    }



}