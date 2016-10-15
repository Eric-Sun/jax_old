package com.j13.bar.server.poppy.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.j13.bar.server.poppy.RequestData;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private static Logger LOG = LoggerFactory.getLogger(MainController.class);

    @Autowired
    ApiDispatcher dispatcher;


    @RequestMapping("/api")
    public String api(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String act = request.getParameter("act");

        RequestData requestData = parseRequest(request);

        LOG.info("request : {}", JSON.toJSONString(requestData.getData()));

        Object obj = dispatcher.dispatch(act, requestData);
        String json = JSON.toJSONString(obj);
        LOG.info("response : {}", json);
        response.getWriter().write(json);
        response.flushBuffer();
        return null;
    }

    private RequestData parseRequest(HttpServletRequest request) throws FileUploadException {
        RequestData requestData = new RequestData();
        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                if (!item.isFormField()) {
                    requestData.setFileItem(item);
                } else {
                    requestData.getData().put(item.getFieldName(), item.getString());
                }
            }
            return requestData;
        } else {
            Enumeration<String> enumKeys = request.getParameterNames();
            while (enumKeys.hasMoreElements()) {
                String key = enumKeys.nextElement();
                String value = request.getParameter(key);
                requestData.getData().put(key, value);
            }

            return requestData;
        }


    }


}
