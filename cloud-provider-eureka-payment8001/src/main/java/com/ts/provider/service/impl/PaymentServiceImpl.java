package com.ts.provider.service.impl;

import com.ts.provider.bo.PaymentBo;
import com.ts.provider.dao.PaymentDao;
import com.ts.provider.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName PaymentServiceImpl
 * @Description TODO
 * @Author 86175
 * @Date 2020/3/26 14:59
 * @Params TODO
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int save(PaymentBo paymentBo) {
        return paymentDao.save(paymentBo);
    }

    @Override
    public int update(PaymentBo paymentBo) {
        return paymentDao.update(paymentBo);
    }

    @Override
    public PaymentBo findById(Long id) {
        return paymentDao.findById(id);
    }

    @Override
    public int delete(Long id) {
        return paymentDao.delete(id);
    }
}
