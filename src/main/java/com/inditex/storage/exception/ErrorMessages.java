package com.inditex.storage.exception;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    ENTITY_NOT_FOUND("entity.notfound", "Entity %s with id %s does not exists"),
    JOURNEY_ID_NOT_NULL("journey.id.notNull", "Journey id should not be null"),
    JOURNEY_DUPLICATED_ID("journey.id.duplicated", "Journey id already exists"),
    JOURNEY_IS_NULL("journey.entity.notNull", "Maybe happened an error during conversion from Dto"),
    JOURNEY_NOT_FOUND("journey.notFound", "Could not find the journey"),
    SAVE_ALL_ERROR("saveAll.error", "Error occurred saving cars to database"),
    CAR_SEATS_INCORRECT("car.seat.incorrectNumber", "The seats allowed for cars are between 4 and 6, please check request"),
    CAR_NOT_FOUND_FOR_JOURNEY("car.journey.notFound", "The car for journey could not be found"),
    PASSENGERS_LIMIT_EXCEEDED("passengers.limit.exceeded", "The maximum passengers per car is 6"),
    PASSENGERS_NOT_INFORMED("passengers.notNull", "The passenger cant be null or zero"),
    CAR_FIND_ERROR("select.car.error", "An error occurred finding a car for this journey %s");

    private final String errorCode;
    private final String errorMessage;

    ErrorMessages(final String errorCode, final String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
