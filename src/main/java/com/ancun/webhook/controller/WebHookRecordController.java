package com.ancun.webhook.controller;

import com.ancun.webhook.base.PageContent;
import com.ancun.webhook.base.RespBody;
import com.ancun.webhook.model.WebHookRecord;
import com.ancun.webhook.service.WebHookRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/5
 */
@Controller
@RequestMapping("/WebHookRecord")
public class WebHookRecordController {

    @Autowired
    private WebHookRecordService webHookRecordService;


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RespBody<WebHookRecord> save(PageContent page, @Valid @ModelAttribute WebHookRecord webHookRecord) {
        return new RespBody<>(webHookRecordService.createdWebHookRecord(webHookRecord));
    }


    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public RespBody<WebHookRecord> find(PageContent page, @Valid @ModelAttribute WebHookRecord webHookRecord) {
        return new RespBody<>(webHookRecordService.findOneById(Long.valueOf(webHookRecord.getId())));
    }


    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RespBody<WebHookRecord> delete(PageContent page, @Valid @ModelAttribute WebHookRecord webHookRecord) {
        webHookRecordService.deleteById(Long.valueOf(webHookRecord.getId()));
        return new RespBody<>();
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody<WebHookRecord> update(PageContent page, @Valid @ModelAttribute WebHookRecord webHookRecord) {
        webHookRecordService.updateWebHookRecord(webHookRecord);
        return new RespBody<>();
    }


}
