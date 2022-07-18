package com.ripple.vmprovisioning.service.exceptions;

import com.ripple.vmprovisioning.exceptions.InternalServerException;

public class VMException extends InternalServerException {

    public VMException(String message) {
        super(message);

    }

}
