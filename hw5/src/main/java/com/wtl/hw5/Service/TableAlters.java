package com.wtl.hw5.Service;

import com.wtl.hw5.Data.ContactInfor;
import com.wtl.hw5.Data.Table;

import java.util.List;
import java.util.Vector;

public class TableAlters {

    // 判断一个用户信息是否可以被增加
    public static boolean checkValidAdd(Table table, ContactInfor infor) {
        boolean isvalid = true;
        Vector<ContactInfor> list = table.getTableinfo();
        for (int i = 0; i < list.size() && isvalid; i++) {
            if (list.elementAt(i).getContactname().equals(infor.getContactname()))
                isvalid = false;
        }
        return isvalid;
    }

    // 更新指定元素
    public static boolean alterElem(Table table, ContactInfor infor) {
        int index = -1;
        Vector<ContactInfor> list = table.getTableinfo();
        for (int i = 0; i < list.size() && -1 == index; i++) {
            if (list.elementAt(i).getContactname().equals(infor.getContactname()))
                index = i;
        }

        if (index != -1) { //如果找到替换
            list.set(index, infor);
            return true;
        } else {
            return false;
        }
    }

    // 判断是否有重复的电话号码
    public static boolean hasTel(List<ContactInfor> info, String tel) {
        boolean hasTel = false;
        for (int i = 0; i < info.size() && !hasTel; i++) {
            if (info.get(i).getTel().equals(tel))
                hasTel = true;
        }
        return hasTel;
    }
}
