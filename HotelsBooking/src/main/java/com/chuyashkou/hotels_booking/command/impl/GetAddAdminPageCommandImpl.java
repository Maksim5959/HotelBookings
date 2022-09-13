package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;

public class GetAddAdminPageCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        return new CommandResult(PageManager.getPageURI(PageMappingConstant.ADD_ADMIN_PAGE.getKey()),
                NavigationType.FORWARD);
    }
}
