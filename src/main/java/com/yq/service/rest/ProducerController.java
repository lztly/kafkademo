

package com.yq.service.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yq.domain.DeviceMessage;
import com.yq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Simple to Introduction
 * className: ProducerController
 *
 * @author yqbjtu
 * @version 2018/4/27 8:57
 */
@RestController
@RequestMapping(value = "/producer")
public class ProducerController {
    @Autowired
    ProducerService producerService;

    @ApiOperation(value = "send")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "topic", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "content", required = true, dataType = "string", paramType = "query")
    })
    @PostMapping(value = "/send", produces = "application/json;charset=UTF-8")
    public String create(@RequestParam  String topic, @RequestParam String content) {
        producerService.send(topic, content);
        return "{\"result\":\"ok\"}";
    }

    @ApiOperation(value = "devMsg")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "topic", defaultValue = "iiot.prod", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deviceId", value = "deviceId", defaultValue = "86b874260d224cf8870bef1df60bcfff",  required = true, dataType = "string", paramType = "query")
    })
    @PostMapping(value = "/devMsg", produces = "application/json;charset=UTF-8")
    public String createDeviceMsg(@RequestParam  String topic, @RequestParam String deviceId) {
        producerService.send(topic, deviceId);
        return "{\"result\":\"deviceId ok\"}";
    }

    @ApiOperation(value = "devJsonMsg")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "topic", defaultValue = "iiot.prod", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "jsonStr", value = "jsonStr", defaultValue = "jsonStr",  required = true, dataType = "DeviceMessage", paramType = "body")
    })
    @PostMapping(value = "/devJsonMsg", produces = "application/json;charset=UTF-8")
    public String createDeviceJsonMsg(@RequestParam  String topic, @RequestBody String jsonStr) {
        //String jsonStr = JSON.toJSONString(json);
        producerService.sendJson(topic, jsonStr);
        return "{\"result\":\"json ok\"}";
    }

    @ApiOperation(value = "notUsed")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "topic", defaultValue = "iiot.prod", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "json", value = "deviceId", defaultValue = "",  required = true, dataType = "DeviceMessage", paramType = "body")
    })
    @PostMapping(value = "/notUsed", produces = "application/json;charset=UTF-8")
    public String notUsed(@RequestParam  String topic, @RequestBody DeviceMessage msg) {
        JSONObject json = new JSONObject();
        json.put("result", "not used method");
        return json.toString();
    }
}
