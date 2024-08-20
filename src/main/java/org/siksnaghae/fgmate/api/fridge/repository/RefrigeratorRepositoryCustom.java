package org.siksnaghae.fgmate.api.fridge.repository;


import org.siksnaghae.fgmate.api.fridge.model.RefrigeratorDto;

import java.util.List;

public interface RefrigeratorRepositoryCustom {
    List<RefrigeratorDto> findRefrigeratorAll(Long userId);
}
