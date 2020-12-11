package com.wtl.hw5.Service;

import java.util.List;
import java.util.Vector;

public class TypeTransformer {
    // 列表转数组向量
    public static<E> Vector<E> listToVector(List<E> list) {
        return new Vector<>(list);
    }
}
