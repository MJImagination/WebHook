package com.ancun.webhook.controller;

import com.ancun.webhook.base.JPATimeVO;
import com.ancun.webhook.base.PageContent;
import com.ancun.webhook.base.RespBody;
import com.ancun.webhook.base.RespBodyLayUi;
import com.ancun.webhook.model.PublicityData;
import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.repository.PublicityDataRepository;
import com.ancun.webhook.service.PublicityDataService;
import com.ancun.webhook.service.impl.WebHookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/5
 */
@Controller
@RequestMapping("/publicityData")
public class PublicityDataController {

    @Autowired
    private PublicityDataService publicityDataService;

    @Autowired
    private PublicityDataRepository publicityDataRepository;


    @Autowired
    private WebHookServiceImpl webHookService;

    //最新备案
    @ResponseBody
    @RequestMapping(value = "/latestRecord", method = RequestMethod.POST)
    public RespBody<Page<PublicityData>> latestRecord(PageContent page
                                                      ,@Valid @ModelAttribute PublicityData publicityData
                                                      ,@Valid JPATimeVO jpaTimeVO) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getRows());
        LinkedHashMap<String, String> orders = new LinkedHashMap<>();
        orders.put("recoredTime","desc");
        Page<PublicityData> tdApplies = publicityDataService.findPageList(pageable, publicityData,jpaTimeVO,orders);
        return new RespBody<>(tdApplies);
    }

    //分类公示
    @ResponseBody
    @RequestMapping(value = "/publicityClass", method = RequestMethod.POST)
    public RespBodyLayUi<Page<PublicityData>> publicityClass(PageContent page
                                                        , @Valid @ModelAttribute PublicityData publicityData
                                                        , @Valid JPATimeVO jpaTimeVO) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getRows());
        Page<PublicityData> tdApplies = publicityDataService.findPageList(pageable, publicityData,jpaTimeVO,null);
        List<PublicityData> publicityDataList = tdApplies.getContent();
        long totalElements = tdApplies.getTotalElements();
        return new RespBodyLayUi<>(totalElements,tdApplies.getContent());
    }

    //根据保全号查询
    @ResponseBody
    @RequestMapping(value = "/findByRecoredNo", method = RequestMethod.POST)
    public RespBody<PublicityData> findByRecoredNo(PageContent page,String recoredNo) {
        PublicityData publicityData =publicityDataRepository.findByrecoredNo(recoredNo);
        return new RespBody<>(publicityData);
    }


    // 查询验证
    @ResponseBody
    @RequestMapping(value = "/validationQuery", method = RequestMethod.POST)
    public RespBody<Page<PublicityData>> validationQuery(PageContent page
                                                        , @Valid @ModelAttribute PublicityData publicityData
                                                        , @Valid JPATimeVO jpaTimeVO) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getRows());
        Page<PublicityData> tdApplies = publicityDataService.findPageList(pageable, publicityData,jpaTimeVO,null);
        return new RespBody<>(tdApplies);
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RespBody<WebHook> save(PageContent page, @Valid @ModelAttribute WebHook webHook) {
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
        webHookService.updateWebHook(webHook);
        return new RespBody<>();
    }




}
