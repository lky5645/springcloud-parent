package com.ts.provider.util;

/**
 * @ClassName CommonDao
 * @Description TODO
 * @Author 86175
 * @Date 2020/3/26 14:38
 * @Params TODO
 */
public interface CommonDao<T> {
    int save(T t);
    int update(T t);

}
