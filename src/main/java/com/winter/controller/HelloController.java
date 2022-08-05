package com.winter.controller;

import com.alibaba.fastjson.JSON;
import com.winter.utils.HttpClientUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

//nohup java -jar router.jar > Log.log &

@RestController
@RequestMapping("router")
public class HelloController {


        @PostMapping(value = "/GwMessage")
    public String GwMessage(String phone) {

        System.out.println("------" + phone);
        Map<String, String> param = new HashMap<>();
        param.put("phone", phone);
        String result = HttpClientUtil.sendPostByJson("http://223.71.183.52:8099/GwMessage", JSON.toJSONString(param), 0);

        return result;
    }


    /**
     * 解决跨域请求数据
     *
     * @param response
     * @param callBack 前端回调函数名
     * @return
     */
    @RequestMapping(value = "/GwMessageP")
    public void getUser(HttpServletResponse response, @RequestParam String callBack, @RequestParam String phone) {
        response.setContentType("text/javascript");
        Writer writer = null;
        try {
            writer = response.getWriter();
            writer.write(callBack + "(");
            writer.write(GwMessage(phone));
            writer.write(");");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                writer = null;
            }
        }
    }

}