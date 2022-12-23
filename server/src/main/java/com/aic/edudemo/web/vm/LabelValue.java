package com.aic.edudemo.web.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data  //增加setter、getter方法
@AllArgsConstructor //增加建構方法，其參數是所有成員變量
@NoArgsConstructor
public class LabelValue {
    private String label;
    private String value;
    private boolean selectable = true;

}
