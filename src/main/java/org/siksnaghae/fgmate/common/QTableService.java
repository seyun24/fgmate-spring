package org.siksnaghae.fgmate.common;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.siksnaghae.fgmate.api.fridge.model.QRefrigerator;
import org.siksnaghae.fgmate.api.group.model.QRefrigeratorGroup;
import org.siksnaghae.fgmate.api.user.model.user.QUser;
import org.springframework.beans.factory.annotation.Autowired;

public class QTableService {
    @Autowired
    protected JPAQueryFactory queryFactory;

    protected QUser qUser = QUser.user;
    protected QRefrigerator qRefri = QRefrigerator.refrigerator;
    protected QRefrigeratorGroup qGroup =QRefrigeratorGroup.refrigeratorGroup;

    public QTableService() {
        super();
    }
}
