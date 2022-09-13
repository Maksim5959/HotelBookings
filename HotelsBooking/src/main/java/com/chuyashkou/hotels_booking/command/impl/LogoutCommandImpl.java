package com.chuyashkou.hotels_booking.command.impl;

import com.chuyashkou.hotels_booking.command.Command;
import com.chuyashkou.hotels_booking.command.CommandResult;
import com.chuyashkou.hotels_booking.command.NavigationType;
import com.chuyashkou.hotels_booking.exception.CommandException;
import com.chuyashkou.hotels_booking.util.PageManager;
import com.chuyashkou.hotels_booking.util.PageMappingConstant;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommandImpl implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req) throws CommandException {
        req.getSession().invalidate();
        return new CommandResult(req.getContextPath() + PageManager.getPageURI(PageMappingConstant.LOGIN_PAGE.getKey()), NavigationType.REDIRECT);
    }
}
