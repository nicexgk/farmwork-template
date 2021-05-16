package com.huawei.cloud.nice.exception.handle;

import com.huawei.cloud.dto.ResponseDTO;
import com.huawei.cloud.dto.ResultDTO;
import com.huawei.cloud.exception.BusinessException;
import com.huawei.cloud.exception.SystemException;
import com.huawei.cloud.i18n.MessageSourceService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    @Resource
    private MessageSourceService messageSourceService;

    /**
     * This method is used for handling BusinessException
     *
     * @param ex
     * @param request
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, final WebRequest request) {
        String uri = (((ServletWebRequest) request).getRequest().getRequestURI());
        ResponseDTO<?> responseDto = new ResponseDTO<>();
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(ex.getCode());
        if (StringUtils.isEmpty(ex.getMessage()) || ex.getMessage().equals(ex.getCode())) {
            resultDTO.setMessage(messageSourceService.getMessage(ex.getCode()));
        } else {
            resultDTO.setMessage(ex.getMessage());
        }
        responseDto.setResult(resultDTO);
        return new ResponseEntity<Object>(responseDto, HttpStatus.OK);
    }

    /**
     * This method is used for handling SystemException
     *
     * @param ex
     * @param request
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<Object> handleSystemException(SystemException ex, final HttpServletRequest request) {
        final ResponseDTO<?> responseDto = new ResponseDTO<>();
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(ex.getCode());
        if (StringUtils.isEmpty(ex.getMessage()) || ex.getMessage().equals(ex.getCode())) {
            resultDTO.setMessage(messageSourceService.getMessage(ex.getCode()));
        } else {
            resultDTO.setMessage(ex.getMessage());
        }
        LOGGER.error(ex.getMessage(), ex);
        responseDto.setResult(resultDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is used for handling common Exception
     *
     * @param ex
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> exceptionHandler(Exception ex, final WebRequest request) {
        final ResponseDTO<?> responseDto = new ResponseDTO<>();
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode("-10000");
        resultDTO.setMessage(ex.getMessage());
        LOGGER.error(ex.getMessage(), ex);
        responseDto.setResult(resultDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method is used for handling invalid method argument
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers
            , HttpStatus status, WebRequest request) {
        String uri = (((ServletWebRequest) request).getRequest().getRequestURI());
        final ResponseDTO<?> responseDto = new ResponseDTO<>();
        ResultDTO resultDTO = new ResultDTO();
        responseDto.setResult(resultDTO);
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        resultDTO.setMessage(objectError.getDefaultMessage());
        resultDTO.setCode("400");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
