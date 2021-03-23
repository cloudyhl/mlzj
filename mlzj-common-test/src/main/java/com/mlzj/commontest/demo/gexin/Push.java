//package com.mlzj.commontest.demo.gexin;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author yhl
// * @date 2020/4/22
// */
//public class Push {
//
//
//    private static String appId = "SFFT4AtQ3O7oRNmhZvDGp2";
//    private static String appKey = "kVvvGuOm3n8jlWdolAogQ2";
//    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
//    private static String masterSecret = "Mv8UlDpXtk6h8ATnnI8nZ7";
//    public static void main(String[] args) {
//
//        IGtPush push = new IGtPush(url, appKey, masterSecret);
//
//        NotificationTemplate template = new NotificationTemplate();
//        // 设置APPID与APPKEY
//        template.setAppId(appId);
//        template.setAppkey(appKey);
//
//
//        // STEP2：设置通知样式
//        Style0 style = new Style0();
//        // 设置通知栏标题与内容
//        style.setTitle("这是通知");
//        style.setText("这是内容");
//        // 配置通知栏图标
//        style.setLogo("icon.png");
//        // 配置通知栏网络图标
//        style.setLogoUrl("");
//        // 设置通知是否响铃，震动，或者可清除
//        style.setRing(true);
//        style.setVibrate(true);
//        style.setClearable(true);
//        style.setChannel("通知渠道id");
//        style.setChannelName("通知渠道名称");
//        style.setChannelLevel(3); //设置通知渠道重要性
//        template.setStyle(style);
//
//        List<String> appIds = new ArrayList<>();
//        appIds.add(appId);
//        // STEP3：设置推送其他参数
//        AppMessage message = new AppMessage();
//        message.setData(template);
//        message.setOffline(true);
//        message.setOfflineExpireTime(1000 * 600);
//        message.setAppIdList(appIds);
//
//        SingleMessage singleMessage = new SingleMessage();
//        singleMessage.setData(template);
//        Target target = new Target();
//        target.setClientId("1e1a23f1a569ca941e13fa36bd7ee3f7");
//        target.setAppId(appId);
//
//        // STEP4：执行推送
//        //IPushResult iPushResult = push.pushMessageToApp(message);
//        IPushResult iPushResult = push.pushMessageToSingle(singleMessage, target);
//        System.out.println(iPushResult.getResponse());
//    }
//}
