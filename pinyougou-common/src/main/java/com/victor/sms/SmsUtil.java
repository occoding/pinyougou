package com.victor.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Component;

@Component
public class SmsUtil {
    private   String accessKeyId=PropertiesUtil.getProperty("ali.msg.accessKeyId","LTAIiF8i9jwv3zPp");
    private   String secret=PropertiesUtil.getProperty("ali.msg.accessKeySecret","Bh8VhJVjZqpBxAQhqqXA5c8dSVAYft");
    private   String SignName=PropertiesUtil.getProperty("ali.msg.SignName","occoding通用");
    private   String RegionId=PropertiesUtil.getProperty("ali.msg.RegionId","cn-hangzhou");
    private   String Domain=PropertiesUtil.getProperty("ali.msg.Domain","dysmsapi.aliyuncs.com");
    private   String TemplateCode=PropertiesUtil.getProperty("ali.msg.TemplateCode","SMS_172741582");
    private   String Version=PropertiesUtil.getProperty("ali.msg.Version","2017-05-25");



    public void sendMessage(String phoneNumber,String templateParam){

        DefaultProfile profile = DefaultProfile.getProfile(RegionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(Domain);
        request.setVersion(Version);
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", RegionId);
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "occoding通用");
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("TemplateParam", templateParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
