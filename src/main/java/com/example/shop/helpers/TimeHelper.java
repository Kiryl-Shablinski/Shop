package com.example.shop.helpers;

import com.example.shop.models.ItemModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimeHelper {

    public static List<ItemModel> getTime(List<ItemModel> list) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd.MM");
        for (ItemModel itemModel : list) {
            itemModel.setTimeFormat(simpleDateFormat.format(new Date(itemModel.getTime())));
        }
        return list;
    }
}
