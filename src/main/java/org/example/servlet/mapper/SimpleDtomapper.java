package org.example.servlet.mapper;

import org.example.model.User;
import org.example.servlet.dto.IncomingDto;
import org.example.servlet.dto.OutGoingDto;

public interface SimpleDtomapper {
    User map(IncomingDto incomingDto);

    OutGoingDto map(User user);
}
