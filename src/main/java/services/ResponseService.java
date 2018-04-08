package services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class ResponseService {

    private static final String SHELL_SCRIPT = "application/x-sh";

    public void prepareResponse(HttpServletResponse response, String fileName, Long length) {
        response.setContentType(SHELL_SCRIPT);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentLengthLong(length);
    }
}
