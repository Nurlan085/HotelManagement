package dev.nurlan.service;

import dev.nurlan.request.ReqRoom;
import dev.nurlan.response.RespRoom;
import dev.nurlan.response.RespStatus;

public interface RoomService {

    RespRoom getRoomById(Long roomId);

    RespStatus createRoom(ReqRoom reqRoom);
}
