package com.sys.establishment.dao;

import com.sys.establishment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentDAO extends JpaRepository<Comment, Long>{

}
