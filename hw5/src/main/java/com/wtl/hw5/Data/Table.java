package com.wtl.hw5.Data;

import lombok.Data;

import java.io.Serializable;
import java.util.Vector;

@Data
public class Table implements Serializable {

    private Vector<ContactInfor> tableinfo;

    public Table(Vector<ContactInfor> info) {
        this.tableinfo = info;
    }
}
