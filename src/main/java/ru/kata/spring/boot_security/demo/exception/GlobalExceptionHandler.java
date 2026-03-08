package ru.kata.spring.boot_security.demo.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kata.spring.boot_security.demo.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private final RoleService roleService;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handleValidationException(MethodArgumentNotValidException ex,
	                                        HttpServletRequest request,
	                                        Model model) {

		log.info("Ошибка валидации для запроса: {}", request.getRequestURI());

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		model.addAttribute("errors", errors);
		model.addAttribute("user", ex.getBindingResult().getTarget());
		model.addAttribute("allRoles", roleService.getAllRoles());

		String requestUri = request.getRequestURI();
		if (requestUri.contains("admin")) {
			return "user-form";
		} else {
			return "registration-form";
		}
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception ex, Model model) {
		log.error("Внутренняя ошибка сервера: {}", ex.getMessage(), ex);

		String errorMessage = "Произошла внутренняя ошибка сервера";
		if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
			errorMessage = ex.getMessage();
		}

		model.addAttribute("errorMessage", errorMessage);
		return "error";
	}
}