package com.easc01.disastermediaapi.repository;

import com.easc01.disastermediaapi.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
