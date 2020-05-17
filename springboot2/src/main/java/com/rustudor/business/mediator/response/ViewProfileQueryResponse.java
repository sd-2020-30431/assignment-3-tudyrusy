package com.rustudor.business.mediator.response;

import com.rustudor.Dto.UserDto;
import com.rustudor.business.mediator.Response;

public class ViewProfileQueryResponse implements Response {
    private UserDto userDto;

    public ViewProfileQueryResponse(UserDto userDto) {
        this.userDto = userDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
