package com.reviewapp.repository;

import cds.gen.db.Comment;
import static cds.gen.db.Db_.COMMENT;

import com.sap.cds.ql.CQL;
import com.sap.cds.ql.Select;
import com.sap.cds.services.persistence.PersistenceService;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CommentRepository {

    private PersistenceService persistenceService;

    public CommentRepository(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public List<Comment> findCommentsByRefferedTo(String refferedTo) {
        Select query = Select.from(COMMENT).columns(CQL.star()).where(c-> c.refferedTo_ID().eq(refferedTo));
       return persistenceService.run(query).listOf(Comment.class);

    }
}
