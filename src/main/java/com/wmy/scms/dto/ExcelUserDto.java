package com.wmy.scms.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExcelUserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "团体名称", index = 0)
    private String teamName;

    @ExcelProperty(value = "姓名", index = 1)
    private String nickname;

    @ExcelProperty(value = "性别", index = 2)
    private String userSex;

    @ExcelProperty(value = "账号", index = 3)
    private String username;

    @ExcelProperty(value = "密码", index = 4)
    private String password;

    @ExcelProperty(value = "电话", index = 5)
    private String phone;
}
