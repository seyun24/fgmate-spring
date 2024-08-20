package org.siksnaghae.fgmate.api.group.service;

import lombok.RequiredArgsConstructor;
import org.siksnaghae.fgmate.api.group.model.RefrigeratorGroup;
import org.siksnaghae.fgmate.api.group.repository.RefrigeratorGroupRepository;
import org.siksnaghae.fgmate.common.response.BaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.siksnaghae.fgmate.common.response.BaseResponseStatus.DATABASE_ERROR;
import static org.siksnaghae.fgmate.common.response.BaseResponseStatus.DUPLICATED_GROUP_USER;

@Service
@RequiredArgsConstructor
public class RefrigeratorGroupService {
    private final RefrigeratorGroupRepository refrigeratorGroupRepository;

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public void inviteGroup(Long id, Long userId) throws BaseException {
        if (refrigeratorGroupRepository.existsByRefrigeratorIdAndUserId(id,userId)) {
            throw new BaseException(DUPLICATED_GROUP_USER);
        }
        try {
            RefrigeratorGroup refrigeratorGroup=RefrigeratorGroup.builder()
                    .userId(userId)
                    .refrigeratorId(id)
                    .build();
            refrigeratorGroupRepository.save(refrigeratorGroup);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public void deleteGroupUser(Long fridgeId, Long userId) throws BaseException {
        try {
            refrigeratorGroupRepository.deleteByUserIdAndRefrigeratorId(userId, fridgeId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public List<Long> findGroupUserIds(Long fridgeId) throws BaseException {
        try {
            List<Long> userIds = refrigeratorGroupRepository.findByRefrigeratorId(fridgeId).stream()
                    .map(RefrigeratorGroup::getUserId)
                    .collect(Collectors.toList());
            return userIds;

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
