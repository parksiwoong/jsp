package com.study.bbs.services;

import com.study.bbs.daos.BBSDao;
import com.study.bbs.vos.BBSWriteVo;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class BBSService {
    private final DataSource dataSource;
    private final BBSDao bbsDao;

    @Autowired
    public BBSService(DataSource dataSource, BBSDao bbsDao) {
        this.dataSource = dataSource;
        this.bbsDao = bbsDao;
    }

    public void write(BBSWriteVo bbsWriteVo) throws
            SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            this.bbsDao.insertArticle(connection, bbsWriteVo);
        }
    }

    public int uploadImage(String imageData) throws
            SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            this.bbsDao.insertImage(connection, imageData);
            return this.bbsDao.selectLastIndex(connection);
        }
    }

    public byte[] downloadImage(int id) throws
            SQLException, IOException {
        try (Connection connection = this.dataSource.getConnection()) {
            String imageData = this.bbsDao.selectImage(connection, id).split(",")[1];
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(imageData);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}