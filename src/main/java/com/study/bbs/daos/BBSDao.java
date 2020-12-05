package com.study.bbs.daos;

import com.study.bbs.vos.BBSWriteVo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BBSDao {
    public void insertArticle(Connection connection, BBSWriteVo bbsWriteVo) throws
            SQLException {
        String query = "" +
                "INSERT INTO `bbs`.`articles` (`article_writer`,\n" +
                "                              `article_password`,\n" +
                "                              `article_title`,\n" +
                "                              `article_content`)\n" +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bbsWriteVo.getWriter());
            preparedStatement.setString(2, bbsWriteVo.getPassword());
            preparedStatement.setString(3, bbsWriteVo.getTitle());
            preparedStatement.setString(4, bbsWriteVo.getContent());
            preparedStatement.execute();
        }
    }

    public void insertImage(Connection connection, String imageData) throws
            SQLException {
        String query = "" +
                "INSERT INTO `bbs`.`images` (`image_data`)\n" +
                "VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, imageData);
            preparedStatement.execute();
        }
    }

    public int selectLastIndex(Connection connection) throws
            SQLException {
        int index = -1;
        String query = "SELECT LAST_INSERT_ID() AS `index`";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    index = resultSet.getInt("index");
                }
            }
        }
        return index;
    }

    public String selectImage(Connection connection, int id) throws
            SQLException {
        String imageData = null;
        String query = "SELECT `image_data` AS `imageData` FROM `bbs`.`images` WHERE `image_index` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    imageData = resultSet.getString("imageData");
                }
            }
        }
        return imageData;
    }
}