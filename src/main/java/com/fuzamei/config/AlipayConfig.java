package com.fuzamei.config;

/**
 * Created by fuzamei on 2018/10/10.
 */
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092100559329";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCN/OwLGYDiFrCdyJlQFbG5Y30yJMOO+nyd5MJRarYTEurhSqPbcVU1davyrkVVkpMkeMlp7CPGLdN384z0eXdOwNF1JCruleyKuIZAZoB1gbfwm/82AIk1u4awfS0BGsXejRzI47QkBOSmW6Rlg6L2M/VhTia27LjVHu/YDV2nS1sGJVGP3/HpZOHNC1ugyBVCVKvPCvVvrvdNkCncvRSuFRN1uJt9zEHMRts29kZIPu7MKuxLTyl6AFMDzOfdCQvewEMez3gAWAbLsUD6zhd3i/qdhb8YVoHlhQcGoy9I3ntZLiuaI0oOP2D3YMJGz8upI63BEv5YvkvjBI4KWOW1AgMBAAECggEAaIfjXuiPseLyc7bS59qQ1OMhsGDQd9qBE9zuEfJ1yOUMdn/00gFFse3HgQo7kRwvp5Ik9g4a6DOkk5KCGxmxdBEQIyVVVgCZ/CaM76yGtfuh50lxQdhzK1O7fQMqtr13HrjUkDDS1hGkI7Uj+nhG6Oqno9NdPR5ZlM0J1g1A/X+n47ttqplJulC7gqH1K3WJwWOrXgHe9KfjwsRR5TkSAukKwkVfJWd9y5XN87BDnq+mXErd/+Wf+Gzpuj8W+eN9qF6A4/SwPKET5cO6VMMqcWQBj/GBuZchED5PsWg/dkPOHPlMkKrNZzz3I2T5AysDMIEZv7L2P+KtkKf+8HuXAQKBgQDh806pl9UFvQCvSacU9cOWmjluk5mYzE6KlFEJNMiJkv2wxR1V/HuGExEulkRrhHWGtGn5sRz9OXj49ic2TwEPdoQ/5sR4+U9HNa5060NNsCJVXm9MOolzOOOV7ftyzuALjpdZFkxjY3FpRsXzZqSd7XBI4Bf3r6drHiaRtJI4+QKBgQCg3wj9w/cefzFxGvEzyuxxHyR3rF4QftXqsXpxuOsfxx1Bb55ykcrLf2CUmDb5HsbKYUQZC4LdsT40HB4RWnHPQ+Cd4k/okNqQF/G7lqpUr1pvBSxXueLRmI5s+vwd8Yz+fsgCuYnpalteSICCgxpLFo7K4CYp1ZG78xATYaXdnQKBgHWQ17k+m0SlFxodxKYPSnmuPXvfqOfsRmGs0o1rvXx30zCLH7yB5vPYL/SBk5AuF8jDTvDwyxinMt63laeYbADTtfRZ6pjXf/yZXfDQCPD/WJ36yQZs3t/pFeIJNfCC2+inhPCBvbVpJLWWcaE4IydZSDdjApWJ/B6sTX0662phAoGAK+OtuE42pYNeIdWP6Ol8CIDOmjfUkfuIsTcqiMxCmbDhMnEZcXiFM2/VEbVVijHzWlOfGJYjZghtSBpDuchLNJj4Nxr/YhvjBV0C/oLkhaYxrghjSKvWTgipEuKG1kSIiwZb2yJzldMGinqNVQ4fpkzJOGBfCRrTX8u4nxENB6ECgYEAgWAe1Tu4XKIDWfilCJuk/pzo0rpnI6MxsF0wAC9eS07sQ5rhhPTODVok9UREVb7YDaZJ0w2Om/HQz1giA/e9mFIsux5jTsoC4VOi2+/h4t05wW1i8HEr+AKq5VdL0t2FqiVtVkfA2eD7parkEvDgW9Cs2TNiySnWcBNnFB3G6SI=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs+1YvaTTvTSgpMMI630R8MQqAY7C3Rb9r/R2IG/Uxamzwch3qOhJmh624IIQizo1RzFak4d74RikRe67bK08JZyon99DR5fC8DG4+pBapuM/EnKmGTDOjAEwUPuaug73FJr+oPJE37WqHiEyZrhXb2bOui6oHv+3npSayKdcNznJUDY79vsBXpnhbFi/g1ggLGpZyzTy9DIo9PtLOW7KVY04RfMEeDP51dQGE1C4MKsQhkcCGuDf6+G/CNSPYskqP2e4STduyDEhS23MSyjnxDT9xQ7IS1MqjLfSMJY1MPgq9GG8kHYRLqkPD51LD22oa5NgrUebQN3TkJvh5ZlfLQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://e306096b.ngrok.io/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
//	public static String notify_url = "notify.dengw.online/do/9634e0ed-2824-4990-8307-ee82bb70349e";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://e306096b.ngrok.io/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
//	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";

}
