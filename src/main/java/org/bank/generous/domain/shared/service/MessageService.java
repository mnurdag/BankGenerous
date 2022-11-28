package org.bank.generous.domain.shared.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MessageService {

    private final Locale locale = new Locale("ar");

    MessageSource messageSource;

    public String getMessage(String key, Object... parameters) {
        return messageSource.getMessage(key, parameters, locale);
    }

}
