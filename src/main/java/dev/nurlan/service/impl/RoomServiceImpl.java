package dev.nurlan.service.impl;

import dev.nurlan.entity.Room;
import dev.nurlan.entity.RoomType;
import dev.nurlan.enums.EnumAvailableStatus;
import dev.nurlan.exception.ExceptionConstants;
import dev.nurlan.repository.RoomDao;
import dev.nurlan.repository.RoomTypeDao;
import dev.nurlan.request.ReqRoom;
import dev.nurlan.response.RespRoom;
import dev.nurlan.response.RespRoomType;
import dev.nurlan.response.RespStatus;
import dev.nurlan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private RoomTypeDao roomTypeDao;

    @Override
    public RespRoom getRoomById(Long roomId) {

        RespRoom response = new RespRoom();

        try {
            if (roomId == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                return response;
            }

            Room room = roomDao.findByIdAndActive(roomId, EnumAvailableStatus.ACTIVE.getValue());

            if (room == null) {
                response.setStatus(new RespStatus(ExceptionConstants.ROOM_NOT_FOUND, "Room not found"));
                return response;
            }

            RespRoomType respRoomType = new RespRoomType();
            respRoomType.setRoomTypeId(room.getRoomType().getId());
            respRoomType.setName(room.getRoomType().getName());
            respRoomType.setDescription(room.getRoomType().getDescription());

            response.setRoomId(roomId);
            response.setRoomNo(room.getRoomNo());
            response.setRespRoomType(respRoomType);
            response.setRoomStatus(room.getRoomStatus());
            response.setRoomFloor(room.getRoomFloor());
            response.setRoomPrice(room.getRoomPrice());
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespStatus createRoom(ReqRoom reqRoom) {
        RespStatus response = new RespStatus();

        try {
            Long roomTypeId = reqRoom.getRoomTypeId();
            String roomNo = reqRoom.getRoomNo();
            Integer roomFloor = reqRoom.getRoomFloor();
            Float roomPrice = reqRoom.getRoomPrice();

            if (roomTypeId == null || reqRoom.getRoomFloor() == null || (roomNo == null || roomNo.isEmpty())
                    || roomFloor == null || roomPrice == null) {
                response.setStatusCode(ExceptionConstants.INVALID_REQUEST_DATA);
                response.setStatusMessage("Invalid request data");
                return response;
            }

            RoomType roomType = roomTypeDao.findByIdAndActive(roomTypeId, EnumAvailableStatus.ACTIVE.getValue());
            if (roomType == null) {
                response.setStatusCode(ExceptionConstants.ROOM_TYPE_NOT_FOUND);
                response.setStatusMessage("Room type not found");
                return response;
            }

            Room room = new Room();
            room.setRoomNo(roomNo);
            room.setRoomType(roomType);
            room.setRoomFloor(roomFloor);
            room.setRoomPrice(roomPrice);
            roomDao.save(room);
            response.setStatusCode(RespStatus.getSuccessMessage().getStatusCode());
            response.setStatusMessage(RespStatus.getSuccessMessage().getStatusMessage());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(ExceptionConstants.INTERNAL_EXCEPTION);
            response.setStatusMessage("Internal exception");
            return response;
        }
        return response;
    }
}
