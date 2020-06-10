package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.TicketSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketSubmissionRepository extends JpaRepository<TicketSubmission, Integer> {
}
