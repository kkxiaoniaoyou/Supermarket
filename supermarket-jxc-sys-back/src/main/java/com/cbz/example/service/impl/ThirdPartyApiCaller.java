package com.cbz.example.service.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.cbz.example.entity.ThirdPartyLog;
import com.cbz.example.mapper.ThirdPartyLogMapper;
import com.cbz.example.service.ThirdPartyLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ThirdPartyApiCaller {

    private final ThirdPartyLogService thirdPartyLogService;

    /**
     * 发送
     *
     * @param url
     * @param params
     * @param requestKeyword
     * @return
     */
    public HttpResponse sendGetRequest(String url, String params, String requestKeyword) {
        long startTime = System.currentTimeMillis();

        try {
            // 发送 GET 请求
            String getUrl = params.isEmpty() ? url : url + "?" + params;
            HttpResponse response = HttpUtil.createGet(getUrl).execute();

            // 记录请求日志
            recordApiLog("GET", url, params, response.body(), startTime, requestKeyword, response.getStatus());

            return response;
        } catch (Exception e) {
            // 异常时记录错误日志
            recordErrorLog("GET", url, params, startTime, requestKeyword, e);
            throw e;
        }
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param postData
     * @param requestKeyword
     * @return
     */
    public HttpResponse sendPostRequest(String url, String postData, String requestKeyword) {
        long startTime = System.currentTimeMillis();

        try {
            // 发送 POST 请求
            HttpResponse response = HttpUtil.createPost(url).body(postData).execute();

            // 记录请求日志
            recordApiLog("POST", url, postData, response.body(), startTime, requestKeyword, response.getStatus());

            return response;
        } catch (Exception e) {
            // 异常时记录错误日志
            recordErrorLog("POST", url, postData, startTime, requestKeyword, e);
            throw e;
        }
    }

    private void recordApiLog(String requestType, String requestUrl, String requestParams, String responseResult,
                              long startTime, String requestKeyword, int responseStatus) {
        long endTime = System.currentTimeMillis();
        int requestDuration = (int) (endTime - startTime);
        ThirdPartyLog apiLog = new ThirdPartyLog();
        apiLog.setRequestType(requestType);
        apiLog.setRequestUrl(requestUrl);
        apiLog.setRequestParams(requestParams);
        apiLog.setResponseResult(responseResult);
        apiLog.setRequestTime(new Date());
        apiLog.setResponseTime(new Date());
        apiLog.setRequestDuration(requestDuration);
        apiLog.setResponseStatus(responseStatus);
        apiLog.setRequestKeyword(requestKeyword);
        apiLog.setIsSuccess(responseStatus >= 200 && responseStatus < 300 ? 1 : 0);
        thirdPartyLogService.save(apiLog);
    }

    private void recordErrorLog(String requestType, String requestUrl, String requestParams, long startTime,
                                String requestKeyword, Exception exception) {
        long endTime = System.currentTimeMillis();
        int requestDuration = (int) (endTime - startTime);

        ThirdPartyLog errorLog = new ThirdPartyLog();
        errorLog.setRequestType(requestType);
        errorLog.setRequestUrl(requestUrl);
        errorLog.setRequestParams(requestParams);
        errorLog.setRequestTime(new Date());
        errorLog.setRequestDuration(requestDuration);
        errorLog.setRequestKeyword(requestKeyword);
        errorLog.setIsSuccess(0);
        errorLog.setExceptionMessage(exception.getMessage());
        thirdPartyLogService.save(errorLog);
    }
}

