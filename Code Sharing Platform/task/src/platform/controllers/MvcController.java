package platform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.dtos.CodeDto;
import platform.services.CodeService;

import java.util.List;

@Controller
public class MvcController {

    private final CodeService codeService;

    public MvcController(CodeService codeService) {
        this.codeService = codeService;
    }


    @GetMapping("/code/{id}")
    public String getCode(Model model, @PathVariable String id) {
        var codeDto = codeService.getCode(id);
        model.addAttribute("code", codeDto);
        return "code";
    }

    @GetMapping("/code/new")
    public String getUpdateCode() {
        return "addCode";
    }

    @GetMapping("/code/latest")
    public String getLatest10(Model model) {
        model.addAttribute("codes", codeService.getLatest10());
        return "latest";
    }
}
