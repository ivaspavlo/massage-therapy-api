package pavlo.pro.massagetherapyapi.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.config.PropertiesConfig;

import java.text.MessageFormat;
import java.util.Optional;

@Component
public class AppException {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    public AppException(PropertiesConfig propertiesConfig) {
        AppException.propertiesConfig = propertiesConfig;
    }

    public static RuntimeException buildException(EntityType entityType, ExceptionType exceptionType, String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType);
        if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return new EntityNotFoundException(format(messageTemplate, args));
        } else if (ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
            return new DuplicateEntityException(format(messageTemplate, args));
        } else if (ExceptionType.ENTITY_EXCEPTION.equals(exceptionType)) {
            return new EntityException(format(messageTemplate, args));
        } else if (ExceptionType.DATABASE_EXCEPTION.equals(exceptionType)) {
            return new DatabaseException(format(messageTemplate, args));
        }
        return new RuntimeException(format(messageTemplate, args));
    }

    private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
    }

    private static String format(String template, String... args) {
        Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
        if (templateContent.isPresent()) {
            return MessageFormat.format(templateContent.get(), (Object[]) args);
        }
        return String.format(template, (Object[]) args);
    }

    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super(message);
        }
    }

    public static class DatabaseException extends RuntimeException {
        public DatabaseException(String message) {
            super(message);
        }
    }

    public static class EntityException extends RuntimeException {
        public EntityException(String message) {
            super(message);
        }
    }
}
