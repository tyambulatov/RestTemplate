package org.example.servlet.mapper;

import org.example.model.User;
import org.example.servlet.dto.IncomingDto;
import org.example.servlet.dto.OutGoingDto;

public class SimpleDtomapperImpl implements SimpleDtomapper {
    @Override
    public User map(IncomingDto incomingDto) {
        return null;
    }

    @Override
    public OutGoingDto map(User user) {
        return null;
    }
}
