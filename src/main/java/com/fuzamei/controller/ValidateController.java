package com.fuzamei.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.fuzamei.config.AlipayConfig;
import com.fuzamei.pojo.BeanBO;
import com.fuzamei.pojo.BeanBO2;
import com.fuzamei.pojo.BeanCustom;
import com.fuzamei.pojo.beanBO3.*;
import com.fuzamei.validategroupd.First;
import com.fuzamei.validategroupd.Second;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ylx
 * Created by fuzamei on 2018/10/8.
 */
@Api(value = "校验类values",tags = "校验类tags",description = "校验类desc")
@RestController
@RequestMapping("/validate")
public class ValidateController {

    @CrossOrigin
    @ApiOperation(value = "校验方法1的value",notes = "校验方法1的note")
    @RequestMapping(value="/v1",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public String validate(@Validated @RequestBody BeanBO beanBO){
        System.out.println(beanBO.getName());
        System.out.println(beanBO.getAddress());
        System.out.println(beanBO.getV1());
        System.out.println(beanBO.getV2());
        System.out.println(beanBO.getV3());
        return "success";
    }

    @CrossOrigin
    @ApiOperation(value = "校验方法2的value",notes = "校验方法2的note(与v3方法一样，只是采用了另外一组进行数据校验)")
    @RequestMapping(value="/v2",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public String validate2(@Validated({First.class}) @RequestBody BeanBO2 beanBO2){
        System.out.println(beanBO2.getId());
        System.out.println(beanBO2.getName());
        System.out.println(beanBO2.getUsername());
        System.out.println(beanBO2.getPassword());
        System.out.println(beanBO2.getOther());
        return "success";
    }

    @CrossOrigin
    @ApiOperation(value = "校验方法3的value",notes = "校验方法3的note(与v2方法一样，只是采用了另外一组进行数据校验)")
    @RequestMapping(value="/v3",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public String validate3(@Validated({Second.class}) @RequestBody BeanBO2 beanBO2){
        System.out.println(beanBO2.getId());
        System.out.println(beanBO2.getName());
        System.out.println(beanBO2.getUsername());
        System.out.println(beanBO2.getPassword());
        System.out.println(beanBO2.getOther());
        return "success";
    }

    @CrossOrigin
    @ApiOperation(value = "校验方法4的value",notes = "校验方法4的note(按照顺序对参数进行校验)")
    @RequestMapping(value="/v4",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public String validate4(@Validated(Group.class) @RequestBody BeanBO3 beanBO3){
        System.out.println(beanBO3.getAddress());
        System.out.println(beanBO3.getName());
        System.out.println(beanBO3.getUsername());
        System.out.println(beanBO3.getPassword());
        System.out.println(beanBO3.getAge());
        return "success";
    }

    @CrossOrigin
    @ApiOperation(value = "校验方法5的value",notes = "校验方法4的note(加了自定义注解)")
    @RequestMapping(value="/v5",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public String validate5(@Validated @RequestBody BeanCustom beanCustom){
//        System.out.println(beanCustom.getBook());
        return "success";
    }



    @CrossOrigin
    @RequestMapping(value="/notify")
    public String notify(HttpServletRequest request) throws Exception{
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        System.out.println(signVerified);
        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
            System.out.println("success from notify url");
//            out.println("success");

        }else {//验证失败
//            out.println("fail");

            System.out.println("fail from notify url");

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }

        return "success";
    }


}
