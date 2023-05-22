package crepe.backend.domain.feedback.mapper;

import crepe.backend.domain.branch.domain.entity.Branch;
import crepe.backend.domain.feedback.domain.entity.Feedback;
import crepe.backend.domain.feedback.dto.FeedbackCreate;
import crepe.backend.domain.feedback.dto.FeedbackCreateInfo;
import crepe.backend.domain.user.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class FeedbackMapper {

    public Feedback getFeedback(FeedbackCreate createrequest, User user, Branch branch)
    {
        return Feedback.builder()
                .user(user)
                .branch(branch)
                .message(createrequest.getMessage())
                .build();
    }

    public FeedbackCreateInfo mapFeedbackToFeedbackCreateInfo(Feedback feedback)
    {
        return FeedbackCreateInfo.builder()
                .feedbackUuid(feedback.getUuid())
                .build();
    }
}
