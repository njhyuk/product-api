package com.njhyuk.codi.core.price.exception

import com.njhyuk.codi.config.exception.BusinessException
import com.njhyuk.codi.config.exception.ErrorCode

class NotExistsProductException : BusinessException(ErrorCode.NOT_EXISTS_PRODUCT)
