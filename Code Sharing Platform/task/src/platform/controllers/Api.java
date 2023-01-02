package platform.controllers;

import org.springframework.web.bind.annotation.*;
import platform.dtos.CodeDto;
import platform.dtos.NewCodeDto;
import platform.services.CodeService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class Api {

    private final CodeService codeService;

    public Api(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/api/code/{id}")
    public CodeDto getCode(@PathVariable String id) {
        return codeService.getCode(id);
    }

    @PostMapping("/api/code/new")
    public Map<String,String> setCode(@RequestBody NewCodeDto codeDto) {
        String id = codeService.addCode(codeDto);
        System.out.println("ID: " + id);
        return Map.of("id", id);
    }

    //GET /api/code/latest should return a JSON array with 10 most recently uploaded code snippets sorted from the newest to the oldest.
    @GetMapping("/api/code/latest")
    public List<CodeDto> getLatest10() {
        return codeService.getLatest10();
    }
}
