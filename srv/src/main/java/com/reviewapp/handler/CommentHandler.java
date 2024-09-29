package com.reviewapp.handler;

import cds.gen.db.Comment;
import cds.gen.srv.review.Comments;
import cds.gen.srv.review.Comments_;
import cds.gen.srv.review.Review_;
import com.reviewapp.repository.CommentRepository;
import com.reviewapp.repository.UserRepository;
import com.reviewapp.security.ReviewUserInfo;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ServiceName(Review_.CDS_NAME)
public class CommentHandler implements EventHandler {

    private final ReviewUserInfo reviewUserInfo;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    public CommentHandler(ReviewUserInfo reviewUserInfo, CommentRepository commentRepository, UserRepository userRepository) {
        this.reviewUserInfo = reviewUserInfo;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Before(entity = Comments_.CDS_NAME, event = CqnService.EVENT_DELETE)
    public void beforeDeleteComment(EventContext context, Comments comments) {
        if (!reviewUserInfo.getUser().getRole().equals("admin")) {
            throw new ServiceException(ErrorStatuses.FORBIDDEN, "You do not have permission to delete this comment");
        }
    }

    @After(entity = Comments_.CDS_NAME, event = CqnService.EVENT_CREATE)
    public void afterCreateComment(EventContext context, Comments comments) {
        String userId = comments.getRefferedToId();
        List<Comment> storedComments =  commentRepository.findCommentsByRefferedTo(userId);
        double averageRate = (double) (storedComments.stream().mapToInt(comment -> comment.getEvaluation()).sum()/storedComments.size());
        userRepository.updateAvrRating(userId, averageRate);

    }
}