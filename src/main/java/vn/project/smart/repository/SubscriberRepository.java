package vn.project.smart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.project.smart.domain.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long>,
                JpaSpecificationExecutor<Subscriber> {

        boolean existsByEmail(String email);

        Subscriber findByEmail(String email);
}