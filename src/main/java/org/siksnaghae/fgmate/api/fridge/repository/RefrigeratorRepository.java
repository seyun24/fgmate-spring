package org.siksnaghae.fgmate.api.fridge.repository;

import org.siksnaghae.fgmate.api.fridge.model.Refrigerator;
import org.siksnaghae.fgmate.api.fridge.model.RefrigeratorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public
interface RefrigeratorRepository extends JpaRepository<Refrigerator,Long>, RefrigeratorRepositoryCustom{
    Refrigerator findByRefrigeratorId(Long fridgeId);
    void deleteByRefrigeratorId(Long fridgeId);

    Boolean existsByOwnerIdAndRefrigeratorId(Long ownerId, Long fridgeId);
//    @Query("select new org.siksnaghae.fgmate.api.fridge.model.RefrigeratorDto(R.refrigeratorName, R.refrigeratorId) " +
//            "from Refrigerator R join RefrigeratorGroup G where G.userId =:userId")
//    List<RefrigeratorDto> findRefrigeratorByUserId(@Param("userId") Long userId);


}
