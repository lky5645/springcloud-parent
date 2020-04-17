package com.ts.provider.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName PaymentBo
 * @Description TODO
 * @Author 86175
 * @Date 2020/3/26 14:28
 * @Params TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentBo implements Serializable{
    private Long id;
    private String serial;
}
