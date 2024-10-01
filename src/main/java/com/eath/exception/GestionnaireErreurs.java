package com.eath.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GestionnaireErreurs {

    private static final Logger logger = LoggerFactory.getLogger(GestionnaireErreurs.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> gererErreur(Exception ex) {
        // Log l'exception
        logger.error("Exception détectée: ", ex);

        // Crée une réponse d'erreur
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Une erreur est survenue : " + ex.getMessage()
        );

        // Retourne la réponse d'erreur avec le statut HTTP 500
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class ErrorResponse {
        private int statusCode;
        private String status;
        private String message;

        // Constructeur
        public ErrorResponse(int statusCode, String status, String message) {
            this.statusCode = statusCode;
            this.status = status;
            this.message = message;
        }

        // Getters et Setters
        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
