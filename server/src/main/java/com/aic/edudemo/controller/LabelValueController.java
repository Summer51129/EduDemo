package com.aic.edudemo.controller;

import com.aic.edudemo.service.LabelValueService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController //return的值會直接被預設為Response body回傳
@RequestMapping("/api")
public class LabelValueController {
    private final LabelValueService labelValueService;

    @GetMapping("/jobsLabelValues")
    public Object jobsLabelValues(){
        return this.labelValueService.findAllJobsAsLabelValues();
    }

    @GetMapping("/departmentsLabelValues")
    public Object departmentsLabelValues(){
        return this.labelValueService.findAllDepartmentsAsLabelValues();
    }

}
