package com.wmy.scms.dto;

import com.wmy.scms.entity.Mainmenu;
import com.wmy.scms.entity.Submenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private Mainmenu mainmenu;
    private List<Submenu> mlist;
}
