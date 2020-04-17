package com.ts.provider.dao;

import com.ts.provider.bo.PaymentBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName PaymentDao
 * @Description TODO
 * @Author 86175
 * @Date 2020/3/26 14:30
 * @Params TODO
 */
@Mapper
//@Repository
public interface PaymentDao {

    public int save(PaymentBo paymentBo);
    int update(PaymentBo paymentBo);
    PaymentBo findById(@Param("id") Long id);
    int delete(@Param("id") Long id);
}
