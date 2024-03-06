package com.reunion.reunion.room.controller;

import com.reunion.reunion.room.exception.ResourceNotFoundException;
import com.reunion.reunion.room.model.Room;
import com.reunion.reunion.room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController @CrossOrigin(origins = "https://localhost:4200")
@RequestMapping("api/v1")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
     return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value ="id") long roomId)
        throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room){
        return roomRepository.save(room);
    }
    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value ="id") long roomId, @Valid @RequestBody Room roomDetails)
            throws ResourceNotFoundException {
            Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));
            room.setName(roomDetails.getName());
            room.setDate(roomDetails.getDate());
            room.setEndHour(roomDetails.getEndHour());
            room.setStartHour(roomDetails.getStartHour());

            final Room updateRoom = roomRepository.save(room);
            return ResponseEntity.ok(updateRoom);
    }


    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId)
        throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));

        roomRepository.delete(room);
        Map<String , Boolean> response  = new HashMap<>();
        response.put ("deleted", Boolean.TRUE);
        return response;
    }


}
