package com.liang.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Liangxp
 * @date 2020/12/22 16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("id")
public class EsPerson {
    private String id;

    private String name;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
}
