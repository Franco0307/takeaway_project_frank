package com.ellaskitchen.exception;

import com.ellaskitchen.context.BaseContext;

public class DeleteNotAllowedException extends BaseException {
    public DeleteNotAllowedException(String message) {
        super(message);
    }
}
