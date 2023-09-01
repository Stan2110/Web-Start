package com.smartcode.web.repository.comment.impl;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryimpl implements CommentRepository {

    private Connection connection = DataSource.getInstance().getConnection();

    public CommentRepositoryimpl() {
        try {
            connection.createStatement().execute(
                    """
                            CREATE TABLE IF NOT EXISTS users (
                                id serial primary key ,
                                title varchar(255) not null ,
                                description varchar(255) not null ,
                            );
                            """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Comment comment) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (title,description) values(?,?)");
            preparedStatement.setString(1, comment.getTitle());
            preparedStatement.setString(2, comment.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Comment update(Integer commentId, Comment comment) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    """
                            UPDATE users SET
                            title = ?,
                            description = ?,
                            WHERE id = ?
                                """);

            preparedStatement.setString(1, comment.getTitle());
            preparedStatement.setString(2, comment.getDescription());
            preparedStatement.setInt(3, comment.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comment;
    }

    @Override
    public List<Comment> getAll(Integer userId) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "get * Coments Where userid="+"userid"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List list=new ArrayList<>();
        //list.add();
        return null;
    }

    @Override
    public Comment getById(Integer commentId) {
        Comment comment = new Comment();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * from comment WHERE id = ?");
            preparedStatement.setInt(1, commentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return fromResultSet(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comment;
    }

    @Override
    public void delete(Integer commentId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM comment WHERE id = ?");
            preparedStatement.setInt(1, commentId);
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteall(){
        try {
            Statement statement = connection.createStatement ( );
            statement.execute("drop table public.comment");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private Comment fromResultSet(ResultSet resultSet) throws SQLException {
        Comment comment=new Comment();
        comment.setId(resultSet.getInt("id"));
        comment.setTitle(resultSet.getString("title"));
        comment.setDescription(resultSet.getString("description"));
        return comment;
    }
}
