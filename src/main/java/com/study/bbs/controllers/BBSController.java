package com.study.bbs.controllers;

import com.study.bbs.Converter;
import com.study.bbs.services.BBSService;
import com.study.bbs.vos.BBSWriteVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "/bbs")
public class BBSController {
    private final BBSService bbsService;

    @Autowired
    public BBSController(BBSService bbsService) {
        this.bbsService = bbsService;
    }

    @RequestMapping(value = "write", method = RequestMethod.GET)
    public String writeGet(HttpServletRequest request, HttpServletResponse response) {
        return "bbs/write";
    }

    @RequestMapping(value = "write", method = RequestMethod.POST)
    public void writePost(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(name = "writer", defaultValue = "") String writer,
                          @RequestParam(name = "password", defaultValue = "") String password,
                          @RequestParam(name = "title", defaultValue = "") String title,
                          @RequestParam(name = "content", defaultValue = "") String content) throws
            SQLException {
        BBSWriteVo bbsWriteVo = new BBSWriteVo(writer, password, title, content);
        this.bbsService.write(bbsWriteVo);
    }

    @RequestMapping(
            value = "upload_image",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImage(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(name = "upload") MultipartFile image,
                            @RequestParam(name = "ckCsrfToken", defaultValue = "") String token) throws
            SQLException, IOException {
        String imageData = Converter.imageToString(image);
        int index = this.bbsService.uploadImage(imageData);
        JSONObject jsonResponse = new JSONObject();
        if (index > 0) {
            jsonResponse.put("uploaded", true);
            jsonResponse.put("url", String.format("/bbs/download_image?id=%d", index));
        } else {
            jsonResponse.put("uploaded", false);
        }
        response.getWriter().print(jsonResponse.toString());
    }

    @RequestMapping(
            value = "download_image",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] downloadImage(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(name = "id", defaultValue = "") String idText) throws
            SQLException, IOException {
        int index = Converter.stringToInt(idText, -1);
        if (index > 0) {
            return this.bbsService.downloadImage(index);
        } else {
            return null;
        }
    }
}