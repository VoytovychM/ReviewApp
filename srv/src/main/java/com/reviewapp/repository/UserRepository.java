package com.reviewapp.repository;

import com.sap.cds.ql.Update;
import com.sap.cds.services.persistence.PersistenceService;
import org.springframework.stereotype.Component;
import static cds.gen.db.Db_.USER;


@Component
public class UserRepository {
    private final PersistenceService persistenceService;

    public UserRepository(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public void updateAvrRating(String userId, double avrRating) {
       Update updateAvrRating = Update.entity(USER).data("averageRating", avrRating).byId(userId);
        persistenceService.run(updateAvrRating);
    }
}
