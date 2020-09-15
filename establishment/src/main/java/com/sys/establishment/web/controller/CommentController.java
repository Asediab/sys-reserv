package com.sys.establishment.web.controller;

import com.sys.establishment.dto.CommentDTO;
import com.sys.establishment.service.CommentService;
import com.sys.establishment.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apiEst/comment")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping("{id}")
    public CommentDTO getOne(@PathVariable("id") Long id){
        return service.detOne(id);
    }

    @PostMapping
    public ResponseEntity<Void> saveComment(@RequestBody CommentDTO commentDTO){
        CommentDTO comment = service.save(commentDTO);
        if (comment != null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyComment(@RequestBody CommentDTO commentDTO){
        CommentDTO comment = service.save(commentDTO);
        if (comment != null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteComment(@RequestBody CommentDTO commentDTO){
        try {
            service.delete(commentDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
