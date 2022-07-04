package com.ripple.vmprovisioning.service.exceptions;

import com.ripple.vmprovisioning.exceptions.BadRequestException;

public class DuplicateUserException extends BadRequestException {

    public DuplicateUserException(String message) {
        super(message);

    }

}
