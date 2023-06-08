package crepe.backend.domain.feedback.service;

import crepe.backend.domain.branch.domain.entity.Branch;
import crepe.backend.domain.branch.domain.repository.BranchRepository;
import crepe.backend.domain.branch.exception.NotFoundBranchEntityException;
import crepe.backend.domain.feedback.domain.entity.Feedback;
import crepe.backend.domain.feedback.domain.entity.FeedbackStatus;
import crepe.backend.domain.feedback.domain.repository.FeedbackRepository;
import crepe.backend.domain.feedback.dto.FeedbackCreate;
import crepe.backend.domain.feedback.dto.FeedbackCreateInfo;
import crepe.backend.domain.feedback.dto.FeedbackStatusRequest;
import crepe.backend.domain.feedback.exception.NotFoundFeedbackEntityException;
import crepe.backend.domain.feedback.exception.NotFoundFeedbackStatusException;
import crepe.backend.domain.feedback.mapper.FeedbackMapper;
import crepe.backend.domain.project.exception.EventDuplicationUserException;
import crepe.backend.domain.user.domain.entity.User;
import crepe.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserService userService;
    private final BranchRepository branchRepository;
    private final FeedbackMapper feedbackMapper;

    public FeedbackCreateInfo createFeedback(FeedbackCreate createrequest)
    {
        User findUser = userService.findUserById(createrequest.getUserId());
        Branch findBranch = branchRepository.findBranchByIdAndIsActiveTrue(createrequest.getBranchId()).orElseThrow(NotFoundBranchEntityException:: new);
        Feedback feedback = feedbackRepository.save(feedbackMapper.getFeedback(createrequest, findUser, findBranch));

        return feedbackMapper.mapFeedbackToFeedbackCreateInfo(feedback);
    }

    public void deleteFeedback(UUID uuid)
    {
        Feedback feedback = feedbackRepository.findFeedbackByUuidAndIsActiveTrue(uuid).orElseThrow(NotFoundFeedbackEntityException::new);
        feedbackRepository.delete(feedback);
    }

    public void updateFeedbackStatus(UUID feedbackUuid, FeedbackStatusRequest feedbackStatus)
    {
        boolean status = false;

        for(FeedbackStatus fbStatus : FeedbackStatus.values())
        {
            if(fbStatus == FeedbackStatus.valueOf(feedbackStatus.getFeedbackStatus()))
            {
                status = true;
            }
        }

        if(status == true)
        {
            Feedback findfeedback = feedbackRepository.findFeedbackByUuidAndIsActiveTrue(feedbackUuid).orElseThrow(NotFoundFeedbackEntityException::new);

            findfeedback.updateStatus(FeedbackStatus.valueOf(feedbackStatus.getFeedbackStatus()));
            feedbackRepository.save(findfeedback);
        }
        else
        {
            throw new NotFoundFeedbackStatusException();
        }
    }
}
