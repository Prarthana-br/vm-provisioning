package com.ripple.vmprovisioning.service.exceptions;

import com.ripple.vmprovisioning.exceptions.BadRequestException;

public class VMBadRequestException extends BadRequestException {

    public VMBadRequestException(String message) {
        super(message);

    }

}
