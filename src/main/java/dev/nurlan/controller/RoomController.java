package dev.nurlan.controller;


import dev.nurlan.request.ReqRoom;
import dev.nurlan.response.RespRoom;
import dev.nurlan.response.RespStatus;
import dev.nurlan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/getRoomById/{roomId}", method = {RequestMethod.GET, RequestMethod.POST})
    public RespRoom getRoomById(@PathVariable("roomId") Long roomId) {
        return roomService.getRoomById(roomId);
    }

    @PostMapping(value = "/createRoom")
    public RespStatus createRoom(@RequestBody ReqRoom reqRoom) {
        return roomService.createRoom(reqRoom);
    }

    @PostMapping(value = "/deleteRoom/{roomId}")
    public RespStatus deleteRoom(@PathVariable("roomId") Long roomId) {
        return roomService.deleteRoom(roomId);
    }

}
