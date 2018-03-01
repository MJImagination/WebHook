package com.ancun.webhook.controller;

import com.ancun.webhook.base.PageContent;
import com.ancun.webhook.base.RespBody;
import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.service.WebHookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/5
 */
@Controller
@RequestMapping("/WebHook")
public class WebHookController {

    @Autowired
    private WebHookService webHookService;


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RespBody<WebHook> save(PageContent page, @Valid @ModelAttribute WebHook webHook) {
        if (webHook.getStatus() == null) {
            webHook.setStatus(1);
        }
        webHook.setCreateTime(new Date());
        return new RespBody<>(webHookService.createdWebHook(webHook));
    }


    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public RespBody<WebHook> find(PageContent page, @Valid @ModelAttribute WebHook webHook) {
        return new RespBody<>(webHookService.findOneById(Long.valueOf(webHook.getId())));
    }


    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RespBody<WebHook> delete(PageContent page, @Valid @ModelAttribute WebHook webHook) {
        webHookService.deleteById(Long.valueOf(webHook.getId()));
        return new RespBody<>();
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody<WebHook> update(PageContent page, @Valid @ModelAttribute WebHook webHook) {
        WebHook tempWebHook = webHookService.findOneById(webHook.getId());
        if (webHook.getStatus() != null) {
            tempWebHook.setStatus(webHook.getStatus());
        } else if (StringUtils.isNotBlank(webHook.getRemarks())) {
            tempWebHook.setRemarks(webHook.getRemarks());
        } else if (webHook.getPartnerId() != null) {
            tempWebHook.setPartnerId(webHook.getPartnerId());
        } else if (StringUtils.isNotEmpty(webHook.getCallBackUrl())) {
            tempWebHook.setCallBackUrl(webHook.getCallBackUrl());
        }
        webHookService.updateWebHook(tempWebHook);
        return new RespBody<>();
    }


}
