package com.deark.be.alarm.repository;

import com.deark.be.alarm.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmRepositoryCustom {

    Boolean existsByUserIdAndIsDeletedFalseAndIsReadFalse(Long userId);
}
