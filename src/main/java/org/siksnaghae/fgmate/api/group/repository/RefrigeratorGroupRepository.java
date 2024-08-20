package org.siksnaghae.fgmate.api.group.repository;

import org.siksnaghae.fgmate.api.group.model.RefrigeratorGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefrigeratorGroupRepository extends JpaRepository<RefrigeratorGroup,Long> {
    Boolean existsByRefrigeratorIdAndUserId(Long fridgeId, Long userId);
    Optional<RefrigeratorGroup> findByRefrigeratorIdAndUserId(Long fridgeId, Long userId);

    void deleteByUserIdAndRefrigeratorId(Long userId, Long fridgeId);

    List<RefrigeratorGroup> findByRefrigeratorId(Long fridgeId);
}
