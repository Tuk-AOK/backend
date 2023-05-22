package crepe.backend.domain.feedback.service;

import crepe.backend.domain.branch.domain.entity.Branch;
import crepe.backend.domain.branch.domain.repository.BranchRepository;
import crepe.backend.domain.branch.exception.NotFoundBranchEntityException;
import crepe.backend.domain.feedback.domain.entity.Feedback;
import crepe.backend.domain.feedback.domain.repository.FeedbackRepository;
import crepe.backend.domain.feedback.dto.FeedbackCreate;
import crepe.backend.domain.feedback.dto.FeedbackCreateInfo;
import crepe.backend.domain.feedback.exception.NotFoundFeedbackEntityException;
import crepe.backend.domain.user.domain.entity.User;
import crepe.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserService userService;
    private final BranchRepository branchRepository;

    public FeedbackCreateInfo createFeedback(FeedbackCreate createrequest)
    {
        User findUser = userService.findUserById(createrequest.getUserId());
        Branch findBranch = branchRepository.findBranchByIdAndIsActiveTrue(createrequest.getBranchId()).orElseThrow(NotFoundBranchEntityException:: new);
        Feedback feedback = feedbackRepository.save(getFeedback(createrequest, findUser, findBranch));

        return mapFeedbackToFeedbackCreateInfo(feedback);
    }

    public void deleteFeedback(UUID uuid)
    {
        Feedback feedback = feedbackRepository.findFeedbackByUuidAndIsActiveTrue(uuid).orElseThrow(NotFoundFeedbackEntityException::new);
        feedbackRepository.delete(feedback);
    }

    private Feedback getFeedback(FeedbackCreate createrequest, User user, Branch branch)
    {
        return Feedback.builder()
                .user(user)
                .branch(branch)
                .message(createrequest.getMessage())
                .build();
    }

    private FeedbackCreateInfo mapFeedbackToFeedbackCreateInfo(Feedback feedback)
    {
        return FeedbackCreateInfo.builder()
                .feedbackUuid(feedback.getUuid())
                .build();
    }
}
