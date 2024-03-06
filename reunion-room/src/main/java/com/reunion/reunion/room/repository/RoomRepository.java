package com.reunion.reunion.room.repository;

import com.reunion.reunion.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository  extends JpaRepository<Room, Long> {
}
