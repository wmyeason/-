package com.wmy.scms.service;

import com.wmy.scms.entity.Item;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 *
 * @since 2023-04-01
 */
public interface ItemService {
    IPage<Item> getItemByItemCondition(Page<Item> page, Item item);

    Item getOneItemByCondition(Item item);

    int addItem(Item item);

    int modifyItem(Item item);

    int removeItem(int itemId);

    List<Item> getItemTemplateList();

    Item getItemTemplateDetail(Item item);

    Item isStartItem(Integer itemId);
}
