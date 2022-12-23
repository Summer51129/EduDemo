package com.aic.edudemo.service;

import com.aic.edudemo.repository.DepartmentRepository;
import com.aic.edudemo.repository.JobRepository;
import com.aic.edudemo.web.vm.LabelValue;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Log4j2
public class LabelValueService {
    private final DepartmentRepository departmentRepository;
    private final JobRepository jobRepository;
    public List<LabelValue> findAllDepartmentsAsLabelValues(){
        List<Object[]> source=this.departmentRepository.findDepartmentLabelValue();
        log.info("Departments:{}",source);
        //透過loop映射label value資料
        List<LabelValue> result=new ArrayList<>();
        for(Object[] data:source){
            LabelValue temp=new LabelValue();
            temp.setValue(String.valueOf((Integer)data[0]));
            temp.setLabel((String)data[1]);
            result.add(temp);
        }
        return result;
    }
    public List<LabelValue> findAllJobsAsLabelValues(){
        List<Object[]>source=this.jobRepository.findJobLabelValue();
        log.info("Jobs:{}",source);
        return source.stream().map(data ->{
            LabelValue temp=new LabelValue();
            temp.setValue((String)data[0]);
            temp.setLabel((String)data[1]);
            return temp;
        }).collect(Collectors.toList());
    }
}
