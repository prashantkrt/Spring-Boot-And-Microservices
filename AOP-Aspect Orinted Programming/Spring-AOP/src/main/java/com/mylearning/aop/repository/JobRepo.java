package com.mylearning.aop.repository;

import com.mylearning.aop.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepo extends JpaRepository<JobPost,Integer> {
    List<JobPost> findByPostProfileContainingOrPostDescContaining(String profile, String desc);
}
